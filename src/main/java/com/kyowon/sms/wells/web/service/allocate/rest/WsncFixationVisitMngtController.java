package com.kyowon.sms.wells.web.service.allocate.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncFixationVisitDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncFixationVisitService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "[WSNC] 고정방문 관리")
@RequiredArgsConstructor
@RestController
@RequestMapping(CommConst.REST_URL_V1 + "/sms/wells/service/fixation-visit")
@Slf4j
public class WsncFixationVisitMngtController {
    private final WsncFixationVisitService wsncFixationVisitMgntService;

    @ApiOperation(value = "고정방문 관리 화면 - 고정방문 목록 조회", notes = "조회조건에 따른 고정방문 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "fxnPrtnrDvCd", value = "관리구분(1 : 매니저, 2 : 엔지니어)", paramType = "query", required = false),
        @ApiImplicitParam(name = "sellTpCd", value = "판매유형", paramType = "query", required = false),
        @ApiImplicitParam(name = "fxnPrtnrNo", value = "방문담당자", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<WsncFixationVisitDto.SearchRes> getFixationVisits(
        WsncFixationVisitDto.SearchReq dto, @Valid
        PageInfo pageInfo
    ) {
        return wsncFixationVisitMgntService.getFixationVisits(dto, pageInfo);
    }

    @GetMapping("/excel-download")
    public List<WsncFixationVisitDto.SearchRes> getFixationVisitsExcelDownload(
        WsncFixationVisitDto.SearchReq dto
    ) {
        return wsncFixationVisitMgntService.getFixationVisitsExcelDownload(dto);
    }

    @ApiOperation(value = "고정방문 등록 팝업 - 고정방문 등록 팝업 조회", notes = "조회조건에 따른 고정방문 등록 팝업 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "cntrNo", value = "계약번호", paramType = "query", required = false),
        @ApiImplicitParam(name = "cntrSn", value = "계약일련번호", paramType = "query", required = false),
    })
    @GetMapping
    public WsncFixationVisitDto.SearchRegRes getFixationVisit(
        WsncFixationVisitDto.SearchRegReq dto
    ) {
        return wsncFixationVisitMgntService.getFixationVisit(dto);
    }

    @ApiOperation(value = "고정방문 등록 팝업 - 고정방문 등록 및 수정", notes = "고정방문 등록")
    @PostMapping
    public SaveResponse saveFixationVisit(
        @Valid
        @RequestBody
        WsncFixationVisitDto.SaveRegReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(wsncFixationVisitMgntService.saveFixationVisit(dto))
            .build();
    }
}
