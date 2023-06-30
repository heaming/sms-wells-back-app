package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsManagerIchrExcdDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsManagerIchrExcdDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbWellsManagerIchrExcdMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WsnbWellsManagerIchrExcdService {
    private final WsnbWellsManagerIchrExcdMapper mapper;

    public PagingResult<SearchRes> getWellsManagerInchargeExcds(
        SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectWellsManagerInchargeExcds(dto, pageInfo);
    }

    public List<SearchRes> getWellsManagerInchargeExcdsForExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectWellsManagerInchargeExcds(dto);
    }
}
