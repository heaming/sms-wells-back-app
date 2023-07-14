package com.kyowon.sms.wells.web.closing.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaAgainDisbursementObjectDivideDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaAgainDisbursementObjectDivideRentalDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaBznsAtamBasDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaDelinquentDepositRefundDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaDepositRefundProcessingAmountDvo;

/**
 * <pre>
 * 연체 입금/환불 반영 서비스 맵퍼
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-05-22
 */
@Mapper
public interface WdcaDelinquentDepositRefundMapper {
    /**
     * @param 연체기본 Table 정보 존재여부 및 입금/환불 처리금액 조회
     * @param searchParam 검색 조건(key: cntrNo(계약번호), cntrSn(계약일련번호))
     * @return WdcaDepositRefundAmountDvo 검색 결과
     */
    WdcaDepositRefundProcessingAmountDvo selectDepositRefundProcessingAmount(WdcaDelinquentDepositRefundDvo dvo);

    int updateDlqBas(WdcaDelinquentDepositRefundDvo inputDvo);

    int insertDlqBasHist(WdcaDelinquentDepositRefundDvo inputDvo);

    int updateBndCntrBas(WdcaDelinquentDepositRefundDvo inputDvo);

    int updateBznsAtamBas(WdcaBznsAtamBasDvo dvo);

    int updatebznsAtamProcsIz(WdcaBznsAtamBasDvo dvo);

    WdcaAgainDisbursementObjectDivideDvo selectAgainDisbursementObjectDivide(WdcaDelinquentDepositRefundDvo dvo);

    WdcaAgainDisbursementObjectDivideRentalDvo selectAgainDisbursementObjectDivideRental(
        WdcaDelinquentDepositRefundDvo dvo
    );

    int updateRedfAdsbBas(WdcaDelinquentDepositRefundDvo dvo, int adsbRt, int adsbAmt);
}
