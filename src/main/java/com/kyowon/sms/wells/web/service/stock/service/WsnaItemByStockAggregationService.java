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
     * @param baseDt    (필수) 기준일자
     * @param wareDvCd  (필수) 창고구분코드
     * @return
     */
    public List<SearchWareRes> getWareHouses(String baseDt, String wareDvCd) {
        ValidAssert.hasText(baseDt);

        return this.mapper.selectItemByStockWareHouses(baseDt, wareDvCd);
    }

    /**
     * 품목별 재고 집계 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<HashMap<String, Object>> getItemByStockAggsExcelDownload(SearchReq dto) {

        WsnaItemByStockAggregationDvo dvo = this.converter.mapSearchReqToWsnaItemByStockAggregationDvo(dto);
        // 기준일자
        String baseDt = dvo.getBaseDt();
        // 창고구분
        String wareDvCd = dvo.getWareDvCd();

        // 창고 리스트 조회
        List<SearchWareRes> wares = this.mapper.selectItemByStockWareHouses(baseDt, wareDvCd);

        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.wareNo() + "' AS QTY_" + item.wareNo())
            .collect(Collectors.joining(","));

        // PIVOT 필드
        String wareNoFields = wares.stream()
            .map(item -> "NVL(T2.QTY_" + item.wareNo() + ", 0) AS QTY_" + item.wareNo())
            .collect(Collectors.joining(","));

        dvo.setWareNoInStr(wareNoInStr);
        dvo.setWareNoFields(wareNoFields);

        return this.mapper.selectItemByStockAggs(dvo);
    }
}
