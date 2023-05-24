package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositComparisonComfirmationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbIntegrationDepositInfoDvo;

@Mapper
public interface WwdbDepositMapper {
    List<WwdbIntegrationDepositInfoDvo> selectIntegrationDepositInfos(WwdbDepositDto.SearchReq dto);

    List<WwdbIntegrationDepositInfoDvo> selectReceiveAskDetailInfos(String itgDpNo);

    int insertDepositComparisonComfirmation(WwdbDepositComparisonComfirmationDvo dpCrcnfDvo);

    int insertVirtualAccountDistribution(String itgDpNo);

}
