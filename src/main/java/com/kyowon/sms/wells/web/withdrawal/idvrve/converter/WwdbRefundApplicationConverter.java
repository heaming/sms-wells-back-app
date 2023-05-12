package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDvo;

@Mapper(componentModel = "spring")
public interface WwdbRefundApplicationConverter {
    WwdbRefundApplicationDvo mapSaveWwdbRefundApplicationDvo(SaveReq req);
}
