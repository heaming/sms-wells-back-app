package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaRentalProductPriceCalculationDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaRentalProductPriceCalculationMapper;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaRentalProductPriceCalculationService {
    private final WctaRentalProductPriceCalculationMapper mapper;

    public List<WctaRentalProductPriceCalculationDvo> getRentalFeeCalculation(
        WctaRentalProductPriceCalculationDvo paramDvo
    ) {
        // 파라미터 유효성 체크
        ValidAssert.hasText(paramDvo.getPdCd()); // 상품코드
        ValidAssert.hasText(paramDvo.getSellTpCd()); // 판매유형코드
        ValidAssert.hasText(paramDvo.getSellChnlCd()); // 판매채널코드

        return mapper.selectRentalFeeCalculation(paramDvo);
    }
}
