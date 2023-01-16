package com.kyowon.sms.wells.web.service.common.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyBeforeServiceBizClDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyBeforeServiceBizClDvo;

@Mapper(componentModel = "spring")
public interface WsnyBeforeServiceBizClConverter {
    WsnyBeforeServiceBizClDvo mapSaveReqToBeforeServiceBizClDvo(WsnyBeforeServiceBizClDto.SaveReq dto)
        throws Exception;
}
