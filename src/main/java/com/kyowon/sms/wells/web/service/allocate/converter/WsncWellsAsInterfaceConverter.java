package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncWellsAsInterfaceDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncWellsAsInterfaceCustInfoDvo;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WsncWellsAsInterfaceConverter {
    List<WsncWellsAsInterfaceDto.SearchCustInfoRes> mapAllListCustInfoDvoToListRes(List<WsncWellsAsInterfaceCustInfoDvo> dvos);
}
