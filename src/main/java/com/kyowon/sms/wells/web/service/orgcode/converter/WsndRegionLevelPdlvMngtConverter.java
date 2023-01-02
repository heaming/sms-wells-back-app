package com.kyowon.sms.wells.web.service.orgcode.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMngtDto.DeleteReq;
import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelPdlvMngtDto.SaveReq;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndPlaceOfDeliveryDvo;

/**
 * <pre>
 * W-SV-U-0218M01 - 급지 출고지 관리
 * </pre>
 *
 * @author gs.piit129 천영화
 * @since 2022.12.30
 */
@Mapper(componentModel = "spring")
public interface WsndRegionLevelPdlvMngtConverter {

    WsndPlaceOfDeliveryDvo mapDeleteReqToWsndPlaceOfDeliveryDvo(DeleteReq dto);

    WsndPlaceOfDeliveryDvo mapSaveReqToWsndPlaceOfDeliveryDvo(SaveReq dto);
}
