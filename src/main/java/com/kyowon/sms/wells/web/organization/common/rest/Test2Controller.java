package com.kyowon.sms.wells.web.organization.common.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.sflex.system.config.constant.CommConst;

import io.swagger.annotations.Api;


@RequestMapping(value = CommConst.REST_URL_V1 + "/products")
@Api(tags = "[OZZ] Sample Test")
@RestController
public class Test2Controller {

    @GetMapping
    public List<String> getData() {
        return List.of("AAAA", "BBB");
    }
}
