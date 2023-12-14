package com.kyowon.sms.wells.web.competence.evaluate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.competence.evaluate.converter.WpsdExcellentDivisionBaseMgtConverter;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionBaseMgtDto.*;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdElvBaseDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdElvDetailDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdExcellentDivisionDeadlineDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdPdBaseDvo;
import com.kyowon.sms.wells.web.competence.evaluate.mapper.WpsdExcellentDivisionBaseMgtMapper;
import com.sds.sflex.common.common.dvo.ExcelUploadErrorDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WpsdExcellentDivisionBaseMgtService {

    private final WpsdExcellentDivisionBaseMgtMapper mapper;
    private final WpsdExcellentDivisionBaseMgtConverter converter;
    private final MessageResourceService messageResourceService;
    private static final String SAVE_ERROR_MESSAGE = "MSG_ALT_SVE_ERR";
    private static final String BLANK_ERROR_MESSAGE = "MSG_ALT_EMPTY_REQUIRED_VAL";

    /**
     * 우수사업부 기준관리 - 상품 기준 관리 페이징 목록 조회
     * @param req, pageInfo
     * @return PagingResult<PdSearchRes>
     */
    public PagingResult<PdSearchRes> getProductBaseMgtPages(PdSearchReq req, PageInfo pageInfo) {
        return mapper.selectProductBaseMgtPages(req, pageInfo);
    }

    /**
     * 우수사업부 기준관리 - 상품 기준 관리 엑셀목록 조회
     * @param req
     * @return PagingResult<PdSearchRes>
     */
    public List<PdSearchRes> getProductBaseMgtsForExcelDownload(PdSearchReq req) {
        return mapper.selectProductBaseMgtPages(req);
    }

    /**
     * 우수사업부 기준관리 - 상품 기준 관리 저장
     * @param reqs
     * @return int
     */
    @Transactional
    public int saveProductBase(List<PdSaveReq> reqs) {
        int processCount = 0;
        for (PdSaveReq req : reqs) {
            WpsdPdBaseDvo dvo = converter.pdMapToPdBaseDvo(req);
            int resultCnt = 0;
            if ("created".equals(dvo.getRowState())) {
                resultCnt = mapper.insertProductBase(dvo);
            } else if ("updated".equals(dvo.getRowState())) {
                resultCnt = mapper.updateProductBase(dvo);
            }
            BizAssert.isTrue(resultCnt > 0, SAVE_ERROR_MESSAGE);
            processCount += resultCnt;
        }
        return processCount;
    }

    /**
     * 우수사업부 기준관리 - 상품 기준 관리 삭제
     * @param reqs
     * @return int
     */
    public int removeProductBase(List<PdSaveReq> reqs) {
        int processCount = 0;
        for (PdSaveReq req : reqs) {
            WpsdPdBaseDvo dvo = converter.pdMapToPdBaseDvo(req);
            int resultCnt = 0;
            resultCnt = mapper.removeProductBase(dvo);
            processCount += resultCnt;
        }
        return processCount;
    }

    /**
     * 우수사업부 기준관리 - 엑셀 업로드 유효성 검사
     * @param excelData
     * @return List<ExcelUploadErrorDvo>
     */
    public List<ExcelUploadErrorDvo> isValidExcelData(List<WpsdPdBaseDvo> excelData) {
        List<ExcelUploadErrorDvo> response = new ArrayList<>();
        int row = 2;
        for (WpsdPdBaseDvo dvo : excelData) {
            if (StringUtils.isBlank(dvo.getBaseYm())) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(messageResourceService.getMessage("MSG_TXT_MGT_YNM"));
                errorDvo.setErrorData(messageResourceService.getMessage(BLANK_ERROR_MESSAGE));
                response.add(errorDvo);
            }
            if (StringUtils.isBlank(dvo.getEvlPdDvCd())) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(messageResourceService.getMessage("MSG_TXT_MNGT_DV"));
                errorDvo.setErrorData(messageResourceService.getMessage(BLANK_ERROR_MESSAGE));
                response.add(errorDvo);
            }
            if (StringUtils.isBlank(dvo.getPdCd())) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(messageResourceService.getMessage("MSG_TXT_PRDT_CODE"));
                errorDvo.setErrorData(messageResourceService.getMessage(BLANK_ERROR_MESSAGE));
                response.add(errorDvo);
            }
            if (StringUtils.isBlank(dvo.getCvtPc())) {
                ExcelUploadErrorDvo errorDvo = new ExcelUploadErrorDvo();
                errorDvo.setErrorRow(row);
                errorDvo.setHeaderName(messageResourceService.getMessage("MSG_TXT_CVT_CT"));
                errorDvo.setErrorData(messageResourceService.getMessage(BLANK_ERROR_MESSAGE));
                response.add(errorDvo);
            }

        }
        return response;
    }

    /**
     * 우수사업부 기준관리 - 평가기준관리 페이징 조회
     * @param req, pageInfo
     * @return PagingResult<EvlSearchRes>
     */
    public PagingResult<EvlSearchRes> getEvaluationBaseMgtPages(EvlSearchReq req, PageInfo pageInfo) {
        return mapper.selectEvaluationBaseMgtPages(req, pageInfo);
    }

    /**
     * 우수사업부 기준관리 - 평가기준관리 저장
     * @param reqs
     * @return int
     */
    @Transactional
    public int saveEvaluationBase(List<EvlSaveReq> reqs) {
        int processCount = 0;
        for (EvlSaveReq req : reqs) {
            WpsdElvBaseDvo dvo = converter.elvMapToElvBaseDvo(req);
            int resultCnt = 0;
            if (mapper.selectEvaluationBase(dvo) > 0) {
                resultCnt = mapper.updateEvaluationBase(dvo);
            } else {
                resultCnt = mapper.insertEvaluationBase(dvo);
            }
            BizAssert.isTrue(resultCnt > 0, SAVE_ERROR_MESSAGE);
            if (dvo.getRsbDvCdList().length > 0) {
                mapper.removeEvaluationResponsibility(dvo);
                resultCnt = mapper.insertEvaluationResponsibility(dvo);
                BizAssert.isTrue(resultCnt > 0, SAVE_ERROR_MESSAGE);
            }
            processCount += resultCnt;
        }
        return processCount;
    }

    /**
     * 우수사업부 기준관리 - 평가기준관리 상세 페이징 조회
     * @param req
     * @return PagingResult<EvlDetailSearchRes>
     */
    public PagingResult<EvlDetailSearchRes> getEvaluationDetailPages(EvlSearchReq req, PageInfo pageInfo) {
        return mapper.selectEvaluationDetailPages(req, pageInfo);
    }

    /**
     * 우수사업부 기준관리 - 평가기준관리 상세 저장
     * @param reqs
     * @return PagingResult<EvlDetailSearchRes>
     */
    @Transactional
    public int saveEvaluationDetail(List<EvlDetailSaveReq> reqs) {
        int processCount = 0;
        for (EvlDetailSaveReq req : reqs) {
            WpsdElvDetailDvo dvo = converter.elvMapToDetailDvo(req);
            mapper.deleteEvaluationDetail(dvo);
            List<HashMap<String, Object>> target = mapper.selectTargetList(dvo);
            BizAssert.isTrue(!target.isEmpty(), "MSG_ALT_UNRG_EVL_OJ");
            int resultCnt = mapper.insertEvaluationDetail(dvo, target);
            BizAssert.isTrue(resultCnt > 0, "MSG_ALT_NTHNG_PROCS_OJ");
            processCount += resultCnt;
        }
        return processCount;
    }

    /**
     * 우수사업부 기준관리 - 평가 기준 항목 조회
     * @param req
     * @return PagingResult<EvlDetailSearchRes>
     */
    public List<EvlArticlesSearchRes> getEvaluationArticales(EvlSearchReq req) {
        return mapper.selectEvaluationArticales(req);
    }

    /**
     * 우수사업부 기준관리 - 목표기준관리 페이징  조회
     * @param req, pageInfo
     * @return PagingResult<TrgSearchRes>
     */
    public PagingResult<TrgSearchRes> getExcellentDivisionTargetBaseMgtPages(TrgSearchReq req, PageInfo pageInfo) {
        return mapper.selectTargetBaseMgtPages(req, pageInfo);
    }

    /**
     * 우수사업부 기준관리 - 목표기준관리 저장
     * @param reqs
     * @return int
     */
    @Transactional
    public int saveTargetBase(List<EvlDetailSaveReq> reqs) {
        int processCount = 0;
        for (EvlDetailSaveReq req : reqs) {
            WpsdElvDetailDvo dvo = converter.elvMapToDetailDvo(req);
            int resultCnt = mapper.updateTargetBase(dvo);
            BizAssert.isTrue(resultCnt > 0, SAVE_ERROR_MESSAGE);
            processCount += resultCnt;
        }
        return processCount;
    }

    /**
     * 우수사업부 기준관리 - 마감시간 저장
     * @param req
     * @return int
     */
    public DeadlineSearchRes getExcellentDivisionDeadline(DeadlineSearchReq req) {
        return mapper.selectExcellentDivisionDeadline(req);
    }

    /**
     * 우수사업부 기준관리 - 마감시간 저장
     * @param req
     * @return int
     */
    public int saveDeadline(DeadlineSaveReq req) {
        WpsdExcellentDivisionDeadlineDvo dvo = converter.deadlineMapToDvo(req);
        int resultCount = 0;
        int count = mapper.selectExcellentDivisionDeadlineCount(dvo);
        if (count > 0) {
            resultCount = mapper.updateExcellentDivisionDeadline(dvo);
        } else {
            resultCount = mapper.insertExcellentDivisionDeadline(dvo);
        }
        BizAssert.isTrue(resultCount > 0, SAVE_ERROR_MESSAGE);
        return resultCount;
    }

}
