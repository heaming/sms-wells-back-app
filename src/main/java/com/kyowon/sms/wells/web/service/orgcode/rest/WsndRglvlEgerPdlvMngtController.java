package com.kyowon.sms.wells.web.service.orgcode.rest;

import com.kyowon.sms.wells.web.service.orgcode.service.WsndRglvlEgerPdlvMngtService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRglvlEgerPdlvMngtDto.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/wsnd-rglvl-eger-pdlv-mngt")
@Api(tags = "[WSND] RT급지 엔지니어출고지관리 REST API")
@RequiredArgsConstructor
@Validated
public class WsndRglvlEgerPdlvMngtController {
    private final WsndRglvlEgerPdlvMngtService service;

    @ApiOperation(value = "RT급지 엔지니어출고지관리 조회", notes = "RT급지 엔지니어출고지관리 기본 정보를 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getRglvlEgerPdlvMngtPages(SearchReq dto, @Valid PageInfo pageInfo){
        return service.getRglvlEgerPdlvMngtPages(dto, pageInfo);
    }

    @ApiOperation(value = "RT급지 엔지니어출고지관리 목록 엑셀 다운로드", notes = "검색조건을 입력 받아 엑셀 다운로드용 RT급지 엔지니어출고지관리 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getRglvlEgerPdlvMngtExcelDownload(SearchReq dto){
        return service.getRglvlEgerPdlvMngtExcelDownload(dto);
    }
}
