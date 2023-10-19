package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSamsungManufactureNoInqrDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbSamsungManufactureNoInqrDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbSamsungManufactureNoInqrMapper {

    PagingResult<SearchRes> selectSamsungManufactureNoInqrs(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectSamsungManufactureNoInqrs(SearchReq dto);

}
