package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailCssrDto.SaveRcpReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailCssrDvo;

@Mapper(componentModel = "spring")
public interface WctaOrderDetailCssrConverter {
    WctaOrderDetailCssrDvo mapSaveRcpReqToWctaOrderDetailCssrDvo(SaveRcpReq dto);

}
