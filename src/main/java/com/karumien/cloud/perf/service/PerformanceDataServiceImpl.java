/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from 
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf.service;

import java.math.BigInteger;

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
    private PerformanceDataRepository performanceDataRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public String getPerformanceData(int algorithm) {
        
        BigInteger result = BigInteger.ZERO;
        switch (algorithm) {
        case 0:
            result = performanceDataRepository.calculateSalesForCountry("Tanz").toBigInteger();
            break;
        case 1:
            result = BigInteger.valueOf(performanceDataRepository.findAllByCountry("M"));
            break;
        case 2:
            result = BigInteger.valueOf(performanceDataRepository.algoritmus7());
            break;
        case 3:
            result = BigInteger.valueOf(performanceDataRepository.algoritmus4());
            break;
        case 4:
            result = BigInteger.valueOf(performanceDataRepository.algoritmus6());
            break;
        case 5:
            result = BigInteger.valueOf(performanceDataRepository.algoritmus5());
            break;
        case 6:
            result = BigInteger.valueOf(performanceDataRepository.salesMultiSelects4());
            break;
        case 7:
            result = BigInteger.valueOf(performanceDataRepository.algoritmus8());
            break;
        case 8:
            result = BigInteger.valueOf(performanceDataRepository.findAll().size());
            break;
        case 9:
            result = BigInteger.valueOf(performanceDataRepository.salesMultiSelects3());
            break;
        default:
            break;
        }
        return ""+result;
    }
    
}