package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaCodeMgtDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRpbAreaCodeMgtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/responsible-area-codes")
@Api(tags = "[WSNC] 책임 지역 지역 코드 관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsncRpbAreaCodeMgtController {

    private final WsncRpbAreaCodeMgtService service;

    @ApiOperation(value = "책임지역 지역코드 조회", notes = "조회조건에 일치하는 책임지역 지역코드 정보를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<WsncRpbAreaCodeMgtDto.SearchRes> getAreaCodePages(
        WsncRpbAreaCodeMgtDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return service.getAreaCodePages(dto, pageInfo);
    }

    @ApiOperation(value = "책임지역 지역코드 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 책임지역 지역코드 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<WsncRpbAreaCodeMgtDto.SearchRes> getLocalAreaCodePagesExcelDownload(
        WsncRpbAreaCodeMgtDto.SearchReq dto
    ) {
        return service.getAreaCodePagesExcelDownload(dto);
    }

    @ApiOperation(value = "책임지역 지역코드 저장", notes = "책임지역 지역코드를 저장한다.")
    @PostMapping
    public SaveResponse createLocalAreaCodes(
        @Valid
        @RequestBody
        @NotEmpty
        List<WsncRpbAreaCodeMgtDto.SaveReq> dtos
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.createAreaCodes(dtos))
            .build();
    }

}
