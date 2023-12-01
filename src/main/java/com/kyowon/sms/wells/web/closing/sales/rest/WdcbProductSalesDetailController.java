package com.kyowon.sms.wells.web.closing.sales.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchSingleRes;
import com.kyowon.sms.wells.web.closing.sales.service.WdcbProductSalesDetailService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCB] 상품별 매출현황-매출상세조회 (W-CL-U-0028M04)")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/product-sales-detail")
public class WdcbProductSalesDetailController {
    private final WdcbProductSalesDetailService service;

    /**
     * 매출상세조회(일시불,금융리스,정기구매)
     * @param dto
     * @return
     */
    @ApiOperation(value = "매출상세조회(일시불,금융리스,정기구매)", notes = "조회조건에 따른 매출상세를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "sapSlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/single-payment")
    public List<SearchSingleRes> getProductSalesSinglePaymentDetails(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesSinglePaymentDetails(dto);
    }

    /**
     * 매출상세조회(렌탈)
     * @param dto
     * @return
     */
    @ApiOperation(value = "매출상세조회(렌탈)", notes = "조회조건에 따른 매출상세를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "sapSlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/rental")
    public List<SearchRentalRes> getProductSalesRentalDetails(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesRentalDetails(dto);
    }

    /**
     * 매출상세조회(멤버십)
     * @param dto
     * @return
     */
    @ApiOperation(value = "매출상세조회(멤버십)", notes = "조회조건에 따른 매출상세를 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "sapSlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/membership")
    public List<SearchMembershipRes> getProductSalesMembershipDetails(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesMembershipDetails(dto);
    }

    /**
     * 상품별 매출 현황 엑셀 다운로드
     * @param dto
     * @return
     */
    @ApiOperation(value = "상품별 매출 현황 엑셀 다운로드", notes = "조회조건에 따른 상품별 매출 목록을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "sapSlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/single-payment/excel-download")
    public List<SearchSingleRes> getProductSalesSinglePaymentDetailsExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesSinglePaymentDetails(dto);
    }

    /**
     * 상품별 매출 현황 엑셀 다운로드
     * @param dto
     * @return
     */
    @ApiOperation(value = "상품별 매출 현황 엑셀 다운로드", notes = "조회조건에 따른 상품별 매출 목록을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "sapSlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/rental/excel-download")
    public List<SearchRentalRes> getProductSalesRentalDetailsExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesRentalDetails(dto);
    }

    @ApiOperation(value = "상품별(렌탈) 매출 현황 대량 엑셀 다운로드", notes = "조회조건에 따른 상품별 매출 목록을 엑셀 다운로드")
    @PostMapping("/rental/bulk-excel-download")
    public void getProductSalesRentalDetailsBulkExcelDownload(
        @RequestBody
        ExcelBulkDownloadDto.DownloadReq req,
        HttpServletResponse response
    ) throws IOException {
        service.getProductSalesRentalDetailsBulkExcelDownload(req, response);
    }

    /**
     * 상품별 매출 현황 엑셀 다운로드
     * @param dto
     * @return
     */
    @ApiOperation(value = "상품별 매출 현황 엑셀 다운로드", notes = "조회조건에 따른 상품별 매출 목록을 엑셀 다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseDtmnFrom", value = "매출시작일자", paramType = "query"),
        @ApiImplicitParam(name = "baseDtmnTo", value = "매출종료일자", paramType = "query"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query"),
        @ApiImplicitParam(name = "sellTpDtlCd", value = "판매유형상세", paramType = "query"),
        @ApiImplicitParam(name = "sellChnlDtl", value = "판매채널", paramType = "query"),
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query"),
        @ApiImplicitParam(name = "sapSlpno", value = "SAP전표번호", paramType = "query"),
        @ApiImplicitParam(name = "sapPdDvCd", value = "SAP상품구분코드명", paramType = "query"),
    })
    @GetMapping("/membership/excel-download")
    public List<SearchMembershipRes> getProductSalesMembershipDetailsExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getProductSalesMembershipDetails(dto);
    }
}
