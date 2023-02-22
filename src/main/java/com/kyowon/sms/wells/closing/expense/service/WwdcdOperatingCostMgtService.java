package com.kyowon.sms.wells.closing.expense.service;

import com.kyowon.sms.wells.closing.expense.dto.WOpcsRgstMngtDto.SearchReq;
import com.kyowon.sms.wells.closing.expense.dvo.WOpcsRgstMngtDvo;
import com.kyowon.sms.wells.closing.expense.mapper.WOpcsRgstMngtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WOpcsRgstMngtService {

    private final WOpcsRgstMngtMapper mapper;

    public WOpcsRgstMngtDvo getWellsOrtCsRgstMngts(SearchReq dto) {

        //운영비 금액현황
        //운영비 적요 현황
         /*if (registration.equals("OperatingCostSummaryPresentState")) {

            dvo = service.getWellsOrtCsRgstMngts(dto);
        } else if (registration.equals("OperatingCostAmountPresentState")) {
            //TODO 변경해야 할꺼
            // dto= service.getOperatingCostAmountPresentState(dto);
            dvo = service.getWellsOrtCsRgstMngts(dto);
        }*/

        WOpcsRgstMngtDvo dvo = new WOpcsRgstMngtDvo();
        String registration = dto.registration();

        switch (registration) {
            case "OperatingCostSummaryPresentState" -> {
                dvo = mapper.selectOperatingCostSummaryPresentState(dto);
            }
            case "OperatingCostAmountPresentState" -> {
                dvo = mapper.selectOperatingCostAmountPresentState(dto);
            }
            case "OperatingCostSummaryPresentStateTab" -> {
                dvo = mapper.selectOperatingCostSummaryPresentStateTab(dto);
            }
            case "OperatingCostAmountPresentStateTab" -> {
                dvo = mapper.selectOperatingCostAmountPresentStateTab(dto);
            }
        }

        return dvo;
    }
}
