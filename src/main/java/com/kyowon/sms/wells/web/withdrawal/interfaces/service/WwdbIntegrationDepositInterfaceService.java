package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchCompanyCodeReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchCompanyCodeRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchDepoCodeReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchDepoCodeRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchDetailTradeReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchDetailTradeRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchItemizationReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchItemizationRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbIntegrationDepositInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbIntegrationDepositInterfaceService {

    private final WwdbIntegrationDepositInterfaceMapper mapper;

    public List<SearchCompanyCodeRes> getIntegrationDepositCompanyCode(SearchCompanyCodeReq dto) {
        return mapper.selectIntegrationDepositCompanyCode(dto);
    }
    
    public List<SearchDepoCodeRes> getIntegrationDepositDepoCode(SearchDepoCodeReq dto) {
        return mapper.selectIntegrationDepositDepoCode(dto);
    }

    public List<SearchDetailTradeRes> getIntegrationDepositDetailTrades(SearchDetailTradeReq dto) {
        return mapper.selectIntegrationDepositDetailTrades(dto);
    }
    
    public List<SearchItemizationRes> getIntegrationDepositItemizations(SearchItemizationReq dto) {
        return mapper.selectIntegrationDepositItemizations(dto);
    }
    
}
