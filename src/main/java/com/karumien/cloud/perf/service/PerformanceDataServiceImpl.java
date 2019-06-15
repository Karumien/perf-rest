/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from 
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karumien.cloud.perf.model.PerformanceData;
import com.karumien.cloud.perf.repository.PerformanceDataRepository;

/**
 * Implementation {@link PerformanceDataService} for managing {@link PerformanceData} entity.
 *
 * @author <a href="viliam.litavec@karumien.com">Viliam Litavec</a>
 * @since 1.0, 10. 6. 2019 22:07:27 
 */
@Service
public class PerformanceDataServiceImpl implements PerformanceDataService {

    @Autowired
    private PerformanceDataRepository perfDataRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public String getPerformanceData(int algorithm) {
    	// TODO: viliam.litavec - specific DB work for each algorithm 0-9
    	return "" + perfDataRepository.findAll().size();
    }
    
}