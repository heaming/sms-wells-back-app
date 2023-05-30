package com.kyowon.sms.wells.web.competence.voc.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshFalseVisitMgtDvo;

@Mapper(componentModel = "spring")
public interface WpshFalsehoodMgtConverter {

    WpshFalseVisitMgtDvo mapSaveReq(WpshFalseVisitMgtDto.SaveReq dto);

    WpshFalseVisitMgtDvo mapRemoveReq(WpshFalseVisitMgtDto.RemoveReq dto);
}
