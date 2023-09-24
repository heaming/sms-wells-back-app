package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncMonthBsExpcCustAgrgStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncMonthBsExpcCustAgrgStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncMonthBsExpcCustAgrgStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] B/S예상고객 집계현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/month-bs-expc-cust-agrg-state")
@Validated
public class WsncMonthBsExpcCustAgrgStateController {

    private final WsncMonthBsExpcCustAgrgStateService service;

    @ApiOperation(value = "B/S 예상고객 집계현황 조회", notes = "B/S 예상고객 집계현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "baseYm", value = "배정기준일", paramType = "query", example = "202305"),
        @ApiImplicitParam(name = "dgr1LevlOgId", value = "총괄단", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "dgr2LevlOgId", value = "지역단", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "dgr3LevlOgId", value = "지점", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "ogId", value = "OG_ID", paramType = "query", example = ""),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", example = ""),
    })
    @GetMapping
    public List<SearchRes> getMonthBsExpcCustAgrgStates(
        @Valid
        SearchReq dto
    ) {
        return service.getMonthBsExpcCustAgrgState(dto);
    }

}
