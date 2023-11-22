package com.kyowon.sms.wells.web.competence.report.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kyowon.sms.common.web.competence.tasks.dto.ZpsiBusinessRequestDto;
import com.kyowon.sms.common.web.competence.tasks.dvo.ZpsiBusinessRequestDvo;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgBusinessManagerReportMgtDto;
import com.kyowon.sms.wells.web.competence.report.dto.WwpsgRentManagementDto;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpscBusinessManagerReportMgtDvo;
import com.kyowon.sms.wells.web.competence.report.dvo.WwpsgRentManagementDvo;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.competence.report.converter.WwpsgRentManagementConverter;
import com.kyowon.sms.wells.web.competence.report.mapper.WwpsgRentManagementMapper;

import static com.kyowon.sms.wells.web.competence.report.dto.WwpsgRentManagementDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WwpsgRentManagementService {

    private final WwpsgRentManagementMapper mapper;
    private final WwpsgRentManagementConverter converter;
    private final AttachFileService attachFileService;

    public PagingResult<SearchRes> getRentManagementPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WwpsgRentManagementDvo> result = mapper.selectRentManagementPages(dto, pageInfo);
        PagingResult<WwpsgRentManagementDto.SearchRes> converterResult = this.converter.dvoToSearchRes(result);

        converterResult.setPageInfo(result.getPageInfo());

        return converterResult;
    }

    public PopupSearchRes getRentManagementPopup(SearchReq dto) {
        return converter.dvoToPopupSearchRes(mapper.selectPsgaRpotbizProcsIz(dto));
    }

    public Map<String, List> getBaseInfo(BaseSearchReq dto) {

        Map<String, List> hMap = new HashMap<String, List>();

        hMap.put("businessType", mapper.selectPsgaRpotBizTpBas(dto));
        List<BaseSearchRes> list = converter.dvoToBaseSearchRes(mapper.selectPsgaRpotBizPsicBas(dto));
        hMap.put("BaseSearchInfo", list);

        if (list.size() > 0) {
            BaseSearchReq dto2 = new BaseSearchReq(dto.rpotBizTpId(), dto.rpotBizTpDvCd(), list.get(0).ogTpCd());
            hMap.put("bldInfo", mapper.selectOgbsBldBas(dto2));
        }

        return hMap;
    }

    public List<SearchRes> getRentManagementsForExcelDownload(SearchReq dto) {
        return this.converter.dvoToSearchRes(mapper.selectRentManagementPages(dto));
    }

    @Transactional
    public int saveRentManagement(SaveReq req) throws Exception{
        int processCount = 0;

        WwpsgRentManagementDvo dvo = converter.reqToWwpsgRentManagementDvo(req);

        processCount = mapper.insertPsgaRpotBizAplcIz(dvo);
        processCount += mapper.insertPsgaRpotBizProcsIz(dvo);
        processCount += mapper.insertPsgaRntAplcIz(dvo);

        BizAssert.isTrue(processCount == 3, "MSG_ALT_SVE_ERR");
        attachFileService.saveAttachFiles("ATG_PSI_BUSINESS_REQUEST", dvo.getRpotBizAplcId(), dvo.getAttachFiles());

        return processCount;
    }

    public int saveRentProcs(SaveReq req) throws Exception{
        int processCount = 0;

        processCount = mapper.updatePsgaRpotBizProcsIz(req);

        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        return processCount;
    }
}
