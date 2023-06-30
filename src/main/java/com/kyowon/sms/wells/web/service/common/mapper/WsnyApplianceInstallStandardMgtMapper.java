package com.kyowon.sms.wells.web.service.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyApplianceInstallStandardMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyApplianceInstallStandardMgtDvo;

/**
 *
 * <pre>
 * W-SV-U-0016M01 AS 코드관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-11-08
 */
@Mapper
public interface WsnyApplianceInstallStandardMgtMapper {

    WsnyApplianceInstallStandardMgtDto.SearchRes selectInstallStandards(
        WsnyApplianceInstallStandardMgtDto.SearchReq searchReq
    );

    int saveApplianceInstallStandard(WsnyApplianceInstallStandardMgtDvo dvo);
}
