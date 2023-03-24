package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbEdiCardAprovalRefundInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbEdiCardAprovalRefundInterfaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WwdbEdiCardAprovalRefundInterfaceService {

    private final WwdbEdiCardAprovalRefundInterfaceMapper mapper;

    public List<WwdbEdiCardAprovalRefundInterfaceDto.SearchRes> getEdiCardAprovalRefunds(WwdbEdiCardAprovalRefundInterfaceDto.SearchReq dto) {
        return mapper.selectEdiCardAprovalRefunds(dto);
    }
}
