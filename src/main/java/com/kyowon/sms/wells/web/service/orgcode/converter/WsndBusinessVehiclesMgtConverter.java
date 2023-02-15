package com.kyowon.sms.wells.web.service.orgcode.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesRegDto;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesRegDvo;

@Mapper(componentModel = "spring")
public interface WsndBusinessVehiclesMgtConverter {
    WsndBusinessVehiclesRegDvo mapCreateReqToBusinessVehiclesMgtDvo(WsndBusinessVehiclesRegDto.CreateReq dto);

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WsndBusinessVehiclesRegDvo mapEditReqToBusinessVehiclesMgtDvo(WsndBusinessVehiclesRegDto.EditReq dto);
}
