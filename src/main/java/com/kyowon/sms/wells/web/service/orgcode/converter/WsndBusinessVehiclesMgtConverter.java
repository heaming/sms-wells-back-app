package com.kyowon.sms.wells.web.service.orgcode.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.EditReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndBusinessVehiclesMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndBusinessVehiclesMgtDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WsndBusinessVehiclesMgtConverter {
    WsndBusinessVehiclesMgtDvo mapCreateReqToBusinessVehiclesMgtDvo(CreateReq dto);

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WsndBusinessVehiclesMgtDvo mapEditReqToBusinessVehiclesMgtDvo(EditReq dto);

    PagingResult<SearchRes> mapAllBusinessVehiclesMgtDvoToSearchRes(List<WsndBusinessVehiclesMgtDvo> dvos);

    List<SearchRes> mapAllDvoToSearchRes(List<WsndBusinessVehiclesMgtDvo> dvos);
}
