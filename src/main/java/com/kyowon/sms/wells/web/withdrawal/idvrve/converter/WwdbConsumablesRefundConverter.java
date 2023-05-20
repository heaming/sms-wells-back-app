package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbConsumablesRefundDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbConsumablesRefundDvo;

@Mapper(componentModel = "spring")
public interface WwdbConsumablesRefundConverter {
    WwdbConsumablesRefundDvo mapRemoveConsumablesRefundDvo(RemoveReq dto);

    WwdbConsumablesRefundDvo mapSaveConsumablesRefundDvo(SaveReq dto);
}
