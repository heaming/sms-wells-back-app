package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.*;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncSameCustomerContractMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncSameCustomerContractService {
    private final WbncSameCustomerContractMapper mapper;

    public List<FindContractRes> getContracts(String cstNo) {
        return mapper.selectContracts(cstNo);
    }

    public List<FindDepositRes> getContractDeposits(String bndBizDvCd, String cntrNo, int cntrSn) {
        return mapper.selectContractDeposits(bndBizDvCd, cntrNo, cntrSn);
    }

    public FindDepositDtlRes getContractDeposit(String bndBizDvCd, String cntrNo, int cntrSn) {
        return mapper.selectContractDeposit(bndBizDvCd, cntrNo, cntrSn);
    }

    public FindBreachOfPromiseRes getBreachOfPromise(String bndCntrRefId) {
        return mapper.selectBreachOfPromise(bndCntrRefId);
    }

    public FindSalesRes getContractSales(String bndCntrRefId) {
        return mapper.selectContractSales(bndCntrRefId);
    }
}
