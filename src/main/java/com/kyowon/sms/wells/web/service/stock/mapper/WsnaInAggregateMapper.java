package com.kyowon.sms.wells.web.service.stock.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInAggregateDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInAggregateWareDvo;

/**
 *
 * <pre>
 * K-W-SV-U-0265M01 입고 집계
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.07
 */
@Mapper
public interface WsnaInAggregateMapper {
    List<WsnaInAggregateWareDvo> selectMcByWares(WsnaInAggregateWareDvo dvo);

    List<HashMap<String, String>> selectInAggregateList(WsnaInAggregateDvo dvo);
}
