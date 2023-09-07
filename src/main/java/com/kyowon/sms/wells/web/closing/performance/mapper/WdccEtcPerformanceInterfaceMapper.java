package com.kyowon.sms.wells.web.closing.performance.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccEtcPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccEtcPerformanceInterfaceDto.FindRes;

@Mapper
public interface WdccEtcPerformanceInterfaceMapper {
    FindRes selectOtherLumpSumPerformance(FindReq req);
}
