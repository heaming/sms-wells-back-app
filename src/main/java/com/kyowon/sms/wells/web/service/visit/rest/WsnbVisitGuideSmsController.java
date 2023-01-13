package com.kyowon.sms.wells.web.service.visit.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbVisitGuideSmsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/visit-guide-sms")
@Api(tags = "[WSNB] 방문 안내 문자 REST API")
@RequiredArgsConstructor
@Slf4j
public class WsnbVisitGuideSmsController {

    private final WsnbVisitGuideSmsService service;

    @ApiOperation(value = "방문일 D-1 전에 방문 안내 문자 발송", notes = "고객 서비스 방문일 1일전(D-1)에 고객에게 방문 안내 문자를 발송한다.")
    @PostMapping
    public SaveResponse sendVisitGuideSms() throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.sendVisitGuideSms())
            .build();
    }

}
