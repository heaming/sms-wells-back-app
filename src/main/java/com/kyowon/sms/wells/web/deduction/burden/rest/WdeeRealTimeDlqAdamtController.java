package com.kyowon.sms.wells.web.deduction.burden.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.deduction.burden.dto.WdeeRealTimeDlqAdamtDto;
import com.kyowon.sms.wells.web.deduction.burden.service.WdeeRealTimeDlqAdamtService;
import com.kyowon.sms.wells.web.deduction.zcommon.constants.DeDeductionConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "wells 가산금합계, 가산입금, 잔액")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(DeDeductionConst.REST_URL_V1 + "/bu-ddtn")
public class WdeeRealTimeDlqAdamtController {
    private final WdeeRealTimeDlqAdamtService service;

    @ApiOperation(value = "wells 가산금합계, 가산입금, 잔액", notes = "wells 가산금합계, 가산입금, 잔액 데이터를 조회하고 결과를 반환한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query"),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query"),
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query"),
    })
    @GetMapping("/real-time-dlq-adamts")
    public List<WdeeRealTimeDlqAdamtDto.SearchRes> getRealTimeDelinquentAdamt(
        @Valid
        WdeeRealTimeDlqAdamtDto.SearchReq dto
    ) {
        return service.getRealTimeDelinquentAdamt(dto);
    }
}
