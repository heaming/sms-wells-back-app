package com.kyowon.sms.wells.web.organization.insurance.converter;

import org.mapstruct.Mapper;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.EditReq;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdIndustrialDisasterInsuranceDto.RemoveReq;
import com.kyowon.sms.wells.web.organization.insurance.dvo.WogdIndustrialDisasterInsuranceDvo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WogdIndustrialDisasterInsuranceConverter {
    List<WogdIndustrialDisasterInsuranceDvo> mapAllEditReqToWogdInddInsrDvo(List<EditReq> dtos);

    List<WogdIndustrialDisasterInsuranceDvo> mapAllRemoveReqToWogdInddInsrDvo(List<RemoveReq> dtos);
}
