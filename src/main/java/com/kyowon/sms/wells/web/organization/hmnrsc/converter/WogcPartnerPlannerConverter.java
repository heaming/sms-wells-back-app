package com.kyowon.sms.wells.web.organization.hmnrsc.converter;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SaveQulificationReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerQualificationDvo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WogcPartnerPlannerConverter {
    WogcPartnerPlannerDvo mapSaveReqToWogcPartnerPlannerDvo(WogcPartnerPlannerDto.SaveReq dto);

    WogcPartnerPlannerDvo mapEditReqToWogcPartnerPlannerDvo(WogcPartnerPlannerDto.EditReq dto);

    WogcPartnerPlannerDvo mapDeleteReqToWogcPartnerPlannerDvo(WogcPartnerPlannerDto.DeleteReq dto);

    WogcPartnerPlannerQualificationDvo mapSaveQulificationReqToPartnerPlannerQualificationDvo(SaveQulificationReq dto);
}
