package com.kyowon.sms.wells.web.fee.aggregate.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaEgerAllowanceDto;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaEgerAllowanceService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/eger-allowances")
@Api(tags = "[WFEA] 엔지니어 실적 집계")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfeaEgerAllowanceController {

    private final WfeaEgerAllowanceService service;

    @ApiOperation(value = "엔지니어 실적 집계", notes = "실적년월의 엔지니어 실적을 집계한다.")
    @PostMapping("/aggregates")
    public String saveEgerPerformances(
        @Valid
        @RequestBody
        WfeaEgerAllowanceDto.SaveReq dto
    ) throws Exception {
        return service.saveEgerPerformances(dto);
    }

}
