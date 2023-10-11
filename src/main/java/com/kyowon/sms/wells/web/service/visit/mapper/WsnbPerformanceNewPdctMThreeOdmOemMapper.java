package com.kyowon.sms.wells.web.service.visit.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbPerformanceNewPdctMThreeOdmOemDto.*;

import java.util.List;

@Mapper
public interface WsnbPerformanceNewPdctMThreeOdmOemMapper {
    List<SearchRes> selectPerformanceNewPdctMThreeOdmOems(SearchReq dto);
}
