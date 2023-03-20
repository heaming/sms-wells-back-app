package com.kyowon.sms.wells.web.closing.performances.mapper;

import com.kyowon.sms.wells.web.closing.performances.dto.WdccPerformanceInterfaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccPerformanceInterfaceMapper {
    List<WdccPerformanceInterfaceDto.FindRes> selectLumpSumPerformance(WdccPerformanceInterfaceDto.FindReq req);
}
