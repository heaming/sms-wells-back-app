package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbInstallLocationMgtDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallLocationDvo;

/**
 * <pre>
 * W-SV-U-0059M01 - 설치 위치 상세 관리
 * </pre>
 *
 * @author yeonghwa.cheon 천영화
 * @since 2023.01.02
 */
@Mapper(componentModel = "spring")
public interface WsnbInstallLocationMgtConverter {

    WsnbInstallLocationDvo mapCreateReqToWsnbInstallLocationDvo(CreateReq dto);

}
