package com.kyowon.sms.wells.web.closing.sales.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSalesConfirmCreateDto.CreateReq;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmCreateDvo;

@Mapper(componentModel = "spring")
public interface WdcbSalesConfirmCreateConverter {

    WdcbSalesConfirmCreateDvo mapCreateReqToWdcbSalesConfirmCreateDvo(CreateReq dto);

}
