package com.kyowon.sms.wells.web.deduction.redf.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaSoleDistributorMgtDto.SaveReq;
import com.kyowon.sms.wells.web.deduction.redf.dvo.WdeaSoleDistributorMgtDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WdeaSoleDistributorMgtConverter {

    WdeaSoleDistributorMgtDvo mapSaveReq(SaveReq dto);
}
