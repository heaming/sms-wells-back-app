package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaMovementStoreDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMovementStoreConfirmDvo;

/**
 * <pre>
 * W-SV-U-0169P01 이관입고 등록
 * </pre>
 *
 * @author inho.Choi
 * @since 2023.04.14
 */
@Mapper(componentModel = "spring")
public interface WsnaMovementStoreConfirmConverter {
    WsnaMovementStoreConfirmDvo mapDtoToWsnaMovementStoreConfirmDvo(
        WsnaMovementStoreDto.MovementStrSaveReq dto
    );
}
