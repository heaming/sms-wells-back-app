package com.kyowon.sms.wells.web.service.visit.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFiverbikeApplicationAgreementDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbFiverbikeApplicationAgreementDvo;

/**
 * <pre>
 * W-SV-U-0150P01 피버바이크 신청동의서 화면
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.06.01
 */
@Mapper(componentModel = "spring")
public interface WsnbFiverbikeApplicationAgreementConverter {

    WsnbFiverbikeApplicationAgreementDvo mapCreateReqToWsnbFiverbikeApplicationAgreementDvo(CreateReq dto);

}
