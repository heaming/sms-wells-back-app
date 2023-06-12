package com.kyowon.sms.wells.web.service.allocate.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlBsAssignInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlExchangeInfoDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsncManagementCstRglvlConverter {
    WsncManagementCstRglvlExchangeInfoDvo mapSavePartnerReqToWsncManagementCstRglvlExchangeInfoDvo(
        WsncManagementCstRglvlDto.SavePartnerReq dto
    );

    WsncManagementCstRglvlBsAssignInfoDvo mapSavePartnerReqToWsncManagementCstRglvlBsAssignInfoDvo(
        WsncManagementCstRglvlDto.SavePartnerReq dto
    );
}
