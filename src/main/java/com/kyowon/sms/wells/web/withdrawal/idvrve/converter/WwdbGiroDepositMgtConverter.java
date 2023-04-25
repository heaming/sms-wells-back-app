package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SaveIntegrationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SaveErrosReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositErrorSaveDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositSaveDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositSaveInfoDvo;;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbGiroDepositMgtConverter {

    WwdbGiroDepositSaveDvo mapSaveWwwdbGiroDepositSaveDvo(SaveReq dto);

    WwdbGiroDepositErrorSaveDvo mapSaveGiroDepositErrorSaveDvo(SaveErrosReq dto);

    WwdbGiroDepositSaveInfoDvo mapSaveGiroDepositSaveDvo(SaveIntegrationReq dto);
}
