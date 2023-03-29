package com.kyowon.sms.wells.web.service.common.converter;

import com.kyowon.sms.wells.web.service.common.dto.WsnyRecapAsSvCsMngtDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyRecapAsSvCsMngtDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsnyRecapAsSvCsMngtConverter {

    WsnyRecapAsSvCsMngtDvo mapSaveReqToWsnyRecapAsSvCsMngtDvo(SaveReq dto);

}
