package com.kyowon.sms.wells.web.service.personal.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.personal.converter.WsnjHealthcareAgreementConverter;
import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.SearchReq;
import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.SearchRes;
import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.MergeReq;
import com.kyowon.sms.wells.web.service.personal.dvo.WsnjHealthcareAgreementDvo;
import com.kyowon.sms.wells.web.service.personal.mapper.WsnjHealthcareAgreementMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public int mergeHealthcareAgreement(MergeReq dto) {
        WsnjHealthcareAgreementDvo dvo = converter.mapMergeReqToWsnjHealthcareAgreementDvo(dto);

        if (mapper.mergeHealthcareAgreement(dvo) == 1) {
            return mapper.updateHealthcareAgreementAgreementImageContent(dvo);
        }
        return 0;
    }
}
