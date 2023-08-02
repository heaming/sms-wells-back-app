package com.kyowon.sms.wells.web.service.stock.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthMgtStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStockContinueMonthMgtStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.service.WsnaStockContinueMonthMgtStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WSNA] 재고지속월관리현황 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/stock-continue-month-mgt-state")

public class WsnaStockContinueMonthMgtStateController {

    private final WsnaStockContinueMonthMgtStateService service;

    @ApiOperation(value = "재고지속월관리현황 목록 조회", notes = "조회조건에 일치하는 재고지속월관리현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "조직/개인창고", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급", paramType = "query"),
        @ApiImplicitParam(name = "stockTpCd", value = "재고유형", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
        @ApiImplicitParam(name = "itmKindCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "matDiv", value = "자재구분", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getStockContinueMonthMgtStatePages(
        @Valid
        SearchReq dto
    ) {
        return service.getStockContinueMonthMgtState(dto);
    }

    @ApiOperation(value = "재고지속월관리현황 목록 조회 엑셀다운로드", notes = "조회조건에 일치하는 재고지속월관리현황 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "startDt", value = "시작일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "endDt", value = "종료일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDvCd", value = "창고", paramType = "query", required = true),
        @ApiImplicitParam(name = "wareDtlDvCd", value = "조직/개인창고", paramType = "query"),
        @ApiImplicitParam(name = "itmGdCd", value = "등급", paramType = "query"),
        @ApiImplicitParam(name = "stockTpCd", value = "재고유형", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부", paramType = "query"),
        @ApiImplicitParam(name = "itmKindCd", value = "품목구분", paramType = "query"),
        @ApiImplicitParam(name = "matDiv", value = "자재구분", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> excelDownload(SearchReq dto) {

        return service.getStockContinueMonthMgtState(dto);
    }

}
