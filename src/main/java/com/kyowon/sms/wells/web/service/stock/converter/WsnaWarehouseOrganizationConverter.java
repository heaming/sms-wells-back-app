package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseOrganizationDvo;
import org.mapstruct.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.*;

/**
 * <pre>
 * W-SV-U-0138M01 창고조직 관리
 * W-SV-U-0175P01 창고조직 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2022-12-13
 */
@Mapper(componentModel = "spring")
public interface WsnaWarehouseOrganizationConverter {
    WsnaWarehouseOrganizationDvo mapCreateReqToWsnaWarehouseOgDvo(CreateReq dto);
}
