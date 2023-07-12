package com.kyowon.sms.wells.web.closing.performance.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformPenaltyDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformPenaltyDto.SearchRes;

@Mapper
public interface WdccSalesPerformPenaltyMapper {
    SearchRes selectPerformPenalty(SearchReq dto);
}
