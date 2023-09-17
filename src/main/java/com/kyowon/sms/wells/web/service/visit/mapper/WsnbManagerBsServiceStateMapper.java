package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbManagerBsServiceStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbManagerBsServiceStateDto.SearchRes;

@Mapper
public interface WsnbManagerBsServiceStateMapper {

    List<SearchRes> selectManagerBsServiceState(SearchReq dto);
}
