package com.kyowon.sms.wells.web.closing.sales.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbAdvancedSellFeeReplaceDto.*;
import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbAdvancedSellFeeReplaceDvo;

@Mapper
public interface WdcbAdvancedSellFeeReplaceMapper {
    List<SearchAggregateRes> selectAggregateLists(
        SearchReq dto
    );

    List<SearchAggregateSummaryRes> selectAggregateSummary(
        SearchReq dto
    );

    List<SearchRes> selectDtlLists(
        SearchReq dto
    );

    List<SearchDtlSummaryRes> selectDtlSummary(
        SearchReq dto
    );

    List<SearchDivideRes> selectDivideLists(
        SearchReq dto
    );

    List<SearchCodeRes> selectFeeGubunCodes();

    SearchPopRes selectPop(
        String kwGrpCoCd
    );

    int updatePop(WdcbAdvancedSellFeeReplaceDvo dvo);
}
