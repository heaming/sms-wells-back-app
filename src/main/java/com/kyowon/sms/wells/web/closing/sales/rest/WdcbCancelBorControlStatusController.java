package com.kyowon.sms.wells.web.closing.sales.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbCancelBorControlStatusDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbCancelBorControlStatusDto.SearchRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbCancelBorControlStatusService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 리스 매출조정/취소 집계 ")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/cancel-bor-control")
public class WdcbCancelBorControlStatusController {
    private final WdcbCancelBorControlStatusService service;

    @ApiOperation(value = "리스 매출조정/취소 집계 - 목록조회", notes = "리스 매출조정/취소 집계 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true)
    })
    @GetMapping("/paging")
    public List<SearchRes> getAdjustCancellationPages(
        @Valid
        SearchReq req
    ) {
        return service.getAdjustCancellationPages(req);
    }

    //    @ApiOperation(value = "렌탈 취소위약/조정현황 매출조정/취소집계 엑셀다운로드", notes = "검색조건을 입력 받아 렌탈 취소위약/조정현황 매출조정/취소집계 조회결과를 엑셀다운로드 한다.")
    //    @GetMapping("/excel-download")
    //    public List<SearchRes> getAdjustCancellationForExcelDownload(
    //        SearchReq Req
    //    ) {
    //        return service.getAdjustCancellationForExcelDownload(Req);
    //    }

}

