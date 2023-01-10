package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaZipMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaZipMgtDto.District;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaZipMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaZipMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRpbAreaZipMgtService;
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
@RequestMapping(SnServiceConst.REST_URL_V1 + "/responsible-area-zips")
@Api(tags = "[WSNC] 책임지역 우편번호 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsncRpbAreaZipMgtController {

    private final WsncRpbAreaZipMgtService service;

    @ApiOperation(value = "책임지역 우편번호 조회", notes = "조회조건에 일치하는 책임지역 우편번호 정보를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101", required = true)
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getZipNoPages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return this.service.getZipNoPages(dto, pageInfo);
    }

    @ApiOperation(value = "책임지역 우편번호 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 책임지역 우편번호 목록을 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "zipFrom", value = "우편번호From", paramType = "query", example = "011"),
        @ApiImplicitParam(name = "zipTo", value = "우편번호To", paramType = "query", example = "022"),
        @ApiImplicitParam(name = "ctpvNm", value = "시도명", paramType = "query", example = "서울특별시"),
        @ApiImplicitParam(name = "ctctyNm", value = "시군구명", paramType = "query", example = "도봉구"),
        @ApiImplicitParam(name = "wkGrpCd", value = "작업그룹코드", paramType = "query", example = "10", required = true),
        @ApiImplicitParam(name = "applyDate", value = "적용일자", paramType = "query", dataType = "date", example = "20220101", required = true)
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getZipNosForExcelDownload(
        SearchReq dto
    ) {
        return this.service.getZipNosForExcelDownload(dto);
    }

    @ApiOperation(value = "책임지역 법정동 행정동 리스트 조회", notes = "책임지역 법정동 행정동 리스트 조회한다.")
    @GetMapping("/districts")
    public List<District> getDistricts() {
        return this.service.getDistricts();
    }

    @ApiOperation(value = "책임지역 우편번호 저장", notes = "책임지역 우편번호를 저장한다.")
    @PostMapping
    public SaveResponse createZipNos(
        @Valid
        @RequestBody
        List<CreateReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(this.service.createZip(dtos))
            .build();
    }

}
