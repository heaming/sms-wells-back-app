package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccEtcPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccEtcPerformanceInterfaceDto.FindRes;

@Mapper
public interface WdccEtcPerformanceInterfaceMapper {
    List<FindRes> selectOtherLumpSumPerformance(FindReq req);
}
