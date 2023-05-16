package com.kyowon.sms.wells.web.closing.sales.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.sales.converter.WdcbBusinessAtamAdjustMgtConverter;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SaveSlpnoReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchSapPdDvCdRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchSlpnoRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SearchTotalRes;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbBusinessAtamAdjustDvo;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbBusinessAtamAdjustMgtMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbBusinessAtamAdjustMgtService {
    private final WdcbBusinessAtamAdjustMgtMapper mapper;
    private final WdcbBusinessAtamAdjustMgtConverter converter;

    public List<SearchSapPdDvCdRes> getSapPdDvCds() {
        return mapper.selectSapPdDvCds();
    }

    public List<SearchTotalRes> getBusinessAtamTotals(SearchReq dto) {
        return mapper.selectBusinessAtamTotals(dto);
    }

    public List<SearchDetailRes> getBusinessAtamDetails(SearchReq dto) {
        return mapper.selectBusinessAtamDetails(dto);
    }

    public List<SearchSlpnoRes> getSapAlrpySlpnos(String sapAlrpySlpno) {
        return mapper.selectSapAlrpySlpnos(sapAlrpySlpno);
    }

    @Transactional
    public int saveSlpnoInitializes(List<SaveSlpnoReq> dtos) {
        int processCount = 0;
        for (SaveSlpnoReq dto : dtos) {
            WdcbBusinessAtamAdjustDvo dvo = converter.mapSaveSlpnoReqToWdcbBusinessAtamAdjustDvo(dto);
            int result = mapper.updateAllRepaymentBase(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            mapper.updateDepositSlip(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            processCount += result;
        }
        return processCount;
    }
}
