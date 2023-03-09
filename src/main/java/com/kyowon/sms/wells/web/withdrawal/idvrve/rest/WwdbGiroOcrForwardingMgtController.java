package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SavePrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchPrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchPrintRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.removePrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.service.WwdbGiroOcrForwardingMgtService;
import com.kyowon.sms.wells.web.withdrawal.zcommon.constants.WdWithdrawalConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "[수납입출금 - 개별수납] 지로OCR발송관리")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = WdWithdrawalConst.REST_URL_IDVRVE + "/giro-ocr-forwardings")
public class WwdbGiroOcrForwardingMgtController {

    private final WwdbGiroOcrForwardingMgtService service;

    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wkDt", value = "일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "giroRglrDvCd", value = "정기구분", paramType = "query", required = false),
    })
    @ApiOperation(value = "지로OCR발송관리 목록 조회", notes = " 검색조건을 받아 지로OCR발송관리 코드관리 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getGiroOcrForwardingPages(SearchReq dto, PageInfo pageInfo) {
        return service.getGiroOcrForwardingPages(dto, pageInfo);
    }

    @ApiOperation(value = "지로OCR발송관리 목록 조회", notes = " 검색조건을 받아 지로OCR발송관리 코드관리 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getGiroOcrForwardingExcels(SearchReq dto) {
        return service.getGiroOcrForwardingExcels(dto);
    }

    @ApiOperation(value = "지로OCR발송관리 대상 조회", notes = "지로OCR발송관리 대상 목록을 조회한다.")
    @GetMapping("/objects")
    public List<SearchRes> getGiroOcrForwardingObjects() {
        return service.getGiroOcrForwardingObjects();
    }

    @ApiOperation(value = "지로OCR발송관리 저장", notes = "지로OCR발송관리 등록 및 수정한다.")
    @PostMapping
    public SaveResponse saveGiroOcrForwardings(
        @RequestBody
        @Valid
        List<SaveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveGiroOcrForwardings(dto))
            .build();
    }

    @ApiOperation(value = "지로OCR발송관리 삭제", notes = "지로OCR발송관리 삭제한다.")
    @DeleteMapping
    public SaveResponse removeGiroOcrForwardings(
        @RequestBody
        @Valid
        List<SaveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeGiroOcrForwardings(dto))
            .build();
    }

    @ApiOperation(value = "지로OCR발송관리 출력 조회", notes = "지로OCR발송관리 출력 목록을 조회한다.")
    @GetMapping("/print")
    public PagingResult<SearchPrintRes> getGiroOcrForwardingPrints(SearchPrintReq dto, PageInfo pageInfo) {

        return service.getGiroOcrForwardingPrints(dto, pageInfo);
    }

    @ApiOperation(value = "지로OCR발송관리 출력 엑셀 다운로드", notes = "지로OCR발송관리 출력 엑셀 다운로드 한다.")
    @GetMapping("/print/excel-download")
    public List<SearchPrintRes> getGiroOcrForwardingExels(SearchPrintReq dto) {

        return service.getGiroOcrForwardingExels(dto);
    }

    @ApiOperation(value = "지로OCR발송관리 출력 등록", notes = "지로OCR발송관리 출력 등록")
    @PostMapping("/print")
    public SaveResponse saveGiroOcrForwardingPrints(
        @RequestBody
        @Valid
        SavePrintReq dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveGiroOcrForwardingPrints(dto))
            .build();
    }

    @ApiOperation(value = "지로OCR발송관리 출력 삭제", notes = "지로OCR발송관리 출력 목록을 삭제한다.")
    @DeleteMapping("/print")
    public SaveResponse removeGiroOcrForwardingPrints(
        @RequestBody
        @Valid
        List<removePrintReq> dto
    ) throws Exception {
        log.info("=======cont=========");
        log.info(dto.toString());
        log.info("================");

        return SaveResponse.builder()
            .processCount(service.removeGiroOcrForwardingPrints(dto))
            .build();
    }
}
