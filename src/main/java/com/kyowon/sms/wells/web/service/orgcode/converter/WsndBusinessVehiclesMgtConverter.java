package com.kyowon.sms.wells.web.service.orgcode.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesMgtDvo;

@Mapper(componentModel = "spring")
public interface WsndBusinessVehiclesMgtConverter {
    WsndBusinessVehiclesMgtDvo mapCreateReqToVehiclesDsbDvo(WsndBusinessVehiclesMgtDto.CreateReq dto);

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WsndBusinessVehiclesMgtDvo mapEditReqToVehiclesDsbDvo(WsndBusinessVehiclesMgtDto.EditReq dto);
}
