package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchCompanyCodeReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchCompanyCodeRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchDepoCodeReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchDepoCodeRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchDetailTradeReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchDetailTradeRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchItemizationReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbIntegrationDepositInterfaceDto.SearchItemizationRes;

@Mapper
public interface WwdbIntegrationDepositInterfaceMapper {
    
    List<SearchCompanyCodeRes> selectIntegrationDepositCompanyCode(SearchCompanyCodeReq dto);
    
    List<SearchDepoCodeRes> selectIntegrationDepositDepoCode(SearchDepoCodeReq dto);
    
    List<SearchDetailTradeRes> selectIntegrationDepositDetailTrades(SearchDetailTradeReq dto);

    List<SearchItemizationRes> selectIntegrationDepositItemizations(SearchItemizationReq dto);
    
}
