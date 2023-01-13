package com.kyowon.sms.wells.web.service.visit.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbIotProductIstBiztalkService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/iot-installation-biztalks")
@Api(tags = "[WSNB] IoT 알림톡 발송 REST API")
@RequiredArgsConstructor
@Slf4j
public class WsnbIotProductIstBiztalkController {

    private final WsnbIotProductIstBiztalkService service;

    @ApiOperation(value = "IoT 제품 설치 완료 알림톡 발송", notes = "IoT 제품 설치 완료 후, 익일 오후 3시에 알림톡을 발송한다.")
    @PostMapping
    public SaveResponse sendIotBiztalks() throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.sendIotBiztalks())
            .build();
    }

}
