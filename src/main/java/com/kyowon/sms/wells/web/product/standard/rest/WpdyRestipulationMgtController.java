package com.kyowon.sms.wells.web.product.standard.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.DuplicationRes;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SaveReq;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SearchReq;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SearchRes;
import com.kyowon.sms.wells.web.product.standard.service.WpdyRestipulationMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/*
    PDA 분류체계관리 category
    PDB 표준속성관리 property
    PDC 상품운영관리 manage
    PDY 기준정보관리 standard
*/
@RestController
@Api(tags = "[WPDY] 상품 >> 기본정보성관리 >> 재약정 기본정보 관리")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/restipulations")
@RequiredArgsConstructor
@Validated
public class WpdyRestipulationMgtController {

    private final WpdyRestipulationMgtService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", required = false, example = "WM2021030500001"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query", required = false, example = "정수기"),
        @ApiImplicitParam(name = "startDate", value = "적용기간 시작일", paramType = "query", required = false, example = "2023-01-01"),
        @ApiImplicitParam(name = "endDate", value = "적용기간 종료일", paramType = "query", required = false, example = "2023-12-31"),
    })
    @ApiOperation(value = "재약정 목록 조회", notes = "검색조건을 받아 조건에 맞는 재약정 목록을 조회한다.")
    @GetMapping
    public PagingResult<SearchRes> getRestipulationPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getRestipulationPages(dto, pageInfo);
    }

    @ApiOperation(value = "재약정 목록 엑셀다운로드", notes = "검색조건을 입력 받아 엑셀다운로드용 재약정 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getRestipulationsForExcelDownload(SearchReq dto) {
        return service.getRestipulationsForExcelDownload(dto);
    }

    @ApiOperation(value = "PK 중복체크", notes = "PK 중복체크")
    @PostMapping("/duplication-check")
    public DuplicationRes checkDuplicationByPk(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return service.checkDuplicationByPk(dtos);
    }

    @ApiOperation(value = "재약정 목록 저장", notes = "CUD 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @PostMapping
    public SaveResponse saveRestipulations(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveRestipulations(dtos))
            .build();
    }

    @ApiOperation(value = "재약정 목록 삭제", notes = "재약정 PK를 배열로 받은 후 일괄 삭제한다.")
    @DeleteMapping
    public SaveResponse removeRestipulations(
        @RequestBody
        @Valid
        List<RemoveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeRestipulations(dtos))
            .build();
    }

}
