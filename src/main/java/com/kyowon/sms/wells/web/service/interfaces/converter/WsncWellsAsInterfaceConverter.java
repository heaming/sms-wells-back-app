package com.kyowon.sms.wells.web.service.interfaces.converter;

import java.util.List;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniAsSidingChangeInterfaceDvo;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsInterfaceDto.*;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCustomerInformationDvo;

@Mapper(componentModel = "spring")
public interface WsncWellsAsInterfaceConverter {
    List<SearchCustInfoRes> mapAllCustInfoDvoToRes(
        List<WsncAsInterfaceCustInfoDvo> dvos
    );

    List<SearchRecInfoRes> mapAllRecInfoDvoToRes(
        List<WsncAsInterfaceRecInfoDvo> dvos
    );

    List<SearchUsingProductsRes> mapAllUsingProductDvoToRes(
        List<WsncAsInterfaceUsingPdutDvo> dvos
    );

    List<SearchServiceHistoryRes> mapAllServiceHistoryDvoToRes(
        List<WsncAsInterfaceServHistDvo> dvos
    );

    List<SearchServiceContentsRes> mapAllServiceContentsDvoToRes(
        List<WsncAsInterfaceServContDvo> dvos
    );

    SearchCustomerInformationRes mapCustomerInformationDvoToRes(WsniCustomerInformationDvo dvo);

    SearchAsSidingChangeRes mapAsSidingChangeDvoToRes(WsniAsSidingChangeInterfaceDvo dvo);

}
