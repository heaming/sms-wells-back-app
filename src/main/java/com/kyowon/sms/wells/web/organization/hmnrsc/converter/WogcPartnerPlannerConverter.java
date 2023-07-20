package com.kyowon.sms.wells.web.organization.hmnrsc.converter;

import java.util.List;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerDvo;
import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveEngineerGradeReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveJoeManagementReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerEngineerDvo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper(componentModel = "spring")
public interface WogcPartnerPlannerConverter {
    WogcPartnerPlannerDvo mapSaveReqToWogcPartnerPlannerDvo(WogcPartnerPlannerDto.SaveReq dto);

    WogcPartnerPlannerDvo mapEditReqToWogcPartnerPlannerDvo(WogcPartnerPlannerDto.EditReq dto);

    WogcPartnerPlannerDvo mapDeleteReqToWogcPartnerPlannerDvo(WogcPartnerPlannerDto.DeleteReq dto);

}
