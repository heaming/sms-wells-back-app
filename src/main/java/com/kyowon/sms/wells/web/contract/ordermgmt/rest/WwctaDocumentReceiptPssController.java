package com.kyowon.sms.wells.web.contract.ordermgmt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WwctaDocumentReceiptPssDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WwctaDocumentReceiptPssService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTA] wells 서류접수현황(추가)")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1)
public class WwctaDocumentReceiptPssController {

    private final WwctaDocumentReceiptPssService service;

    @ApiOperation(value = "wells 서류접수현황(추가) 조회", notes = "계약번호와 계약구분별로 카카오톡 발송내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrChRcpStrtDtm", value = "접수시작일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrChRcpFinsDtm", value = "접수종료일자", paramType = "query"),
        @ApiImplicitParam(name = "cntrChPrgsStatCd", value = "접수현황", paramType = "query"),
        @ApiImplicitParam(name = "cntrChTypeCd", value = "접수유형", paramType = "query"),
        @ApiImplicitParam(name = "cntrChRcpId", value = "접수번호", paramType = "query"),
        @ApiImplicitParam(name = "cstKnm", value = "고객명", paramType = "query"),
        @ApiImplicitParam(name = "cralLocapaMexnoIdvTno", value = "휴대전화번호", paramType = "query"),
    })
    @GetMapping("/document-receipts")
    public List<SearchRes> getDocumentReceipts(
        @Valid
        SearchReq dto
    ) {
        return service.getDocumentReceipts(dto);
    }

    @GetMapping("/document-receipts/excel-download")
    public List<SearchRes> getDocumentReceiptsExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getDocumentReceiptsExcelDownload(dto);
    }
}
