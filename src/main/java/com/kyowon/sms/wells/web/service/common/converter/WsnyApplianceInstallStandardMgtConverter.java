package com.kyowon.sms.wells.web.service.common.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyApplianceInstallStandardMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyApplianceInstallStandardMgtDvo;

@Mapper(componentModel = "spring")
public interface WsnyApplianceInstallStandardMgtConverter {
    List<WsnyApplianceInstallStandardMgtDto.SearchRes> mapAllInstallStandardDvoToSearchRes(
        List<WsnyApplianceInstallStandardMgtDvo> dvos
    );

    WsnyApplianceInstallStandardMgtDvo mapAllInstallStandardDvoToSaveRes(
        WsnyApplianceInstallStandardMgtDto.SaveReq dto
    );
}
