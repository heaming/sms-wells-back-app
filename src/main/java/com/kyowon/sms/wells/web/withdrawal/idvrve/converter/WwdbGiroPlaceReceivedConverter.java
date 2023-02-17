package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroPlaceReceivedDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroPlaceReceivedDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbGiroPlaceReceivedConverter {

    WwdbGiroPlaceReceivedDvo mapSaveWwdbGiroPlaceReceivedDvo(SaveReq dto);
}
