package com.kyowon.sms.wells.web.bond.consultation.service;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncSameCustomerContractMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncSameCustomerContractService {
    private final WbncSameCustomerContractMapper mapper;

    public List<FindContractRes> getSameCustomerContracts(
        String cstNo, String safeKey, String clctamPrtnrNo
    ) {
        return mapper.selectSameCustomerContracts(cstNo, safeKey, clctamPrtnrNo);
    }

    public List<FindDepositRes> getSameCstCntrDeposits(
        String bndCntrRefId
    ) {
        return mapper.selectSameCstCntrDeposits(bndCntrRefId);
    }

    public FindDepositDtlRes getSameCstCntrDeposit(String bndCntrRefId) {
        return mapper.selectSameCstCntrDeposit(bndCntrRefId);
    }
}
