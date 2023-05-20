package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.EditRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SaveRefundReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationInfoDvo;

@Mapper(componentModel = "spring")
public interface WwdbRefundApplicationConverter {
    WwdbRefundApplicationDvo mapSaveWwdbRefundApplicationDvo(SaveRefundReq req);

    WwdbRefundApplicationInfoDvo mapEditWwdbRefundApplicationDvo(EditRefundReq req);
}
