package com.kyowon.sms.wells.web.bond.consultation.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SaveConfirmReq;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WbncRegularShippingResignConverter {
    WbncAuthorityResignIzDvo mapSaveConfirmReqToAuthorityResignIzDvo(SaveConfirmReq dto);
}
