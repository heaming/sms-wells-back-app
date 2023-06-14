package com.kyowon.sms.wells.web.customer.prospective.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.ContactReq;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.SaveReq;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbNewReceiptDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbPspcCstCnslBasDvo;

@Mapper(componentModel = "spring")
public interface WcsbNewReceiptMgtConverter {

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WcsbNewReceiptDvo mapSaveReqToEpdyReceiptDvo(SaveReq dto);

    WcsbPspcCstCnslBasDvo mapContactReqToWcsbPspcCstCnslBasDvo(ContactReq dto);
}
