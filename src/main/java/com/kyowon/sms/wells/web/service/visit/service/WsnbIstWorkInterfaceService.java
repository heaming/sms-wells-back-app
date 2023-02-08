package com.kyowon.sms.wells.web.service.visit.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstWorkInterfaceDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIstWorkInterfaceDto.CreateRes;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbIstWorkInterfaceMapper;

import lombok.RequiredArgsConstructor;

/**
 * TODO: API 스펙 확인 후 수정 필요
 * <pre>
 * W-SV-S-0001 타시스템용(Wells) 설치 오더 생성 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.08
 */
@Service
@RequiredArgsConstructor
public class WsnbIstWorkInterfaceService {

    private final WsnbIstWorkInterfaceMapper mapper;

    private final WsnbServiceWorkInterfaceService serviceWorkInterfaceService;

    @Transactional
    public CreateRes createInstallationWorks(CreateReq createReq) {

        serviceWorkInterfaceService.saveDefaultData();

        // LC_ASREGN_API_U06 KSS 홈케어 맴버쉽 패키지 설치오더 생성

        return new CreateRes("S001");
    }

}
