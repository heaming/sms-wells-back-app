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
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdTrgBaseDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdPdBaseDvo;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdExcellentDivisionDeadlineDvo;
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
    public int saveProductBases(List<PdSaveReq> reqs) {
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
    public int removeProductBases(List<PdSaveReq> reqs) {
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
    public int saveEvaluationBases(List<EvlSaveReq> reqs) {
        int processCount = 0;
        for (EvlSaveReq req : reqs) {
            WpsdElvBaseDvo dvo = converter.elvMapToElvBaseDvo(req);
            int resultCnt = 0;
            if(StringUtils.isBlank(dvo.getAwdEvlId())){
                resultCnt = mapper.insertEvaluationBase(dvo);
            }else{
                resultCnt = mapper.updateEvaluationBase(dvo);
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
     * 우수사업부 기준관리 - 평가기준관리 삭제
     * @param reqs
     * @return int
     */
    public int removeEvaluationBases(List<EvlDeleteReq> reqs) {
        int processCount = 0;
        for (EvlDeleteReq req : reqs) {
            WpsdElvBaseDvo dvo = converter.elvMapToElvBaseDvo(req);
            int resultCnt = mapper.removeEvaluationBase(dvo);
            processCount += resultCnt;
        }
        return processCount;
    }

    /**
     * 우수사업부 기준관리 - 평가기준관리 상세 삭제
     * @param reqs
     * @return PagingResult<EvlDetailSearchRes>
     */
    public int removeEvaluationDetails(List<EvlDetailDeleteReq> reqs) {
        int processCount = 0;
        for (EvlDetailDeleteReq req : reqs) {
            WpsdElvDetailDvo dvo = converter.elvMapToDetailDvo(req);
            // 1. 기존 시상평가기준상세 데이터 삭제
            mapper.deleteEvaluationDetail(dvo); // 시상평가기준상세 삭제
            mapper.deleteEvaluationArticleDetail(dvo);  // 시상평가항목상세(대상) 삭제
        }
        return processCount;
    }

    /**
     * 우수사업부 기준관리 - 평가기준관리 상세 저장
     * @param reqs
     * @return PagingResult<EvlDetailSearchRes>
     */
    @Transactional
    public int saveEvaluationDetails(List<EvlDetailSaveReq> reqs) {
        int processCount = 0;
        for (EvlDetailSaveReq req : reqs) {
            WpsdElvDetailDvo dvo = converter.elvMapToDetailDvo(req);
            // 1. 기존 시상평가기준상세 데이터 삭제
            mapper.deleteEvaluationDetail(dvo); // 시상평가기준상세 삭제
            mapper.deleteEvaluationArticleDetail(dvo);  // 시상평가항목상세(대상) 삭제
            // 2. 대상자 조회
            List<HashMap<String, Object>> target = mapper.selectTargetList(dvo);
            BizAssert.isTrue(!target.isEmpty(), "MSG_ALT_UNRG_EVL_OJ");
            // 3. 시상평가기준상세 등록
            int resultCnt = mapper.insertEvaluationDetail(dvo);
            BizAssert.isTrue(resultCnt > 0, "MSG_ALT_NTHNG_PROCS_OJ");
            // 4. 시상평가항목상세 등록
            resultCnt = mapper.insertEvaluationArticleDetail(dvo, target);
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
     * 우수사업부 기준관리 - 월별 시상구분 조회
     * @param req
     * @return PagingResult<EvlSearchRes>
     */
    public List<EvlAwardRes> getMonthAwardTypeList(EvlSearchReq req) {
        return mapper.selectMonthAwardTypeList(req);
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
    public int saveTargetBase(List<TrgSaveReq> reqs) {
        int processCount = 0;
        for (TrgSaveReq req : reqs) {
            WpsdTrgBaseDvo dvo = converter.mapToTrgBaseDvo(req);
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


    public List<EvlDetailSearchRes> getExcellentDivisionEvaluationDetail(EvlSearchReq req) {
        return mapper.selectEvaluationDetailPages(req);
    }
}
