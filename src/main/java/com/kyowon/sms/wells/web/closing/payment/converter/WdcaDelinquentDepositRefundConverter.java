package com.kyowon.sms.wells.web.closing.payment.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDelinquentDepositRefundDto.SaveReq;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaDelinquentDepositRefundDvo;

@Mapper(componentModel = "spring")
public interface WdcaDelinquentDepositRefundConverter {
    WdcaDelinquentDepositRefundDvo mapSaveReqToWdcaDelinquentDepositRefundDvo(SaveReq dto);
}
