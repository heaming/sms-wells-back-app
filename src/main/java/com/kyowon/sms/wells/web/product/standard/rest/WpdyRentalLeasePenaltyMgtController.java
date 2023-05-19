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

import com.kyowon.sms.wells.web.product.standard.dto.WpdyRentalLeasePenaltyMgtDto;
import com.kyowon.sms.wells.web.product.standard.service.WpdyRentalLeasePenaltyMgtService;
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
@Api(tags = "[WPDY] 상품(Wells) - 기준정보관리 - 렌탈/리스 위약금 관리")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/cancel-charges")
@RequiredArgsConstructor
@Validated
public class WpdyRentalLeasePenaltyMgtController {

    private final WpdyRentalLeasePenaltyMgtService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prdtCateHigh", value = "대분류", paramType = "query", example = "PDC000000000001"),
        @ApiImplicitParam(name = "prdtCateMid", value = "중분류", paramType = "query", example = "PDC000000000012"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query", example = "서비스"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WS02"),
        @ApiImplicitParam(name = "svcStartDt", value = "적용 시작일", paramType = "query", example = "20230421"),
        @ApiImplicitParam(name = "svcEndDt", value = "적용 종료일", paramType = "query", example = "20230431"),
    })
    @ApiOperation(value = "렌탈/리스 위약금 조회", notes = "렌탈/리스 위약금 정보 목록을 조회한다.")
    @GetMapping
    public List<WpdyRentalLeasePenaltyMgtDto.SearchRes> getRentalLeasePenalties(
        WpdyRentalLeasePenaltyMgtDto.SearchReq dto
    ) {
        return service.getRentalLeasePenalties(dto);
    }

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "prdtCateHigh", value = "대분류", paramType = "query", example = "PDC000000000001"),
        @ApiImplicitParam(name = "prdtCateMid", value = "중분류", paramType = "query", example = "PDC000000000012"),
        @ApiImplicitParam(name = "pdNm", value = "상품명", paramType = "query", example = "서비스"),
        @ApiImplicitParam(name = "pdCd", value = "상품코드", paramType = "query", example = "WS02"),
        @ApiImplicitParam(name = "svcStartDt", value = "적용 시작일", paramType = "query", example = "20230421"),
        @ApiImplicitParam(name = "svcEndDt", value = "적용 종료일", paramType = "query", example = "20230431"),
    })
    @ApiOperation(value = "렌탈/리스 위약금 페이징 조회", notes = "검색조건을 입력 받아 Paging된 렌탈/리스 위약금 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<WpdyRentalLeasePenaltyMgtDto.SearchRes> getRentalLeasePenaltyPages(
        WpdyRentalLeasePenaltyMgtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getRentalLeasePenaltyPages(dto, pageInfo);
    }

    @ApiOperation(value = "렌탈/리스 위약금 수정", notes = "수정된 렌탈/리스 위약금 정보를 반영한다.")
    @PostMapping
    public SaveResponse saveRentalLeasePenalties(
        @Valid
        @RequestBody
        WpdyRentalLeasePenaltyMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveRentalLeasePenalties(dto))
            .build();
    }

    @ApiOperation(value = "렌탈/리스 위약금 삭제")
    @DeleteMapping
    public SaveResponse removeRentalLeasePenalties(
        @RequestBody
        @NotEmpty
        List<WpdyRentalLeasePenaltyMgtDto.CancelChargeBase> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeRentalLeasePenalties(dtos))
            .build();
    }
}
