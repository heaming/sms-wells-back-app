package com.kyowon.sms.wells.web.bond.consultation.service;

import com.kyowon.sms.common.web.closing.payment.dvo.ZdcaCancellationFeeComputationResultWellsDvo;
import com.kyowon.sms.common.web.closing.payment.dvo.ZdcaCancellationFeeComputationWellsDvo;
import com.kyowon.sms.common.web.closing.payment.service.ZdcaCancellationFeeComputationWellsService;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncSameCustomerContractDto.*;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncSameCustomerContractMapper;
import com.sds.sflex.common.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

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
    private final ZdcaCancellationFeeComputationWellsService zdcaCancellationFeeComputationWellsService;

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
            // 고객요청해약(301), 연체해약(302) 이 아닐 경우 위약금 산출 서비스로 계산된 위약금 정보 반환
            var computedBreachPromise = getBreachPromiseAmt(cntrNo, cntrSn);
            return FindBreachOfPromiseRes.builder()
                .borAmt(String.valueOf(computedBreachPromise.getBorAmt()))
                .borBlam(String.valueOf(computedBreachPromise.getBorAmt()))
                .rgstCostDscBorAmt(String.valueOf(computedBreachPromise.getRgstCostDscBorAmt()))
                .rentalDscBorAmt(String.valueOf(computedBreachPromise.getRentalDscBorAmt()))
                .csmbCostBorAmt(String.valueOf(computedBreachPromise.getCsmbCostBorAmt()))
                .pBorAmt(String.valueOf(computedBreachPromise.getPBorAmt()))
                .reqdCsBorAmt(String.valueOf(computedBreachPromise.getReqdCsBorAmt()))
                .lsRntf(String.valueOf(computedBreachPromise.getLsRntf()))
                .rstlBorAmt(String.valueOf(computedBreachPromise.getRstlBorAmt()))
                .cntrDtlStatCd(defaultBreachPromise.cntrDtlStatCd()) /* 현재계약상세상태코드 */
                .dpCcamSumAmt(defaultBreachPromise.dpCcamSumAmt()) /* 입금위약금합계금액 */
                .thmSlSumAmt(defaultBreachPromise.thmSlSumAmt()) /* 당월매출합계금액 */
                .acuDpAmt(defaultBreachPromise.acuDpAmt()) /* 누적입금금액 */
                .ucAmt(defaultBreachPromise.ucAmt()) /* 미수금액 */
                .rsgBorAmt(defaultBreachPromise.rsgBorAmt()) /* 해지위약금액 */
                .build();
        }
    }

    /**
     * 위약금 산출 서비스를 통해 계산된 위약금 정보 조회
     *
     * @param cntrNo, cntrSn
     * @return 동일고객 계약 위약정보 조회 결과
     */
    private ZdcaCancellationFeeComputationResultWellsDvo getBreachPromiseAmt(String cntrNo, int cntrSn) {
        ZdcaCancellationFeeComputationWellsDvo inputDvo = new ZdcaCancellationFeeComputationWellsDvo();
        inputDvo.setCntrNo(cntrNo);
        inputDvo.setCntrSn(cntrSn);
        inputDvo.setCnfmYm(StringUtils.EMPTY);

        return zdcaCancellationFeeComputationWellsService.saveDelinquentDepositRefund(inputDvo);
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
