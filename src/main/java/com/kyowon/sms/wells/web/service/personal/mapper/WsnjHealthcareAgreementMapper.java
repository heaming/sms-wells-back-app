package com.kyowon.sms.wells.web.service.personal.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.SearchReq;
import com.kyowon.sms.wells.web.service.personal.dvo.WsnjHealthcareAgreementDvo;

@Mapper
public interface WsnjHealthcareAgreementMapper {

    WsnjHealthcareAgreementDvo selectHealthcareAgreement(SearchReq dto);

    int mergeHealthcareAgreement(WsnjHealthcareAgreementDvo dvo);

    int updateHealthcareAgreementAgreementImageContent(WsnjHealthcareAgreementDvo dvo);
}
