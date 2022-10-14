package com.kyowon.sms.wells.web;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties()
@SpringBootApplication(scanBasePackages = {
        "com.sds.sflex", "com.kyowon.sflex", "com.kyowon.sms.common", "com.kyowon.sms.wells"
})
public class CommonApplication {

    @Generated
    public static void main(String args[]) {
        SpringApplication.run(CommonApplication.class, args);
    }
}
