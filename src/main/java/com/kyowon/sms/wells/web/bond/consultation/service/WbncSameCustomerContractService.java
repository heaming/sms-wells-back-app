package com.kyowon.sms.wells.web.bond.consultation.service;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.*;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncSameCustomerContractMapper;
import com.sds.sflex.common.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
        var defaultBreachPromise = mapper.selectBreachOfPromise(bndBizDvCd, cntrNo, cntrSn);
        // 고객요청해약(301), 연체해약(302) 일 경우 조회된 위약금 정보 반환
        if (StringUtil.isNotEmpty(defaultBreachPromise.cntrDtlStatCd()) && List.of("301", "302").contains(defaultBreachPromise.cntrDtlStatCd())) {
            return defaultBreachPromise;
        } else {
            // 고객요청해약(301), 연체해약(302) 이 아닐 경우 금액 모두 0 으로 반환
            return FindBreachOfPromiseRes.builder()
                .borAmt(String.valueOf(BigDecimal.ZERO))
                .borBlam(String.valueOf(BigDecimal.ZERO))
                .rgstCostDscBorAmt(String.valueOf(BigDecimal.ZERO))
                .rentalDscBorAmt(String.valueOf(BigDecimal.ZERO))
                .csmbCostBorAmt(String.valueOf(BigDecimal.ZERO))
                .pBorAmt(String.valueOf(BigDecimal.ZERO))
                .reqdCsBorAmt(String.valueOf(BigDecimal.ZERO))
                .lsRntf(String.valueOf(BigDecimal.ZERO))
                .rstlBorAmt(String.valueOf(BigDecimal.ZERO))
                .cntrDtlStatCd(defaultBreachPromise.cntrDtlStatCd()) /* 현재계약상세상태코드 */
                .dpCcamSumAmt(String.valueOf(BigDecimal.ZERO)) /* 입금위약금합계금액 */
                .thmSlSumAmt(String.valueOf(BigDecimal.ZERO)) /* 당월매출합계금액 */
                .acuDpAmt(String.valueOf(BigDecimal.ZERO)) /* 누적입금금액 */
                .ucAmt(String.valueOf(BigDecimal.ZERO)) /* 미수금액 */
                .rsgBorAmt(String.valueOf(BigDecimal.ZERO)) /* 해지위약금액 */
                .build();
        }
    }

    /**
     * 동일고객 계약 매출정보 조회
     * @param bndBizDvCd, cntrNo, cntrSn
     * @return 조회결과
     */
    public FindSalesRes getContractSales(String bndBizDvCd, String cntrNo, int cntrSn) {
        return mapper.selectContractSales(bndBizDvCd, cntrNo, cntrSn);
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
