package com.kyowon.sms.wells.web.fee.simulation.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefSellFeeEtPerfDto.SearchRes;
import com.kyowon.sms.wells.web.fee.simulation.service.WfefSellFeeEtPerfService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WFEF] 수수료 예상 실적 조회 (판매)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/sell-fee-et-perf")
public class WfefSellFeeEtPerfController {

    private final WfefSellFeeEtPerfService service;

    @ApiOperation(value = "수수료 예상 실적 조회 (판매)", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "inqrStrtDt", value = "조회시작일자", defaultValue = "20230801", paramType = "query", required = true),
        @ApiImplicitParam(name = "inqrEndDt", value = "조회종료일자", defaultValue = "20230801", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfBaseDv", value = "실적기준구분 1: 접수, 2: 매출", defaultValue = "1", paramType = "query", required = true),
        @ApiImplicitParam(name = "inqrDv", value = "조회구분 1: 건수&실적, 2: 계정순증, 3: 판매상세, 4: 순증상세", defaultValue = "1", paramType = "query", required = true),
        @ApiImplicitParam(name = "nincDv", value = "순증구분 01: 전월취소 02: 신규판매", paramType = "query"),
        @ApiImplicitParam(name = "pstnDvCd", value = "직급구분코드(01,02,03,04)", defaultValue = "01", paramType = "query", required = true),
        @ApiImplicitParam(name = "dgr1LevlOgCd", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "사번", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getSellFeeEtPerf(
        @Valid
        SearchReq dto
    ) {
        return service.getSellFeeEtPerf(dto);
    }
}
