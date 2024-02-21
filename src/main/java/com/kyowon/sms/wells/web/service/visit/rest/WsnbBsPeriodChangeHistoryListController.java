package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsPeriodChangeHistoryListDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsPeriodChangeHistoryListDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbBsPeriodChangeHistoryListService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/zip-assignments/bs-period-change-history-list")
@Api(tags = "[WSNB] B/S 주기변경 이력 조회 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbBsPeriodChangeHistoryListController {

    private final WsnbBsPeriodChangeHistoryListService service;

    @ApiOperation(value = "B/S 주기변경 이력 조회", notes = "조회조건에 일치하는 B/S 주기변경 이력 조회 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "관리년", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "inqrDvCd", value = "조회구분코드", paramType = "query"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getBsPeriodChangeHistoryList(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getBsPeriodChangeHistoryList(dto, pageInfo);
    }

    @ApiOperation(value = "B/S 주기변경 이력 조회 엑셀다운로드", notes = "조회조건에 일치하는 B/S 주기변경 이력 조회 데이터를 엑셀다운로드한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "관리년", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrDtlNo", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "inqrDvCd", value = "조회구분코드", paramType = "query"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getBsPeriodChangeHistoryListForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return this.service.getBsPeriodChangeHistoryListForExcelDownload(dto);
    }

}
