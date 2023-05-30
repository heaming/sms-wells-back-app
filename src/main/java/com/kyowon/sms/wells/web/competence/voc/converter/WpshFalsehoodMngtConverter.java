package com.kyowon.sms.wells.web.competence.voc.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMngtDto;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshFalseVisitMngtDvo;

@Mapper(componentModel = "spring")
public interface WpshFalsehoodMngtConverter {

    WpshFalseVisitMngtDvo mapSaveReq(WpshFalseVisitMngtDto.SaveReq dto);

    WpshFalseVisitMngtDvo mapRemoveReq(WpshFalseVisitMngtDto.RemoveReq dto);
}
