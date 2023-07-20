package com.kyowon.sms.wells.web.service.visit.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.FindProductRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.service.WsnbInstallLocationMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/installation-locations")
@Api(tags = "[WSNB] 설치위치상세관리 REST API")
@RequiredArgsConstructor
@Validated
@Slf4j
public class WsnbInstallLocationMgtController {

    private final WsnbInstallLocationMgtService service;

    @ApiOperation(value = "설치위치상세관리 조회", notes = "설치위치상세관리를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getInstallLocationPages(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getInstallLocationPages(dto, pageInfo);
    }

    @ApiOperation(value = "설치위치상세관리 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 설치위치상세관리 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getInstallLocationPagesExcelDownload(SearchReq dto) {
        return service.getInstallLocationPagesExcelDownload(dto);
    }

    @ApiOperation(value = "설치위치상세관리 저장", notes = "설치위치상세관리를 저장한다.")
    @PostMapping
    public SaveResponse createInstallLocations(
        @Valid
        @RequestBody
        @NotEmpty
        List<CreateReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createInstallLocations(dtos))
            .build();
    }

    @ApiOperation(value = "설치위치상세관리 저장 프로시저", notes = "설치위치상세관리 초기화값을 저장한다.")
    @PostMapping("/initialize")
    public SaveResponse createInitializeInstallLocations(
        @Valid
        @RequestBody
        @NotEmpty
        List<CreateReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createInitializeInstallLocations(dtos))
            .build();
    }

    @ApiOperation(value = "상품 조회", notes = "상품 리스르를 조회한다.")
    @GetMapping("/products")
    public List<FindProductRes> getProducts() {
        return service.getProducts();
    }

}
