package com.kyowon.sms.wells.web.closing.payment.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchContractReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchContractRes;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDepositDelinquentDetailDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.service.WdcaDepositDelinquentDetailService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WDCA] 입금 연체 상세")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = DcClosingConst.COMMON_URL_V1 + "/deposit-delinquent-details")
public class WdcaDepositDelinquentDetailController {
    private final WdcaDepositDelinquentDetailService service;

    @ApiOperation(value = "입금 연체 상세", notes = "조회조건에 따른 입금 연체 상세를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "dlqDv", value = "연체구분", paramType = "query"),
        @ApiImplicitParam(name = "dlqMcnt", value = "연체개월", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "ogTp", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgCd", value = "조직레벨-총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "조직레벨-지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "조직레벨-지점", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "개인/법인구분", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getDepositDelinquentDetails(
        @Valid
        SearchReq dto
    ) {
        return service.getDepositDelinquentDetails(dto);
    }

    @ApiOperation(value = "입금 연체 상세-계약별 상세조회", notes = "조회조건에 따른 매출 채권(계약상세번호 Level)의 매출 / 입금/ 연체정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "dlqDv", value = "연체구분", paramType = "query"),
        @ApiImplicitParam(name = "dlqMcnt", value = "연체개월", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "ogTp", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgCd", value = "조직레벨-총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "조직레벨-지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "조직레벨-지점", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "개인/법인구분", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/contract")
    public PagingResult<SearchContractRes> getDepositDelinquentContractPages(
        @Valid
        SearchContractReq dto,
        PageInfo pageInfo
    ) {
        return service.getDepositDelinquentContractPages(dto, pageInfo);
    }

    @ApiOperation(value = "입금 연체 상세-계약별 상세조회", notes = "조회조건에 따른 매출 채권(계약상세번호 Level)의 매출 / 입금/ 연체정보를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
        @ApiImplicitParam(name = "dlqDv", value = "연체구분", paramType = "query"),
        @ApiImplicitParam(name = "dlqMcnt", value = "연체개월", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "ogTp", value = "조직유형", paramType = "query"),
        @ApiImplicitParam(name = "dgr1LevlOgCd", value = "조직레벨-총괄단", paramType = "query"),
        @ApiImplicitParam(name = "dgr2LevlOgCd", value = "조직레벨-지역단", paramType = "query"),
        @ApiImplicitParam(name = "dgr3LevlOgCd", value = "조직레벨-지점", paramType = "query"),
        @ApiImplicitParam(name = "copnDvCd", value = "개인/법인구분", paramType = "query"),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrCstNo", value = "고객번호", paramType = "query"),
    })
    @GetMapping("/contract/excel-download")
    public List<SearchContractRes> getDepositDelinquentContractForExcelDownload(
        @Valid
        SearchContractReq dto
    ) {
        return service.getDepositDelinquentContractForExcelDownload(dto);
    }
}
