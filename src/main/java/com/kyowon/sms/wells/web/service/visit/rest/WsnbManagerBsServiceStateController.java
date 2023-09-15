package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbManagerBsServiceStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbManagerBsServiceStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbManagerBsServiceStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNB] 매니저 B/S 서비스 현황 조회 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/manager-bs-service-state")
public class WsnbManagerBsServiceStateController {

    private final WsnbManagerBsServiceStateService service;

    @ApiOperation(value = "매니저 B/S 서비스 현황 조회", notes = "조회조건에 일치하는 매니저 B/S 서비스 현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYy", value = "기준년도", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "cnfmPsicDvCd", value = "관리구분", paramType = "query"),
        @ApiImplicitParam(name = "vstPrgsStatCd", value = "작업구분", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getManagerBsServiceStates(
        @Valid
        SearchReq dto
    ) {
        return service.getManagerBsServiceStates(dto);

    }
}
