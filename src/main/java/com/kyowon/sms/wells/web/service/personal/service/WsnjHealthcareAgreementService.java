package com.kyowon.sms.wells.web.service.personal.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.personal.converter.WsnjHealthcareAgreementConverter;
import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.SearchReq;
import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.SearchRes;
import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.MergeReq;
import com.kyowon.sms.wells.web.service.personal.dvo.WsnjHealthcareAgreementDvo;
import com.kyowon.sms.wells.web.service.personal.mapper.WsnjHealthcareAgreementMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnjHealthcareAgreementService {
    private final WsnjHealthcareAgreementMapper mapper;

    private final WsnjHealthcareAgreementConverter converter;

    public SearchRes getHealthcareAgreement(
        SearchReq dto
    ) {
        WsnjHealthcareAgreementDvo wsnjHealthcareAgreementDvo = mapper.selectHealthcareAgreement(dto);
        if (wsnjHealthcareAgreementDvo == null)
            return null;

        return converter.mapWsnjHealthcareAgreementDvoToSearchRes(wsnjHealthcareAgreementDvo);
    }

    public int mergeHealthcareAgreement(MergeReq dto) {
        WsnjHealthcareAgreementDvo dvo = converter.mapMergeReqToWsnjHealthcareAgreementDvo(dto);

        return mapper.mergeHealthcareAgreement(dvo);
    }
}
