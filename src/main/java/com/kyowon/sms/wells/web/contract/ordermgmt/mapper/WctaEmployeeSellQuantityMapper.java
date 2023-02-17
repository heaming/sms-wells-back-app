package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaEmployeeSellQuantityDto;

@Mapper
public interface WctaEmployeeSellQuantityMapper {
    WctaEmployeeSellQuantityDto.SearchRes selectEmployeeSellQuantity(WctaEmployeeSellQuantityDto.SearchReq dto);
}
