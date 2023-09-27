package com.kyowon.sms.wells.web.closing.performances.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performances.dto.WdccPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.performances.dto.WdccPerformanceInterfaceDto.FindRes;

@Mapper
public interface WdccPerformanceInterfaceMapper {
    List<FindRes> selectLumpSumPerformance(FindReq req);
}
