package com.kyowon.sms.wells.web.fee.standard.converter;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyNewChannelBaseDto.SaveNewChannelBaseReq;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyNewChannelBaseDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WfeyNewChannelBaseConverter {
    WfeyNewChannelBaseDvo mapSaveReqWfeyNewChannelBaseDvo(SaveNewChannelBaseReq dto);
}
