package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageCtrQtyRegDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedingPackageCtrQtyRegDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSeedingPackageCtrQtyRegDvo;

@Mapper
public interface WsnaSeedingPackageCtrQtyRegMapper {

    List<SearchRes> selectSeedingPackageCtrQtys(SearchReq dto);

    int mergeSdingPkgQtyCtrIz(WsnaSeedingPackageCtrQtyRegDvo dvo);

}
