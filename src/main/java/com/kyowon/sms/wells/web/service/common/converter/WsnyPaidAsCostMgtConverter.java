package com.kyowon.sms.wells.web.service.common.converter;

import com.kyowon.sms.wells.web.service.common.dto.WsnyPaidAsCostMgtDto.*;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyPaidAsCostMgtDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsnyPaidAsCostMgtConverter {

    WsnyPaidAsCostMgtDvo mapSaveReqToWsnyPaidAsCostMgtDvo(SaveReq dto);

}
