package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbPerformanceNewPdctMThreeOdmOemDto.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbPerformanceNewPdctMThreeOdmOemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnbPerformanceNewPdctMThreeOdmOemService {
    private final WsnbPerformanceNewPdctMThreeOdmOemMapper mapper;

    public List<SearchRes> getPerformanceNewPdctMThreeOdmOemList(SearchReq dto){
        return mapper.selectPerformanceNewPdctMThreeOdmOems(dto);
    }
}
