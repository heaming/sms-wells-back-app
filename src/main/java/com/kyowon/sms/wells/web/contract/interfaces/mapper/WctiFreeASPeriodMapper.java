package com.kyowon.sms.wells.web.contract.interfaces.mapper;

import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeASPeriodDto.FindReq;
import static com.kyowon.sms.wells.web.contract.interfaces.dto.WctiFreeASPeriodDto.FindRes;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WctiFreeASPeriodMapper {

    FindRes selectFreeASPeriodFromEx(FindReq dto);

    FindRes selectFreeASPeriod(FindReq dto);
}
