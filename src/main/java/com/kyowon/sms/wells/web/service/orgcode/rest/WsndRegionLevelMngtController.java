package com.kyowon.sms.wells.web.service.orgcode.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMngtDto;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMngtDto.EditReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMngtDto.SearchExcelRes;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.service.WsndRegionLevelPdlvMngtService;
import com.kyowon.sms.wells.web.service.orgcode.service.WsndRegionLevelZipMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/region-levels")
@Api(tags = "[WSND] 급지 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsndRegionLevelMngtController {

    private final WsndRegionLevelZipMngtService service;
    private final WsndRegionLevelPdlvMngtService placeService;

    @ApiOperation(value = "급지 우편번호 조회", notes = "조회조건에 일치하는 우편번호별 법정동, 행정동, 행정동 주민센터 등의 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", example = "011"),
    })
    @GetMapping("/zip-nos/paging")
    public PagingResult<SearchRes> getZipNoPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getZipNoPages(dto, pageInfo);
    }

    @ApiOperation(value = "급지 우편번호 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 급지 우편번호 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "ogId", value = "서비스센터", paramType = "query", example = "011"),
    })
    @GetMapping("/zip-nos/excel-download")
    public List<SearchExcelRes> getZipNosForExcelDownload(SearchReq dto) {
        return this.service.getZipNosForExcelDownload(dto);
    }

    @ApiOperation(value = "급지 우편번호 저장", notes = "우편번호의 출고지 정보를 저장한다.")
    @PutMapping("/zip-nos")
    public SaveResponse editZipNo(
        @Valid
        @RequestBody
        @NotEmpty
        List<EditReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.saveZipNo(dtos))
            .build();
    }

    @ApiOperation(value = "급지 출고지 조회", notes = "조회조건에 일치하는 출고지 정보를 조회한다.")
    @GetMapping("/place-of-deliverys/paging")
    public PagingResult<WsndRegionLevelPdlvMngtDto.SearchRes> getPlaceOfDeliveryPages(
        WsndRegionLevelPdlvMngtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return placeService.getPlaceOfDeliveryPages(dto, pageInfo);
    }

    @ApiOperation(value = "급지 출고지 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 급지 출고지 목록을 조회한다.")
    @GetMapping("/place-of-deliverys/excel-download")
    public List<WsndRegionLevelPdlvMngtDto.SearchRes> getPlaceOfDeliveryPagesExcelDownload(
        WsndRegionLevelPdlvMngtDto.SearchReq dto
    ) {
        return placeService.getPlaceOfDeliveryPagesExcelDownload(dto);
    }

    @ApiOperation(value = "급지 출고지 삭제", notes = "급지 출고지 정보를 삭제한다.")
    @DeleteMapping("/place-of-deliverys")
    public SaveResponse removePlaceOfDeliverys(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsndRegionLevelPdlvMngtDto.DeleteReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(placeService.removePlaceOfDeliverys(dtos))
            .build();
    }

    @ApiOperation(value = "급지 출고지 저장", notes = "급지 출고지 CU 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @PostMapping("/place-of-deliverys")
    public SaveResponse savePlaceOfDeliverys(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsndRegionLevelPdlvMngtDto.SaveReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(placeService.savePlaceOfDeliverys(dtos))
            .build();
    }
}
