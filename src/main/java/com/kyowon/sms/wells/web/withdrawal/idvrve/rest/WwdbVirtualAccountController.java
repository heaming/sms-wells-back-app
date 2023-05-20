package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbVirtualAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAdvanceRefundAccountDto.SearchAdvanceRefundAccountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbVirtualAccountDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbAdvanceRefundAccountService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[입금관리 - 개별수납 - 서비스] 가상계좌")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/virtual-accounts")
public class WwdbVirtualAccountController {

    private final WwdbVirtualAccountService service;

    @ApiOperation(value = "가상계좌조회(W-WD-S-0038)", notes = "가상계좌조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "prtnrNo", value = "파트너번호", paramType = "query", required = false),
    })
    @GetMapping
    public List<WwdbVirtualAccountDto.SearchRes> getVirtualAccounts(
        WwdbVirtualAccountDto.SearchReq req
    ) {
        return service.getVirtualAccounts(req);
    }

    @ApiOperation(value = "가상계좌입금내역 조회(W-WD-S-0039)", notes = "가상계좌입금내역 조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "prtnrNo", value = "판매자번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "bnkCd", value = "은행코드", paramType = "query", required = false),
        @ApiImplicitParam(name = "strtdt", value = "시작일", paramType = "query", required = false),
        @ApiImplicitParam(name = "enddt", value = "종료일", paramType = "query", required = false),
    })
    @GetMapping("/deposit-itemizations")
    public List<WwdbVirtualAccountDto.SearchDtlRes> getVirtualAccountDepositItemizations(
        WwdbVirtualAccountDto.SearchReq req
    ) {
        return service.getVirtualAccountDepositItemizations(req);
    }
}
