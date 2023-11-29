package com.kyowon.sms.wells.web.competence.evaluate.rest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionBaseMgtDto.*;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdPdBaseDvo;
import com.kyowon.sms.wells.web.competence.evaluate.service.WpsdExcellentDivisionBaseMgtService;
import com.kyowon.sms.wells.web.competence.zcommon.constants.PsCompetenceConst;
import com.sds.sflex.common.common.dto.ExcelUploadDto;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[PSD] 우수사업부 기준관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(PsCompetenceConst.REST_URL_V1 + "/excellent-division-base")
public class WpsdExcellentDivisionBaseMgtController {

    private final WpsdExcellentDivisionBaseMgtService service;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    @ApiOperation(value = "우수사업부 기준관리 - 상품기준관리 페이징 조회", notes = "상품기준관리 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlPdDvCd", value = "상품구분", paramType = "query", required = true),
        @ApiImplicitParam(name = "elvOgTpCd", value = "평가조직유형코드", paramType = "query", required = true),
    })
    @GetMapping("/product/paging")
    public PagingResult<PdSearchRes> getProductBaseMgtPages(
        @Valid
        PdSearchReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getProductBaseMgtPages(req, pageInfo);
    }

    @ApiOperation(value = "우수사업부 기준관리 - 상품기준관리 엑셀 다운로드", notes = "상품기준관리 엑셀다운로드")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlPdDvCd", value = "평가상품구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlOgTpCd", value = "평가조직유형코드", paramType = "query", required = true),
    })
    @GetMapping("/product/excel-download")
    public List<PdSearchRes> getProductBaseMgtsForExcelDownload(
        @Valid
        PdSearchReq req
    ) {
        return service.getProductBaseMgtsForExcelDownload(req);
    }

    @ApiOperation(value = "화상렌탈 신청현황 - 엑셀 업로드", notes = "")
    @PostMapping("/product/excel-upload")
    public ExcelUploadDto.UploadRes getProductBaseMgtsForExcelUpload(
        @RequestParam("file")
        MultipartFile file
    ) throws Exception {
        // file 읽어서 처리.
        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("baseYm", messageResourceService.getMessage("MSG_TXT_MGT_YNM"));
        headerTitle.put("evlPdDvCd", messageResourceService.getMessage("MSG_TXT_MNGT_DV"));
        headerTitle.put("pdCd", messageResourceService.getMessage("MSG_TXT_PRDT_CODE"));
        headerTitle.put("cvtPc", messageResourceService.getMessage("MSG_TXT_CVT_CT"));
        List<WpsdPdBaseDvo> excelData;
        try {
            excelData = excelReadService.readExcel(file, new ExcelMetaDvo(1, headerTitle), WpsdPdBaseDvo.class);
        } catch (Exception e) {
            throw new BizException("MSG_ALT_INVALID_EXCEL_FILE");
        }
        List<ExcelUploadErrorDvo> errList = service.isValidExcelData(excelData);
        if (CollectionUtils.isNotEmpty(errList)) {
            return ExcelUploadDto.UploadRes.builder()
                .status("E")
                .excelData(excelData)
                .errorInfo(errList)
                .build();
        }
        return ExcelUploadDto.UploadRes.builder()
            .status("S")
            .excelData(excelData)
            .build();
    }

    @ApiOperation(value = "우수사업부 기준관리 - 상품기준관리 저장", notes = "상품기준관리 저장")
    @PostMapping("/product")
    public SaveResponse saveProductBase(
        @Valid @RequestBody
        List<PdSaveReq> reqs
    ) {
        return SaveResponse.builder().processCount(service.saveProductBase(reqs))
            .build();
    }

    @ApiOperation(value = "우수사업부 기준관리 - 상품기준관리 저장", notes = "상품기준관리 저장")
    @DeleteMapping("/product")
    public SaveResponse removeProductBase(
        @Valid @RequestBody
        List<PdSaveReq> reqs
    ) {
        return SaveResponse.builder().processCount(service.removeProductBase(reqs))
            .build();
    }

    @ApiOperation(value = "우수사업부 기준관리 - 평가기준관리 페이징 조회", notes = "상품기준관리 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlOgTpCd", value = "평가조직유형코드", paramType = "query", required = true),
    })
    @GetMapping("/evaluation/paging")
    public PagingResult<EvlSearchRes> getExcellentDivisionEvaluationBaseMgtPages(
        @Valid
        EvlSearchReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getEvaluationBaseMgtPages(req, pageInfo);
    }

    @ApiOperation(value = "우수사업부 기준관리 - 평가기준관리 저장", notes = "평가기준관리 저장")
    @PostMapping("/evaluation")
    public SaveResponse saveEvaluationBase(
        @Valid @RequestBody
        List<EvlSaveReq> reqs
    ) {
        return SaveResponse.builder().processCount(service.saveEvaluationBase(reqs))
            .build();
    }

    @ApiOperation(value = "우수사업부 기준관리 - 상세 페이징 조회", notes = "상세 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlOgTpCd", value = "평가조직유형코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlPdDvCd", value = "평가상품구분코드", paramType = "query", required = true),
    })
    @GetMapping("/evaluation/detail")
    public PagingResult<EvlDetailSearchRes> getExcellentDivisionEvaluationDetailPages(
        @Valid
        EvlSearchReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getEvaluationDetailPages(req, pageInfo);
    }

    @ApiOperation(value = "우수사업부 기준관리 - 상세 저장", notes = "상세 저장")
    @PostMapping("/evaluation/detail")
    public SaveResponse saveEvaluationDetail(
        @Valid @RequestBody
        List<EvlDetailSaveReq> reqs
    ) {
        return SaveResponse.builder().processCount(service.saveEvaluationDetail(reqs))
            .build();
    }

    @ApiOperation(value = "우수사업부 기준관리 - 평가기준구분 조회", notes = "평가기준구분 조회")
    @GetMapping("/evaluation/articles")
    public List<EvlArticlesSearchRes> getEvaluationArticales(
        @Valid
        EvlSearchReq req
    ) {
        return service.getEvaluationArticales(req);
    }

    @ApiOperation(value = "우수사업부 기준관리 - 평가기준관리 페이징 조회", notes = "상품기준관리 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "baseYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "evlOgTpCd", value = "평가조직유형코드", paramType = "query", required = true),
    })
    @GetMapping("/target/paging")
    public PagingResult<TrgSearchRes> getExcellentDivisionTargetBaseMgtPages(
        @Valid
        TrgSearchReq req,
        @Valid
        PageInfo pageInfo
    ) {
        return service.getExcellentDivisionTargetBaseMgtPages(req, pageInfo);
    }

    @ApiOperation(value = "우수사업부 기준관리 - 목표기준관리 저장", notes = "목표기준관리 저장")
    @PostMapping("/target")
    public SaveResponse saveTargetBase(
        @Valid @RequestBody
        List<EvlDetailSaveReq> reqs
    ) {
        return SaveResponse.builder().processCount(service.saveTargetBase(reqs))
            .build();
    }

    @ApiOperation(value = "우수사업부 기준관리 - 마감시간 조회", notes = "마감시간 조회")
    @GetMapping("/deadline")
    public DeadlineSearchRes getExcellentDivisionDeadline(
        @Valid
        DeadlineSearchReq req
    ) {
        return service.getExcellentDivisionDeadline(req);
    }

    @ApiOperation(value = "우수사업부 기준관리 - 마감시간 저장", notes = "마감시간 저장")
    @PostMapping("/deadline")
    public SaveResponse saveExcellentDivisionDeadline(
        @Valid @RequestBody
        DeadlineSaveReq req
    ) {
        return SaveResponse.builder().processCount(service.saveDeadline(req)).build();
    }

}
