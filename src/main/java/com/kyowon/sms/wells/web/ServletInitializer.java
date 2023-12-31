package com.kyowon.sms.wells.web;

import com.sds.sflex.online.CommonApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        setRegisterErrorPageFilter(false);
        return application.sources(CommonApplication.class);
    }

}
