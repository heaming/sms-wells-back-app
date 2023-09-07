package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStockAggregationDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaQomAsnStockAggregationMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0127M01 물량배정 재고이송량 집계 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-04
 */

@Service
@RequiredArgsConstructor
public class WsnaQomAsnStockAggregationService {

    private final WsnaQomAsnStockAggregationMapper mapper;

    /**
     * 품목 조회
     * @return
     */
    public List<SearchPdRes> getProducts() {
        return this.mapper.selectProducts();
    }

    /**
     * 물량배정 재고이송량 집계 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getQomAsnStockAggs(SearchReq dto) {
        return this.mapper.selectQomAsnStockAggs(dto);
    }

}
