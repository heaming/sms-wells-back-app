package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositItemizationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositItemizationResultDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbDepositItemizationMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbDepositItemizationService {

    private final WwdbDepositItemizationMapper mapper;

    /**
     * 입금내역조회(모바일) - W-WD-S-0041
     * @param dvo
     * @return
     */
    public List<WwdbDepositItemizationResultDvo> getDepositItemizations(
        WwdbDepositItemizationDvo dvo
    ) {
        return mapper.selectDepositItemizations(dvo);
    }
}
