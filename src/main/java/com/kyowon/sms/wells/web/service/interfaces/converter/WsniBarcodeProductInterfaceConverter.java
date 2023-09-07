package com.kyowon.sms.wells.web.service.interfaces.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBarcodeProductInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniBarcodeProductInterfaceDvo;

@Mapper(componentModel = "spring")
public interface WsniBarcodeProductInterfaceConverter {
    WsniBarcodeProductInterfaceDto.SearchJsonRes mapBarcodeProductInterfaceDvoToJsonRes(
        WsniBarcodeProductInterfaceDvo dvo
    );

    WsniBarcodeProductInterfaceDto.SearchCustJsonRes mapBarcodeProductCustDtoToJsonRes(
        WsniBarcodeProductInterfaceDto.SearchCustRes dto
    );

    WsniBarcodeProductInterfaceDto.SearchCustServiceJsonRes mapBarcodeProductCustServiceDtoToJsonRes(
        WsniBarcodeProductInterfaceDto.SearchCustRes dto
    );

}
