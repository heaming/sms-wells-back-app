package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.mapper.WsnbFitForLifeFilterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchInfoReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchInfoRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchFiltersReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchFiltersRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchHistoriesReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SearchHistoriesRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFitForLifeFilterDto.SaveFilterReq;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnbFitForLifeFilterService {
    private final WsnbFitForLifeFilterMapper mapper;

    public SearchInfoRes getNextBSCustInfo(SearchInfoReq dto) {
        return mapper.selectNextBSCustInfo(dto);
    }

    public List<SearchFiltersRes> getFilterItems(SearchFiltersReq dto) {
        return mapper.selectFilterItems(dto);
    }

    public List<SearchHistoriesRes> getFilterHistories(SearchHistoriesReq dto) {
        return mapper.selectFilterHistories(dto);
    }

    @Transactional
    public int saveFilter(SaveFilterReq dto) {
        mapper.updateCstSvRgbsprIz(dto);
        return mapper.insertBfsvcNxVstPromIz(dto);
    }
}
