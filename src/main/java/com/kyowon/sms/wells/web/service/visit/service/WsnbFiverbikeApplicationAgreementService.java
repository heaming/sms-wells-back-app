package com.kyowon.sms.wells.web.service.visit.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbFiverbikeApplicationAgreementConverter;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFiverbikeApplicationAgreementDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFiverbikeApplicationAgreementDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbFiverbikeApplicationAgreementDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbFiverbikeApplicationAgreementDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbFiverbikeApplicationAgreementMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0150P01 피버바이크 신청동의서 화면
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.06.01
 */
@Service
@RequiredArgsConstructor
public class WsnbFiverbikeApplicationAgreementService {

    private final WsnbFiverbikeApplicationAgreementMapper mapper;
    private final WsnbFiverbikeApplicationAgreementConverter converter;

    public SearchRes getFiverbikeApplicationAgreement(SearchReq dto) {
        return mapper.selectFiverbikeApplicationAgreement(dto);
    }

    public int createFiverbikeApplicationAgreement(CreateReq dto) {
        int processCount = 0;
        WsnbFiverbikeApplicationAgreementDvo dvo = converter.mapCreateReqToWsnbFiverbikeApplicationAgreementDvo(dto);
        // 서비스쪽 테이블 insert

        processCount += mapper.insertFiverbikeApplicationAgreement(dvo);
        // 계약쪽 테이블 update?? insert??
        return processCount;
    }
}
