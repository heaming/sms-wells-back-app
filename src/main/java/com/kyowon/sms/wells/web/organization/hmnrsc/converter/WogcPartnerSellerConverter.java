package com.kyowon.sms.wells.web.organization.hmnrsc.converter;


import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerInterfaceDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerSellerDto.SearchInformationConfirmRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerSellerInterfaceDvo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WogcPartnerSellerConverter {

    List<SearchInformationConfirmRes> mapAllWogcPartnerSellerDvoToSearchInformationConfirmRes(List<WogcPartnerSellerDvo> dvos);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dvo.errCd", target = "errCd")
    @Mapping(source = "dvo.errNm", target = "errNm")
    @Mapping(source = "dvo.prtnrNo", target = "prtnrNo")
    @Mapping(source = "dvo.prtnrKnm", target = "prtnrKnm")
    @Mapping(source = "dvo.cralLocaraTno", target = "cralLocaraTno")
    @Mapping(source = "dvo.mexnoEncr", target = "mexnoEncr")
    @Mapping(source = "dvo.cralIdvTno", target = "cralIdvTno")
    @Mapping(source = "dvo.dgr3LevlOgCd", target = "dgr3LevlOgCd")
    @Mapping(source = "dvo.dgr3LevlOgNm", target = "dgr3LevlOgNm")
    List<WogcPartnerSellerInterfaceDto.SearchWMRes> mapAllWogcPartnerSellerInterfaceDvoToSearchWMRes(List<WogcPartnerSellerInterfaceDvo> dvos);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dvo.errCd", target = "errCd")
    @Mapping(source = "dvo.errNm", target = "errNm")
    @Mapping(source = "dvo.prtnrNo", target = "prtnrNo")
    @Mapping(source = "dvo.prtnrKnm", target = "prtnrKnm")
    @Mapping(source = "dvo.cralLocaraTno", target = "cralLocaraTno")
    @Mapping(source = "dvo.mexnoEncr", target = "mexnoEncr")
    @Mapping(source = "dvo.cralIdvTno", target = "cralIdvTno")
    @Mapping(source = "dvo.ogCd", target = "ogCd")
    @Mapping(source = "dvo.ogNm", target = "ogNm")
    List<WogcPartnerSellerInterfaceDto.SearchRecentContractRes> mapAllWogcPartnerSellerInterfaceDvoToSearchRecentContractRes(List<WogcPartnerSellerInterfaceDvo> dvos);
}
