package com.kyowon.sms.wells.web.fee.calculation.converter;

import static com.kyowon.sms.wells.web.fee.calculation.dto.WfebEgerAllowanceDto.*;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebEgerAllowanceDvo;

@Mapper(componentModel = "spring")
public interface WfebEgerAllowanceConverter {

    WfebEgerAllowanceDvo mapSaveReqToWfebEgerAllowanceDvo(SaveReq dto);

    WfebEgerAllowanceDvo mapEditReqToWfebEgerAllowanceDvo(EditReq dto);

    WfebEgerAllowanceDvo mapConfirmReqToWfebEgerAllowanceDvo(ConfirmReq dto);

}
