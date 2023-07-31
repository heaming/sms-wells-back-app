package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsRevisitRateListDto;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAsRevisitRateListDvo;

@Mapper(componentModel = "spring")
public interface WsnbAsRevisitRateListConverter {

    List<WsnbAsRevisitRateListDto.SearchRes> mapAllAsRevisitRateListDvoToSearchRes(List<WsnbAsRevisitRateListDvo> dvos);

}
