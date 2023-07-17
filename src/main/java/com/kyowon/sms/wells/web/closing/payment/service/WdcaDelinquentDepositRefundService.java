package com.kyowon.sms.wells.web.closing.payment.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.payment.dvo.*;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaDelinquentDepositRefundMapper;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbContractDtlStatCdChService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 연체 입금/환불 반영 서비스(W-CL-S-0013)
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-05-29
 */
@Service
@RequiredArgsConstructor
public class WdcaDelinquentDepositRefundService {
    private final WdcaDelinquentDepositRefundMapper mapper;
    private final WctbContractDtlStatCdChService service;

    /**
     * WELLS 연체 대상건의 입금 발생 시 입금에 대한 연체금액과 연체가산금액에 대한 입금내역과 잔액을 관리한다.
     * @return int
     * @throws BizException SQL 오류 발생 시 Exception 처리
     */
    @Transactional
    public int saveDelinquentDepositRefund(WdcaDelinquentDepositRefundDvo inputDvo) throws BizException {
        WdcaDepositRefundProcessingAmountDvo searchDvo = mapper.selectDepositRefundProcessingAmount(inputDvo);

        int processCount = 0;
        int result;
        if (!Objects.isNull(searchDvo)) {
            inputDvo.setThmDlqDpSumAmt(searchDvo.getThmDlqAddDpSumAmt());
            inputDvo.setThmDlqAddDpSumAmt(searchDvo.getThmDlqAddDpSumAmt());
            inputDvo.setThmDlqRfndSumAmt(searchDvo.getThmDlqRfndSumAmt());
            inputDvo.setThmDlqAddRfndSumAmt(searchDvo.getThmDlqAddRfndSumAmt());

            result = mapper.updateDlqBas(inputDvo); /*연체기본 Table 정보 수정*/
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            result = mapper.insertDlqBasHist(inputDvo); /*연체기본이력 Table 이력정보 생성*/
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            result = mapper.updateBndCntrBas(inputDvo); /*채권계약기본 Table 정보 수정*/
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            String dpDvCd = inputDvo.getDpDvCd();
            String rveDvCd = inputDvo.getRveDvCd();
            int rveAmt = inputDvo.getRveAmt();
            int eotDlqAmt = searchDvo.getEotDlqAmt();
            int eotDlqAddAmt = searchDvo.getEotDlqAddAmt();
            String sellTpCd = searchDvo.getSellTpCd();
            int bznsAtamBlam = 0;
            int bznsAtamProcsAmt = 0;

            WdcaBznsAtamBasDvo edcaBznsAtamBasDvo = new WdcaBznsAtamBasDvo();
            edcaBznsAtamBasDvo.setCntrNo(inputDvo.getCntrNo());
            edcaBznsAtamBasDvo.setCntrSn(inputDvo.getCntrSn());
            /* 연체금 입금내역 영업선수금에 반영 */
            if (("1".equals(dpDvCd) || "3".equals(dpDvCd))
                && ("03".equals(rveDvCd))) {
                if (rveAmt < eotDlqAmt) {
                    bznsAtamBlam = bznsAtamBlam - rveAmt;
                    bznsAtamProcsAmt = rveAmt;
                } else {
                    bznsAtamBlam = bznsAtamBlam - eotDlqAmt;
                    bznsAtamProcsAmt = eotDlqAmt;
                }
                edcaBznsAtamBasDvo.setBznsAtamBlam(bznsAtamBlam);
                result = mapper.updateBznsAtamBas(edcaBznsAtamBasDvo); /*영업선수금기본 Table 정보 수정*/
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                edcaBznsAtamBasDvo.setBznsAtamProcsCd("1");
                edcaBznsAtamBasDvo.setBznsAtamProcsAmt(bznsAtamProcsAmt);
                result = mapper.updatebznsAtamProcsIz(edcaBznsAtamBasDvo); /*영업선수금처리내역 생성*/
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            }

            /* 연체가산금 입금내역 영업선수금에 반영 */
            if (("1".equals(dpDvCd) || "3".equals(dpDvCd))
                && ("02".equals(rveDvCd))) {
                if (rveAmt < eotDlqAddAmt) {
                    bznsAtamBlam = bznsAtamBlam - rveAmt;
                    bznsAtamProcsAmt = rveAmt;
                } else {
                    bznsAtamBlam = bznsAtamBlam - eotDlqAddAmt;
                    bznsAtamProcsAmt = eotDlqAddAmt;
                }
                edcaBznsAtamBasDvo.setBznsAtamBlam(bznsAtamBlam);
                result = mapper.updateBznsAtamBas(edcaBznsAtamBasDvo); /*영업선수금기본 Table 정보 수정*/
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

                edcaBznsAtamBasDvo.setBznsAtamProcsCd("4");
                edcaBznsAtamBasDvo.setBznsAtamProcsAmt(bznsAtamProcsAmt);
                result = mapper.updatebznsAtamProcsIz(edcaBznsAtamBasDvo); /*영업선수금처리내역 생성*/
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            }

            /*계약상세상태 변경*/
            if (eotDlqAmt <= rveAmt) {
                WctbContractDtlStatCdChDvo ectbContractDtlStatCdChDvo = new WctbContractDtlStatCdChDvo();
                ectbContractDtlStatCdChDvo.setCntrNo(inputDvo.getCntrNo());
                ectbContractDtlStatCdChDvo.setCntrSn(inputDvo.getCntrSn());
                ectbContractDtlStatCdChDvo.setCntrDtlStatCd("101");
                try {
                    service.editContractDtlStatCdCh(ectbContractDtlStatCdChDvo);
                } catch (Exception e) {
                    throw new BizException("계약 상세 상태 변경 실패");
                }
            }

            /*재지급데이터 생성 - 전집*/
            int adsbRt = 0; /*재지급율*/
            int adsbAmt = 0; /*재지급금액*/
            if (("1".equals(sellTpCd) || "2".equals(sellTpCd)) && ("1".equals(dpDvCd) || "3".equals(dpDvCd))
                && ("03".equals(rveDvCd))) {
                if ("1".equals(sellTpCd)) {
                    WdcaAgainDisbursementObjectDivideDvo edcaAgainDisbursementObjectDivideDvo = mapper
                        .selectAgainDisbursementObjectDivide(inputDvo);
                    int cntrTam = edcaAgainDisbursementObjectDivideDvo.getCntrTam();
                    int dpAcuAmt = edcaAgainDisbursementObjectDivideDvo.getDpAcuAmt();
                    if (cntrTam + eotDlqAmt <= dpAcuAmt + rveAmt) {
                        result = mapper.updateRedfAdsbBas(inputDvo, adsbRt, adsbAmt); /*재지급데이터 업데이트*/
                        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    }
                }
                if ("2".equals(sellTpCd)) {
                    WdcaAgainDisbursementObjectDivideRentalDvo edcaAgainDisbursementObjectDivideRentalDvo = mapper
                        .selectAgainDisbursementObjectDivideRental(inputDvo);
                    int rentalTn = edcaAgainDisbursementObjectDivideRentalDvo.getRentalTn(); //렌탈차월
                    int fnlAmt = edcaAgainDisbursementObjectDivideRentalDvo.getFnlAmt(); //렌탈료
                    int dpAcuAmt = edcaAgainDisbursementObjectDivideRentalDvo.getDpAcuAmt(); //입금총액
                    int ackmtPerfAmt = edcaAgainDisbursementObjectDivideRentalDvo.getAckmtPerfAmt(); //인정실적금액
                    String canDt = edcaAgainDisbursementObjectDivideRentalDvo.getCanDt(); //취소일자
                    String istDt = edcaAgainDisbursementObjectDivideRentalDvo.getIstDt(); //설치일자
                    int ucAmt = edcaAgainDisbursementObjectDivideRentalDvo.getUcAmt(); //미수금액
                    if (eotDlqAmt <= rveAmt) {
                        if (rentalTn <= 24) {
                            adsbRt = 100;
                        } else {
                            adsbRt = 0;
                        }
                        if (fnlAmt * 12 <= dpAcuAmt) {
                            adsbAmt = ackmtPerfAmt * adsbRt;
                        } else {
                            adsbAmt = 0;
                        }
                    }
                    if (StringUtil.isNotEmpty(canDt)) {
                        adsbRt = 80;
                        if (ucAmt == 0
                            && (rentalTn < 24
                                || (rentalTn == 12 && canDt.substring(7, 2).compareTo(istDt.substring(7, 2)) >= 1))
                            || (rentalTn == 24 && canDt.substring(7, 2).compareTo(istDt.substring(7, 2)) <= 1)) {
                            adsbAmt = ackmtPerfAmt * adsbRt;
                        } else {
                            adsbAmt = 0;
                        }
                    }
                    result = mapper.updateRedfAdsbBas(inputDvo, adsbRt, adsbAmt); /*재지급데이터 업데이트*/
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                }

            }

            processCount += result;
        }
        return processCount;
    }
}
