package com.kyowon.sms.wells.web.service.orgcode.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndVehiclesDsbRgstDto;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndVehiclesDsbRgstDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WsndVehiclesDsbRgstConverter {
    WsndVehiclesDsbRgstDvo mapCreateReqToVehiclesDsbDvo(WsndVehiclesDsbRgstDto.CreateReq dto);

    WsndVehiclesDsbRgstDvo mapEditReqToVehiclesDsbDvo(WsndVehiclesDsbRgstDto.EditReq dto);
}
