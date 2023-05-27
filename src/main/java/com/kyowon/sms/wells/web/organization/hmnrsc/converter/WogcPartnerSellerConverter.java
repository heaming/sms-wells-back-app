package com.kyowon.sms.wells.web.organization.hmnrsc.converter;


import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmRes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WogcPartnerSellerConverter {

    List<SearchInformationConfirmRes> mapAllWogcPartnerSellerDvoToSearchInformationConfirmRes(List<WogcPartnerSellerDvo> dvos);
}
