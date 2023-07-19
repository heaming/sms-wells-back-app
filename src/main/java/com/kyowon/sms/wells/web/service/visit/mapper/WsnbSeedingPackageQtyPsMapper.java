package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSeedingPackageQtyPsDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSeedingPackageQtyPsDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbSeedingPackageQtyPsMapper {
    PagingResult<SearchRes> selectSeedingPackageQtyPs(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectSeedingPackageQtyPs(SearchReq dto);
}
