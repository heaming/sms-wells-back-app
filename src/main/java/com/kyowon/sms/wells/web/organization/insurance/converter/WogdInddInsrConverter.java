package com.kyowon.sms.wells.web.organization.insurance.converter;

import org.mapstruct.Mapper;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdInddInsrDto.EditReq;
import com.kyowon.sms.wells.web.organization.insurance.dto.WogdInddInsrDto.RemoveReq;
import com.kyowon.sms.wells.web.organization.insurance.dvo.WogdInddInsrDvo;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WogdInddInsrConverter {
    List<WogdInddInsrDvo> mapAllEditReqToWogdInddInsrDvo(List<EditReq> dtos);

    List<WogdInddInsrDvo> mapAllRemoveReqToWogdInddInsrDvo(List<RemoveReq> dtos);
}
