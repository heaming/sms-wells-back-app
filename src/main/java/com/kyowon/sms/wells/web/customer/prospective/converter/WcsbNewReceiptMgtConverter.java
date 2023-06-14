package com.kyowon.sms.wells.web.customer.prospective.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.ContactReq;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbPspcCstCnslBasDvo;

@Mapper(componentModel = "spring")
public interface WcsbNewReceiptMgtConverter {

    WcsbPspcCstCnslBasDvo mapContactReqToWcsbPspcCstCnslBasDvo(ContactReq dto);
}
