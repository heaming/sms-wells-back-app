package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.CreateReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.SearchRes;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnCreateDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnIndividualSearchDvo;

@Mapper(componentModel = "spring", imports = {org.apache.commons.lang3.StringUtils.class})
public interface WsnaQomAsnConverter {

    @Mapping(target = "telNo", expression = "java(StringUtils.defaultString(dvo.getLocaraTno()) + StringUtils.defaultString(dvo.getExnoEncr()) + StringUtils.defaultString(dvo.getIdvTno()))")
    SearchRes mapWsnaQomAsnIndividualSearchDvoToSearchRes(WsnaQomAsnIndividualSearchDvo dvo);

    List<SearchRes> mapAllWsnaQomAsnIndividualSearchDvoToSearchRes(List<WsnaQomAsnIndividualSearchDvo> dvos);

    List<WsnaQomAsnCreateDvo> mapAllCreateReqToWsnaQomAsnCreateDvo(List<CreateReq> dtos);

}
