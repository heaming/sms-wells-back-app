package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbVisitStopConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.SearchCodeReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.SearchCodeRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbVisitStopDto.SearchHistoryRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbVisitStopDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbVisitStopMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbVisitStopService {
    private final WsnbVisitStopMapper mapper;
    private final WsnbVisitStopConverter converter;

    public List<SearchCodeRes> getVisitStopCodes(String cntrNo, String cntrSn) {
        return mapper.selectVisitStopCodes(cntrNo, cntrSn);
    }

    public List<SearchHistoryRes> getVisitStopHistory(SearchCodeReq dto) {
        return mapper.selectVisitStopHistory(dto);
    }

    public int createVisitStop(CreateReq dto) {
        WsnbVisitStopDvo dvo = converter.mapCreatReqToVisitStopInquiryDto(dto);
        return mapper.insertVisitStopHistory(dvo);
    }
}
