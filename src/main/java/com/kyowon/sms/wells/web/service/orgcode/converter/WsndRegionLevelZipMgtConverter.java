package com.kyowon.sms.wells.web.service.orgcode.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.EditReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelZipMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndRegionLevelZipNoDvo;

@Mapper(componentModel = "spring")
public interface WsndRegionLevelZipMgtConverter {

    List<SearchRes> mapAllZipNoDvoToSearchRes(List<WsndRegionLevelZipNoDvo> dvos);

    WsndRegionLevelZipNoDvo mapEditReqToWsndRegionLevelZipNoDvo(EditReq dto);

}
