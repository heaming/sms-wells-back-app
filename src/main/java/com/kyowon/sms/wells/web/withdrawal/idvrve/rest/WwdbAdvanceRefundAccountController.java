package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbAdvanceRefundAccountService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "[입금관리 - 개별수납] 선환불계좌조회")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/advance-refund-account")
public class WwdbAdvanceRefundAccountController {

    private final WwdbAdvanceRefundAccountService service;

    @ApiOperation(value = "선환불계좌조회 목록", notes = "선환불계좌조회 목록")
    @GetMapping("/paging")
    public PagingResult<SearchAdvanceRefundAccountRes> getAdvanceRefundAccountPages(
        @ApiParam
        @Valid
        SearchAdvanceRefundAccountReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getAdvanceRefundAccountPages(req, pageInfo);
    }

    @ApiOperation(value = "선환불계좌조회 엑셀 다운로드", notes = "선환불계좌조회 엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<SearchAdvanceRefundAccountRes> getAdvanceRefundAccountExcels(
        SearchAdvanceRefundAccountReq req
    ) {
        return service.getAdvanceRefundAccountExcels(req);
    }
}
