package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemByStockAggregationDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemByStockAggregationDto.SearchWareRes;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemByStockAggregationConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemByStockAggregationDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemByStockAggregationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0140M01 품목별 재고 집계 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-08
 */

@Service
@RequiredArgsConstructor
public class WsnaItemByStockAggregationService {

    private final WsnaItemByStockAggregationMapper mapper;

    private final WsnaItemByStockAggregationConverter converter;

    /**
     * 창고 조회 (PIVOT Header 용)
     * @param baseDt
     * @return
     */
    public List<SearchWareRes> getWareHouses(String baseDt) {
        ValidAssert.hasText(baseDt);

        return this.mapper.selectItemByStockWareHouses(baseDt);
    }

    /**
     * 품목별 재고 집계 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<HashMap<String, Object>> getItemByStockAggsPaging(SearchReq dto, PageInfo pageInfo) {

        WsnaItemByStockAggregationDvo dvo = this.converter.mapSearchReqToWsnaItemByStockAggregationDvo(dto);
        String baseDt = dvo.getBaseDt();

        // 창고 리스트 조회
        List<SearchWareRes> wares = this.mapper.selectItemByStockWareHouses(baseDt);

        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.wareNo() + "' AS QTY_" + item.wareNo())
            .collect(Collectors.joining(","));

        // PIVOT 필드
        String wareNoFields = wares.stream()
            .map(item -> "T2.QTY_" + item.wareNo())
            .collect(Collectors.joining(","));

        dvo.setWareNoInStr(wareNoInStr);
        dvo.setWareNoFields(wareNoFields);

        return this.setItemByStockAggsPaging(dvo, pageInfo);
    }

    /**
     * 품목별 재고 집계 페이징 처리
     * @param dvo
     * @param pageInfo
     * @return
     */
    private PagingResult<HashMap<String, Object>> setItemByStockAggsPaging(
        WsnaItemByStockAggregationDvo dvo, PageInfo pageInfo
    ) {

        boolean needTotalCount = pageInfo.isNeedTotalCount();
        // 총 건수를 조회해야 할 경우
        if (needTotalCount) {
            // 건수 조회
            long totalCount = this.mapper.selectItemByStockAggCount(dvo);
            pageInfo.setTotalCount(totalCount);
            pageInfo.setNeedTotalCount(Boolean.FALSE);
        }

        int pageIndex = pageInfo.getPageIndex();
        int pageSize = pageInfo.getPageSize();

        int startRow = (pageIndex - 1) * pageSize;
        dvo.setOffSet(startRow);
        dvo.setSize(pageSize);

        List<HashMap<String, Object>> stockAggs = this.mapper.selectItemByStockAggs(dvo);

        return new PagingResult<>(
            stockAggs, pageInfo
        );
    }

    /**
     * 품목별 재고 집계 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<HashMap<String, Object>> getItemByStockAggsExcelDownload(SearchReq dto) {

        WsnaItemByStockAggregationDvo dvo = this.converter.mapSearchReqToWsnaItemByStockAggregationDvo(dto);
        String baseDt = dvo.getBaseDt();

        // 창고 리스트 조회
        List<SearchWareRes> wares = this.mapper.selectItemByStockWareHouses(baseDt);

        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.wareNo() + "' AS QTY_" + item.wareNo())
            .collect(Collectors.joining(","));

        // PIVOT 필드
        String wareNoFields = wares.stream()
            .map(item -> "T2.QTY_" + item.wareNo())
            .collect(Collectors.joining(","));

        dvo.setWareNoInStr(wareNoInStr);
        dvo.setWareNoFields(wareNoFields);

        return this.mapper.selectItemByStockAggs(dvo);
    }
}
