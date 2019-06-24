/*
 * Copyright (c) 2019-2029 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from 
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf.api;

import java.time.Duration;
import java.time.OffsetDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.karumien.cloud.perf.api.handler.PerfApi;
import com.karumien.cloud.perf.api.model.PerfWorkDTO;
import com.karumien.cloud.perf.service.PerformanceDataService;

/**
 * REST Controller for Performance Service (API).
 *
 * @author <a href="miroslav.svoboda@karumien.com">Miroslav Svoboda</a>
 * @since 1.0, 15. 6. 2019 17:57:22
 */
@RestController
public class PerformanceController implements PerfApi {

    @Autowired
    private PerformanceDataService performanceDataService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<PerfWorkDTO> getWork(Integer id, @Valid Integer category, @Valid Boolean nowork, @Valid Integer delay) {

        PerfWorkDTO perfWork = new PerfWorkDTO();
        perfWork.setId(id);
        perfWork.setCategory(category != null && category >= 0 ? category % 10 : id % 10);

        perfWork.setStart(OffsetDateTime.now());

        if (Boolean.TRUE.equals(nowork)) {
            delay = delay != null && delay > 0 ? delay : 100;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            perfWork.setResult("nowork-" + delay);
        } else {
            perfWork.setResult(performanceDataService.getPerformanceData(perfWork.getCategory()));
        }

        perfWork.setEnd(OffsetDateTime.now());

        perfWork.setLength(Duration.between(perfWork.getStart(), perfWork.getEnd()).toMillis());
        return new ResponseEntity<>(perfWork, HttpStatus.OK);
    }
    
}
