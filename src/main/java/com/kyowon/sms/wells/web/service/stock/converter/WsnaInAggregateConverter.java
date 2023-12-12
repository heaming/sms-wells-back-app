package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaInAggregateDto.SearchReq;
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
@Mapper(componentModel = "spring")
public interface WsnaInAggregateConverter {
    WsnaInAggregateDvo mapSearchResDvo(SearchReq dto);

}
