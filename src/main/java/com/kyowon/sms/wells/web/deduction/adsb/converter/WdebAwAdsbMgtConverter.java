package com.kyowon.sms.wells.web.deduction.adsb.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.deduction.adsb.dto.WdebAwAdsbMgtDto.CreateReq;
import com.kyowon.sms.wells.web.deduction.adsb.dvo.WdebAwAdsbMgtDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WdebAwAdsbMgtConverter {

    WdebAwAdsbMgtDvo mapCreateReq(CreateReq dto);
}
