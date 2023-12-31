package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.service.WsnbHealthCareSmsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/healthcare-sms")
@Api(tags = "[WSNC]  W-SV-S-0038 건강케어 알림톡 발송 , 설치 후 다음날 발송 안마의자, 웰스팜, 매트리스 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbHealthCareSmsController {

    private final WsnbHealthCareSmsService service;

    @ApiOperation(value = "안마의자, 웰스팜, 매트리스 등 설치 후 건강케어 고객에게 알림톡으로 발송한다.")
    @PostMapping
    public SaveResponse saveHealthCareNotakFws() throws Exception {
        return SaveResponse.builder().processCount(service.sendHealthCareSms(null)).build();
    }

}
