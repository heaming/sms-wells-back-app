package com.kyowon.sms.wells.web.fee.standard.converter;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyContractBsFeeExDto.SaveContractBsFeeExReq;
import com.kyowon.sms.wells.web.fee.standard.dvo.WfeyContractBsFeeExDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WfeyContractBsFeeExConverter {
    WfeyContractBsFeeExDvo mapSaveReqWfeyContractBsFeeExDvo(SaveContractBsFeeExReq dto);
}
