package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailInqrDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailInqrDvo;

@Mapper(componentModel = "spring")
public interface WctaOrderDetailInqrConverter {
    WctaOrderDetailInqrDto.SearchRes mapWctaOrderDetailInqrDvoToSearchRes(WctaOrderDetailInqrDvo dvo);
}
