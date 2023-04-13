package com.kyowon.sms.wells.web.closing.sales.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesConfirmCreateDto.CreateReq;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmCreateDvo;

@Mapper(componentModel = "spring")
public interface WdcbSalesConfirmCreateConverter {
    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WdcbSalesConfirmCreateDvo mapCreateReqToWdcbSalesConfirmCreateDvo(CreateReq dto);

}
