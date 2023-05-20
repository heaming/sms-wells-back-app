package com.kyowon.sms.wells.web.service.common.converter;

import com.kyowon.sms.wells.web.service.common.dto.WsnyInstallSeparationMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyInstallSeparationMgtDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsnyInstallSeparationMgtConverter {
    WsnyInstallSeparationMgtDvo mapSaveReqToInstallSeparationMgtDvo(WsnyInstallSeparationMgtDto.SaveReq dto) throws Exception;

    WsnyInstallSeparationMgtDvo mapDeleteReqToInstallSeparationMgtDvo(WsnyInstallSeparationMgtDto.RemoveReq dto) throws Exception;
}
