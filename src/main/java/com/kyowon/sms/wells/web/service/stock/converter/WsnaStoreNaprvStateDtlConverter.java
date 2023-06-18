package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDtlDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStoreNaprvStateDtlDvo;

/**
 *
 * <pre>
 * 입고 미승인상세현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.06.13
 */
@Mapper(componentModel = "spring")
public interface WsnaStoreNaprvStateDtlConverter {

    WsnaStoreNaprvStateDtlDvo mapSaveReqToStoreNaprvStateDtlDvo(WsnaStoreNaprvStateDtlDto.SaveReq dto);

}
