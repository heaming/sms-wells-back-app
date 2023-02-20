package com.kyowon.sms.wells.web.service.stock.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMatAutoAplcCrdovrDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.service.WsnaMatAutoAplcCrdovrService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/auto-aplc-crdovr")
@Api(tags = "[WSNA] 서비스운영팀 자재자동신청 관련 데이터 이월 REST API")
@RequiredArgsConstructor
public class WsnaMatAutoAplcCrdovrController {

    private final WsnaMatAutoAplcCrdovrService service;

    @ApiOperation(value = "서비스운영팀 자재자동신청 관련 데이터 이월")
    @PostMapping
    public SaveResponse saveMatAutoAplcCrdovrs(CreateReq dto) {
        return SaveResponse.builder()
            .processCount(service.saveMatAutoAplcCrdovrs(dto))
            .build();
    }
}
