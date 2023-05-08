package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositRefundItemizationDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbDepositRefundItemizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbVirtualAccountDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbVirtualAccountService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[입금관리 - 개별수납 - 서비스] 월별입금/환불금액조회")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/deposit-refund")
public class WwdbDepositRefundItemizationController {

    private final WwdbDepositRefundItemizationService service;

    @ApiOperation(value = "월별입금/환불금액조회(W-WD-S-0007)", notes = "월별입금/환불금액조회")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "cstNo", value = "고객번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfY", value = "실적년", paramType = "query", required = true),
        @ApiImplicitParam(name = "perfM", value = "실적월", paramType = "query", required = true),
    })
    @GetMapping
    public List<WwdbDepositRefundItemizationDto.SearchRes> getDepositRefundItemizations(
        WwdbDepositRefundItemizationDto.SearchReq req
    ) {
        return service.getDepositRefundItemizations(req);
    }
}
