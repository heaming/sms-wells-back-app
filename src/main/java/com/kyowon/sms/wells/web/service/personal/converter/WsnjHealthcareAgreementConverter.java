package com.kyowon.sms.wells.web.service.personal.converter;

import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.SearchRes;
import com.kyowon.sms.wells.web.service.personal.dto.WsnjHealthcareAgreementDto.MergeReq;
import com.kyowon.sms.wells.web.service.personal.dvo.WsnjHealthcareAgreementDvo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WsnjHealthcareAgreementConverter {
    SearchRes mapWsnjHealthcareAgreementDvoToSearchRes(WsnjHealthcareAgreementDvo wsnjHealthcareAgreementDvo);

    WsnjHealthcareAgreementDvo mapMergeReqToWsnjHealthcareAgreementDvo(MergeReq mergeReq);
}
