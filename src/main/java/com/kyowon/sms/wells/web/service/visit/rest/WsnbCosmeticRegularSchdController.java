package com.kyowon.sms.wells.web.service.visit.rest;

import com.kyowon.sms.wells.web.service.visit.service.WsnbCosmeticRegularSchdService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

// TODO: 필요 서비스 개발 완료 후 추가 작업
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/cosmetic-regular-schedules")
@Api(tags = "[WSNB] 화장품 캡슐 정기구매 고객 스케줄 REST API")
@RequiredArgsConstructor
public class WsnbCosmeticRegularSchdController {

    private final WsnbCosmeticRegularSchdService service;

    @ApiOperation(value = "화장품 캡슐 정기구매 고객 스케줄 생성", notes = "화장품 캡슐 정기구매 고객의 스케줄 정보를 insert/update한다.")
    @PostMapping
    public SaveResponse saveCosmeticRegularSchedules() throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.saveCosmeticRegularSchedules())
            .build();
    }

}
