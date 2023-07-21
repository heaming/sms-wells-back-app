package com.kyowon.sms.wells.web.bond.consultation.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.SaveReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;

@Mapper(componentModel = "spring")
public interface WbncRentalResignExpectedMgtConverter {
    List<SearchRes> ListAuthorityResignIzToSearchRes(List<WbncAuthorityResignIzDvo> dvos);

    WbncAuthorityResignIzDvo mapSaveReqToAuthorityResignIz(SaveReq dto);
}
