package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncTransferHistoryDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncTransferHistoryService;
import com.sds.sflex.system.config.constant.CommConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 이관이력 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/transfer-histories")
public class WsncTransferHistoryController {
    private final WsncTransferHistoryService service;

    @ApiOperation(value = "이관이력 조회 화면 - 이관이력 조회 조회", notes = "조회조건에 따른 이관이력 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
    })
    @GetMapping
    public List<WsncTransferHistoryDto.SearchRes> getFixationVisits(
        WsncTransferHistoryDto.SearchReq dto
    ) {
        return service.getTransferHistories(dto);
    }
}
