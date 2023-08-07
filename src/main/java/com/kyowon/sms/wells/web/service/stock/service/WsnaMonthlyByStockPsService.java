package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMonthlyByStockPsDto.*;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStockAggregationDto.SearchPdRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMonthlyByStockPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

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
     * 월별 재고현황 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getMonthlyByStocksStatePaging(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectMonthlyByStocksState(dto, pageInfo);
    }

    /**
     * 월별 재고현황 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getMonthlyByStocksStateExcelDownload(SearchReq dto) {
        return this.mapper.selectMonthlyByStocksState(dto);
    }

}
