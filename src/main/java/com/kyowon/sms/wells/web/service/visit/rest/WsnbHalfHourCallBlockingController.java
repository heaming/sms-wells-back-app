package com.kyowon.sms.wells.web.service.visit.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.service.WsnbHalfHourCallBlockingService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 30분 별 콜블록킹 안올라간 건")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/half-hour-callblockings")
public class WsnbHalfHourCallBlockingController {

    private final WsnbHalfHourCallBlockingService service;

    @PostMapping
    public SaveResponse saveHalfHourCallBlocking() {
        return SaveResponse.builder()
            .processCount(service.saveHalfHourCallBlocking())
            .build();
    }
}
