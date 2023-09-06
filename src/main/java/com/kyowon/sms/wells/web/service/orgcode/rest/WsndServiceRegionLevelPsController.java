package com.kyowon.sms.wells.web.service.orgcode.rest;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndServiceRegionLevelPsDto.*;
import com.kyowon.sms.wells.web.service.orgcode.service.WsndServiceRegionLevelPsService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "[WSND] 서비스 급지현황")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(SnServiceConst.REST_URL_V1 + "/service-region-level-ps")
@Slf4j
public class WsndServiceRegionLevelPsController {
    private final WsndServiceRegionLevelPsService service;

    @ApiOperation(value = "서비스 급지현황 조회", notes = "조회조건에 일치하는 서비스 급지현황 정보를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getServiceRegionLevelPsPaging(
        SearchReq dto, @Valid PageInfo pageInfo
    ) {
        return service.getServiceRegionLevelPsPaging(dto, pageInfo);
    }

    @ApiOperation(value = "서비스 급지현황 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 서비스 급지현황 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getPlaceOfDeliveryPagesExcelDownload(SearchReq dto) {
        return this.service.getServiceRegionLevelPsExcelDownload(dto);
    }
}
