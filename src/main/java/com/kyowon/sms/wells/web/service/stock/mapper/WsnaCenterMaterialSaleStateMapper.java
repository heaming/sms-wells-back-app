package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaCenterMaterialSaleStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaCenterMaterialSaleStateDto.SearchRes;

@Mapper
public interface WsnaCenterMaterialSaleStateMapper {

    List<SearchRes> selectCenterMaterialSaleState(SearchReq dto);
}
