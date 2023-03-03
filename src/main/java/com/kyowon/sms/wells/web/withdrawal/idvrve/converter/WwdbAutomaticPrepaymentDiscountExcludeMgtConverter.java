package com.kyowon.sms.wells.web.withdrawal.idvrve.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbAutomaticPrepaymentDiscountExcludeMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbAutomaticPrepaymentDiscountExcludeMgtDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WwdbAutomaticPrepaymentDiscountExcludeMgtConverter {
    WwdbAutomaticPrepaymentDiscountExcludeMgtDvo mapSaveWwdbAutomaticPrepaymentDiscountExcludeMgtDvo(SaveReq dto);
}
