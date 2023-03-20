package com.kyowon.sms.wells.web.closing.etcPerformances.mapper;

import com.kyowon.sms.wells.web.closing.etcPerformances.dto.WdccEtcPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.etcPerformances.dto.WdccEtcPerformanceInterfaceDto.FindRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccEtcPerformanceInterfaceMapper {
    List<FindRes> selectOtherLumpSumPerformance(FindReq req);
}
