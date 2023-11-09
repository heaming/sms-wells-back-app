package com.kyowon.sms.wells.web.competence.affiars.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto;
import com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkPsDto;
import com.kyowon.sms.wells.web.competence.affiars.dvo.WwpseGenenalAffairsElcBizAkDvo;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgRentManagementDto;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.competence.affiars.converter.WwpseGenenalAffairsElcBizAkConverter;
import com.kyowon.sms.wells.web.competence.affiars.mapper.WwpseGenenalAffairsElcBizAkMapper;

import static com.kyowon.sms.wells.web.competence.affiars.dto.WwpseGenenalAffairsElcBizAkDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WwpseGenenalAffairsElcBizAkService {

    private final WwpseGenenalAffairsElcBizAkMapper mapper;
    private final WwpseGenenalAffairsElcBizAkConverter converter;
    private final AttachFileService attachFileService;

    public PagingResult<SearchRes> getGenenalAffairsElcBizAkPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WwpseGenenalAffairsElcBizAkDvo> result = mapper.selectGenenalAffairsElcBizAkPages(dto, pageInfo);
        PagingResult<SearchRes> converterResult = this.converter.dvoToSearchRes(result);

        converterResult.setPageInfo(result.getPageInfo());

        return converterResult;
    }

    public SearchRes getRentManagementPopup(SearchReq dto) {
        return converter.dvoToSearchRes(mapper.selectPsgaRpotbizProcsIz(dto));
    }

    public Map<String, List> getBusinessType(BaseSearchReq dto) {
        Map<String, List> hMap = new HashMap<String, List>();
        hMap.put("businessType", mapper.selectPsgaRpotBizTpBas(dto));
        hMap.put("bldInfo", mapper.selectOgbsMmOgIz(dto));
        hMap.put("prtnrInfo", converter.dvoToBaseSearchRes(mapper.selectOgbsPrtnrBas(dto)));
        return hMap;
    }

    public List<BaseSearchRes> getBaseInfo(BaseSearchReq dto) {
        return converter.dvoToBaseSearchRes(mapper.selectPsgaRpotBizPsicBas(dto));
    }

    @Transactional
    public int saveGenenalAffairsElcBizAk(SaveReq req) throws Exception{
        int processCount = 0;

        WwpseGenenalAffairsElcBizAkDvo dvo = converter.reqToWwpseGenenalAffairsElcBizAkDvo(req);

        processCount = mapper.insertPsgaRpotBizAplcIz(dvo);
        processCount += mapper.insertPsgaRpotBizProcsIz(dvo);

        BizAssert.isTrue(processCount == 2, "MSG_ALT_SVE_ERR");
        attachFileService.saveAttachFiles("ATG_PSI_BUSINESS_REQUEST", dvo.getRpotBizAplcId(), dvo.getAttachFiles());

        return processCount;
    }

    public int saveRentProcs(SaveReq req) throws Exception{
        int processCount = 0;

        processCount = mapper.updatePsgaRpotBizProcsIz(req);

        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        return processCount;
    }

    public List<WwpseGenenalAffairsElcBizAkDto.SearchRes> getGenenalAffairsElcBizAkForExcelDownload(WwpseGenenalAffairsElcBizAkDto.SearchReq dto) {
        return mapper.selectGenenalAffairsElcBizAkPages(dto);
    }
}
