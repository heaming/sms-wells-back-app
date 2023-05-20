package com.kyowon.sms.wells.web.product.standard.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.product.standard.dto.WpdyRestipulationMgtDto.SaveReq;
import com.kyowon.sms.wells.web.product.standard.dvo.WpdyRestipulationDvo;

@Mapper(componentModel = "spring")
public interface WpdyRestipulationMgtConverter {

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WpdyRestipulationDvo mapSaveReqToWpdyRestipulationDvo(SaveReq dto);

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WpdyRestipulationDvo mapDelReqToWpdyRestipulationDvo(RemoveReq dto);
}
