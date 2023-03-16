package com.kyowon.sms.wells.web.fee.calculation.rest;

import com.kyowon.sms.wells.web.fee.calculation.service.WfebHomeMasterGradeService;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.fee.zcommon.constants.CtFeeConst;
import com.kyowon.sms.wells.web.fee.calculation.dto.WfebHomeMasterGradeDto.*;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CtFeeConst.REST_URL_V1 + "/home-master-grades")
@Api(tags = "[WFEB] 홈마스터 등급관리")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WfebHomeMasterGradeController {

    private final WfebHomeMasterGradeService service;

    @ApiOperation(value = "홈마스터 등급관리 목록 조회", notes = "조회조건에 일치하는 홈마스터의 등급 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "emplNm", value = "성명", paramType = "query", required = false),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = false),
    })
    @GetMapping
    public List<SearchRes> getHomeMasterGrades(
        SearchReq dto
    ) {
        return this.service.getHomeMasterGrades(dto);
    }

    @ApiOperation(value = "홈마스터 등급관리 상세 조회", notes = "조회조건에 일치하는 실적년월에 생성된 홈마스터 등급관리 상세 내역을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "실적년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "emplNm", value = "성명", paramType = "query", required = true),
        @ApiImplicitParam(name = "prtnrNo", value = "번호", paramType = "query", required = true),
    })
    @GetMapping("/details")
    public List<SearchDetailRes> getHomeMasterGradeDetails(
        SearchDetailReq dto
    ) {
        return this.service.getHomeMasterGradeDetails(dto);
    }

    @ApiOperation(value = "홈마스터 포인트 저장", notes = "해당 관리년월의 홈마스터 포인트를 저장한다.")
    @PostMapping("/grade")
    public SaveResponse saveHomeMasterGrade(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveHomeMasterGrade(dto))
            .build();
    }

    @ApiOperation(value = "홈마스터 등급 리스트 저장", notes = "해당 관리년월의 홈마스터 등급 리스트를 저장한다.")
    @PostMapping("/grades")
    public SaveResponse saveHomeMasterGrades(
        @RequestBody
        @Valid
        List<SaveReq> info
    ) {
        return SaveResponse.builder().processCount(service.saveHomeMasterGrades(info)).build();
    }

    @ApiOperation(value = "홈마스터 포인트 저장", notes = "해당 관리년월의 홈마스터 포인트를 저장한다.")
    @PostMapping("/point")
    public SaveResponse saveHomeMasterPoint(
        @RequestBody
        @Valid
        SavePointReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveHomeMasterPoint(dto))
            .build();
    }

    @ApiOperation(value = "홈마스터 포인트 리스트 저장", notes = "해당 관리년월의 홈마스터 포인트를 저장한다.")
    @PostMapping("/points")
    public SaveResponse saveHomeMasterPoints(
        @RequestBody
        @Valid
        List<SavePointReq> info
    ) {
        return SaveResponse.builder().processCount(service.saveHomeMasterPoints(info)).build();
    }

    @ApiOperation(value = "홈마스터 등급 이관", notes = "해당 관리년월의 전달 등급을 이번달로 적용한다.")
    @PostMapping("/grade-transfers")
    public SaveResponse saveHomeMasterGradeTransfers(
        @RequestBody
        @Valid
        SearchReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveHomeMasterGradeTransfers(dto))
            .build();
    }

    @ApiOperation(value = "홈마스터 포인트 삭제", notes = "해당 관리년월의 홈마스터 포인트를 삭제한다.")
    @DeleteMapping
    public SaveResponse removeHomeMasterPoint(
        @RequestBody
        @Valid
        RemoveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeHomeMasterPoint(dto))
            .build();
    }

}
