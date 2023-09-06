package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbTotalAfterServiceRateOdmPerOemDto.*;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbTotalAfterServiceRateOdmPerOemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnbTotalAfterServiceRateOdmPerOemService {
    private final WsnbTotalAfterServiceRateOdmPerOemMapper mapper;

    public List<SearchRes> getTotalAfterServiceRateOdmPerOemList(SearchReq dto){
        return mapper.selectTotalAfterServiceRateOdmPerOemList(dto);
    }
}
