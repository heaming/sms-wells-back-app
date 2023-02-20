package com.kyowon.sms.wells.web.service.orgcode.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.service.WsndRegionLevelPdlvMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/region-levels")
@Api(tags = "[WSND] 급지 출고지 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsndRegionLevelPdlvMgtController {

    private final WsndRegionLevelPdlvMgtService service;

    @ApiOperation(value = "급지 출고지 조회", notes = "조회조건에 일치하는 출고지 정보를 조회한다.")
    @GetMapping("/place-of-deliverys/paging")
    public PagingResult<SearchRes> getPlaceOfDeliveryPages(
        SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return this.service.getPlaceOfDeliveryPages(dto, pageInfo);
    }

    @ApiOperation(value = "급지 출고지 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 급지 출고지 목록을 조회한다.")
    @GetMapping("/place-of-deliverys/excel-download")
    public List<SearchRes> getPlaceOfDeliveryPagesExcelDownload(SearchReq dto) {
        return this.service.getPlaceOfDeliveryPagesExcelDownload(dto);
    }

    @ApiOperation(value = "급지 출고지 삭제", notes = "급지 출고지 정보를 삭제한다.")
    @DeleteMapping("/place-of-deliverys")
    public SaveResponse removePlaceOfDeliverys(
        @Valid
        @RequestParam
        @NotEmpty
        List<String> pdlvNos,
        @Valid
        @RequestParam
        @NotEmpty
        List<String> pdlvDvCds
    ) {
        return SaveResponse.builder()
            .processCount(this.service.removePlaceOfDeliverys(pdlvNos, pdlvDvCds))
            .build();
    }

    @ApiOperation(value = "급지 출고지 저장", notes = "급지 출고지 CU 변경 데이터를 List 형태로 받아 일괄 저장한다.")
    @PostMapping("/place-of-deliverys")
    public SaveResponse savePlaceOfDeliverys(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos
    ) {
        return SaveResponse.builder()
            .processCount(this.service.savePlaceOfDeliverys(dtos))
            .build();
    }

}
