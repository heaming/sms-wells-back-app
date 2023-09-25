package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbBsContactVisitPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbBsContactVisitPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbBsContactVisitPsService {

    private final WsnbBsContactVisitPsMapper mapper;

    public PagingResult<SearchRes> getBsContactVisitPsPages(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectBsContactVisitPs(dto, pageInfo);
    }

    public List<SearchRes> getBsContactVisitPsExcelDownload(SearchReq dto) {
        return mapper.selectBsContactVisitPs(dto);
    }
}
