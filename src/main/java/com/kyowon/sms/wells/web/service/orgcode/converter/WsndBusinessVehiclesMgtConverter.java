package com.kyowon.sms.wells.web.service.orgcode.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.EditReq;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesMgtDvo;

@Mapper(componentModel = "spring")
public interface WsndBusinessVehiclesMgtConverter {
    WsndBusinessVehiclesMgtDvo mapCreateReqToBusinessVehiclesMgtDvo(CreateReq dto);

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WsndBusinessVehiclesMgtDvo mapEditReqToBusinessVehiclesMgtDvo(EditReq dto);
}
