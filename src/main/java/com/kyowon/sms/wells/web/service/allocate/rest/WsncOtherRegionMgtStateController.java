package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncOtherRegionMgtStateDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncOtherRegionMgtStateService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 타지역단 관리현황")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/other-region-mgt-state")
@Slf4j
public class WsncOtherRegionMgtStateController {

    private final WsncOtherRegionMgtStateService service;

    @ApiOperation(value = "타지역 관리현황 조회", notes = "조회")
    @GetMapping("/paging")
    public PagingResult<WsncOtherRegionMgtStateDto.SearchRes> getOtherRegionMgtState(
        WsncOtherRegionMgtStateDto.SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getOtherRegionMgtState(dto, pageInfo);
    }

    @ApiOperation(value = "타지역 관리현황 엑셀 다운로드", notes = "엑셀 다운로드")
    @GetMapping("/excel-download")
    public List<WsncOtherRegionMgtStateDto.SearchRes> getgetOtherRegionMgtStateExcelDownload(
        WsncOtherRegionMgtStateDto.SearchReq dto
    ) {
        return service.getOtherRegionMgtStateExcelDownload(dto);
    }
}
