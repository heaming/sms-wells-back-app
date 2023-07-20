package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.SalesControlItemizationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdbSalesControlItemizationMapper {
    List<SalesControlItemizationDto.SearchRes> selectSalesControlItemizations(SalesControlItemizationDto.SearchReq dto);
}
