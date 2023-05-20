package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncContactHistoryDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncContactHistoryService;
import com.sds.sflex.system.config.constant.CommConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 고객 컨택이력")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/contact-histories")
public class WsncContactHistoryController {
    private final WsncContactHistoryService service;

    @ApiOperation(value = "고객 컨택이력 화면 - 고객 컨택이력 조회", notes = "조회조건에 따른 고객 컨택이력 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cstSvAsnNo", value = "고객서비스배정번호", paramType = "query", required = true),
    })
    @GetMapping
    public List<WsncContactHistoryDto.SearchRes> getContactHistories(
        WsncContactHistoryDto.SearchReq dto
    ) {
        return service.getContactHistories(dto);
    }
}
