package com.kyowon.sms.wells.web.bond.credit.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchPaymentRes;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchReq;
import com.kyowon.sms.wells.web.bond.credit.dto.WbndRentalCbMgtObjectDto.SearchRes;
import com.kyowon.sms.wells.web.bond.credit.dvo.WbndRentalCbDelinquentIzDvo;

@Mapper(componentModel = "spring")
public interface WbndRentalCbMgtObjectConverter {
    WbndRentalCbDelinquentIzDvo mapSearchReqToRentalCbDlqIzDvo(SearchReq req);

    List<SearchRes> listRentalCbDlqIzDvoToSearchRes(List<WbndRentalCbDelinquentIzDvo> dvos);

    List<SearchPaymentRes> listRentalCbDlqIzDvoToSearchPaymentRes(List<WbndRentalCbDelinquentIzDvo> dvos);
}
