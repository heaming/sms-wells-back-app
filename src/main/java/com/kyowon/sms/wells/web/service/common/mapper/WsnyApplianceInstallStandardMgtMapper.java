package com.kyowon.sms.wells.web.service.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyApplianceInstallStandardMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyApplianceInstallStandardMgtDvo;

@Mapper
public interface WsnyApplianceInstallStandardMgtMapper {

    WsnyApplianceInstallStandardMgtDto.SearchRes selectInstallStandards(
        WsnyApplianceInstallStandardMgtDto.SearchReq searchReq
    );

    int saveApplianceInstallStandard(WsnyApplianceInstallStandardMgtDvo dvo);
}
