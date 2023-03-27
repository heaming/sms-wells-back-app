package com.kyowon.sms.wells.web.service.interfaces.mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBarCodeProductInfDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsniBarCodeProductInfMapper {

    WsniBarCodeProductInfDto.SearchRes selectBarCodeProduct(String qrcd);
}
