package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbDeliveryAggregateDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbDeliveryAggregateDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBsCsmbDeliveryAggregateMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0294M01 B/S소모품 배부집계 현황 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-06
 */

@Service
@RequiredArgsConstructor
public class WsnaBsCsmbDeliveryAggregateService {

    private final WsnaBsCsmbDeliveryAggregateMapper mapper;

    /**
     * 빌딩명 조회
     * @param mngtYmFrom
     * @param mngtYmTo
     * @return
     */
    public List<SearchBldRes> getBuildingList(String mngtYmFrom, String mngtYmTo) {
        return this.mapper.selectBuildingList(mngtYmFrom, mngtYmTo);
    }

    /**
     * 배부집계 현황 조회
     * @param dto
     * @return
     */
    public List<WsnaBsCsmbDeliveryAggregateDvo> getDeliveryAggregates(SearchReq dto) {
        return mapper.selectDeliveryAggregate(dto);
    }

}
