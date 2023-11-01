package com.kyowon.sms.wells.web.competence.report.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessManagerReportMgtDto;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessSpptMngerRpotMgtDto;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessSpptMngerRpotMgtDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpscBusinessManagerReportMgtDvo;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpsgBusinessSpptMngerRpotMgtDvo;
import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.competence.report.converter.WwpsgBusinessSpptMngerRpotMgtConverter;
import com.kyowon.sms.wells.web.competence.report.mapper.WwpsgBusinessSpptMngerRpotMgtMapper;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessSpptMngerRpotMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwpsgBusinessSpptMngerRpotMgtService {

    private final WwpsgBusinessSpptMngerRpotMgtMapper mapper;
    private final WwpsgBusinessSpptMngerRpotMgtConverter converter;

    public PagingResult<SearchRes> getBusinessSpptMngerRpotMgtPages(SearchReq dto, PageInfo pageInfo) {

        PagingResult<WwpsgBusinessSpptMngerRpotMgtDvo> result = mapper.selectBusinessSpptMngerRpotMgtPages(dto, pageInfo);
        PagingResult<WwpsgBusinessSpptMngerRpotMgtDto.SearchRes> converterResult = this.converter.dvoToSearchRes(result);

        converterResult.setPageInfo(result.getPageInfo());

        return converterResult;
    }

    public Map<String, List> getBusinessType(WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto) {

        Map<String, List> hMap = new HashMap<String, List>();

        hMap.put("businessType", mapper.selectPsgaRpotBizTpBas(dto));
        hMap.put("inChargeDepartment", mapper.selectOgbsMmOgIz(dto));

        return hMap;
    }

    public List<WwpsgBusinessSpptMngerRpotMgtDto.businessTypeRes> getBusinessTypeContent(WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto) {
        return mapper.selectPsgaRpotBizTpBas(dto);
    }

    public List<WwpsgBusinessSpptMngerRpotMgtDto.PrtnrRes> getPrtnrInfo(WwpsgBusinessSpptMngerRpotMgtDto.SearchReq dto) {
        return converter.dvoToPrtnrRes(mapper.selectOgbsPrtnrBas(dto));
    }

    public int saveBusinessManagerReport(List<WwpsgBusinessSpptMngerRpotMgtDto.SaveReq> dtos) {
        int res = 0;
        for (WwpsgBusinessSpptMngerRpotMgtDto.SaveReq dto : dtos) {
            if (dto.dgYn().equals("Y") && dto.rpotBizTpId().equals("30")) {
                WwpsgBusinessSpptMngerRpotMgtDto.CheckSearchReq checkSearchReq = new WwpsgBusinessSpptMngerRpotMgtDto.CheckSearchReq(dto.rpotBizTpId(), dto.dgYn());
                List<WwpsgBusinessSpptMngerRpotMgtDto.SearchRes> list = mapper.selectBusinessManagerReportMgtPages(checkSearchReq);
                BizAssert.isTrue(list.size() == 0, "MSG_ALT_BIZ_TP_DG_PSB");
            }
        }

        for (WwpsgBusinessSpptMngerRpotMgtDto.SaveReq dto : dtos) {

            int result = mapper.updatePsgaRpotBizPsicBas(dto);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            res += result;
        }
        return res;
    }

    public int removeBusinessManagerReport(List<WwpsgBusinessSpptMngerRpotMgtDto.SaveReq> dtos) {
        int res = 0;
        for (WwpsgBusinessSpptMngerRpotMgtDto.SaveReq dto : dtos) {

            int result = mapper.deletePsgaRpotBizPsicBas(dto);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            res += result;
        }
        return res;
    }

    public int saveReportBusinessType(WwpsgBusinessSpptMngerRpotMgtDto.SaveReq dto) {

        int result = mapper.updatePsgaRpotBizTpBas(dto);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        return result;
    }
}
