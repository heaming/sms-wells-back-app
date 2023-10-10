package com.kyowon.sms.wells.web.bond.consultation.service;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncServiceDto.FindRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncServiceMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 서비스상세
 * </pre>
 *
 * @author songmi.in
 * @since 2023-05-15
 */
@Service
@RequiredArgsConstructor
public class WbncServiceService {
    private final WbncServiceMapper mapper;

    /**
     * 서비스상세 - 조회
     * @param cntrNo, cntrSn
     * @return
     */
    public List<FindRes> getServices(String cntrNo, int cntrSn) {
        return mapper.selectServices(cntrNo, cntrSn);
    }
}
