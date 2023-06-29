package com.kyowon.sms.wells.web.closing.performance.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccNormalAccountExceptDto.SaveReq;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccNormalAccountExceptDvo;

@Mapper(componentModel = "spring")
public interface WdccNormalAccountExceptConverter {

    WdccNormalAccountExceptDvo mapSaveReqToWdccNormalAccountExceptDvo(SaveReq dto);

}
