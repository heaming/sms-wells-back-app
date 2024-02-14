package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnStockAggregationDto.SearchPdRes;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWasteFilterCollectionPsDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWasteFilterCollectionPsDto.SearchRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaWasteFilterCollectionPsMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0275M01 폐필터 회수 현황 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-10
 */

@Service
@RequiredArgsConstructor
public class WsnaWasteFilterCollectionPsService {

    private final WsnaWasteFilterCollectionPsMapper mapper;

    /**
     * 품목 리스트 조회
     * @return
     */
    public List<SearchPdRes> getProducts() {
        return this.mapper.selectProducts();
    }

    /**
     * 폐필터 회수현황 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getWasteFilterCollections(SearchReq dto) {
        return this.mapper.selectWasteFilterCollections(dto);
    }

}
