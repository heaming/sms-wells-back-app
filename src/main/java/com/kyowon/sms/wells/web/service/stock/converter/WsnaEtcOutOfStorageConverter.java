package com.kyowon.sms.wells.web.service.stock.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto.DeleteReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEtcOutOfStorageDvo;

/**
 * <pre>
 * W-SV-U-0143M01 기타출고 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.11
 */
@Mapper(componentModel = "spring")
public interface WsnaEtcOutOfStorageConverter {

    WsnaEtcOutOfStorageDvo mapDeleteReqToWsnaEtcOutOfStorageDvo(DeleteReq dto);

    WsnaEtcOutOfStorageDvo mapSaveReqToWsnaEtcOutOfStorageDvo(SaveReq dto);

}
