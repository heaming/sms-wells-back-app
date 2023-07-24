package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageQtyCtrDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageQtyCtrDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaSeedingPackageQtyCtrMapper {

    List<SearchRes> selectSeedingPackageQtyCtrExcdQtys(SearchReq dto);

    List<SearchRes> selectSeedingPackageQtyCtrSpmtQtys(SearchReq dto);

}
