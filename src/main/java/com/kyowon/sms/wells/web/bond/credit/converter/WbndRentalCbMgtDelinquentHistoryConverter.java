package com.kyowon.sms.wells.web.bond.credit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SaveReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtDelinquentHistoryDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbDelinquentIzDvo;

@Mapper(componentModel = "spring")
public interface WbndRentalCbMgtDelinquentHistoryConverter {
    WbndRentalCbDelinquentIzDvo mapSearchReqToRentalCbDlqIzDvo(SearchReq req);

    WbndRentalCbDelinquentIzDvo mapSaveReqToRentalCbDlqIzDvo(SaveReq req);

    List<SearchRes> listRentalCbDlqIzDvoToSearchRes(List<WbndRentalCbDelinquentIzDvo> dvos);
}
