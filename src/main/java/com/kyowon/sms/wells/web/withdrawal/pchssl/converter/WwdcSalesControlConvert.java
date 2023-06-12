package com.kyowon.sms.wells.web.withdrawal.pchssl.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.RemoveSalesControlReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.SaveSalesControlReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dvo.WwdcSalesControlDvo;

@Mapper(componentModel = "spring")
public interface WwdcSalesControlConvert {
    WwdcSalesControlDvo mapSaveWwdcSalesControlDvo(SaveSalesControlReq dto);

    WwdcSalesControlDvo mapRemoveWwdcSalesControlDvo(RemoveSalesControlReq dto);
}
