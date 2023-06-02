package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniIotQrcodeCustomerInterfaceDto.SearchReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniIotQrcodeCustomerInterfaceDto.SearchRes;

@Mapper
public interface WsniIotQrcodeCustomerInterfaceMapper {
    SearchRes selectContractInfoByIotQrcode(SearchReq dto);
}
