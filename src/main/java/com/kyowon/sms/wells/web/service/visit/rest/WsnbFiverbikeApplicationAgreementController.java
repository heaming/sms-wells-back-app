package com.kyowon.sms.wells.web.service.visit.rest;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFiverbikeApplicationAgreementDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFiverbikeApplicationAgreementDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFiverbikeApplicationAgreementDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbFiverbikeApplicationAgreementService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/fiverbike-application-agreement")
@Api(tags = "[WSNB] 피버바이크 신청 동의서 화면 REST API")
@RequiredArgsConstructor
@Validated
public class WsnbFiverbikeApplicationAgreementController {

    private final WsnbFiverbikeApplicationAgreementService service;

    @ApiOperation(value = "피버바이크 신청 동의서 조회", notes = "이전에 신청한 피버바이크 신청 동의서 내용을 조회한다.")
    @GetMapping
    public SearchRes getFiverbikeApplicationAgreement(
        SearchReq dto
    ) {
        return service.getFiverbikeApplicationAgreement(dto);
    }

    @ApiOperation(value = "피버바이크 신청 동의서 저장", notes = "피버바이크 신청 동의서 내용을 저장한다.")
    @PostMapping
    public SaveResponse createFiverbikeApplicationAgreement(
        @Valid
        @RequestBody
        CreateReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createFiverbikeApplicationAgreement(dto))
            .build();
    }
}
