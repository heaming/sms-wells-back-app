package com.kyowon.sms.wells.web.fee.aggregate.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaOgNetOrderDto;
import com.kyowon.sms.wells.web.fee.aggregate.service.WfeaOgNetOrderService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/og-netorders")
@Api(tags = "[WFEA] 조직별 실적 집계")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfeaOgNetOrderController {

    private final WfeaOgNetOrderService service;

    @ApiOperation(value = "조직별 실적 집계", notes = "실적년월의 실적을 조직별로 집계한다.")
    @PostMapping("/aggregates")
    public SaveResponse saveOrganizationAggregates(
        @RequestBody
        @Valid
        WfeaOgNetOrderDto.SaveOgNetOrderReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.saveOrganizationAggregates(dto))
            .build();
    }

    @ApiOperation(value = "BS 실적 집계", notes = "실적년월의 BS실적을 집계한다.")
    @PostMapping("/bs-aggregates")
    public SaveResponse saveBsPerformances(
        @RequestBody
        @Valid
        WfeaOgNetOrderDto.SaveBsReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.saveBsPerformances(dto))
            .build();
    }

    @ApiOperation(value = "조직별 실적 확정", notes = "실적년월의 조직별 실적을 확정한다.")
    @PutMapping
    public SaveResponse editOrganizationAggregates(
        @RequestBody
        @Valid
        WfeaOgNetOrderDto.SaveOgNetOrderReq dto
    ) {
        return SaveResponse.builder()
            .processCount(service.editOrganizationAggregates(dto))
            .build();
    }

}
