package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCorporationDepositSspMgtDto.CreateReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbCorporationDepositSspMgtDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbCorporationDepositSspMgtConverter {

    WwdbCorporationDepositSspMgtDvo mapSaveReq(CreateReq dto);
}
