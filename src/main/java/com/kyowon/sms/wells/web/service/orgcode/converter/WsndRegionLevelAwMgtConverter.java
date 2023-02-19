package com.kyowon.sms.wells.web.service.orgcode.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAwMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndRegionLevelAwDvo;

/**
 * <pre>
 * W-SV-U-0226M01 급지 수당 관리
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2022.12.14
 */
@Mapper(componentModel = "spring")
public interface WsndRegionLevelAwMgtConverter {

    WsndRegionLevelAwDvo mapSaveReqToWsndRegionLevelAwDvo(SaveReq dto);

}
