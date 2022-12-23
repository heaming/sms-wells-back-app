package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseOgDvo;
import org.mapstruct.Mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto.*;

/**
 * <pre>
 * W-SV-U-0138M01 창고조직 관리
 * W-SV-U-0175P01 창고조직 등록
 * </pre>
 *
 * @author gs.piit58 송태성
 * @since 2022-12-13
 */
@Mapper(componentModel = "spring")
public interface WsnaWarehouseOgConverter {
    WsnaWarehouseOgDvo mapCreateReqToWsnaWarehouseOgDvo(CreateReq dto);
}
