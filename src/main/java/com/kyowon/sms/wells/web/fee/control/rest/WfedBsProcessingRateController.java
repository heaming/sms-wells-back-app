package com.kyowon.sms.wells.web.fee.control.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.control.dto.WfedBsProcessingRateDto;
import com.kyowon.sms.wells.web.fee.control.service.WfedBsProcessingRateService;
import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/bs-processing-rate")
@Api(tags = "[WFED] BS처리율 조정 관리")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfedBsProcessingRateController {

    private final WfedBsProcessingRateService service;

    @ApiOperation(value = "BS처리율 목록 조회", notes = "조회조건에 일치하는 실적년월에 생성된 BS처리율 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "perfYm", value = "실적년월", paramType = "query", example = "202301", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", example = "123456"),
    })
    @GetMapping
    public List<WfedBsProcessingRateDto.SearchRes> getBsProcessingRates(
        WfedBsProcessingRateDto.SearchReq dto
    ) {
        return service.getBsProcessingRates(dto);
    }

    @ApiOperation(value = "BS처리율 저장", notes = "BS처리율을 변경 정보로 저장한다.")
    @PutMapping
    public SaveResponse saveBsProcessingRates(
        @Valid
        @RequestBody
        List<WfedBsProcessingRateDto.SaveReq> dtos
    ) {
        return SaveResponse.builder().processCount(service.saveBsProcessingRates(dtos)).build();
    }

}
