package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCallingLogSaveDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCallingLogDvo;

/**
 * <pre>
 * W-SV-S-0013 PR_KIWI_WRK_CREATE_V2M 호출 로그 저장
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.08
 */
@Mapper(componentModel = "spring")
public interface WsnbCallingLogSaveConverter {

    WsnbCallingLogDvo mapCreateReqToWsnbCallingLogDvo(CreateReq dto);

}
