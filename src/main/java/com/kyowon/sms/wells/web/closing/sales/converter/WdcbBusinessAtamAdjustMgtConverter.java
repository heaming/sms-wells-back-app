package com.kyowon.sms.wells.web.closing.sales.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbBusinessAtamAdjustMgtDto.SaveSlpnoReq;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbBusinessAtamAdjustDvo;

@Mapper(componentModel = "spring")
public interface WdcbBusinessAtamAdjustMgtConverter {
    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WdcbBusinessAtamAdjustDvo mapSaveSlpnoReqToWdcbBusinessAtamAdjustDvo(SaveSlpnoReq dto);
}
