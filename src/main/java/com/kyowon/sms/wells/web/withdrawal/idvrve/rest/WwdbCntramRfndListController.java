package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListAgrgReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListAgrgRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCntramRfndListDto.SearchCntramRfndListRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbCntramRfndListService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 계약금 환불 목록")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/wells-refund-lists")
public class WwdbCntramRfndListController {

    private final WwdbCntramRfndListService service;

    @ApiOperation(value = "계약금 환불현황 목록", notes = "계약금 환불현황 목록")
    @GetMapping("/paging")
    public PagingResult<SearchCntramRfndListRes> getCntramRfndListPages(
        @ApiParam
        @Valid
        SearchCntramRfndListReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getCntramRfndListPages(req, pageInfo);
    }

    @ApiOperation(value = "계약금 환불현황 엑셀 다운로드", notes = "웰스 환불 목록(카드사별) 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchCntramRfndListRes> getCntramRfndListExcelDownload(
        SearchCntramRfndListReq req
    ) {
        return service.getCntramRfndListExcelDownload(req);
    }

    @ApiOperation(value = "계약금 환불 집계", notes = "계약금 환불 집계")
    @GetMapping("/aggregate")
    public SearchCntramRfndListAgrgRes getCntramRfndListAgrg(
        SearchCntramRfndListAgrgReq req
    ) {
        return service.getCntramRfndListAgrg(req);
    }
}
