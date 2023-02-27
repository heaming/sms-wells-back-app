package com.kyowon.sms.wells.web.fee.calculation.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.fee.calculation.dto.WfebOgFeeDto;
import com.kyowon.sms.wells.web.fee.calculation.dvo.WfebOgFeeDvo;

@Mapper(componentModel = "spring")
public interface WfebOgFeeConverter {

    WfebOgFeeDvo mapSaveReqToWfebOgFeeDvo(WfebOgFeeDto.SaveReq dto);

}
