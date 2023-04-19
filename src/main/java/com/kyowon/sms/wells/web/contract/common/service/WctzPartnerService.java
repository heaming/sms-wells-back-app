package com.kyowon.sms.wells.web.contract.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchBranchDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchEntrepreneurBaseRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchGeneralDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzPartnerDto.SearchRegionalDivisionsRes;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzPartnerMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzPartnerService {

    private final WctzPartnerMapper mapper;

    public List<SearchEntrepreneurBaseRes> getEntrepreneurBases(String bzrno) {
        return mapper.selectEntrepreneurBases(bzrno);
    }

    public List<SearchGeneralDivisionsRes> getGeneralDivisions(String baseYm) {
        return mapper.selectGeneralDivisions(baseYm);
    }

    public List<SearchRegionalDivisionsRes> getRegionalDivisions(String baseYm) {
        return mapper.selectRegionalDivisions(baseYm);
    }

    public List<SearchBranchDivisionsRes> getBranchDivisions(String baseYm) {
        return mapper.selectBranchDivisions(baseYm);
    }
}
