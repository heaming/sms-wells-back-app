package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutoPrepaymentDiscountExcludeMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbAutoPrepaymentDiscountExcludeMgtDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbAutoPrepaymentDiscountExcludeMgtConverter {
    WwdbAutoPrepaymentDiscountExcludeMgtDvo mapSaveWwdbAutoPrepaymentDiscountExcludeMgtDvo(SaveReq dto);
}
