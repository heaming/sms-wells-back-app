package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRoutineBsPsicAssignStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRoutineBsPsicAssignStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRoutineBsPsicAssignStateService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSNC] 정기B/S담당자배정 현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/routine-bs-psic-assign-state")
@Validated
public class WsncRoutineBsPsicAssignStateController {

    private final WsncRoutineBsPsicAssignStateService service;

    @ApiOperation(value = "정기B/S담당자배정 현황 조회", notes = "정기B/S담당자배정 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "배정기준일", paramType = "query", example = "202305"),
        @ApiImplicitParam(name = "locaraMngtDvCd", value = "관리구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "localGroupCd", value = "지역단", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "partnerNo", value = "엔지니어", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "pdGrpDtlCd", value = "상품그룹상세코드", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "promStDt", value = "약속일자시작", paramType = "query", example = "20230501"),
        @ApiImplicitParam(name = "promEndDt", value = "약속일자종료", paramType = "query", example = "20230531"),
        @ApiImplicitParam(name = "svTpCd", value = "서비스유형", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "wkDvCd", value = "작업구분", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "prgsStatCd", value = "진행구분", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "rgsnExcdYn", value = "퇴사자제외", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "vacManaYn", value = "가상매니저", paramType = "query", example = "ALL"),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getRoutineBsPsicAssignStates(
        @Valid
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return service.getRoutineBsPsicAssignStates(dto, pageInfo);
    }

    @ApiOperation(value = "정기B/S담당자배정 현황 조회 (엑셀 다운로드)", notes = "정기B/S담당자배정 현황을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "배정기준일", paramType = "query", example = "202305"),
        @ApiImplicitParam(name = "locaraMngtDvCd", value = "관리구분", paramType = "query", example = "1"),
        @ApiImplicitParam(name = "localGroupCd", value = "지역단", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "partnerNo", value = "엔지니어", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "pdGrpDtlCd", value = "상품그룹상세코드", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "promStDt", value = "약속일자시작", paramType = "query", example = "20230501"),
        @ApiImplicitParam(name = "promEndDt", value = "약속일자종료", paramType = "query", example = "20230531"),
        @ApiImplicitParam(name = "svTpCd", value = "서비스유형", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "wkDvCd", value = "작업구분", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "prgsStatCd", value = "진행구분", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "rgsnExcdYn", value = "퇴사자제외", paramType = "query", example = "ALL"),
        @ApiImplicitParam(name = "vacManaYn", value = "가상매니저", paramType = "query", example = "ALL"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getRoutineBsPsicAssignStatesForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getRoutineBsPsicAssignStatesForExcelDownload(dto);
    }

}
