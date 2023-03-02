package com.kyowon.sms.wells.web.contract.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchEntrepreneurBaseRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchGeneralDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchRegionalDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzPartnerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzPartnerService {

    private final WctzPartnerMapper mapper;

    public List<SearchEntrepreneurBaseRes> getEntrepreneurBases(String dlpnrCd) {
        return mapper.selectEntrepreneurBases(dlpnrCd);
    }

    public List<SearchGeneralDivisionsRes> getGeneralDivisions() {
        return mapper.selectGeneralDivisions();
    }

    public List<SearchRegionalDivisionsRes> getRegionalDivisions() {
        return mapper.selectRegionalDivisions();
    }
}
