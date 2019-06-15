/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf.service;

import com.karumien.cloud.perf.model.PerformanceData;

/**
 * Service for managing {@link PerformanceData} entity.
 *
 * @author <a href="viliam.litavec@karumien.com">Viliam Litavec</a>
 * @since 1.0, 10. 6. 2019 22:07:27
 */
public interface PerformanceDataService {

	/**
	 * Return data for specific algorithm
	 * 
	 * @param algotithm 0-9
	 * @return String description of work done
	 */
	String getPerformanceData(int algotithm);
	
}
