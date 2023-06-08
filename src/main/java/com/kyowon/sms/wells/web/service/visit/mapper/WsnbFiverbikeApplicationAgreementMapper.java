package com.kyowon.sms.wells.web.service.visit.mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbFiverbikeApplicationAgreementDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbFiverbikeApplicationAgreementDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFiverbikeApplicationAgreementDto.SearchRes;

/**
 * <pre>
 * W-SV-U-0150P01 피버바이크 신청동의서 화면
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.06.01
 */
@Mapper
public interface WsnbFiverbikeApplicationAgreementMapper {

    SearchRes selectFiverbikeApplicationAgreement(SearchReq dto);

    int insertFiverbikeApplicationAgreement(WsnbFiverbikeApplicationAgreementDvo dvo);
}
