package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbTotalAfterServiceRateOdmPerOemDto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsnbTotalAfterServiceRateOdmPerOemMapper {
    List<SearchRes> selectTotalAfterServiceRateOdmPerOemList(SearchReq searchReq);
}
