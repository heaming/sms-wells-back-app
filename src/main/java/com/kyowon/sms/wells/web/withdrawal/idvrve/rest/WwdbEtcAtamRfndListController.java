package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListAgrgReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListAgrgRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbEtcAtamRfndListDto.SearchEtcAtamRfndListRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbEtcAtamRfndListService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 기타 선수금 환불 목록")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/etc-atam-rfnd-lists")
public class WwdbEtcAtamRfndListController {

    private final WwdbEtcAtamRfndListService service;

    @ApiOperation(value = "기타 선수금 환불 목록 조회", notes = "기타 선수금 환불 목록 조회")
    @GetMapping("/paging")
    public PagingResult<SearchEtcAtamRfndListRes> getEtcAtamRfndListPages(
        @ApiParam
        @Valid
        SearchEtcAtamRfndListReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getEtcAtamRfndListPages(req, pageInfo);
    }

    @ApiOperation(value = "기타 선수금 환불 목록 조회", notes = "기타 선수금 환불 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchEtcAtamRfndListRes> getEtcAtamRfndListExcelDownload(
        SearchEtcAtamRfndListReq req
    ) {
        return service.getEtcAtamRfndListExcelDownload(req);
    }

    @ApiOperation(value = "기타 선수금 환불 집계", notes = "기타 선수금 환불 집계")
    @GetMapping("/aggregate")
    public SearchEtcAtamRfndListAgrgRes getEtcAtamRfndListAgrg(
        SearchEtcAtamRfndListAgrgReq req
    ) {
        return service.getEtcAtamRfndListAgrg(req);
    }
}
