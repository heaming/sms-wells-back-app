package com.kyowon.sms.wells.web.closing.payment.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.payment.converter.WdcaDelinquentDepositRefundConverter;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaDelinquentDepositRefundDto.SaveReq;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaDelinquentDepositRefundDvo;
import com.kyowon.sms.wells.web.closing.payment.mapper.WdcaDelinquentDepositRefundMapper;
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
    private final WdcaDelinquentDepositRefundConverter converter;
    private final WdcaDelinquentDepositRefundMapper mapper;

    /**
     * WELLS 연체 대상건의 입금 발생 시 입금에 대한 연체금액과 연체가산금액에 대한 입금내역과 잔액을 관리한다.
     * @param cntrNo        계약번호
     * @param cntrSn         계약일련번호
     * @param kwGrpCoCd      교원그룹회사코드
     * @param rveNo          수납번호
     * @param rveSn          수납일련번호
     * @param dpDvCd         입금구분코드
     * @param dpMesCd        입금수단코드
     * @param dpTpCd         입금유형코드
     * @param rveDvCd        수납구분코드
     * @param rveCd          수납코드
     * @param rveDt          수납일자
     * @param perfDt         실적일자
     * @param rveAmt         수납금액
     * @return WdcaDelinquentDepositRefundDto
     * @throws BizException SQL 오류 발생 시 Exception 처리
     */
    @Transactional
    public int saveDelinquentDepositRefund(SaveReq dto) throws BizException {
        WdcaDelinquentDepositRefundDvo inputDvo = converter.mapSaveReqToWdcaDelinquentDepositRefundDvo(dto);

        WdcaDelinquentDepositRefundDvo searchDvo = mapper.selectDepositRefundProcessingAmount(inputDvo);

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

            processCount += result;
        }
        return processCount;
    }
}
