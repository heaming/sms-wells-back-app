package com.kyowon.sms.wells.web.fee.control.converter;

import com.kyowon.sms.wells.web.fee.control.dto.WfedWelsMngerBsFeeDto.*;
import com.kyowon.sms.wells.web.fee.control.dvo.WfedWelsMngerBsFeeDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WfedWelsMngerBsFeeConverter {
    WfedWelsMngerBsFeeDvo mapSaveFeeReqToWfedWelsMngerBsFeeDvo(SaveFeeReq dto);
}
