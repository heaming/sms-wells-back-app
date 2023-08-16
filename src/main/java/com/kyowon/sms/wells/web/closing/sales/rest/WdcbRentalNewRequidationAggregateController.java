package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbRentalNewRequidationAggregateDto;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbRentalNewRequidationAggregateService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDCB] 렌탈 신규/철거 집계현황")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/rental-new-requidation-aggregates")
public class WdcbRentalNewRequidationAggregateController {
    public final WdcbRentalNewRequidationAggregateService service;

    @ApiOperation(value = "렌탈 신규/철거 집계현황", notes = "검색 조건을 입력 받아 렌탈 신규/철거 집계현황 정보를 조회한다.")
    @GetMapping
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "매출일자from", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "매출일자to", paramType = "query"),
        @ApiImplicitParam(name = "adrpcTpCd", value = "설치/택배 제품구분", paramType = "query"),
        @ApiImplicitParam(name = "sppProcsBzsCd", value = "업체코드", paramType = "query"),
        @ApiImplicitParam(name = "slYm", value = "매출년월", paramType = "query"),
    })
    public List<WdcbRentalNewRequidationAggregateDto.SearchRes> getAggregates(
        WdcbRentalNewRequidationAggregateDto.SearchReq dto
    ) {
        return service.getAggregates(dto);
    }

    @ApiOperation(value = "렌탈 신규/철거 집계현황 상세조회", notes = "검색 조건을 입력 받아 렌탈 신규/철거 집계현황 정보를 상세조회한다.")
    @GetMapping("/details")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "매출일자from", paramType = "query"),
        @ApiImplicitParam(name = "endDt", value = "매출일자to", paramType = "query"),
        @ApiImplicitParam(name = "adrpcTpCd", value = "설치/택배 제품구분", paramType = "query"),
        @ApiImplicitParam(name = "sppProcsBzsCd", value = "업체코드", paramType = "query"),
        @ApiImplicitParam(name = "slYm", value = "매출년월", paramType = "query"),
        @ApiImplicitParam(name = "divCd", value = "구분코드", paramType = "query"),
        @ApiImplicitParam(name = "divDtlCd", value = "상세구분코드", paramType = "query"),
    })
    public List<WdcbRentalNewRequidationAggregateDto.SearchDetailRes> getRentalNewRequidations(
        WdcbRentalNewRequidationAggregateDto.SearchDetailReq dto
    ) {
        return service.getRentalNewRequidations(dto);
    }
}
