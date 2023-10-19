package com.kyowon.sms.wells.web.competence.evaluate.service;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.SearchContestReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.SearchContestRes;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.SearchReq;
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

    /**
     * 우수사업부 현황 - 페이징 조회
     * @param req
     * @param pageInfo
     * @return Map
     */
    public HashMap<String, Object>  getExcellentDivisionPages(SearchReq req, PageInfo pageInfo) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        List<HashMap<String, Object>> config = mapper.selectGridConfigList(req);
        List<HashMap<String, Object>> target = mapper.selectTargetList(req);
        BizAssert.isTrue(target.size() > 0, "MSG_ALT_UNRG_EVL_OJ");
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
        BizAssert.isTrue(target.size() > 0, "MSG_ALT_UNRG_EVL_OJ");


        return mapper.selectExcellentDivisionPages(req, config, target);
    }

    /**
     * 우수사업부 현황 - 경진조 조회
     * @param req
     * @return List<SearchContestRes>
     */
    public List<SearchContestRes> getContestGroupList(SearchContestReq req) {
        return mapper.selectContestGroupList(req);
    }

}
