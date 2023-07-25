package com.kyowon.sms.wells.web.service.visit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbAsProcsAgrgListDto;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbAsProcsAgrgListDvo;

@Mapper(componentModel = "spring")
public interface WsnbAsProcsAgrgListConverter {

    List<WsnbAsProcsAgrgListDto.SearchRes> mapAllAsProcsAgrgListDvoToSearchRes(List<WsnbAsProcsAgrgListDvo> dvos);

}
