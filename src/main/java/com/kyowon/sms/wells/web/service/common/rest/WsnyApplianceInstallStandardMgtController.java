package com.kyowon.sms.wells.web.service.common.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.common.dto.WsnyApplianceInstallStandardMgtDto;
import com.kyowon.sms.wells.web.service.common.service.WsnyApplianceInstallStandardMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/install-standard")
@Api(tags = "[WSNY] 환경가전 설치기준 관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnyApplianceInstallStandardMgtController {

    private final WsnyApplianceInstallStandardMgtService service;

    @ApiOperation(value = "환경가전 설치기준 조회", notes = "조회조건에 일치하는 환경가전 설치기준 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "dgr1ClsfCd", value = "1차 분류", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "dgr2ClsfCd", value = "2차 분류", paramType = "query", example = "11")
    })
    @GetMapping
    public WsnyApplianceInstallStandardMgtDto.SearchRes getApplianceInstallStandardSearch(
        WsnyApplianceInstallStandardMgtDto.SearchReq installStandardReq
    ) {
        return service.getApplianceInstallStandard(installStandardReq);
    }

    @ApiOperation(value = "환경가전 설치기준 저장", notes = "입력정보에 따른 환경가전 설치기준 저장")
    @PostMapping
    public SaveResponse saveInstallStandard(
        @RequestBody
        @Valid
        List<WsnyApplianceInstallStandardMgtDto.SaveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveApplianceInstallStandard(dto))
            .build();
    }
}
