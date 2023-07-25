package com.kyowon.sms.wells.web.service.standard.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto;
import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.standard.dto.WsnyBusinessTypeWorkHourMgtDto.SearchRes;
import com.kyowon.sms.wells.web.service.standard.dvo.WsnyBusinessTypeWorkHourMgtSaveDvo;

@Mapper(componentModel = "spring")
public interface WsnyBusinessTypeWorkHourMgtConverter {

    List<SearchRes> mapDvoToSearchRes(List<WsnyBusinessTypeWorkHourMgtDto> dtos);

    WsnyBusinessTypeWorkHourMgtSaveDvo mapSaveReqToBusinessTypeWorkHourDvo(SaveReq dto);

}
