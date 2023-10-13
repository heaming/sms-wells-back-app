package com.kyowon.sms.wells.web.closing.performance.mapper;

import java.util.List;
import java.util.Map;

import com.kyowon.sms.wells.web.closing.performance.dvo.WdccMonthlySalesClosingAggregationDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WdccMonthlySalesClosingAggregationMapper {
    List<WdccMonthlySalesClosingAggregationDvo> selectSalesMonthEnd(Map<String, Object> parameterValues);

    int mergeMonthlySalesClosingAggregation(WdccMonthlySalesClosingAggregationDvo dvo);
}
