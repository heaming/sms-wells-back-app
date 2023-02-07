package com.kyowon.sms.wells.web.customer.contact.converter;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.common.web.customer.common.dto.ZcsaCustomerInfoDto.SearchParameterTypeReq;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoDvo;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerInfoReq;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerRes;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaInterfaceResultDvo;

@Mapper(componentModel = "spring")
public interface WcsaCustomerInterfaceConverter {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "searchType", constant = "C01")
    SearchParameterTypeReq copy(SearchCustomerInfoReq dto);

    @BeanMapping(ignoreByDefault = true)
    SearchCustomerRes copy(WcsaInterfaceResultDvo dvo);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dvo2.rsCd", target = "rsCd")
    @Mapping(source = "dvo2.rsMsg", target = "rsMsg")
    @Mapping(source = "dvo.exnoEncr", target = "exno")
    @Mapping(source = "dvo.mexnoEncr", target = "mexno")
    SearchCustomerRes mapCreateIndvCustomerToCustomerRes(ZcsaCustomerInfoDvo dvo, WcsaInterfaceResultDvo dvo2);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dvo2.rsCd", target = "rsCd")
    @Mapping(source = "dvo2.rsMsg", target = "rsMsg")
    @Mapping(source = "dvo.exnoEncr", target = "exno")
    @Mapping(source = "dvo.mexnoEncr", target = "mexno")
    SearchCustomerRes mapCreateCrpCustomerToCustomerRes(ZcsaCustomerInfoDvo dvo, WcsaInterfaceResultDvo dvo2);
}
