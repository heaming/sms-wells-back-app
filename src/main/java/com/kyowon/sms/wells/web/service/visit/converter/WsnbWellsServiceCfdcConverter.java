package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.FindOzRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWellsServiceCfdcDvo;

@Mapper(componentModel = "spring")
public interface WsnbWellsServiceCfdcConverter {
    FindOzRes mapWellsServiceCfdcDvoToFindOzRes(WsnbWellsServiceCfdcDvo dvo);
}
