package com.kyowon.sms.wells.web.fee.calculation.rest;

import com.kyowon.sms.wells.web.fee.calculation.service.WfebAgainDisbursementFeeService;
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

@Api(tags = "[WFEB] 재지급 계산")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/again-disbursement-calculation")
@Slf4j
public class WfebAgainDisbursementFeeController {

    private final WfebAgainDisbursementFeeService againDisbursementFeeService;

    @ApiOperation(value = "재지급 생성(집계 + 취소 + 연체)", notes = "기준년월, 계약실적생성구분코드를 파라메터로 재지급 생성")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "path", required = true),
            @ApiImplicitParam(name = "cntrPerfCrtDvCd", value = "계약실적생성구분코드", paramType = "path", required = true),
    })
    @PostMapping("/all-again-disbursement-fees/{baseYm}-{cntrPerfCrtDvCd}")
    public void saveAgainDisbursementOfFees(@PathVariable String baseYm, @PathVariable String cntrPerfCrtDvCd) {
        againDisbursementFeeService.saveAgainDisbursementOfFees(baseYm, cntrPerfCrtDvCd);
    }

    @ApiOperation(value = "연체재지급 생성", notes = "기준년월, 계약실적생성구분코드를 파라메터로 연체재지급 처리")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "baseYm", value = "기준년월", paramType = "path", required = true),
            @ApiImplicitParam(name = "cntrPerfCrtDvCd", value = "계약실적생성구분코드", paramType = "path", required = true),
    })
    @PostMapping("/delinquent-again-disbursement-fees/{baseYm}-{cntrPerfCrtDvCd}")
    public SaveResponse saveDlqAgainDisbursementOfFees(@PathVariable String baseYm, @PathVariable String cntrPerfCrtDvCd) {
        return SaveResponse.builder().processCount(againDisbursementFeeService.saveDlqAgainDisbursementOfFees(baseYm, cntrPerfCrtDvCd)).build();
    }

}
