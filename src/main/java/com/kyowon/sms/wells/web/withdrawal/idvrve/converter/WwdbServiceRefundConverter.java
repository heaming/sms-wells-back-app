package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbServiceRefundDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbServiceRefundDvo;

@Mapper(componentModel = "spring")
public interface WwdbServiceRefundConverter {
    WwdbServiceRefundDvo mapSaveWwdbServiceRefundDvo(SaveReq req);
}
