package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.mapper.WdcdOperatingCostMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WdcdOperatingCostMgtService {

    private final WdcdOperatingCostMgtMapper mapper;
    
    public List<SearchAmountRes> getAmount(SearchReq req) {
        //운영비 금액현황
        //운영비 적요 현황
        return mapper.selectAmount(req);
    }

    public List<SearchSummaryRes> getSummary(SearchReq req) {
        //운영비 금액현황
        //운영비 적요 현황
        return mapper.selectSummary(req);
    }

    public void saveWithholdingTaxCfdcAdd(FileReq req) {

        mapper.insertWithholdingTaxCfdc(req);
    }

    public SaveRes getWithholdingTaxCfdcDld(SaveReq req) {

        return mapper.selectWithholdingTaxCfdc(req);
    }
}
