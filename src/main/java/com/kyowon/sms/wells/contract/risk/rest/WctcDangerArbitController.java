package com.kyowon.sms.wells.contract.risk.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.contract.risk.Service.WctcDangerArbitService;
import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto.SearchReq;
import com.kyowon.sms.wells.contract.risk.dto.WctcDangerArbitDto.SearchRes;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WCTC] wells 비정도영업 조치사항 조회")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(CtContractConst.REST_URL_V1 + "/risk-audits")
public class WctcDangerArbitController {
    private final WctcDangerArbitService service;

    @ApiOperation(value = "비정도영업 조치사항 조회", notes = "조회조건에 따른 비정도영업 조치사항 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "searchGubun", value = "조회구분", paramType = "query"),
        @ApiImplicitParam(name = "startDate", value = "조회시작날짜", paramType = "query"),
        @ApiImplicitParam(name = "endDate", value = "조회마지막날짜", paramType = "query"),
        @ApiImplicitParam(name = "startMonth", value = "조회시작월", paramType = "query"),
        @ApiImplicitParam(name = "endMonth", value = "조회마지막월", paramType = "query"),
        @ApiImplicitParam(name = "generalDivision", value = "총괄단", paramType = "query"),
        @ApiImplicitParam(name = "regionalGroup", value = "지역단", paramType = "query"),
        @ApiImplicitParam(name = "branchOffice", value = "지점", paramType = "query"),
        @ApiImplicitParam(name = "employeeNo", value = "사번", paramType = "query"),
    })
    @GetMapping("/irg-bzns-arbit-artcs")
    public List<SearchRes> getIrgBznsArbitArtc(
        @Valid
        SearchReq dto
    ) {
        return service.getIrgBznsArbitArtc(dto);
    }

    @ApiOperation(value = "비정도 영업 조치 사항 삭제", notes = "비정도 영업 조치 사항 삭제")
    @DeleteMapping("/irg-bzns-arbit-artcs")
    public SaveResponse removeIrgBznsArbitArtc(
        @RequestParam
        @NotEmpty
        List<String> dangChkIds
    ) {
        return SaveResponse.builder().processCount(service.removeIrgBznsArbitArtc(dangChkIds)).build();
    }
}
