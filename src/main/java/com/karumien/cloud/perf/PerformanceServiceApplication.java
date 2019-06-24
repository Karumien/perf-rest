/*
 * Copyright (c) 2019 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from 
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Performance Service Application.
 *
 * @author <a href="miroslav.svoboda@karumien.com">Miroslav Svoboda</a>
 * @since 1.0, 10. 6. 2019 13:53:43
 */
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableHystrix
//@EnableHystrixDashboard
//@EnableFeignClients
public class PerformanceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformanceServiceApplication.class, args);
    }
    
}
