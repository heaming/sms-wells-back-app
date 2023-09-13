package com.kyowon.sms.wells.web.fee.aggregate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.fee.aggregate.dvo.WfeaNetOrderDvo;
import com.kyowon.sms.wells.web.fee.aggregate.dto.WfeaNetOrderDto.*;

@Mapper
public interface WfeaNetOrderMapper {
    List<SearchRes> selectNetOrders(
        SearchReq dto
    );

    List<SearchRes> selectAggreateNetOrders(
        SearchReq dto
    );

    List<SearchFeeRes> selectNetOrderFees(
        SearchReq dto
    );

    SearchConfirmRes selectNetAggregateConfirm(
        SearchReq dto
    );

    int deleteNetOrders(
        WfeaNetOrderDvo dvo
    );

    int deleteWelsNetOrders(
        WfeaNetOrderDvo dvo
    );

    int insertNetOrder(
        WfeaNetOrderDvo dvo
    );

    int insertManagerNetOrders(
        WfeaNetOrderDvo dvo
    );

    int insertPlannerNetOrders(
        WfeaNetOrderDvo dvo
    );

    int insertHomeMasterNetOrders(
        WfeaNetOrderDvo dvo
    );

    int updateNetOrders(
        WfeaNetOrderDvo dvo
    );

    List<SearchProductRes> selectNetAggregateProducts(
        SearchReq dto
    );

    String selectNetAggregateJobId(
        SearchReq dto
    );
}
