package com.kyowon.sms.wells.web.service.allocate.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncVisitCustomerRglvlStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncVisitCustomerRglvlStateDvo;

@Mapper(componentModel = "spring")
public interface WsncVisitCustomerRglvlStateConverter {
    List<SearchRes> mapDvoToSearchResPages(List<WsncVisitCustomerRglvlStateDvo> dvos);

    List<SearchRes> mapDvoToSearchRes(List<WsncVisitCustomerRglvlStateDvo> dvos);
}
