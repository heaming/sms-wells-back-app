package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBarcodeProductInterfaceDto;

@Mapper
public interface WsniBarcodeProductInterfaceMapper {
    WsniBarcodeProductInterfaceDto.SearchRes selectBarcodeProduct(
        WsniBarcodeProductInterfaceDto.SearchReq dto
    );

    WsniBarcodeProductInterfaceDto.SearchCustRes selectBarcodeSearchCustomer(WsniBarcodeProductInterfaceDto.SearchCustReq dto);
}
