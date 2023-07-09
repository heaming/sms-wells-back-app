package com.kyowon.sms.wells.web.deduction.redf.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAwRedfEtAmtDto.SaveReq;
import com.kyowon.sms.wells.web.deduction.redf.dvo.WdeaAwRedfEtAmtDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WdeaAwRedfEtAmtConverter {
    WdeaAwRedfEtAmtDvo mapSaveReq(SaveReq dtos);
}
