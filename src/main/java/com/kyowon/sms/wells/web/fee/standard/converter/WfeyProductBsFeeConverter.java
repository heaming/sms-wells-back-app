package com.kyowon.sms.wells.web.fee.standard.converter;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyProductBsFeeDto.SaveProductBsFeeReq;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyProductBsFeeDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WfeyProductBsFeeConverter {
    WfeyProductBsFeeDvo mapSaveReqWfeyProductBsFeeDvo(SaveProductBsFeeReq dto);
}
