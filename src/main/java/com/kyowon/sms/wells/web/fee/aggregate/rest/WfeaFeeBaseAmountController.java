package com.kyowon.sms.wells.web.fee.aggregate.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaFeeBaseAmountDto.*;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaFeeBaseAmountService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;

@Api(tags = "[WFEA]수수료 기준금액 체크리스트")
@RequiredArgsConstructor
@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/fee-base-amounts")
@Slf4j
public class WfeaFeeBaseAmountController {
    private final WfeaFeeBaseAmountService service;

    @ApiOperation(value = "수수료 기준금액 체크리스트 목록 조회", notes = "조회조건에 따른 수수료 기준금액 체크리스트 목록 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ogtp", value = "조직유형", paramType = "query", required = true),
    })

    @GetMapping()
    public List<SearchRes> getFeeBaseAmounts(
        @Valid
        SearchReq dto
    ) {
        return this.service.getFeeBaseAmounts(dto);
    }

}
