package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.FindReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.SaveReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstLctDtlMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIstLctDtlMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/installation-location-details")
@Api(tags = "[WSNB] 설치위치상세관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbIstLctDtlMngtController {

    private final WsnbIstLctDtlMngtService service;

    @ApiOperation(value = "설치위치상세관리 조회", notes = "설치위치상세관리를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getIstLocationDetailPages(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getIstLocationDetailPages(dto, pageInfo);
    }

    @ApiOperation(value = "설치위치상세관리 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 설치위치상세관리 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getIstLocationDetailExcelDownload(SearchReq dto) {
        return service.getIstLocationDetailExcelDownload(dto);
    }

    @ApiOperation(value = "설치위치상세관리 저장", notes = "설치위치상세관리를 저장한다.")
    @PostMapping
    public SaveResponse createIstLocationDetails(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createIstLocationDetails(dtos))
            .build();
    }

    @ApiOperation(value = "설치위치상세관리 저장 프로시저", notes = "설치위치상세관리 초기화값을 저장한다.")
    @PostMapping("/initialize")
    public SaveResponse createInitializeIstLocationDetails(
        @Valid
        @RequestBody
        @NotEmpty
        List<FindReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createInitializeIstLocationDetails(dtos))
            .build();
    }

}
