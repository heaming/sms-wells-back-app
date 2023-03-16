package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccSalesBondMapper {

    List<SearchRes> selectSalesBondAggregateList(SearchReq req);

    List<SearchRes> selectSalesBondDateList(SearchReq req);

    List<SearchRes> selectSalesBondrderList(SearchReq req);

    List<SearchRes> selectSalesBondMemberList(SearchReq req);
}
