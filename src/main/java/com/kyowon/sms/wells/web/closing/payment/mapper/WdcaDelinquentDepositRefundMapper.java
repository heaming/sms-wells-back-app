package com.kyowon.sms.wells.web.closing.payment.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dvo.*;

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
