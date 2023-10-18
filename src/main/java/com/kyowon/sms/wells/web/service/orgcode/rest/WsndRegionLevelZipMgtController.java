package com.kyowon.sms.wells.web.service.orgcode.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.EditReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.SearchExcelRes;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.service.WsndRegionLevelZipMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
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
 * W-SV-U-0145M01 급지 우편번호 관리
 * </pre>
 *
 * @author hyewon.kim
 * @since 2022.12.06
 */
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/region-level-zips")
@Api(tags = "[WSND] 급지 우편번호 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsndRegionLevelZipMgtController {

    private final WsndRegionLevelZipMgtService service;

    /**
     * 급지 우편번호 조회
     * @param dto 조회조건
     * @param pageInfo 페이지정보
     * @return 급지 우편번호 목록
     */
    @ApiOperation(value = "급지 우편번호 조회", notes = "조회조건에 일치하는 우편번호별 법정동, 행정동, 행정동 주민센터 등의 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "unrgCenLocaraYn", value = "미등록중심지", paramType = "query", example = "N")
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getZipNoPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getZipNoPages(dto, pageInfo);
    }

    /**
     * 급지 우편번호 목록 엑셀 다운로드
     * @param dto 조회조건
     * @return 급지 우편번호 목록
     */
    @ApiOperation(value = "급지 우편번호 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 급지 우편번호 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", example = "011"),
    })
    @GetMapping("/excel-download")
    public List<SearchExcelRes> getZipNosForExcelDownload(@Valid
    SearchReq dto) {
        return this.service.getZipNosForExcelDownload(dto);
    }

    /**
     * 급지 우편번호 저장
     * @param dtos 급지 우편번호 출고지 정보
     * @return 처리건수
     * @throws Exception
     */
    @ApiOperation(value = "급지 우편번호 저장", notes = "우편번호의 출고지 정보를 저장한다.")
    @PutMapping
    public SaveResponse editZipNos(
        @Valid
        @RequestBody
        List<EditReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.saveZipNo(dtos))
            .build();
    }

}
