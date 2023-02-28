package com.kyowon.sms.wells.web.service.stock.converter;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbAreaChargeMgtDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRpbAreaChargeDvo;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEtcOutOfStorageDvo;
import org.mapstruct.Mapper;

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

    WsnaEtcOutOfStorageDvo mapDeleteReqToWsnaEtcOutOfStorageDvo(WsnaEtcOutOfStorageDto.DeleteReq dto);

    WsnaEtcOutOfStorageDvo mapSaveReqToWsnaEtcOutOfStorageDvo(WsnaEtcOutOfStorageDto.SaveReq dto);

}
