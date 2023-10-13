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

import com.kyowon.sms.wells.web.product.standard.dto.WpdySeedlingPriceMgtDto;
import com.kyowon.sms.wells.web.product.standard.service.WpdySeedlingPriceMgtService;
import com.kyowon.sms.wells.web.product.zcommon.constants.PdProductWellsConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 모종제품가격 관리 컨트롤러
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@RestController
@Api(tags = "[WPDY] 상품(Wells) - 기준정보관리 - 모종제품가격 관리")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/seedling-price")
@RequiredArgsConstructor
@Validated
public class WpdySeedlingPriceMgtController {

    private final WpdySeedlingPriceMgtService service;

    /**
     * 모종제품가격 관리 목록 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdctPdNm", value = "상품명", paramType = "query", example = "서비스"),
        @ApiImplicitParam(name = "pdctPdCd", value = "상품코드", paramType = "query", example = "WS02"),
        @ApiImplicitParam(name = "apyStrtdt", value = "적용 시작일", paramType = "query", example = "20230421"),
        @ApiImplicitParam(name = "apyEnddt", value = "적용 종료일", paramType = "query", example = "20230431"),
        @ApiImplicitParam(name = "rglrSppMchnTpCd", value = "기기유형", paramType = "query", example = "ABC"),
        @ApiImplicitParam(name = "rglrSppPrcDvCd", value = "가격구분", paramType = "query", example = "01"),
    })
    @ApiOperation(value = "모종제품가격 관리 목록 조회", notes = "모종제품가격 관리 정보 목록을 조회한다.")
    @GetMapping
    public List<WpdySeedlingPriceMgtDto.SearchRes> getSeedlingPrices(
        WpdySeedlingPriceMgtDto.SearchReq dto
    ) {
        return service.getSeedlingPrices(dto);
    }

    /**
     * 모종제품가격 관리 페이징 조회
     * @param dto 검색조건 정보
     * @param pageInfo 페이지 정보
     * @return 검색 결과 목록
     */
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
    @ApiOperation(value = "모종제품가격 관리 페이징 조회", notes = "검색조건을 입력 받아 Paging된 모종제품가격 관리 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<WpdySeedlingPriceMgtDto.SearchRes> getSeedlingPricePages(
        WpdySeedlingPriceMgtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getSeedlingPricePages(dto, pageInfo);
    }

    /**
     * 모종제품가격 관리 수정
     * @param dto 수정내용 정보
     * @return 처리결과
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiOperation(value = "모종제품가격 관리 수정", notes = "수정된 모종제품가격 관리 정보를 반영한다.")
    @PostMapping
    public SaveResponse saveSeedlingPrices(
        @Valid
        @RequestBody
        WpdySeedlingPriceMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveSeedlingPrices(dto))
            .build();
    }

    /**
     * 모종제품가격 관리 삭제
     * @param dtos 삭제 정보
     * @return 처리결과
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiOperation(value = "모종제품가격 관리 삭제")
    @DeleteMapping
    public SaveResponse removeSeedlingPrices(
        @RequestBody
        @NotEmpty
        List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeSeedlingPrices(dtos))
            .build();
    }

    /**
     * 모종제품가격 관리 중복체크
     * @param dtos 비교 대상 정보
     * @return 체크결과
     */
    @ApiOperation(value = "모종제품가격 관리 중복체크")
    @PostMapping("/duplication-check")
    public SaveResponse checkDuplication(
        @RequestBody
        @NotEmpty
        List<WpdySeedlingPriceMgtDto.SeedlingPriceBase> dtos
    ) {
        return SaveResponse.builder()
            .data(service.checkDuplication(dtos))
            .build();
    }
}
