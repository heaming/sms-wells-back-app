package com.kyowon.sms.wells.web.service.orgcode.converter;

import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndRegionLevelAlwncDvo;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRegionLevelAlwncMgtDto.SaveReq;

/**
 * <pre>
 * W-SV-U-0226M01 급지 수당 관리
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2022.12.14
 */
@Mapper(componentModel = "spring")
public interface WsndRegionLevelAlwncMgtConverter {

    WsndRegionLevelAlwncDvo mapSaveReqToWsndRegionLevelAwDvo(SaveReq dto);

}
