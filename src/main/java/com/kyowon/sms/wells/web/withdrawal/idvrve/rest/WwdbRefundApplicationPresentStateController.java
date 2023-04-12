package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationPresentStateDto.SearchRefundApplicationPresentStateReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationPresentStateDto.SearchRefundApplicationPresentStateRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbRefundApplicationPresentStateService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[입금관리 - 개별수납] 환불 신청 현황")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/refund-application-present-state")
public class WwdbRefundApplicationPresentStateController {

    private final WwdbRefundApplicationPresentStateService service;

    @ApiOperation(value = "환불 신청 현황 목록", notes = "환불 신청 현황 목록 조회")
    @GetMapping("/paging")
    public PagingResult<SearchRefundApplicationPresentStateRes> getRefundApplicationPresentStatePages(
        @ApiParam
        @Valid
        SearchRefundApplicationPresentStateReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRefundApplicationPresentStatePages(req, pageInfo);
    }

    @ApiOperation(value = "환불 신청 현황 목록 엑셀 다운로드", notes = "환불 신청 현황 목록 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchRefundApplicationPresentStateRes> getRefundApplicationPresentStateExcels(
        SearchRefundApplicationPresentStateReq req
    ) {
        return service.getRefundApplicationPresentStateExcels(req);
    }

}
