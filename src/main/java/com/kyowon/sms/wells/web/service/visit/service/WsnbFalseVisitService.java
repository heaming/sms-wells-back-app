package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFalseVisitDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFalseVisitDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbFalseVisitMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WsnbFalseVisitService {
    private final WsnbFalseVisitMapper mapper;

    public PagingResult<SearchRes> getFalseVisits(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectFalseVisits(dto, pageInfo);
    }
}
