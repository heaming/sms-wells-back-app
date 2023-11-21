package com.kyowon.sms.wells.web.competence.report.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessCellPhoneChMgtDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpsgBusinessCellPhoneChMgtDvo;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.competence.report.converter.WwpsgBusinessCellPhoneChMgtConverter;
import com.kyowon.sms.wells.web.competence.report.mapper.WwpsgBusinessCellPhoneChMgtMapper;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessCellPhoneChMgtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WwpsgBusinessCellPhoneChMgtService {

    private final WwpsgBusinessCellPhoneChMgtMapper mapper;
    private final WwpsgBusinessCellPhoneChMgtConverter converter;
    private final AttachFileService attachFileService;

    public PagingResult<SearchRes> getBusinessCellPhoneChMgtPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WwpsgBusinessCellPhoneChMgtDvo> result = mapper.selectBusinessCellPhoneChMgtPages(dto, pageInfo);
        PagingResult<WwpsgBusinessCellPhoneChMgtDto.SearchRes> converterResult = this.converter.dvoToSearchRes(result);

        converterResult.setPageInfo(result.getPageInfo());

        return converterResult;
    }

    public WwpsgBusinessCellPhoneChMgtDto.PopupSearchRes getRentManagementPopup(WwpsgBusinessCellPhoneChMgtDto.SearchReq dto) {

        return converter.dvoToPopupSearchRes(mapper.selectPsgaRpotbizProcsIz(dto));
    }

    public Map<String, List> getBaseInfo(WwpsgBusinessCellPhoneChMgtDto.BaseSearchReq dto) {

        Map<String, List> hMap = new HashMap<String, List>();

        hMap.put("businessType", mapper.selectPsgaRpotBizTpBas(dto));
        List<WwpsgBusinessCellPhoneChMgtDto.BaseSearchRes> list = converter.dvoToBaseSearchRes(mapper.selectPsgaRpotBizPsicBas(dto));
        hMap.put("BaseSearchInfo", list);

        return hMap;
    }

    public List<WwpsgBusinessCellPhoneChMgtDto.SearchRes> getBusinessCellPhoneChMgtsForExcelDownload(WwpsgBusinessCellPhoneChMgtDto.SearchReq dto) {
        return this.converter.dvoToSearchRes(mapper.selectRentManagementPages(dto));
    }

    public WwpsgBusinessCellPhoneChMgtDto.SellPrtnrRes getPrtnrInfo(WwpsgBusinessCellPhoneChMgtDto.BaseSearchReq dto) {
        return this.converter.dvoToSellPrtnrRes(mapper.selectOgbsMmPrtnrIz(dto));
    }


    @Transactional
    public int saveBusinessSellPhone(WwpsgBusinessCellPhoneChMgtDto.SaveReq req) throws Exception{
        int processCount = 0;

        WwpsgBusinessCellPhoneChMgtDvo dvo = converter.reqToWwpsgBusinessCellPhoneChMgtDvo(req);

        processCount = mapper.insertPsgaRpotBizAplcIz(dvo);
        processCount += mapper.insertPsgaRpotBizProcsIz(dvo);
        processCount += mapper.insertPsgaCralTelChAplcIz(dvo);

        BizAssert.isTrue(processCount == 3, "MSG_ALT_SVE_ERR");

        return processCount;
    }

    public int saveBusiPhoneRentProcs(WwpsgBusinessCellPhoneChMgtDto.SaveReq req) throws Exception{
        int processCount = 0;

        processCount = mapper.updatePsgaRpotBizProcsIz(req);

        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        return processCount;
    }
}
