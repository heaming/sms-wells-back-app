package com.kyowon.sms.wells.web.closing.performances.interfaces.mapper;

import com.kyowon.sms.wells.web.closing.performances.interfaces.dto.WdccPerformanceInterfaceDto.FindReq;
import com.kyowon.sms.wells.web.closing.performances.interfaces.dto.WdccPerformanceInterfaceDto.FindRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccPerformanceInterfaceMapper {
    List<FindRes> selectLumpSumPerformance(FindReq req);
}
