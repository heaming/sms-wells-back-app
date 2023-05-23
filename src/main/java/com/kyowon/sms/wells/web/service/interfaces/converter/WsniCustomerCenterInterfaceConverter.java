package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerCenterInterfaceDto.CreateShpadrReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniCustomerCenterInterfaceDto.EditShpadrReq;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCustomerCenterInterfaceDvo;

@Mapper(componentModel = "spring")
public interface WsniCustomerCenterInterfaceConverter {
    WsniCustomerCenterInterfaceDvo mapCreateShpadrResToCenterInterfaceDvo(CreateShpadrReq dto);

    WsniCustomerCenterInterfaceDvo mapEditShpadrResToCenterInterfaceDvo(EditShpadrReq dto);
}
