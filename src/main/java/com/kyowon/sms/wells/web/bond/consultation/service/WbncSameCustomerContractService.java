package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.*;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncSameCustomerContractMapper;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 동일고객 상세
 * </pre>
 *
 * @author songmi.in
 * @since 2023-02-21
 */
@Service
@RequiredArgsConstructor
public class WbncSameCustomerContractService {
    private final WbncSameCustomerContractMapper mapper;

    /**
      * 동일고객 계약내역 조회
      * @param cstNo
      * @return 조회결과
      */
    public List<FindContractRes> getContracts(String cstNo) {
        return mapper.selectContracts(cstNo);
    }

    /**
      * 동일고객 계약 입금정보 조회
      * @param cntrNo, cntrSn
      * @return 조회결과
      */
    public List<FindDepositRes> getContractDeposits(String cntrNo, int cntrSn) {
        return mapper.selectContractDeposits(cntrNo, cntrSn);
    }

    /**
      * 동일고객 계약 입금상세정보 조회
      * @param bndBizDvCd, cntrNo, cntrSn
      * @return 조회결과
      */
    public FindDepositInfoRes getContractDeposit(String bndBizDvCd, String cntrNo, int cntrSn) {
        return mapper.selectContractDeposit(bndBizDvCd, cntrNo, cntrSn);
    }

    /**
     * 동일고객 계약 위약정보 조회
     * @param bndBizDvCd, cntrNo, cntrSn
     * @return 조회결과
     */
    public FindBreachOfPromiseRes getBreachOfPromise(String bndBizDvCd, String cntrNo, int cntrSn) {
        return mapper.selectBreachOfPromise(bndBizDvCd, cntrNo, cntrSn);
    }

    /**
      * 동일고객 계약 매출정보 조회
      * @param bndCntrRefId
      * @return 조회결과
      */
    public FindSalesRes getContractSales(String bndCntrRefId) {
        return mapper.selectContractSales(bndCntrRefId);
    }

    /**
      * 동일고객 계약 입금내역 조회
      * @param cntrNo, cntrSn
      * @return 조회결과
      */
    public List<FindDepositDtlRes> getDeposits(String cntrNo, int cntrSn) {
        return mapper.selectDeposits(cntrNo, cntrSn);
    }
}
