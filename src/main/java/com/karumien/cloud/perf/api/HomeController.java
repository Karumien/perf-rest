/*
 * Copyright (c) 2019-2029 Karumien s.r.o.
 *
 * Karumien s.r.o. is not responsible for defects arising from 
 * unauthorized changes to the source code.
 */
package com.karumien.cloud.perf.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.karumien.cloud.perf.service.VersionInfoService;

import net.logstash.logback.encoder.org.apache.commons.lang3.StringUtils;

/**
 * Home redirection to swagger api documentation.
 *
 * @author <a href="miroslav.svoboda@karumien.com">Miroslav Svoboda</a>
 * @since 1.0, 15. 6. 2019 21:27:49
 */
@Controller
public class HomeController {

    @Autowired
    private VersionInfoService versionInfoService;
    
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/version", produces="text/html")    
    @ResponseBody
    public String getVersion() {
        return versionInfoService.getVersion();
    }
    
}
