/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf.service;

/**
 * Service provides version informations.
 *
 * @author <a href="viliam.litavec@karumien.com">Viliam Litavec</a>
 * @since 1.0, 10. 6. 2019 22:07:27
 */
public interface VersionInfoService {

    /**
     * Returns simple version info informations.
     * 
     * @return String version information   
     */
    String getVersion();

}
