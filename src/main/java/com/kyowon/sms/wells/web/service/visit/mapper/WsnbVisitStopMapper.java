package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.SearchCodeReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.SearchCodeRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.SearchHistoryRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbVisitStopDvo;

@Mapper
public interface WsnbVisitStopMapper {
    List<SearchCodeRes> selectVisitStopCodes(String cntrNo, String cntrSn);

    List<SearchHistoryRes> selectVisitStopHistory(SearchCodeReq dto);

    int insertVisitStopHistory(WsnbVisitStopDvo dvo);
}
