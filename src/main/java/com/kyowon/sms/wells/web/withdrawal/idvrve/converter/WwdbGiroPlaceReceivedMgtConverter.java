package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroPlaceReceivedMgtDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbGiroPlaceReceivedMgtConverter {

    WwdbGiroPlaceReceivedMgtDvo mapSaveWwdbGiroPlaceReceivedDvo(SaveReq dto);
}
