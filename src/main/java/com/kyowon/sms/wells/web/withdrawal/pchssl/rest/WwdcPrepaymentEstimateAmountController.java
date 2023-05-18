package com.kyowon.sms.wells.web.withdrawal.pchssl.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcPrepaymentEstimateDto.SearchPrepaymentEstimateReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcPrepaymentEstimateDto.SearchPrepaymentEstimateRes;
import com.kyowon.sms.wells.web.withdrawal.pchssl.service.WwdcPrepaymentEstimateAmountService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDC] Wells 선납예상금액 조회  ")
@Validated
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(WdWithdrawalConst.REST_URL_PCHSSL + "/prepayment-et-amt")
public class WwdcPrepaymentEstimateAmountController {
    private final WwdcPrepaymentEstimateAmountService service;

    @ApiOperation(value = "[WDC] 선납예상금액조회", notes = "선납 예상 금액을 조회합니다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약상세번호", paramType = "query"),
        @ApiImplicitParam(name = "prmMcn", value = "선납개월수", paramType = "query"),
    })
    @GetMapping
    public List<SearchPrepaymentEstimateRes> getPrepaymentEstimateAmount(
        @Valid
        SearchPrepaymentEstimateReq dto
    ) {
        return service.getPrepaymentEstimateAmount(dto);
    }
}