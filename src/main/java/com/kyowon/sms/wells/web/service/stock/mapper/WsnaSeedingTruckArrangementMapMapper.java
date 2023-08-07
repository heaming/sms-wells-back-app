package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingTruckArrangementMapDto;

@Mapper
public interface WsnaSeedingTruckArrangementMapMapper {
    //    PagingResult<SearchRes> selectSeedingTruckArrangementMap(WsnaSeedingTruckArrangementMapDto.PkgRes dto, PageInfo pageInfo);

    List<WsnaSeedingTruckArrangementMapDto.PkgRes> selectSeedingTruckArrangementMap(
        WsnaSeedingTruckArrangementMapDto.SearchReq dto
    );
}
