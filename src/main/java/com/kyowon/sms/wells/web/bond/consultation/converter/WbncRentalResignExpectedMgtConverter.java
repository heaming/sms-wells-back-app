package com.kyowon.sms.wells.web.bond.consultation.converter;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.SaveCancelReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.SaveConfirmReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.SaveReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WbncRentalResignExpectedMgtConverter {
    List<SearchRes> listAuthorityResignIzToSearchRes(List<WbncAuthorityResignIzDvo> dvos);

    WbncAuthorityResignIzDvo mapSaveReqToAuthorityResignIz(SaveReq dto);

    SaveCancelReq mapSaveReqToCancleDto(SaveConfirmReq dto);
}
