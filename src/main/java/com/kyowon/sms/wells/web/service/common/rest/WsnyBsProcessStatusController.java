package com.kyowon.sms.wells.web.service.common.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.common.dto.WsnyBsProcessStatusDto;
import com.kyowon.sms.wells.web.service.common.service.WsnyBsProcessStatusService;
import com.sds.sflex.system.config.constant.CommConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNY] BS 처리현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/bs-process-status")
public class WsnyBsProcessStatusController {

    private final WsnyBsProcessStatusService service;

    @ApiOperation(value = "BS 처리현황 - BS 처리현황 조회", notes = "조회조건에 따른 BS 처리현황 조회")
    @GetMapping
    public WsnyBsProcessStatusDto.SearchRes getBsProcessStatus(
        WsnyBsProcessStatusDto.SearchReq dto
    ) {
        return service.getBsProcessStatus(dto);
    }
}
