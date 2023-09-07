package com.kyowon.sms.wells.web.withdrawal.interfaces.converter;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbNotPaidMakeAPaymentRgstDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbNotPaidMakeAPaymentRgstReqDvo;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbNotPaidMakeAPaymentRgstConverter {

    WwdbNotPaidMakeAPaymentRgstReqDvo mapWwdbNotPaidMakeAPaymentRgstSaveReqToDvo(
        WwdbNotPaidMakeAPaymentRgstDto.SaveReq dto
    );
}
