package com.kyowon.sms.wells.web.customer.prospective.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbNewReceiptMgtDto.ContactReq;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbPspcCstCnslBasDvo;

/**
 * <pre>
 * 고객 >>  신규접수 배정관리 Converter
 * </pre>
 *
 * @author junho.bae
 * @since 2023-07-01
 */
@Mapper(componentModel = "spring")
public interface WcsbNewReceiptMgtConverter {

    WcsbPspcCstCnslBasDvo mapContactReqToWcsbPspcCstCnslBasDvo(ContactReq dto);
}
