package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbVisitStopInquiryConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopInquiryDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopInquiryDto.SearchCodeReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopInquiryDto.SearchCodeRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopInquiryDto.SearchHistoryRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbVisitStopInquiryDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbVisitStopInquiryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbVisitStopInquiryService {
    private final WsnbVisitStopInquiryMapper mapper;
    private final WsnbVisitStopInquiryConverter converter;

    public List<SearchCodeRes> getVisitStopCodes(String cntrNo, String cntrSn) {
        return mapper.selectVisitStopCodes(cntrNo, cntrSn);
    }

    public List<SearchHistoryRes> getVisitStopHistory(SearchCodeReq dto) {
        return mapper.selectVisitStopHistory(dto);
    }

    public int createVisitStop(CreateReq dto) {
        WsnbVisitStopInquiryDvo dvo = converter.mapCreatReqToVisitStopInquiryDto(dto);
        return mapper.insertVisitStopHistory(dvo);
    }
}
