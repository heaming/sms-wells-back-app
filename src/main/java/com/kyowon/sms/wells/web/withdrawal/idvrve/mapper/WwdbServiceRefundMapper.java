package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundDtlDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbServiceRefundDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WwdbServiceRefundMapper {

    /**
     * 서비스 환불 접수 기본 생성
     * @param dvo
     * @return
     */
    int insertServiceRefundReceiptBas(WwdbServiceRefundDvo dvo);

    /**
     * 서비스 환불 접수 상세 생성
     * @param dvo
     * @return
     */
    int insertServiceRefundReceiptDtl(WwdbServiceRefundDvo dvo);

    /**
     * 서비스 환불 수납요청기본 생성
     * @param dvo
     * @return
     */
    int insertServiceRefundRveAkBas(WwdbServiceRefundDvo dvo);

    /**
     * 서비스 환불 수납요청상세 생성
     * @param dvo
     * @return
     */
    int insertServiceRefundRveAkDtl(WwdbServiceRefundDvo dvo);

    /**
     * 서비스 환불 통합입금기본 생성
     * @param dvo
     * @return
     */
    int insertServiceRefundItgDpBas(WwdbServiceRefundDvo dvo);

    /**
     * 서비스 환불 입금대사기본 생성
     * @param dvo
     * @return
     */
    int insertServiceRefundDpCprcnfBas(WwdbServiceRefundDvo dvo);

    /**
     * 서비스 환불 수납기본 생성
     * @param dvo
     * @return
     */
    int insertServiceRefundRveBas(WwdbServiceRefundDvo dvo);

    /**
     * 서비스 환불 수납상세 생성
     * @param dvo
     * @return
     */
    int insertServiceRefundRveDtl(WwdbServiceRefundDvo dvo);
}
