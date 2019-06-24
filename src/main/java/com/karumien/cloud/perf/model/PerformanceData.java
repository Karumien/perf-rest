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
package com.karumien.cloud.perf.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "PERF_DATA")
@Data
@EqualsAndHashCode(of = "orderId")
public class PerformanceData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "REGION", length = 70)
    private String region;
    
    @Column(name = "COUNTRY", length = 70)
    private String country;
    
    @Column(name = "item_type", length = 70)
    private String item_type;
    
    @Column(name = "sales_channel", length = 70)
    private String salesChannel;
    
    @Column(name = "order_priority", length = 70)
    private String orderPriority;
    
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    
    @Id
    @Column(name = "order_id")
    private String orderId;
    
    @Column(name = "ship_date")
    private LocalDateTime shipDate;
    
    @Column(name = "units_sold")
    private BigDecimal unitsSold;
    
    @Column(name = "unit_price")
    private BigDecimal unitsPrice;
    
    @Column(name = "unit_cost")
    private BigDecimal unitsCost;

    @Column(name = "total_revenue")
    private BigDecimal totalRevenue;
    
    @Column(name = "total_cost")
    private BigDecimal totalCost;
    
    @Column(name = "total_profit")
    private BigDecimal totalProfit;
}
