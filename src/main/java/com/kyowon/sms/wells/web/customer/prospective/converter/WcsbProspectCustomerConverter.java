package com.kyowon.sms.wells.web.customer.prospective.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbCustomerInterfaceDto.CreateNewReceiptInquiryReq;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbCustomerInterfaceDto.CreateNewReceiptInquiryRes;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbInterfaceResultDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbNewReceiptInquiryDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspectCustomerBasDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspectCustomerCnslBasDvo;

@Mapper(componentModel = "spring")
public interface WcsbProspectCustomerConverter {

    WcsbNewReceiptInquiryDvo mapCreateNewReceiptInquiryReqToNewReceiptInquiryDvo(
        CreateNewReceiptInquiryReq dto
    );

    WcsbProspectCustomerBasDvo mapCreatePspcCstBas(WcsbNewReceiptInquiryDvo dvo);

    WcsbProspectCustomerCnslBasDvo mapCreatePspcCstCnslBas(WcsbNewReceiptInquiryDvo dvo);

    CreateNewReceiptInquiryRes mapCreateNewReceiptInquiryRes(WcsbInterfaceResultDvo dvo);
}
