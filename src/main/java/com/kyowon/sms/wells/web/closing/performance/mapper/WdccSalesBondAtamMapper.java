package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondAtamDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesBondAtamDto.SearchRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccSalesBondAtamMapper {
    List<SearchRes> selectSalesBondAtamAggregateList(SearchReq req);

    List<SearchRes> selectSalesBondAtamDateList(SearchReq req);

    List<SearchRes> selectSalesBondAtamOrderList(SearchReq req);

    List<SearchRes> selectSalesBondAtamMemberList(SearchReq req);
}
