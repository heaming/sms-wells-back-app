package com.kyowon.sms.wells.web.bond.consultation.service;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncServiceDto.FindRes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncServiceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncServiceService {
    private final WbncServiceMapper mapper;

    public List<FindRes> getServices(String cntrNo, int cntrSn) {
        return mapper.selectServices(cntrNo, cntrSn);
    }
}
