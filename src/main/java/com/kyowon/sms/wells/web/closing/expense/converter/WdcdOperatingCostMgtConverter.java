package com.kyowon.sms.wells.web.closing.expense.converter;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdOperatingCostMgtDto.EditReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdOperatingCostDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WdcdOperatingCostMgtConverter {

    WdcdOperatingCostDvo mapEditReqToWdcdOperatingCostDvo(EditReq req);
}
