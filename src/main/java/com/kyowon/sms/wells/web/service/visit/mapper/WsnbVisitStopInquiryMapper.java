package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopInquiryDto.SearchCodeReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopInquiryDto.SearchCodeRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopInquiryDto.SearchHistoryRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbVisitStopInquiryDvo;

@Mapper
public interface WsnbVisitStopInquiryMapper {
    List<SearchCodeRes> selectVisitStopCodes(String cntrNo, String cntrSn);

    List<SearchHistoryRes> selectVisitStopHistory(SearchCodeReq dto);

    int insertVisitStopHistory(WsnbVisitStopInquiryDvo dvo);
}
