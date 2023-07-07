package com.kyowon.sms.wells.web.deduction.redf.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAwRedfEtAmtDto.*;
import com.kyowon.sms.wells.web.deduction.redf.service.WdeaAwRedfEtAmtService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WDEA] 수당되물림 예상액 조회")
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/redf/estimate-amounts")
public class WdeaAwRedfEtAmtController {
    private final WdeaAwRedfEtAmtService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직", paramType = "query", required = false),
        @ApiImplicitParam(name = "perfDvCd", value = "실적구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cstNo", value = "회원번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cstNm", value = "회원명", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당되물림 예상액 - 판매리스트 목록조회", notes = "수당되물림 예상액 - 판매리스트 목록을 조회한다.")
    @GetMapping("/itemization/paging")
    public PagingResult<SearchRedfIzRes> getAwRedfEtAmtIzPages(
        SearchRedfIzReq dto, PageInfo pageInfo
    ) {
        return service.getAwRedfEtAmtIzPages(dto, pageInfo);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직", paramType = "query", required = false),
        @ApiImplicitParam(name = "perfDvCd", value = "실적구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cstNo", value = "회원번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cstNm", value = "회원명", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당되물림 예상액 - 판매리스트 목록 엑셀다운로드", notes = "수당되물림 예상액 - 판매리스트 목록을 조회하고 엑셀다운로드 한다.")
    @GetMapping("/itemization/excel-download")
    public List<SearchRedfIzRes> getAwRedfEtAmtIzForExcelDownload(SearchRedfIzReq dto) throws Exception {

        return service.getAwRedfEtAmtIzForExcelDownload(dto);

    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직", paramType = "query", required = false),
        @ApiImplicitParam(name = "perfDvCd", value = "실적구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cstNo", value = "회원번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cstNm", value = "회원명", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당되물림 예상액 - 실적현황 목록조회", notes = "수당되물림 예상액 - 실적현황 목록을 조회한다.")
    @GetMapping("/presentState")
    public List<SearchRedfIzPerfPsRes> getAwRedfIzPerfPsAmt(
        SearchRedfIzReq dto
    ) {
        return service.getAwRedfIzPerfPsAmt(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogTpCd", value = "조직", paramType = "query", required = false),
        @ApiImplicitParam(name = "perfDvCd", value = "실적구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cstNo", value = "회원번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cstNm", value = "회원명", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당되물림 예상액 - 실적현황 목록조회", notes = "수당되물림 예상액 - 실적현황 목록을 조회한다.")
    @GetMapping("/presentState/excel-download")
    public List<SearchRedfIzPerfPsRes> getAwRedfIzPerfPsAmtForExcelDownload(
        SearchRedfIzReq dto
    ) throws Exception {
        return service.getAwRedfIzPerfPsAmtForExcelDownload(dto);
    }

    @ApiOperation(value = "수당되물림 예상액 - Temp 테이블 삭제 및 저장", notes = "수당되물림 예상액 - Temp 테이블 삭제 및 저장한다.")
    @PostMapping
    public SaveResponse saveAwRedfEtAmt(
        @RequestBody
        List<SaveReq> dto
    ) {
        return SaveResponse.builder().processCount(service.saveAwRedfEtAmt(dto)).build();
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당되물림 예상액 - 되물림 예상액 목록조회", notes = "수당되물림 예상액 - 되물림 예상액 목록을 조회한다.")
    @GetMapping("/estimate")
    public List<SearchRedfEtRes> getAwRedfEtAmt() throws Exception {

        return service.getAwRedfEtAmt();

    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
    })
    @ApiOperation(value = "수당되물림 예상액 - 되물림 예상액 목록 엑셀다운로드", notes = "수당되물림 예상액 - 되물림 예상액 목록을 조회하고 엑셀다운로드 한다.")
    @GetMapping("/estimate/excel-download")
    public List<SearchRedfEtRes> getAwRedfEtAmtForExcelDownload() throws Exception {

        return service.getAwRedfEtAmtForExcelDownload();

    }

}
