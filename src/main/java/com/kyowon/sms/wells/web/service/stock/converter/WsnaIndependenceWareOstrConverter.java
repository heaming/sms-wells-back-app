package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.CreateReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.SaveReq;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsDeliveryKssDvo;

/**
 * <pre>
 * W-SV-U-0193M01 독립창고출고관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-03-28
 */
@Mapper(componentModel = "spring", imports = {java.math.BigDecimal.class,
    org.apache.commons.lang3.ObjectUtils.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaIndependenceWareOstrConverter {

    @Mapping(target = "logisticStocQty", expression = "java( ObjectUtils.isEmpty(dto.logisticStocQty()) ? BigDecimal.ZERO : dto.logisticStocQty() )")
    WsnaIndependenceWareOstrDvo mapSaveReqToWsnaIndependenceWareOstrDvo(SaveReq dto);

    List<WsnaIndependenceWareOstrDvo> mapAllSaveReqToWsnaIndependenceWareOstrDvo(List<SaveReq> dtos);

    WsnaLogisticsDeliveryKssDvo mapCreateReqToWsnaLogisticsDeliveryKssDvo(CreateReq dto);

}
