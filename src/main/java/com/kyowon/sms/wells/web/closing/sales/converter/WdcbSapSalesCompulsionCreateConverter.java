package com.kyowon.sms.wells.web.closing.sales.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.RemoveReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbSapSalesCompulsionCreateDto.SaveReq;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSapSalesCompulsionCreateDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WdcbSapSalesCompulsionCreateConverter {

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WdcbSapSalesCompulsionCreateDvo mapSaveReqToWdcbSapSalesCompulsionCreateDvo(SaveReq dto);

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WdcbSapSalesCompulsionCreateDvo mapRemoveReqToWdcbSapSalesCompulsionCreateDvo(RemoveReq dto);
}
