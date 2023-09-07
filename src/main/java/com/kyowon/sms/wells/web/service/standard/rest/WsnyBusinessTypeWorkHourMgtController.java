package com.kyowon.sms.wells.web.service.standard.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.standard.service.WsnyBusinessTypeWorkHourMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[WSNY] 업무유형별 작업시간관리 REST API")
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/business-type-work-hour")

public class WsnyBusinessTypeWorkHourMgtController {

    private final WsnyBusinessTypeWorkHourMgtService service;

    @ApiOperation(value = "업무유형별 작업시간관리 목록 조회", notes = "조회조건에 일치하는 업무유형별 작업시간관리 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svDvCd", value = "서비스유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "업무유형", paramType = "query"),
        @ApiImplicitParam(name = "apyDt", value = "적용일자", paramType = "query"),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query"),
    })
    @GetMapping
    public List<SearchRes> getBusinessTypeWorkHourPages(
        @Valid
        SearchReq dto
    ) {
        return service.getBusinessTypeWorkHour(dto);
    }

    @ApiOperation(value = "업무유형별 작업시간관리 목록 조회 엑셀다운로드", notes = "조회조건에 일치하는 업무유형별 작업시간관리 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svDvCd", value = "서비스유형", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹", paramType = "query", required = true),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query"),
        @ApiImplicitParam(name = "svBizDclsfCd", value = "업무유형", paramType = "query"),
        @ApiImplicitParam(name = "apyDt", value = "적용일자", paramType = "query"),
        @ApiImplicitParam(name = "findGb", value = "조회구분", paramType = "query"),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> excelDownload(SearchReq dto) {

        return service.getBusinessTypeWorkHour(dto);
    }

    @ApiOperation(value = "상품별업무유형별작업그룹내역 저장", notes = "상품별업무유형별작업그룹내역 정보를 저장한다.")
    @PostMapping
    public SaveResponse saveBusinessTypeWorkHour(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {

        return SaveResponse.builder()
            .processCount(service.saveSvpdBpdTpWkGrpIz(dtos))
            .build();
    }

}
