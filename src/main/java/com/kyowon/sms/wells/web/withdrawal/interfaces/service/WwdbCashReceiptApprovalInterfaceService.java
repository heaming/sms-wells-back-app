package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbCashReceiptApprovalInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbCashReceiptApprovalInterfaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WwdbCashReceiptApprovalInterfaceService {

    private final WwdbCashReceiptApprovalInterfaceMapper mapper;

    public List<WwdbCashReceiptApprovalInterfaceDto.SearchRes> getCashReceiptApprovalItemizations(WwdbCashReceiptApprovalInterfaceDto.SearchReq dto) {
        return mapper.selectCashReceiptApprovalItemizations(dto);
    }

}
