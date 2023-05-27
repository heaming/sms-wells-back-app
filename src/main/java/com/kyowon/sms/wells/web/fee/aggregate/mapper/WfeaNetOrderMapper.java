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

    List<SearchFeeRes> selectNetOrderFees(
        SearchReq dto
    );

    int deleteNetOrders(
        WfeaNetOrderDvo dvo
    );

    int insertNetOrders(
        WfeaNetOrderDvo dvo
    );
}
