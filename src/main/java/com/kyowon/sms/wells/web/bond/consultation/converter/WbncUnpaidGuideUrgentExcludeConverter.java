package com.kyowon.sms.wells.web.bond.consultation.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncUnpaidGuideUrgentExcludeDto.SaveReq;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncBondContactExcludeObjectIzDvo;

@Mapper(componentModel = "spring")
public interface WbncUnpaidGuideUrgentExcludeConverter {
    WbncBondContactExcludeObjectIzDvo mapSaveReqToExcludeObjectIzDvo(SaveReq req);
}
