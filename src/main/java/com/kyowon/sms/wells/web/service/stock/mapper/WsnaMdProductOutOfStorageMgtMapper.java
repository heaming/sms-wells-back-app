package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SearchReq;

@Mapper
public interface WsnaMdProductOutOfStorageMgtMapper {
    List selectMdProductOutOfStorages(SearchReq dto);
}
