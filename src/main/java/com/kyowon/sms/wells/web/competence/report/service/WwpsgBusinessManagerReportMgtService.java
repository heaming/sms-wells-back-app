package com.kyowon.sms.wells.web.competence.report.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.common.web.bond.consultation.dto.ZbncBondCollectionRelayServiceDto;
import com.kyowon.sms.common.web.bond.consultation.dvo.ZbncBondCollectionRelayServiceDvo;
import com.kyowon.sms.common.web.withdrawal.idvrve.dto.ZwdbDepositItemizationBulkPrntDto;
import com.kyowon.sms.common.web.withdrawal.idvrve.dvo.ZwdbDepositIzshFwDvo;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgRentManagementDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpscBusinessManagerReportMgtDvo;
import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.competence.report.converter.WwpsgBusinessManagerReportMgtConverter;
import com.kyowon.sms.wells.web.competence.report.mapper.WwpsgBusinessManagerReportMgtMapper;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessManagerReportMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwpsgBusinessManagerReportMgtService {

    private final WwpsgBusinessManagerReportMgtMapper mapper;
    private final WwpsgBusinessManagerReportMgtConverter converter;

    public PagingResult<SearchRes> getBusinessManagerReportMgtPages(SearchReq dto, PageInfo pageInfo) {

        PagingResult<WwpscBusinessManagerReportMgtDvo> result = mapper.selectBusinessManagerReportMgtPages(dto, pageInfo);
        PagingResult<SearchRes> converterResult = this.converter.dvoToSearchRes(result);

        converterResult.setPageInfo(result.getPageInfo());

        return converterResult;
    }

    public Map<String, List> getBusinessType(SearchReq dto) {

        Map<String, List> hMap = new HashMap<String, List>();

        hMap.put("businessType", mapper.selectPsgaRpotBizTpBas(dto));

        hMap.put("inChargeDepartment", mapper.selectOgbsMmOgIz(dto));

        return hMap;
    }

    public List<businessTypeRes> getBusinessTypeContent(SearchReq dto) {
        return mapper.selectPsgaRpotBizTpBas(dto);
    }

    public List<PrtnrRes> getPrtnrInfo(SearchReq dto) {
        return converter.dvoToPrtnrRes(mapper.selectOgbsPrtnrBas(dto));
    }

    public int saveBusinessManagerReport(List<SaveReq> dtos) {
        int res = 0;
        for (SaveReq dto : dtos) {
            if (dto.dgYn().equals("Y")) {
                CheckSearchReq checkSearchReq = new CheckSearchReq(dto.rpotBizTpId(), dto.dgYn());
                List<SearchRes> list = mapper.selectBusinessManagerReportMgtPages(checkSearchReq);
                BizAssert.isTrue(list.size() == 0, "MSG_ALT_BIZ_TP_DG_PSB");
            }
        }

        for (SaveReq dto : dtos) {

            int result = mapper.updatePsgaRpotBizPsicBas(dto);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            res += result;
        }
        return res;
    }

    public int removeBusinessManagerReport(List<SaveReq> dtos) {
        int res = 0;
        for (SaveReq dto : dtos) {

            int result = mapper.deletePsgaRpotBizPsicBas(dto);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            res += result;
        }
        return res;
    }

    public int saveReportBusinessType(SaveReq dto) {

        int result = mapper.updatePsgaRpotBizTpBas(dto);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        return result;
    }

}
