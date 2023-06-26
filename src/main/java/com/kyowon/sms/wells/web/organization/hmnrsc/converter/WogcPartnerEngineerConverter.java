package com.kyowon.sms.wells.web.organization.hmnrsc.converter;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerEngineerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveJoeManagementReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.FindJoeManagementRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerEngineerDto.SaveEngineerGradeReq;

import com.sds.sflex.system.config.datasource.PagingResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WogcPartnerEngineerConverter {
    WogcPartnerEngineerDvo mapSaveJoeManagementReqToWogcPartnerEngineerDvo(
        SaveJoeManagementReq dto
    );

    List<FindJoeManagementRes> mapWogcPartnerEngineerDvoToFindJoeManagementRes(
        List<WogcPartnerEngineerDvo> dvos
    );

    WogcPartnerEngineerDvo mapSaveEngineerGradeReqToWogcPartnerEngineerDvo(
        SaveEngineerGradeReq dto
    );

    WogcPartnerEngineerDvo mapSaveEngineerAttendReqToWogcPartnerEngineerDvo(
        WogcPartnerEngineerDto.SaveEngineerAttendReq dto
    );

    PagingResult<WogcPartnerEngineerDto.FindJoeManagementRes> mapAllWogcPartnerEngineerDvoToFindJoeManagementRes(PagingResult<WogcPartnerEngineerDvo> dvos);
}
