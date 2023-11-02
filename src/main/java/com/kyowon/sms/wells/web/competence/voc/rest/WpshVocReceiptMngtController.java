package com.kyowon.sms.wells.web.competence.voc.rest;

import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshVocReceiptMngtDto.*;
import com.kyowon.sms.wells.web.competence.voc.service.WpshVocReceiptMngtService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "[WPSH] VOC 접수 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/voc")
public class WpshVocReceiptMngtController {

    private final WpshVocReceiptMngtService service;

    @ApiOperation(value = "VOC 접수 관리 페이징 조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getVocReceiptMngtPages(
        @Valid
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getVocReceiptMngtPages(dto, pageInfo);
    }

    @ApiOperation(value = "VOC 접수 관리 엑셀 다운로드", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/excel-download")
    public List<SearchRes> getVocReceiptMngtsForExcelDownload(
        @Valid
        SearchReq dto
    ) {
        return service.getVocReceiptMngtsForExcelDownload(dto);
    }

    @ApiOperation(value = "VOC 접수 관리 상세조회", notes = "")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "", value = "", paramType = "query", required = true),
    })
    @GetMapping("/dtl")
    public SearchDtlRes getVocReceiptDtl(
        @Valid
        SearchDtlReq dto
    ) {
        return service.getVocReceiptDtl(dto);
    }

    @ApiOperation(value = "VOC 접수 관리 VOC 등록", notes = "")
    @PostMapping
    public SaveResponse saveVocReceipt(
        @RequestBody
        @Valid
        SaveReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveVocReceipt(dto)).build();
    }

    @ApiOperation(value = "VOC 접수 관리 평가", notes = "")
    @PostMapping("/evl")
    public SaveResponse saveVocReceiptEvl(
        @RequestBody
        @Valid
        SaveEvlReq dto
    ) {
        return SaveResponse.builder().processCount(service.saveVocReceiptEvl(dto)).build();
    }

    @ApiOperation(value = "VOC 접수 관리 평가", notes = "")
    @PostMapping("/procs")
    public SaveResponse saveVocReceiptProcs(
        @RequestBody
        @Valid
        SaveProcsReq dto
    ) {
        return SaveResponse.builder().processCount(service.saveVocReceiptProcs(dto)).build();
    }

    @ApiOperation(value = "VOC 접수 관리 VOC 접수 등록/수정", notes = "")
    @PostMapping("/rcp")
    public SaveResponse saveVocReceiptRcp(
        @RequestBody
        @Valid
        SaveRcpReq dto
    ) {
        return SaveResponse.builder().processCount(service.saveVocReceiptRcp(dto)).build();
    }

    @ApiOperation(value = "VOC 접수 관리 VOC 답변 등록", notes = "")
    @PostMapping("/rly")
    public SaveResponse saveVocReceiptRly(
        @RequestBody
        @Valid
        SaveRlyReq dto
    ) throws Exception {
        return SaveResponse.builder().processCount(service.saveVocReceiptRly(dto)).build();
    }
}
