package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.SearchReq;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationSearchDvo;

@Mapper(componentModel = "spring", imports = {org.apache.commons.lang3.StringUtils.class})
public interface WsnaItemBaseInformationConverter {

    @Mapping(target = "itmPdNm", expression = "java(StringUtils.isNotEmpty(dto.itmPdNm()) ? dto.itmPdNm().replace(\"%\", \"\\\\%\"): \"\")")
    WsnaItemBaseInformationSearchDvo mapSearchReqToWsnaItemBaseInformationSearchDvo(SearchReq dto);

}
