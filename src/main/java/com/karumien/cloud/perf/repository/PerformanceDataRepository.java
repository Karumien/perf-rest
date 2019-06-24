/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.karumien.cloud.perf.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.karumien.cloud.perf.model.PerformanceData;

/**
 * Data Repository for Tests.
 *
 * @author <a href="viliam.litavec@karumien.com">Viliam Litavec</a>
 * @since 1.0, 16. 6. 2019 8:07:16
 */
public interface PerformanceDataRepository extends JpaRepository<PerformanceData, Long>  {
	
	@Query("select qm from PerformanceData qm where qm.region like :region%")
    List<PerformanceData> findAllByRegion(@Param("region") String region);
	
	@Query("select qm from PerformanceData qm where qm.country like %:country%")
    List<PerformanceData> findAllByCountry(@Param("country") String country);
	
	@Query("select qm from PerformanceData qm where qm.salesChannel like %:channel%")
    List<PerformanceData> findAllBySalesChannel(@Param("channel") String channel);
	
	@Query("select qm.unitsSold * qm.unitsPrice from PerformanceData qm where qm.country like %:country%")
    List<BigDecimal> calculateSalesForCountry (@Param("country") String country);
	
	@Query(value = "SELECT Count(UNITS_SOLD) FROM PERF_DATA td WHERE td.UNITS_SOLD  > 1000" 
		+ " group by td.UNITS_SOLD, country", nativeQuery = true)
    List<Long> salesByCountryGrouping();
	
	@Query(value = "SELECT Count(UNITS_SOLD) FROM PERF_DATA td WHERE td.UNITS_SOLD  > 1000" 
		+ " and order_id in (select order_id from PERF_DATA  where total_profit > 5000)" 
		+ " group by td.UNITS_SOLD, country, region", nativeQuery = true)
    List<Long> salesByCountryGroupingSubSelect();
	
	@Query(value = "SELECT Count(UNITS_SOLD) FROM PERF_DATA td WHERE td.UNITS_SOLD  > 1000"
		+ " and order_id in (select order_id from PERF_DATA  where total_profit > 5000)"
		+ " and region in (select region from PERF_DATA  where region like '%as%' or region like '%eu%' group by region)"
		+ " group by td.UNITS_SOLD, country, region", nativeQuery = true)
    List<Long> salesMultiSelects();
	
	@Query(value = "SELECT Count(UNITS_SOLD) FROM PERF_DATA td"
		+ " WHERE td.UNITS_SOLD  > 1000 and" 
		+ " order_id in (select order_id from PERF_DATA  where total_profit > 5000)"
		+ " and region in (select region from PERF_DATA  where unit_cost > 50  and unit_cost < 100)"
		+ " group by td.UNITS_SOLD, country, region", nativeQuery = true)
    List<Long> salesMultiSelects2();
	
	@Query(value = "select first.TOTAL_PROFIT"
		+ " from (select * from PERF_DATA  where  TOTAL_PROFIT > 1000 and TOTAL_PROFIT < 1020) first"
		+ " left join PERF_DATA  tri on tri.ORDER_PRIORITY = first.ORDER_PRIORITY group by first.order_id", nativeQuery = true)
    List<Long> salesMultiSelects3();
	
	@Query(value = "select count (tri.*) from (select * from PERF_DATA  where  TOTAL_PROFIT > 1000 and TOTAL_PROFIT < 1050)"
        + " first left join PERF_DATA  tri on tri.ORDER_PRIORITY = first.ORDER_PRIORITY", nativeQuery = true)
    Long salesMultiSelects4();

}
