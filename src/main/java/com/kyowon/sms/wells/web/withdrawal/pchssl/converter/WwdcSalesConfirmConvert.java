package com.kyowon.sms.wells.web.withdrawal.pchssl.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesConfirmDto.SaveSalesConfirmReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dvo.WwdcSalesConfirmDvo;

@Mapper(componentModel = "spring")
public interface WwdcSalesConfirmConvert {
    WwdcSalesConfirmDvo mapSaveWwdcSalesConfirmDvo(SaveSalesConfirmReq dto);

}
