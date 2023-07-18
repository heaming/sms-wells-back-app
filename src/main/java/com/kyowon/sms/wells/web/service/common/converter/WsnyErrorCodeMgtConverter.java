package com.kyowon.sms.wells.web.service.common.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyErrorCodeMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyErrorCodeMgtDvo;

@Mapper(componentModel = "spring")
public interface WsnyErrorCodeMgtConverter {

    List<WsnyErrorCodeMgtDto.SearchRes> mapAllErrorCodeDvoToSearchRes(List<WsnyErrorCodeMgtDvo> dvos);

    WsnyErrorCodeMgtDvo mapDeleteReqToWsnyErrorCodeMgtDvo(WsnyErrorCodeMgtDto.DeleteReq dto);

    WsnyErrorCodeMgtDvo mapSaveReqToWsnyErrorCodeMgtDvo(WsnyErrorCodeMgtDto.SaveReq dto);

}
