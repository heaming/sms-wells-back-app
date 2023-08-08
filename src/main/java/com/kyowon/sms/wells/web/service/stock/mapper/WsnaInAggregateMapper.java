package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaInAggregateDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInAggregateDvo;

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
    /**
     * 입고 집계 조회
     *
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsnaInAggregateDvo> selectInAggregateList(SearchReq searchReq);
}
