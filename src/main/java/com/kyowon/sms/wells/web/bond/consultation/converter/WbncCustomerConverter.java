package com.kyowon.sms.wells.web.bond.consultation.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SaveCounselReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SaveUnuitmCnReq;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncCustomerDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WbncCustomerConverter {

    WbncCustomerDvo mapSaveReqToWbncUnuitmCnDvo(SaveUnuitmCnReq dto);

    WbncCustomerDvo mapSaveReqToWbncCounselDvo(SaveCounselReq dto);

}
