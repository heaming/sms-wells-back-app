package com.kyowon.sms.wells.web.withdrawal.idvrve.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SavePrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchObjectRes;
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

    /**
     * 지로OCR발송관리 목록 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "wkDt", value = "일자", paramType = "query", required = false),
        @ApiImplicitParam(name = "giroRglrDvCd", value = "정기구분", paramType = "query", required = false),
    })
    @ApiOperation(value = "지로OCR발송관리 목록 조회", notes = " 검색조건을 받아 지로OCR발송관리 코드관리 목록을 조회한다.")
    @GetMapping("/paging")
    public PagingResult<SearchRes> getGiroOcrForwardingPages(SearchReq dto, PageInfo pageInfo) {
        return service.getGiroOcrForwardingPages(dto, pageInfo);
    }

    /**
     * 지로OCR발송관리 목록 조회 / 엑셀 다운로드
     * @param dto
     * @return List<SearchRes>
     */
    @ApiOperation(value = "지로OCR발송관리 목록 조회", notes = " 검색조건을 받아 지로OCR발송관리 코드관리 목록을 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getGiroOcrForwardingExcels(SearchReq dto) {
        List<SearchRes> test = service.getGiroOcrForwardingExcels(dto);
        log.info("===============");
        for (SearchRes searchRes : test) {
            log.info(searchRes.toString());
        }
        log.info("===============");
        return test;
    }

    /**
     * 지로OCR발송관리 대상 조회
     * @param cntr 계약번호
     * @param wkDt 작업일자
     * @return List<SearchObjectRes>
     */
    @ApiOperation(value = "지로OCR발송관리 대상 조회", notes = "지로OCR발송관리 대상 목록을 조회한다.")
    @GetMapping("/objects/{cntr}/{wkDt}")
    public List<SearchObjectRes> getGiroOcrForwardingObjects(
        @PathVariable("cntr")
        String cntr,
        @PathVariable("wkDt")
        String wkDt
    ) {
        log.info("==================");
        log.info(cntr);
        log.info("==================");
        return service.getGiroOcrForwardingObjects(cntr, wkDt);
    }

    /**
     * 지로OCR발송관리 대상 조회
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @return SearchGiroCntractRes
     */
    @ApiOperation(value = "지로OCR발송관리 대상 계약정보 조회", notes = "지로OCR발송관리 대상 계약정보를 조회한다.")
    @GetMapping("/objects/contract/{cntrno}/{cntrsn}")
    public WwdbGiroOcrForwardingMgtDto.SearchGiroCntractRes getGiroOcrForwardingObjectContractInfo(
        @PathVariable("cntrno")
        String cntrNo,
        @PathVariable("cntrsn")
        String cntrSn
    ) {
        return service.getGiroOcrForwardingObjectContractInfo(cntrNo, cntrSn);
    }

    /**
     * 지로OCR발송관리 저장
     * @param dto
     * @return SaveResponse
     * @throws Exception
     */
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

    /**
     * 지로OCR발송관리 삭제
     * @param dto
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "지로OCR발송관리 삭제", notes = "지로OCR발송관리 삭제한다.")
    @DeleteMapping
    public SaveResponse removeGiroOcrForwardings(
        @RequestBody
        @Valid
        List<RemoveReq> dto
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.removeGiroOcrForwardings(dto))
            .build();
    }

    /**
     * 지로OCR발송관리 출력 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchPrintRes>
     */
    @ApiOperation(value = "지로OCR발송관리 출력 조회", notes = "지로OCR발송관리 출력 목록을 조회한다.")
    @GetMapping("/print")
    public PagingResult<SearchPrintRes> getGiroOcrForwardingPrints(SearchPrintReq dto, PageInfo pageInfo) {

        return service.getGiroOcrForwardingPrints(dto, pageInfo);
    }

    /**
     * 지로OCR발송관리 출력 조회 / 엑셀 다운로드
     * @param dto
     * @return List<SearchPrintRes>
     */
    @ApiOperation(value = "지로OCR발송관리 출력 엑셀 다운로드", notes = "지로OCR발송관리 출력 엑셀 다운로드 한다.")
    @GetMapping("/print/excel-download")
    public List<SearchPrintRes> getGiroOcrForwardingExels(SearchPrintReq dto) {

        return service.getGiroOcrForwardingExels(dto);
    }

    /**
     * 지로OCR발송관리 출력 등록
     * @param dto
     * @param response
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "지로OCR발송관리 출력 등록", notes = "지로OCR발송관리 출력 등록")
    @PostMapping("/print")
    public SaveResponse saveGiroOcrForwardingPrints(
        @RequestBody
        @Valid
        SavePrintReq dto,
        HttpServletResponse response
    ) throws Exception {
        return SaveResponse.builder()
            .processCount(service.saveGiroOcrForwardingPrints(dto, response))
            .build();
    }

    /**
     * 지로OCR발송관리 출력 삭제
     * @param dto
     * @return SaveResponse
     * @throws Exception
     */
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

    /**
     * 지로 출력 업데이트
     * @param dto
     * @return SaveResponse
     * @throws Exception
     */
    @ApiOperation(value = "지로 출력 업데이트", notes = "지로 출력 업데이트")
    @PostMapping("/print/date")
    public SaveResponse updateGiroPrintDate(
        @RequestBody
        @Valid
        WwdbGiroOcrForwardingMgtDto.saveGiroPrintReq dto
    ) throws Exception {
        log.info("=======cont=========");
        log.info(dto.toString());
        log.info("================");

        return SaveResponse.builder()
            .processCount(service.saveGiroPrintDate(dto))
            .build();
    }
}
