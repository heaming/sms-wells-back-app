package com.kyowon.sms.wells.web.fee.simulation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.simulation.dto.WfefBfsvcEtPerfDto.SearchReq;
import com.kyowon.sms.wells.web.fee.simulation.dto.WfefBfsvcEtPerfDto.SearchRes;

@Mapper
public interface WfefBfsvcEtPerfMapper {
    List<SearchRes> selectBsEstimatePerformance(SearchReq dto);

}
