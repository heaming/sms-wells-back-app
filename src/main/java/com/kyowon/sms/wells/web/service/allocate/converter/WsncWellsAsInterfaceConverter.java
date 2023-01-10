package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsInterfaceDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsInterfaceCustInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsInterfaceRecInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsInterfaceServHistDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncAsInterfaceUsingPdutDvo;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WsncWellsAsInterfaceConverter {
    List<WsncAsInterfaceDto.SearchCustInfoRes> mapAllCustInfoDvoToRes(
        List<WsncAsInterfaceCustInfoDvo> dvos
    );

    List<WsncAsInterfaceDto.SearchRecInfoRes> mapAllRecInfoDvoToRes(
        List<WsncAsInterfaceRecInfoDvo> dvos
    );

    List<WsncAsInterfaceDto.SearchUsingProductsRes> mapAllUsingProductDvoToRes(
        List<WsncAsInterfaceUsingPdutDvo> dvos
    );

    List<WsncAsInterfaceDto.SearchServiceHistoryRes> mapAllServiceHistoryDvoToRes(
        List<WsncAsInterfaceServHistDvo> dvos
    );

}
