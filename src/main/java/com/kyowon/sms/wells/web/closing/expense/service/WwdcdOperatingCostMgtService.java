package com.kyowon.sms.wells.web.closing.expense.service;

import com.kyowon.sms.wells.web.closing.expense.dto.WwdcdOperatingCostMgtDto.*;
import com.kyowon.sms.wells.web.closing.expense.mapper.WwdcdOperatingCostMgtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WwdcdOperatingCostMgtService {

    private final WwdcdOperatingCostMgtMapper mapper;

    public SearchRes getWellsOrtCsRgstMngts(SearchReq req) {

        //운영비 금액현황
        //운영비 적요 현황
         /*if (registration.equals("OperatingCostSummaryPresentState")) {

            dvo = service.getWellsOrtCsRgstMngts(dto);
        } else if (registration.equals("OperatingCostAmountPresentState")) {
            //TODO 변경해야 할꺼
            // dto= service.getOperatingCostAmountPresentState(dto);
            dvo = service.getWellsOrtCsRgstMngts(dto);
        }*/

        SearchRes res = new SearchRes("", "", "");
        String registration = req.registration();

        switch (registration) {
            case "amount" -> {
                res = mapper.selectOperatingCostAmount(req);
            }
            case "summary" -> {
                res = mapper.selectOperatingCostSummary(req);
            }

        }
        return res;
    }

    public void saveWithholdingTaxCfdcAdd(FileReq req) {

        mapper.insertWithholdingTaxCfdc(req);
    }

    public SaveRes getWithholdingTaxCfdcDld(SaveReq req) {

        return mapper.selectWithholdingTaxCfdc(req);
    }
}
