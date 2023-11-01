package com.kyowon.sms.wells.web.service.stock.converter;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.CreateReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.SaveReq;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsDeliveryKssDvo;

/**
 * <pre>
 * W-SV-U-0192M01 개인창고출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.16
 */
@Mapper(componentModel = "spring", imports = {java.math.BigDecimal.class,
    org.apache.commons.lang3.ObjectUtils.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WsnaIndividualWareOstrConverter {

    @Mapping(target = "logisticStocQty", expression = "java( ObjectUtils.isEmpty(dto.logisticStocQty()) ? BigDecimal.ZERO : dto.logisticStocQty() )")
    WsnaIndividualWareOstrDvo mapSaveReqToWsnaIndividualWareOstrDvo(SaveReq dto);

    List<WsnaIndividualWareOstrDvo> mapAllSaveReqToWsnaIndividualWareOstrDvo(List<SaveReq> dtos);

    WsnaLogisticsDeliveryKssDvo mapCreateReqToWsnaLogisticsDeliveryKssDvo(CreateReq dto);
}
