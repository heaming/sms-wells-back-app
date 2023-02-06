package com.kyowon.sms.wells.web.service.visit.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbProductService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/products")
@Api(tags = "[WSNB] 큐빅 CC 홈쇼핑건 상담중 상품변경건 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbProductController {

    private final WsnbProductService service;

    @ApiOperation(value = "큐빅 CC 홈쇼핑건 상담중 상품변경건", notes = "CuBig CC에서 홈쇼핑건 상담중에 상품변경 요청건에 대하여 확인한다.")
    @PostMapping
    public SaveResponse editProducts() throws Exception {
        return SaveResponse.builder()
            .processCount(service.editProducts())
            .build();
    }

}
