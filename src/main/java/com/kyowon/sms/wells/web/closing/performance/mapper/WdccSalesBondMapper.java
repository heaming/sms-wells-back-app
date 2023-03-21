package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccSalesBondMapper {

    List<SearchRes> selectSalesBondAggregate(SearchReq req);

    List<SearchRes> selectSalesBondDates(SearchReq req);

    List<SearchRes> selectSalesBondrders(SearchReq req);

    List<SearchRes> selectSalesBondMembers(SearchReq req);
}
