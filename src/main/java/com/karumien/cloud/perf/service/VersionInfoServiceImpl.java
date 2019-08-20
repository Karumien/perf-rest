/*
 * Copyright (c) 2019-2029 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from 
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.jar.Manifest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Service provides version informations.
 *
 * @author <a href="miroslav.svoboda@karumien.com">Miroslav Svoboda</a>
 * @since 1.0, 20. 8. 2019 8:08:15 
 */
@Slf4j
@Service
public class VersionInfoServiceImpl implements VersionInfoService {

    @Getter
    protected String versionMF;
    
    @Getter
    protected LocalDateTime buildTimestamp;

    @Value("${manifest.basename:/META-INF/MANIFEST.MF}")
    protected void setManifestBasename(Resource resource) {

        if (!resource.exists()) {
            return;
        }

        try (final InputStream stream = resource.getInputStream()) {
            final Manifest manifest = new Manifest(stream);
            buildTimestamp = transformDate(manifest.getMainAttributes().getValue("Build-Date"));
            versionMF = transformVersion(manifest.getMainAttributes().getValue("Implementation-Version"));
        } catch (IOException e) {
            log.warn("Manifest file problem", e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return getBuildTimestamp() != null
                ? String.format("%s (%s)", getVersion(), formatter.format(
                        getBuildTimestamp().atZone(ZoneOffset.UTC).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()))
                : "[no-MF]";
    }
    
    private LocalDateTime transformDate(String value) {
        if (value == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(value, formatter);
    }
    
    private String transformVersion(String version) {
        versionMF = version;
        return versionMF;
    }

}
