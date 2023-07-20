package com.kyowon.sms.wells.web.closing.performance.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaCancellationFeeComputationDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaCancellationFeeComputationResultDvo;
import com.kyowon.sms.wells.web.closing.payment.service.WdcaCancellationFeeComputationService;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformPenaltyDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformPenaltyDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.service.WdccSalesPerformPenaltyService;
import com.kyowon.sms.wells.web.closing.zcommon.constants.DcClosingConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WDCC] 위약금 예상")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping(DcClosingConst.COMMON_URL_V1 + "/sales-perf")
public class WdccSalesPerformPenaltyController {
    private final WdccSalesPerformPenaltyService service;
    private final WdcaCancellationFeeComputationService cancellationFeeService;

    @ApiOperation(value = "위약금 예상 조회", notes = "조회조건에 따른 위약금 예상 조회")

    @GetMapping
    public SearchRes getPerformPenalty(
        SearchReq dto
    ) {
        return service.getPerformPenalty(dto);
    }

    @GetMapping("/estimated-penalty")
    public WdcaCancellationFeeComputationResultDvo getEstimatedPenalty(
        WdcaCancellationFeeComputationDvo dvo
    ) {
        return cancellationFeeService.saveDelinquentDepositRefund(dvo);
    }
}
