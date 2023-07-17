package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaMaterialsAssignStocksDto.CreateReq;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMaterialsAssignStocksDvo;

/**
 * <pre>
 * W-SV-U-0125M01 물량배정 입고창고관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-02-10
 */
@Mapper(componentModel = "spring")
public interface WsnaMaterialsAssignStocksConverter {
    List<WsnaMaterialsAssignStocksDvo> mapAllCreateReqToWsnaMaterialsAssignStocksDvo(List<CreateReq> dtos);
}
