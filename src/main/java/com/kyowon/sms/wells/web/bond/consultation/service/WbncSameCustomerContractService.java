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

    public FindDepositInfoRes getContractDeposit(String bndBizDvCd, String cntrNo, int cntrSn) {
        return mapper.selectContractDeposit(bndBizDvCd, cntrNo, cntrSn);
    }

    public FindBreachOfPromiseRes getBreachOfPromise(String bndBizDvCd, String cntrNo, int cntrSn) {
        return mapper.selectBreachOfPromise(bndBizDvCd, cntrNo, cntrSn);
    }

    public FindSalesRes getContractSales(String bndCntrRefId) {
        return mapper.selectContractSales(bndCntrRefId);
    }

    public List<FindDepositDtlRes> getDeposits(String cntrNo, int cntrSn) {
        return mapper.selectDeposits(cntrNo, cntrSn);
    }
}
