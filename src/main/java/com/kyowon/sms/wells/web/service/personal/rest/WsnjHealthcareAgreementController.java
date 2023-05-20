package com.kyowon.sms.wells.web.service.personal.rest;

import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.SearchReq;
import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.SearchRes;
import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.MergeReq;
import com.kyowon.sms.wells.web.service.personal.service.WsnjHealthcareAgreementService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@Api(tags = "[WSND] 헬스케어 동의서")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/healthcare-agreement")
public class WsnjHealthcareAgreementController {

    private final WsnjHealthcareAgreementService service;

    @ApiOperation(value = "고객 헬스케어 동의서 조회", notes = "고객 헬스케어 동의서 상세조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "customerServiceCode", value = "추가고객서비스코드", paramType = "query", required = true),
    })
    @GetMapping
    public SearchRes getHealthcareAgreement(
        SearchReq dto
    ) {
        return service.getHealthcareAgreement(dto);
    }

    @ApiOperation(value = "고객 헬스케어 동의서 생성/수정", notes = "고객 헬스케어 동의서 생성/수정")
    @PutMapping()
    public SaveResponse editUserBasic(
        @RequestBody
        @Valid
        MergeReq dto
    )
        throws Exception {
        return SaveResponse.builder()
            .processCount(service.mergeHealthcareAgreement(dto))
            .build();
    }
}
