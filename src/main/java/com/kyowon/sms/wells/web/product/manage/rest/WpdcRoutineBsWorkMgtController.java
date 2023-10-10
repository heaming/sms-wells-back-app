package com.kyowon.sms.wells.web.product.manage.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.product.manage.dto.WpdcRoutineBsWorkMgtDto;
import com.kyowon.sms.wells.web.product.manage.service.WpdcRoutineBsWorkMgtService;
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
 * 서비스 상품 - B/S 투입관리 컨트롤러
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-10-10
 */
@RestController
@Api(tags = "[WPDC] 상품(Wells) - 상품운영관리 - 방문서비스(필터교체관리)")
@RequestMapping(value = PdProductWellsConst.REST_URL_V1 + "/bs-works")
@RequiredArgsConstructor
@Validated
public class WpdcRoutineBsWorkMgtController {

    private final WpdcRoutineBsWorkMgtService service;

    /**
     * 정기 B/S 투입 방문 작업 기준 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svPdCd", value = "서비스 상품코드", paramType = "query", example = "WS01200001"),
        @ApiImplicitParam(name = "pdctPdCd", value = "제품코드", paramType = "query", example = "WM01200001"),
    })
    @ApiOperation(value = "정기 B/S 투입 방문 작업 기준 조회", notes = "정기 B/S 투입 기준 정보 목록을 조회한다.")
    @GetMapping("/standards")
    public List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkBaseRes> getRoutineBsWorkStandards(
        WpdcRoutineBsWorkMgtDto.SearchReq dto
    ) {
        return service.getRoutineBsWorkStandards(dto);
    }

    /**
     * 정기 B/S 투입 방문 작업 기준 불러오기 페이징 조회
     * @param dto 검색조건 정보
     * @param pageInfo 페이지 정보
     * @return 검색 결과 목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svPdCd", value = "서비스 상품코드", paramType = "query", example = "WS01200001"),
        @ApiImplicitParam(name = "pdctPdCd", value = "제품코드", paramType = "query", example = "WM01200001"),
    })
    @ApiOperation(value = "정기 B/S 투입 방문 작업 기준 불러오기 페이징 조회", notes = "검색조건을 입력 받아 Paging된 정기 B/S 투입 기준 목록을 조회한다.")
    @GetMapping("/standards/paging")
    public PagingResult<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkBaseRes> getRoutineBsWorkStandards(
        WpdcRoutineBsWorkMgtDto.SearchStdBaseReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getRoutineBsWorkStandardPages(dto, pageInfo);
    }

    /**
     * 정기 B/S 투입 방문 작업 기준 목록 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svPdCd", value = "서비스 상품코드", paramType = "query", example = "WS01200001"),
        @ApiImplicitParam(name = "pdctPdCd", value = "제품코드", paramType = "query", example = "WM01200001"),
    })
    @ApiOperation(value = "정기 B/S 투입 방문 작업 기준 조회", notes = "정기 B/S 투입 방문 작업 기준 정보 목록을 조회한다.")
    @GetMapping("/tasks")
    public List<WpdcRoutineBsWorkMgtDto.SearchRoutineBsWorkDetailRes> getRoutineBsWorkTasks(
        WpdcRoutineBsWorkMgtDto.SearchReq dto
    ) {
        return service.getRoutineBsWorkTasks(dto);
    }

    /**
     * 정기 B/S 투입 방문 작업 기준 수정
     * @param dto 수정내용 정보
     * @return 수정 결과
     * @throws Exception 미존재 시 Exception 처리
     */
    @ApiOperation(value = "정기 B/S 투입 방문 작업 기준 수정", notes = "수정된 정기 B/S 투입 기준/상세 정보를 반영한다.")
    @PutMapping
    public SaveResponse editBsWork(
        @Valid
        @RequestBody
        WpdcRoutineBsWorkMgtDto.EditReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveRoutineBsWorks(dto))
            .build();
    }

    /**
     * 정기 B/S 투입 상세 수정
     * @param dto 수정내용 정보
     * @return 처리결과
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiOperation(value = "정기 B/S 투입 상세 수정", notes = "수정된 정기 B/S 투입 상세 정보를 반영한다.")
    @PutMapping("/details")
    public SaveResponse editBsWorkDetail(
        @Valid
        @RequestBody
        WpdcRoutineBsWorkMgtDto.EditDetailReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveRoutineBsWorkDetails(dto))
            .build();
    }

    /**
     * 정기 B/S 투입 상세 삭제
     * @param dtos B/S 투입 삭제 정보
     * @return 처리결과
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiOperation(value = "정기 B/S 투입 상세 삭제")
    @DeleteMapping("/details")
    public SaveResponse removeBsWorkDetail(
        @RequestBody
        @NotEmpty
        List<WpdcRoutineBsWorkMgtDto.RoutineBsWorkDetail> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeRoutineBsWorkDetails(dtos))
            .build();
    }

    /**
     * 정기 B/S 투입 방문 작업 기준 조회
     * @param dto 검색조건 정보
     * @return 검색 결과 목록
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "svPdCd", value = "서비스 상품코드", paramType = "query", example = "WS01200001"),
        @ApiImplicitParam(name = "pdctPdCd", value = "제품코드", paramType = "query", example = "WM01200001"),
        @ApiImplicitParam(name = "partPdCd", value = "부품코드", paramType = "query", example = "WM07104689"),
    })
    @ApiOperation(value = "정기 B/S 투입 방문 작업 기준 조회", notes = "정기 B/S 투입 기준 정보 목록을 조회한다.")
    @GetMapping("/life-filters")
    public List<WpdcRoutineBsWorkMgtDto.SearchLifeCustomFiltersRes> getLifeCustomFilters(
        WpdcRoutineBsWorkMgtDto.SearchReq dto
    ) {
        return service.getLifeCustomFilters(dto);
    }

    /**
     * 생활맞춤형필터 수정
     * @param dto 수정내용 정보
     * @return 처리결과
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiOperation(value = "생활맞춤형필터 수정", notes = "수정된 생활맞춤형필터 정보를 반영한다.")
    @PutMapping("/life-filters")
    public SaveResponse editLifeCustomFilters(
        @Valid
        @RequestBody
        WpdcRoutineBsWorkMgtDto.EditLifeFilterReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveLifeFilters(dto))
            .build();
    }

    /**
     * 생활맞춤형필터 삭제
     * @param dtos 삭제 정보
     * @return 처리결과
     * @throws Exception 미처리 시 Exception 처리
     */
    @ApiOperation(value = "생활맞춤형필터 삭제")
    @DeleteMapping("/life-filters")
    public SaveResponse removeLifeCustomFilters(
        @RequestBody
        @NotEmpty
        List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeLifeFilters(dtos))
            .build();
    }

    /**
     * 생활맞춤형필터 관리 중복체크
     * @param dtos 비교 대상 정보
     * @return 체크결과
     */
    @ApiOperation(value = "생활맞춤형필터 관리 중복체크")
    @PostMapping("/life-filters/duplication-check")
    public SaveResponse checkLifeFilterDuplication(
        @RequestBody
        @NotEmpty
        List<WpdcRoutineBsWorkMgtDto.LifeCustomFilterBase> dtos
    ) {
        return SaveResponse.builder()
            .data(service.checkLifeFilterDuplication(dtos))
            .build();
    }
}
