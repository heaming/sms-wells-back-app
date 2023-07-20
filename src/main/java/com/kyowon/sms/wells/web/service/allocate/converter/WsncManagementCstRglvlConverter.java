package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SavePartnerReq;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlBsAssignInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlExchangeInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSvpdAsnRsTfIzDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsncManagementCstRglvlConverter {
    WsncManagementCstRglvlExchangeInfoDvo mapSavePartnerReqToWsncManagementCstRglvlExchangeInfoDvo(
        SavePartnerReq dto
    );

    WsncManagementCstRglvlBsAssignInfoDvo mapSavePartnerReqToWsncManagementCstRglvlBsAssignInfoDvo(
        SavePartnerReq dto
    );

    WsncSvpdAsnRsTfIzDvo mapSavePartnerReqToWsncSvpdAsnRsTfIzDvo(SavePartnerReq dto);
}
