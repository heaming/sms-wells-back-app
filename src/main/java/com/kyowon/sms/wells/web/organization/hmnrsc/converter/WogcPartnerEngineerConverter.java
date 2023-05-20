package com.kyowon.sms.wells.web.organization.hmnrsc.converter;

import com.kyowon.sms.common.web.organization.attachment.dto.ZogeSeizureDto;
import com.kyowon.sms.common.web.organization.attachment.dvo.ZogeSeizureDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerEngineerDvo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WogcPartnerEngineerConverter {
    WogcPartnerEngineerDvo mapSaveJoeManagementReqToWogcPartnerEngineerDvo(
        WogcPartnerEngineerDto.SaveJoeManagementReq dto
    );

    List<WogcPartnerEngineerDto.FindJoeManagementRes> mapWogcPartnerEngineerDvoToFindJoeManagementRes(
        List<WogcPartnerEngineerDvo> dvos
    );

    WogcPartnerEngineerDvo mapSaveEngineerGradeReqToWogcPartnerEngineerDvo(
        WogcPartnerEngineerDto.SaveEngineerGradeReq dto
    );

    WogcPartnerEngineerDvo mapSaveEngineerAttendReqToWogcPartnerEngineerDvo(
        WogcPartnerEngineerDto.SaveEngineerAttendReq dto
    );
}
