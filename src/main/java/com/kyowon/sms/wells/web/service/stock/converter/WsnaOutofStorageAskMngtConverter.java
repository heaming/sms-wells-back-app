package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskMngtDvo;
import org.mapstruct.Mapper;

/**
 * <pre>
 * W-SV-U-0172P01 출고요청 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.04.21
 */
@Mapper(componentModel = "spring")
public interface WsnaOutofStorageAskMngtConverter {

    WsnaOutOfStorageAskMngtDvo mapSaveReqToOutOfStorageAskMngtDvo(WsnaOutOfStorageAskMngtDto.SaveReq dto);

}
