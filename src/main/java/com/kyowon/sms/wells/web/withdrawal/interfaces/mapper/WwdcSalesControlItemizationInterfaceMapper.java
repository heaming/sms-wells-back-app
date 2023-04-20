package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdcSalesControlItemizationInterfaceDto;

@Mapper
public interface WwdcSalesControlItemizationInterfaceMapper {

    List<WwdcSalesControlItemizationInterfaceDto.SearchRes> selectSalesControlItemizations(
        WwdcSalesControlItemizationInterfaceDto.SearchReq dto
    );

}
