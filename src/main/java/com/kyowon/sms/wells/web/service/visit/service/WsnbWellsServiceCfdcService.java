package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbWellsServiceCfdcMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WsnbWellsServiceCfdcService {
    private final WsnbWellsServiceCfdcMapper mapper;

    public PagingResult<SearchRes> getWellsServiceConfirmations(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectWellsServiceConfirmations(dto, pageInfo);
    }
}
