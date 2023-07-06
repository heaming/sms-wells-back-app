package com.kyowon.sms.wells.web.service.adrwork.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.adrwork.dto.WsnfMonthManagementCstDto;

@Mapper
public interface WsnfMonthManagementCstMapper {
    int deleteMonthManagementCst(WsnfMonthManagementCstDto.RemoveReq dto);
}
