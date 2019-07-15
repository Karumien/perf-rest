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
	
	@Query("select count(qm) from PerformanceData qm where qm.region like :region%")
    Long findAllByRegion(@Param("region") String region);
	
	@Query("select count(qm) from PerformanceData qm where qm.country like %:country%")
    Long findAllByCountry(@Param("country") String country);
	
	@Query("select count(qm) from PerformanceData qm where qm.salesChannel like %:channel%")
    Long findAllBySalesChannel(@Param("channel") String channel);
	
	@Query("select sum(qm.unitsSold * qm.unitsPrice) from PerformanceData qm where qm.country like %:country%")
    BigDecimal calculateSalesForCountry (@Param("country") String country);
	
	@Query(value = "select count (*) from (select  qs.UNITS_SOLD, qs.ORDER_ID from PERF_DATA qs order by qs.ORDER_ID desc)", nativeQuery = true)
    Long algoritmus4();
	
	@Query(value = "select count (*) from (select distinct qs.UNITS_SOLD, qs.ORDER_ID from PERF_DATA qs group by qs.ORDER_ID  order by qs.ORDER_ID desc)", nativeQuery = true)
    Long algoritmus5();	
	
	@Query(value = "select count (*) from (select  top 10 qs.ITEM_TYPE, qs.SALES_CHANNEL, qs.ORDER_ID from PERF_DATA qs order by qs.ORDER_ID desc) q \r\n" + 
			"left join PERF_DATA  d on d.SALES_CHANNEL= q.SALES_CHANNEL", nativeQuery = true)
    Long algoritmus6();
	
	@Query(value = "select count (*) from (select  top 320 qs.ITEM_TYPE, qs.SALES_CHANNEL, qs.ORDER_ID from PERF_DATA qs order by qs.ORDER_ID desc) q left join PERF_DATA  d on d.ITEM_TYPE= q.ITEM_TYPE where d.COUNTRY like '%Am%er%'", nativeQuery = true)
    Long algoritmus7();
	
	@Query(value = "select count (*) from (select  top 100 qs.ITEM_TYPE, qs.SALES_CHANNEL, qs.ORDER_ID from PERF_DATA qs order by qs.ORDER_ID desc) q \r\n" + 
			"left join PERF_DATA  d on d.SALES_CHANNEL= q.SALES_CHANNEL;", nativeQuery = true)
    Long algoritmus8();
	
	@Query(value = "select distinct sum(first.TOTAL_PROFIT) from PERF_DATA first"
		+ " left join PERF_DATA  tri on (tri.ORDER_PRIORITY = first.ORDER_PRIORITY)"
		+ " where first.TOTAL_PROFIT > 1000 and first.TOTAL_PROFIT < 4120", nativeQuery = true)
    Long salesMultiSelects3();
	
	@Query(value = "select count (tri.*) from (select * from PERF_DATA  where  TOTAL_PROFIT > 1000 and TOTAL_PROFIT < 1050)"
        + " first left join PERF_DATA  tri on tri.ORDER_PRIORITY = first.ORDER_PRIORITY", nativeQuery = true)
    Long salesMultiSelects4();

}
