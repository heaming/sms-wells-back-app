package com.kyowon.sms.wells.web.closing.sales.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.common.web.closing.sales.dto.ZdchClearingSlipCreateDto;
import com.kyowon.sms.common.web.closing.sales.service.ZdchClearingSlipCreateService;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.*;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbBusinessAtamAdjustMgtService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;
import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto.DownloadReq;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 영업선수금 정산 관리 - W-CL-U-0034M01")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/business-atam-adjusts")
public class WdcbBusinessAtamAdjustMgtController {

    private final WdcbBusinessAtamAdjustMgtService service;
    private final ZdchClearingSlipCreateService zdchClearingSlipCreateService;

    /**
     * 대표고객코드 조회
     * @return
     */
    @ApiOperation(value = "대표고객코드 조회", notes = "대표고객코드 정보를 조회")
    @GetMapping("/codes")
    public List<SearchSapPdDvCdRes> getSapPdDvCds() {
        return service.getSapPdDvCds();
    }

    /**
     * 영업선수금 정산 관리(집계) bulk 엑셀 다운로드
     * @param req
     * @param response
     * @return
     */
    @ApiOperation(value = "영업선수금 정산 관리(집계) bulk 엑셀 다운로드", notes = "조회조건에 따른 영업선수금 내역을 bulk 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "dpKndCd", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "sapAlrpySlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @PostMapping("/total/bulk-excel-download")
    public void getBusinessAtamTotalsForBulkExcelDownload(
        @RequestBody
        DownloadReq req,
        HttpServletResponse response
    ) throws IOException {
        service.getBusinessAtamTotalsForBulkExcelDownload(req, response);
    }

    /**
     * 영업선수금 정산 관리(집계)
     * @param dto
     * @return
     */
    @ApiOperation(value = "영업선수금 정산 관리(집계)", notes = "조회조건에 따른 영업선수금 내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "dpKndCd", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "sapAlrpySlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/total/paging")
    public PagingResult<SearchTotalRes> getBusinessAtamTotalsPaging(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getBusinessAtamTotalsPaging(dto, pageInfo);
    }

    /**
     * 영업선수금 정산 관리(상세) bulk 엑셀 다운로드
     * @param req
     * @param response
     * @return
     */
    @ApiOperation(value = "영업선수금 정산 관리(상세) bulk 엑셀 다운로드", notes = "조회조건에 따른 영업선수금 내역을 bulk 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "dpKndCd", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "sapAlrpySlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @PostMapping("/detail/bulk-excel-download")
    public void getBusinessAtamDetailsForBulkExcelDownload(
        @RequestBody
        DownloadReq req,
        HttpServletResponse response
    ) throws IOException {
        service.getBusinessAtamDetailsForBulkExcelDownload(req, response);
    }

    /**
     * 영업선수금 정산 관리(상세)
     * @param dto
     * @return
     */
    @ApiOperation(value = "영업선수금 정산 관리(상세)", notes = "조회조건에 따른 영업선수금 내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "query"),
        @ApiImplicitParam(name = "dpKndCd", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "sapAlrpySlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/detail/paging")
    public PagingResult<SearchDetailRes> getBusinessAtamDetailsPaging(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getBusinessAtamDetailsPaging(dto, pageInfo);
    }

    /**
     * 반제전표 생성
     * @param dto
     * @return
     */
    @ApiOperation(value = "반제전표 생성", notes = "반제전표 생성")
    @PostMapping
    public String saveSlipCreate(
        @RequestBody
        @Valid
        ZdchClearingSlipCreateDto.SaveReq dto
    ) throws Exception {
        return zdchClearingSlipCreateService.clearingSlipCreate(dto);
    }

    /**
     * 채권반제 조회
     * @param sapAlrpySlpno
     * @return
     */
    @ApiOperation(value = "채권반제 조회", notes = "조회조건에 따른 채권반제 내역을 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sapAlrpySlpno", value = "SAP전표번호", paramType = "query"),
    })
    @GetMapping("/sapAlrpySlpno")
    public List<SearchSlpnoRes> getSapAlrpySlpnos(
        @RequestParam
        String sapAlrpySlpno
    ) {
        return service.getSapAlrpySlpnos(sapAlrpySlpno);
    }

    /**
     * 전표 초기화
     * @param dtos
     * @return
     */
    @ApiOperation(value = "전표 초기화", notes = "전표 초기화")
    @PostMapping("/sapAlrpySlpno")
    public SaveResponse saveSlpnoInitializes(
        @RequestBody
        @Valid
        @NotEmpty
        List<SaveSlpnoReq> dtos
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveSlpnoInitializes(dtos)).build();
    }
}
