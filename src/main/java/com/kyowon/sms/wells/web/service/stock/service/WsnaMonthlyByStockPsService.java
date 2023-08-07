package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyByStockPsDto.*;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStockAggregationDto.SearchPdRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMonthlyByStockPsMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0136M01 월별 재고현황 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-07
 */

@Service
@RequiredArgsConstructor
public class WsnaMonthlyByStockPsService {

    private final WsnaMonthlyByStockPsMapper mapper;

    private final WsnaQomAsnStockAggregationService qomAsnStockService;

    /**
     * 창고 조회
     * @param dto
     * @return
     */
    public List<SearchWareRes> getMonthlyStateWareHouses(SearchWareReq dto) {
        return this.mapper.selectMonthlyStateWareHouses(dto);
    }

    /**
     * 품목 조회
     * @return
     */
    public List<SearchPdRes> getProducts() {
        return this.qomAsnStockService.getProducts();
    }

    /**
     * 월별 재고현황 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getMonthlyByStocksState(SearchReq dto) {
        return this.mapper.selectMonthlyByStocksState(dto);
    }

}
