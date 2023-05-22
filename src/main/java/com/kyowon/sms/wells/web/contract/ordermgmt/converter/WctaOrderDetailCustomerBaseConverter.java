package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDto.SearchCustomerBaseRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailCustomerBaseDvo;

@Mapper(componentModel = "spring")
public interface WctaOrderDetailCustomerBaseConverter {
    List<SearchCustomerBaseRes> mapWctaOrderDetailCustomerBaseDvoToSearchCustomerBaseRes(
        List<WctaOrderDetailCustomerBaseDvo> dvos
    );

}
