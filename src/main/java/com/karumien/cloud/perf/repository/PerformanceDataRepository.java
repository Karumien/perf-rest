/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karumien.cloud.perf.model.PerformanceData;

/**
 * Repository for managing {@link PerformanceData} entity.
 *
 * @author <a href="viliam.litavec@karumien.com">Viliam Litavec</a>
 * @since 1.0, 10. 6. 2019 22:02:49
 */
public interface PerformanceDataRepository extends JpaRepository<PerformanceData, BigInteger> {
}
