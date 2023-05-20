package com.kyowon.sms.wells.web.service.orgcode.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAlwncMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAlwncMgtDto.SearchBaseRes;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAlwncMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.service.WsndRegionLevelAlwncMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/region-level-allowances")
@Api(tags = "[WSND] 급지 수당 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsndRegionLevelAlwncMgtController {

    private final WsndRegionLevelAlwncMgtService service;

    @ApiOperation(value = "급지 기본 정보 조회", notes = "이동급지, 업무급지 기본 정보를 조회한다.")
    @GetMapping("/base-information")
    public SearchBaseRes getAllowanceBases() {
        return this.service.getAllowanceBases();
    }

    @ApiOperation(value = "급지 수당 조회", notes = "급지 정보에 일치하는 엔지니어 수당 정보를 조회한다.")
    @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", example = "20221214", required = true)
    @GetMapping
    public SearchRes getAllowances(
        @NotEmpty
        @RequestParam
        String applyDate
    ) {
        return this.service.getAllowances(applyDate);
    }

    @ApiOperation(value = "급지 수당 저장", notes = "급지 정보에 일치하는 엔지니어 수당 정보를 저장한다.")
    @PostMapping
    public SaveResponse saveAllowances(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.saveAllowances(dtos))
            .build();
    }

}
