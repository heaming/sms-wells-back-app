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

import com.kyowon.sms.wells.web.product.standard.dto.WpdyWellsAllianceMgtDto;
import com.kyowon.sms.wells.web.product.standard.service.WpdyWellsAllianceMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "[WPDY] 상품(Wells) - 기준정보관리 - 헬스 제휴 관리")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/alliances")
@RequiredArgsConstructor
@Validated
public class WpdyWellsAllianceMgtController {

    private final WpdyWellsAllianceMgtService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "alncmpCd", value = "제휴코드", paramType = "query", example = "ABC"),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", example = "01"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query", example = "서비스"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WS02"),
        @ApiImplicitParam(name = "prdtCateHigh", value = "대분류", paramType = "query", example = "PDC000000000001"),
        @ApiImplicitParam(name = "prdtCateMid", value = "중분류", paramType = "query", example = "PDC000000000012"),
        @ApiImplicitParam(name = "svcStartDt", value = "적용 시작일", paramType = "query", example = "20230421"),
        @ApiImplicitParam(name = "svcEndDt", value = "적용 종료일", paramType = "query", example = "20230431"),
    })
    @ApiOperation(value = "헬스 제휴 조회", notes = "헬스 제휴 정보 목록을 조회한다.")
    @GetMapping
    public List<WpdyWellsAllianceMgtDto.SearchRes> getWellsAlliances(
        WpdyWellsAllianceMgtDto.SearchReq dto
    ) {
        return service.getWellsAlliances(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svcType", value = "서비스 선택 구분", paramType = "query", example = "WS01200001"),
        @ApiImplicitParam(name = "svcValue", value = "서비스 선택 값", paramType = "query", example = "WM01200001"),
        @ApiImplicitParam(name = "prdtType", value = "제품 선택 구분", paramType = "query", example = "WS01200001"),
        @ApiImplicitParam(name = "prdtValue", value = "제품 선택 값", paramType = "query", example = "WM01200001"),
    })
    @ApiOperation(value = "헬스 제휴 페이징 조회", notes = "검색조건을 입력 받아 Paging된 헬스 제휴 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<WpdyWellsAllianceMgtDto.SearchRes> getWellsAlliancePages(
        WpdyWellsAllianceMgtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getWellsAlliancePages(dto, pageInfo);
    }

    @ApiOperation(value = "헬스 제휴 수정", notes = "수정된 헬스 제휴 정보를 반영한다.")
    @PostMapping
    public SaveResponse saveWellsAlliances(
        @Valid
        @RequestBody
        WpdyWellsAllianceMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveWellsAlliances(dto))
            .build();
    }

    @ApiOperation(value = "헬스 제휴 삭제")
    @DeleteMapping
    public SaveResponse removeWellsAlliances(
        @RequestBody
        @NotEmpty
        List<WpdyWellsAllianceMgtDto.AllianceBase> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeWellsAlliances(dtos))
            .build();
    }
}
