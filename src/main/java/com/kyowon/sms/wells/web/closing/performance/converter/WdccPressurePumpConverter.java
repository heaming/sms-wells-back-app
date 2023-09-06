package com.kyowon.sms.wells.web.closing.performance.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.RemoveReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccPressurePumpDto.SaveReq;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccPressurePumpDvo;

@Mapper(componentModel = "spring")
public interface WdccPressurePumpConverter {

    WdccPressurePumpDvo mapSaveReqToWdccPressurePumpDvo(SaveReq dto);

    WdccPressurePumpDvo mapRemoveReqToWdccPressurePumpDvo(RemoveReq dto);

}
