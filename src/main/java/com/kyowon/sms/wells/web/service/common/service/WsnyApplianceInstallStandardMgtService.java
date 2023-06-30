package com.kyowon.sms.wells.web.service.common.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.converter.WsnyApplianceInstallStandardMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyApplianceInstallStandardMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyApplianceInstallStandardMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyApplianceInstallStandardMgtMapper;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.system.config.core.service.MessageResourceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * K-W-SV-U-0303M01 환경가전 설치기준 관리
 * </pre>
 *
 * @author 이재훈
 * @since 2023-06-22
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnyApplianceInstallStandardMgtService {

    private final WsnyApplianceInstallStandardMgtMapper mapper;
    private final WsnyApplianceInstallStandardMgtConverter converter;
    private final Map<String, String> header;
    private final MessageResourceService messageResourceService;
    private final ExcelReadService excelReadService;

    public WsnyApplianceInstallStandardMgtDto.SearchRes getApplianceInstallStandard(
        WsnyApplianceInstallStandardMgtDto.SearchReq dto
    ) {
        return mapper.selectInstallStandards(dto);
    }

    @Transactional
    public int saveApplianceInstallStandard(WsnyApplianceInstallStandardMgtDto.SaveReq dto) {
        WsnyApplianceInstallStandardMgtDvo dvo = converter.mapAllInstallStandardDvoToSaveRes(dto);
        return mapper.saveApplianceInstallStandard(dvo);
    }
}
