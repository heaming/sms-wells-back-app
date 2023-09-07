package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniServiceHistoryInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniServiceHistoryInterfaceDvo;

@Mapper(componentModel = "spring")
public interface WsniServiceHistoryInterfaceConverter {
    @Mapping(target = "cntrNo", source = "CNTR_NO")
    @Mapping(target = "cntrSn", source = "CNTR_SN")
    @Mapping(target = "gubun", source = "GUBUN")
    @Mapping(target = "workEndYn", source = "WORK_END_YN")
    @Mapping(target = "bsCurDtYn", source = "BS_CUR_DT_YN")
    WsniServiceHistoryInterfaceDvo mapSearchReqToServiceHistoryInterfaceDvo(
        WsniServiceHistoryInterfaceDto.SearchReq dto
    );
}
