package com.kyowon.sms.wells.web.contract.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchEntrepreneurBasesRes;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzPartnerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzPartnerService {

    private final WctzPartnerMapper mapper;

    public List<SearchEntrepreneurBasesRes> getEntrepreneurBases(String dlpnrCd) {
        return mapper.selectEntrepreneurBases(dlpnrCd);
    }
}
