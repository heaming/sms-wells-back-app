package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountDepositInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountDepositInterfaceDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbVirtualAccountDepositInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbVirtualAccountDepositInterfaceService {

    private final WwdbVirtualAccountDepositInterfaceMapper mapper;

    public SearchRes saveVirtualAccountDeposit(
        SearchReq dto
    ) {
        if ("KICC".equals(dto.vacDvCd())) {
            mapper.updateVirtualAccountKiccDepositLedgerItemization(dto);
        }
        if ("STL".equals(dto.vacDvCd())) {
            mapper.updateVirtualAccountSettleBankDepositLedgerItemization(dto);
        }
        // 세틀뱅크 가상계좌인 경우 통합입금 완료 후 계좌해지처리
        if ("STL".equals(dto.vacDvCd())) {
            mapper.updateVirtualAccountSettleBankDepositFinish(dto);
        }
        return new SearchRes("Y");
    }
}
