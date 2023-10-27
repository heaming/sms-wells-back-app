package com.kyowon.sms.wells.web.fee.aggregate.rest;

import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaRedemptionPerfService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[WFEA] Wells 사업부 되물림 실적 생성")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/redemptions/aggregate-performance")
public class WfeaRedemptionPerfController {

    private final WfeaRedemptionPerfService service;

    @ApiOperation(value = "Wells 사업부 되물림 실적 생성", notes = "EDU사업부 영업부, 신채널 되물림 실적 생성")
    @GetMapping("/{baseYm}-{ogTpCd}-{perfAgrgCrtDvCd}-{cntrPerfCrtDvCd}")
    public void aggregateRedemptionOfFeePerformance(@PathVariable String baseYm, @PathVariable String ogTpCd, @PathVariable String perfAgrgCrtDvCd, @PathVariable String cntrPerfCrtDvCd) {
        service.aggregateRedemptionOfFeePerformance(baseYm, ogTpCd, perfAgrgCrtDvCd, cntrPerfCrtDvCd);
    }
}
