package com.kyowon.sms.wells.web.service.allocate.rest;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlAgrgDto.*;
import com.kyowon.sms.wells.web.service.allocate.service.WsncVisitCustomerRglvlAgrgService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "[WSNC] 방문고객 급지집계")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/visit-cst-rglvl-agrg")
@Validated
public class WsncVisitCustomerRglvlAgrgController {
    private final WsncVisitCustomerRglvlAgrgService service;

    @ApiOperation(value = "방문고객 급지집계 지역단 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "visitYm", value = "방문년월", paramType = "query", example = "202305"),
        @ApiImplicitParam(name = "rcgvpDiv", value = "고객구분", paramType = "query"),
        @ApiImplicitParam(name = "rcgvpDiv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "executiveGroup", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "localGroupCd", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "partnerNo", value = "매니저", paramType = "query"),
    })
    @GetMapping("/rgrp")
    public List<SearchRgrpRes> getVisitCustomerRglvlAgrgRgrp(SearchReq dto){
        return service.getVisitCustomerRglvlAgrgRgrp(dto);
    }

    @ApiOperation(value = "방문고객 급지집계 담당자 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "visitYm", value = "방문년월", paramType = "query", example = "202305"),
        @ApiImplicitParam(name = "rcgvpDiv", value = "고객구분", paramType = "query"),
        @ApiImplicitParam(name = "rcgvpDiv", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "executiveGroup", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "localGroupCd", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "partnerNo", value = "매니저", paramType = "query"),
    })
    @GetMapping("/psic")
    public List<SearchPsicRes> getVisitCustomerRglvlAgrgPsic(SearchReq dto){
        return service.getVisitCustomerRglvlAgrgPsic(dto);
    }
}
