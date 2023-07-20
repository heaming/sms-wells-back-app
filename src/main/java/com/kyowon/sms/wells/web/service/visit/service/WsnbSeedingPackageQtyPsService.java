package com.kyowon.sms.wells.web.service.visit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSeedingPackageQtyPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSeedingPackageQtyPsDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbSeedingPackageQtyPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnbSeedingPackageQtyPsService {
    private final WsnbSeedingPackageQtyPsMapper mapper;

    public PagingResult<SearchRes> getSeedingPackageQtyPs(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectSeedingPackageQtyPs(dto, pageInfo);
    }

    public List<SearchRes> getSeedingPackageQtyPs(SearchReq dto) {
        return mapper.selectSeedingPackageQtyPs(dto);
    }
}
