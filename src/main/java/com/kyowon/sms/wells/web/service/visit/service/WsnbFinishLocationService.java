package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFinishLocationDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFinishLocationDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbFinishLocationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnbFinishLocationService {
    private final WsnbFinishLocationMapper mapper;

    public PagingResult<SearchRes> getFinishLocations(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectFinishLocations(dto, pageInfo);
    }

    public List<SearchRes> getFinishLocationsForExcelDownload(SearchReq dto) {
        return mapper.selectFinishLocations(dto);
    }
}
