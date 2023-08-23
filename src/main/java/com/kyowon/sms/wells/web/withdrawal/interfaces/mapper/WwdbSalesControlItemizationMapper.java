package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdcSalesControlItemizationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdbSalesControlItemizationMapper {
    List<WwdcSalesControlItemizationDto.SearchRes> selectSalesControlItemizations(
        WwdcSalesControlItemizationDto.SearchReq dto
    );
}
