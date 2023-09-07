package com.kyowon.sms.wells.web.service.orgcode.service;

import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndServiceIndicatorMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndServiceIndicatorDto.SearchReq;
import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndServiceIndicatorDto.SearchRes;

@Service
@RequiredArgsConstructor
public class WsndServiceIndicatorService {
    private final WsndServiceIndicatorMapper mapper;

    public PagingResult<SearchRes> getServiceIndicators(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectServiceIndicators(dto, pageInfo);
    }

    public List<SearchRes> getServiceIndicators(SearchReq dto) {
        return mapper.selectServiceIndicators(dto);
    }

}
