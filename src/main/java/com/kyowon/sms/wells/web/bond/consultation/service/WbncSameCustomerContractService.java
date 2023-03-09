package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindBreachOfPromiseRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindContractRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositDtlRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindDepositRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.FindSalesRes;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncSameCustomerContractMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncSameCustomerContractService {
    private final WbncSameCustomerContractMapper mapper;

    public List<FindContractRes> getContracts(
        String cstNo, String safeKey, String clctamPrtnrNo
    ) {
        return mapper.selectContracts(cstNo, safeKey, clctamPrtnrNo);
    }

    public List<FindDepositRes> getContractDeposits(
        String bndCntrRefId
    ) {
        return mapper.selectContractDeposits(bndCntrRefId);
    }

    public FindDepositDtlRes getContractDeposit(String bndCntrRefId) {
        return mapper.selectContractDeposit(bndCntrRefId);
    }

    public FindBreachOfPromiseRes getBreachOfPromise(String bndCntrRefId) {
        return mapper.selectBreachOfPromise(bndCntrRefId);
    }

    public FindSalesRes getContractSales(String bndCntrRefId) {
        return mapper.selectContractSales(bndCntrRefId);
    }
}
