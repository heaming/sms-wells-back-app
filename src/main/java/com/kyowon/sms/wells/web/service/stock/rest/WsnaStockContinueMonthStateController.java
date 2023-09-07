package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaStockContinueMonthStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WSNA] 재고지속월현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/stock-continue-month-state")

public class WsnaStockContinueMonthStateController {

    private final WsnaStockContinueMonthStateService service;

    @ApiOperation(value = "재고지속월현황 목록 조회", notes = "조회조건에 일치하는 재고지속월현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "조직/개인창고", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
        @ApiImplicitParam(name = "itmDvCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "stokContMmCd", value = "재고지속월", paramType = "query"),
        @ApiImplicitParam(name = "m1BelowYn", value = "1개월이하", paramType = "query"),
        @ApiImplicitParam(name = "m2BelowYn", value = "2.5개월이하", paramType = "query"),
        @ApiImplicitParam(name = "btdMatYn", value = "기초자재", paramType = "query"),
        @ApiImplicitParam(name = "mdimRprMatYn", value = "중수리자재", paramType = "query"),
        @ApiImplicitParam(name = "turnoverTrgtYn", value = "회전율대상", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getStockContinueMonthStatePages(
        @Valid
        SearchReq dto
    ) {
        return service.getStockContinueMonthState(dto);
    }

    @ApiOperation(value = "재고지속월현황 목록 조회 엑셀다운로드", notes = "조회조건에 일치하는 재고지속월현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "조직/개인창고", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
        @ApiImplicitParam(name = "itmDvCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "stokContMmCd", value = "재고지속월", paramType = "query"),
        @ApiImplicitParam(name = "m1BelowYn", value = "1개월이하", paramType = "query"),
        @ApiImplicitParam(name = "m2BelowYn", value = "2.5개월이하", paramType = "query"),
        @ApiImplicitParam(name = "btdMatYn", value = "기초자재", paramType = "query"),
        @ApiImplicitParam(name = "mdimRprMatYn", value = "중수리자재", paramType = "query"),
        @ApiImplicitParam(name = "turnoverTrgtYn", value = "회전율대상", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> excelDownload(SearchReq dto) {

        return service.getStockContinueMonthState(dto);
    }

}
