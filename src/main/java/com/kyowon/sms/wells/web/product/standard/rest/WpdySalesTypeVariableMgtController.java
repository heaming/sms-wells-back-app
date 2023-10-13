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

import com.kyowon.sms.wells.web.product.standard.dto.WpdySalesTypeVariableMgtDto;
import com.kyowon.sms.wells.web.product.standard.service.WpdySalesTypeVariableMgtService;
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
 * 판매유형별 변수 관리 컨트롤러
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@RestController
@Api(tags = "[WPDY] 상품(Wells) - 기준정보관리 - 판매유형별 변수관리")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/variables")
@RequiredArgsConstructor
@Validated
public class WpdySalesTypeVariableMgtController {

    private final WpdySalesTypeVariableMgtService service;

    /**
     * 판매유형별 변수 목록 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", example = "01"),
        @ApiImplicitParam(name = "rgltnVarbNm", value = "변수명", paramType = "query", example = "등록비"),
    })
    @ApiOperation(value = "판매유형별 변수 목록 조회", notes = "판매유형별 변수 정보 목록을 조회한다.")
    @GetMapping
    public List<WpdySalesTypeVariableMgtDto.SearchRes> getSalesTypeVariables(
        WpdySalesTypeVariableMgtDto.SearchReq dto
    ) {
        return service.getSalesTypeVariables(dto);
    }

    /**
     * 판매유형별 변수 페이징 조회
     * @param dto 검색조건 정보
     * @param pageInfo 페이지 정보
     * @return 검색 결과 목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", example = "01"),
        @ApiImplicitParam(name = "rgltnVarbNm", value = "변수명", paramType = "query", example = "등록비"),
    })
    @ApiOperation(value = "판매유형별 변수 페이징 조회", notes = "검색조건을 입력 받아 Paging된 판매유형별 변수 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<WpdySalesTypeVariableMgtDto.SearchRes> getSalesTypeVariablePages(
        WpdySalesTypeVariableMgtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getSalesTypeVariablePages(dto, pageInfo);
    }

    /**
     * 판매유형별 변수 수정
     * @param dto 수정내용 정보
     * @return 처리결과
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiOperation(value = "판매유형별 변수 수정", notes = "수정된 판매유형별 변수 정보를 반영한다.")
    @PostMapping
    public SaveResponse saveSalesTypeVariables(
        @Valid
        @RequestBody
        WpdySalesTypeVariableMgtDto.SaveReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveSalesTypeVariables(dto))
            .build();
    }

    /**
     * 판매유형별 변수 삭제
     * @param dtos 삭제 정보
     * @return 처리결과
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiOperation(value = "판매유형별 변수 삭제")
    @DeleteMapping
    public SaveResponse removeSalesTypeVariables(
        @RequestBody
        @NotEmpty
        List<WpdySalesTypeVariableMgtDto.TypeVariableBase> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeSalesTypeVariables(dtos))
            .build();
    }

    /**
     * 판매유형별 변수 중복체크
     * @param dtos 비교 대상 정보
     * @return 체크결과
     */
    @ApiOperation(value = "판매유형별 변수 중복체크")
    @PostMapping("/duplication-check")
    public SaveResponse checkDuplication(
        @RequestBody
        @NotEmpty
        List<WpdySalesTypeVariableMgtDto.TypeVariableBase> dtos
    ) {
        return SaveResponse.builder()
            .data(service.checkDuplication(dtos))
            .build();
    }
}
