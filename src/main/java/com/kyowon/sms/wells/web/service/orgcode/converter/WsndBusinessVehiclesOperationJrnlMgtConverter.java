package com.kyowon.sms.wells.web.service.orgcode.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesOperationJrnlMgtDvo;

@Mapper(componentModel = "spring")
public interface WsndBusinessVehiclesOperationJrnlMgtConverter {
    WsndBusinessVehiclesOperationJrnlMgtDvo mapEditBusinessVehiclesOperationJrnlMgt(
        WsndBusinessVehiclesOperationJrnlMgtDvo dvo
    ); // 
}
