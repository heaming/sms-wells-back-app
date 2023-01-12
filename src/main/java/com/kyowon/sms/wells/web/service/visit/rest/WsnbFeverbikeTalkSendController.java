package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.service.WsnbFeverbikeTalkSendService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/feverbike-talk-send")
@Api(tags = "[WSNB] 피버바이크 알림톡 발송 RESET API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbFeverbikeTalkSendController {

    private final WsnbFeverbikeTalkSendService service;

    @ApiOperation(value = "피버 바이크 플러스 온라인 강의 무료 구독 신청완료 후 고객에게 알림톡으로 발송한다.")
    @PutMapping
    public SaveResponse saveFeverbikeTalkSend() throws Exception {
        return SaveResponse.builder().processCount(service.saveFeverbikeTalkSend()).build();
    }

}
