package com.kyowon.sms.wells.web.competence.affiars.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizMgtDto;
import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizMgtDvo;
import com.kyowon.sms.wells.web.competence.affiars.mapper.WwpseGenenalAffairsElcBizMgtMapper;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessManagerReportMgtDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpscBusinessManagerReportMgtDvo;
import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.competence.affiars.converter.WwpseGenenalAffairsElcBizMgtConverter;

import static com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwpseGenenalAffairsElcBizMgtService {

    private final WwpseGenenalAffairsElcBizMgtMapper mapper;
    private final WwpseGenenalAffairsElcBizMgtConverter converter;

    public PagingResult<SearchRes> getGenenalAffairsElcBizMgtPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WwpseGenenalAffairsElcBizMgtDvo> result = mapper.selectGenenalAffairsElcBizMgtPages(dto, pageInfo);
        PagingResult<WwpseGenenalAffairsElcBizMgtDto.SearchRes> converterResult = this.converter.dvoToSearchRes(result);

        converterResult.setPageInfo(result.getPageInfo());

        return converterResult;
    }

    public List<businessTypeRes> getBusinessTypeContent(SearchReq dto) {
        return mapper.selectPsgaRpotBizTpBas(dto);
    }

    public Map<String, List> getBusinessType(WwpseGenenalAffairsElcBizMgtDto.SearchReq dto) {

        Map<String, List> hMap = new HashMap<String, List>();

        hMap.put("businessType", mapper.selectPsgaRpotBizTpBas(dto));

        hMap.put("inChargeDepartment", mapper.selectOgbsMmOgIz(dto));

        return hMap;
    }

    public List<WwpseGenenalAffairsElcBizMgtDto.PrtnrRes> getPrtnrInfo(WwpseGenenalAffairsElcBizMgtDto.SearchReq dto) {
        return converter.dvoToPrtnrRes(mapper.selectOgbsPrtnrBas(dto));
    }

    public int saveBusinessManagerReport(List<WwpseGenenalAffairsElcBizMgtDto.SaveReq> dtos) {
        int res = 0;
        for (WwpseGenenalAffairsElcBizMgtDto.SaveReq dto : dtos) {
            if (dto.dgYn().equals("Y") && dto.rpotBizTpId().equals("30")) {
                WwpseGenenalAffairsElcBizMgtDto.CheckSearchReq checkSearchReq = new WwpseGenenalAffairsElcBizMgtDto.CheckSearchReq(dto.rpotBizTpId(), dto.dgYn());
                List<WwpseGenenalAffairsElcBizMgtDto.SearchRes> list = mapper.selectGenenalAffairsElcBizMgtPages(checkSearchReq);
                BizAssert.isTrue(list.size() == 0, "MSG_ALT_BIZ_TP_DG_PSB");
            }
        }

        for (WwpseGenenalAffairsElcBizMgtDto.SaveReq dto : dtos) {

            int result = mapper.updatePsgaRpotBizPsicBas(dto);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            res += result;
        }
        return res;
    }

    public int removeBusinessManagerReport(List<WwpseGenenalAffairsElcBizMgtDto.SaveReq> dtos) {
        int res = 0;
        for (WwpseGenenalAffairsElcBizMgtDto.SaveReq dto : dtos) {

            int result = mapper.deletePsgaRpotBizPsicBas(dto);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            res += result;
        }
        return res;
    }

    public int saveReportBusinessType(WwpseGenenalAffairsElcBizMgtDto.SaveReq dto) {

        int result = mapper.updatePsgaRpotBizTpBas(dto);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        return result;
    }

}
