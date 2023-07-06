package com.kyowon.sms.wells.web.service.common.service;

import static com.kyowon.sms.wells.web.service.common.dto.WsnyApplianceInstallStandardMgtDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.converter.WsnyApplianceInstallStandardMgtConverter;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyApplianceInstallStandardMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyApplianceInstallStandardMgtMapper;

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

    public SearchRes getApplianceInstallStandard(
        SearchReq dto
    ) {
        return mapper.selectInstallStandards(dto);
    }

    @Transactional
    public int saveApplianceInstallStandard(List<SaveReq> dtos) {
        int processCnt = 0;

        for (SaveReq dto : dtos) {
            WsnyApplianceInstallStandardMgtDvo dvo = converter.mapAllInstallStandardDvoToSaveRes(dto);

            mapper.saveApplianceInstallStandard(dvo);
        }

        return processCnt;
    }
}
