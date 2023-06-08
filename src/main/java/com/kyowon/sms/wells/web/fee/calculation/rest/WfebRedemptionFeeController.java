package com.kyowon.sms.wells.web.fee.calculation.rest;

import com.kyowon.sms.wells.web.fee.calculation.service.WfebRedemptionFeeService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "[WFEB] 되물림 계산")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/fee/redemption-calculation")
@Slf4j
public class WfebRedemptionFeeController {

    private final WfebRedemptionFeeService redemptionFeeService;

    @ApiOperation(value = "연체되물림 생성", notes = "기준년월, 계약실적생성구분코드를 파라메터로 연체되물림 생성")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "path", required = true),
        @ApiImplicitParam(name = "cntrPerfCrtDvCd", value = "계약실적생성구분코드", paramType = "path", required = true),
    })
    @PostMapping("/delinquent-redemption-fees/{baseYm}-{cntrPerfCrtDvCd}")
    public SaveResponse saveDlqRedemptionOfFees(@PathVariable String baseYm, @PathVariable String cntrPerfCrtDvCd) {
        return SaveResponse.builder().processCount(redemptionFeeService.saveDlqRedemptionOfFees(baseYm, cntrPerfCrtDvCd)).build();
    }

}
