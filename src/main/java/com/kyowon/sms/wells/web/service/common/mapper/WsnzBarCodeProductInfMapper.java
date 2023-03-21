package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnzBarCodeProductInfDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnzBarCodeProductInfMapper {

    WsnzBarCodeProductInfDto.SearchRes selectBarCodeProduct(String qrcd);
}
