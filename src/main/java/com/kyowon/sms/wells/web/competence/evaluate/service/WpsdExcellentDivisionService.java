package com.kyowon.sms.wells.web.competence.evaluate.service;

import com.kyowon.sms.wells.web.competence.evaluate.converter.WpsdExcellentDivisionConverter;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.*;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdExcellentDivisionDvo;
import com.kyowon.sms.wells.web.competence.evaluate.mapper.WpsdExcellentDivisionMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WpsdExcellentDivisionService {

    private final WpsdExcellentDivisionMapper mapper;
    private final WpsdExcellentDivisionConverter converter;
    private static final String SAVE_ERROR_MESSAGE = "MSG_ALT_SVE_ERR";

    /**
     * 우수사업부 현황 - 페이징 조회
     * @param req
     * @param pageInfo
     * @return Map
     */
    public Map<String, Object>  getExcellentDivisionPages(SearchReq req, PageInfo pageInfo) {
        HashMap<String, Object> res = new HashMap<>();
        List<HashMap<String, Object>> config = mapper.selectGridConfigList(req);
        List<HashMap<String, Object>> target = mapper.selectTargetList(req);
        BizAssert.isTrue(!target.isEmpty(), "MSG_ALT_UNRG_EVL_OJ");
        res.put("config", config );
        res.put("result", mapper.selectExcellentDivisionPages(req, pageInfo, config, target));
        return res;
    }

    /**
     * 우수사업부 현황 - 엑셀 다운 목록 조회
     * @param req
     * @return Map
     */
    public List<HashMap<String, Object>> getExcellentDivisionsForExcelDownload(SearchReq req) {
        List<HashMap<String, Object>> config = mapper.selectGridConfigList(req);
        List<HashMap<String, Object>> target = mapper.selectTargetList(req);
        BizAssert.isTrue(!target.isEmpty(), "MSG_ALT_UNRG_EVL_OJ");
        return mapper.selectExcellentDivisionPages(req, config, target);
    }

    /**
     * 우수사업부 현황 - 목표 저장
     * @param reqs
     * @return int
     */
    public int saveExcellentDivision(List<SaveReq> reqs) {
        int processCount = 0;
        for(SaveReq req : reqs){
            WpsdExcellentDivisionDvo dvo = converter.mapToDvo(req);
            int resultCnt = mapper.updateExcellentDivisionBaseDtl(dvo);
            BizAssert.isTrue(resultCnt > 0, SAVE_ERROR_MESSAGE);
            resultCnt = mapper.updateExcellentDivisionItemization(dvo);
            BizAssert.isTrue(resultCnt > 0, SAVE_ERROR_MESSAGE);
            processCount++;
        }
        return processCount;
    }

    /**
     * 우수사업부 현황 - 경진조 조회
     * @param req
     * @return List<SearchContestRes>
     */
    public List<SearchContestRes> getContestGroupList(SearchContestReq req) {
        return mapper.selectContestGroupList(req);
    }

    /**
     * 우수사업부 현황 - 평가직책 조회
     * @param req
     * @return List<SearchEvlRsbRes>
     */
    public List<SearchEvlRsbRes> getEvaluationResponsibility(SearchEvlRsbReq req) {
        return mapper.selectEvaluationResponsibility(req);
    }

    /**
     * 우수사업부 현황 - 경진조 조회 (경진조 등록 팝업)
     * @param req
     * @return List<SearchContestRes>
     */
    public List<SearchContestPartnerRes> getContestResponsibilityGroupList(SearchContestPartnerReq req) {
        return mapper.selectContestResponsibilityGroupList(req);
    }
    /**
     * 우수사업부 현황 - 경진조 저장 (경진조 등록 팝업)
     * @param reqs
     * @return int
     */
    public int saveContestResponsibilityGroup(List<SaveContestPartnerReq> reqs) {
        int processCount = 0;
        for(SaveContestPartnerReq req : reqs){
            WpsdExcellentDivisionDvo dvo = converter.mapToDvo(req);
            int resultCnt = mapper.updateContestResponsibilityGroup(dvo);
            BizAssert.isTrue(resultCnt > 0, SAVE_ERROR_MESSAGE);
            processCount += resultCnt;
        }
        return processCount;
    }


}
