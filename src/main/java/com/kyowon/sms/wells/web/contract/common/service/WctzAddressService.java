package com.kyowon.sms.wells.web.contract.common.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.common.dto.WctzAddressDto.SearchAdrRes;
import com.kyowon.sms.wells.web.contract.common.mapper.WctzAddressMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctzAddressService {

    private final WctzAddressMapper mapper;

    public SearchAdrRes getContractorAddressByCntr(String cntrNo) {
        return mapper.selectContractorAddressByCntr(cntrNo);
    }

    public SearchAdrRes getInstallerAddressByCntr(String dtlCntrNo, int dtlCntrSn) {
        return mapper.selectInstallerAddressByCntr(dtlCntrNo, dtlCntrSn);
    }
}
