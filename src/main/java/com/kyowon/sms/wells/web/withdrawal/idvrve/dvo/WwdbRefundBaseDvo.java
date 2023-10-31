package com.kyowon.sms.wells.web.withdrawal.idvrve.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 환불 요청 기본 DVO
 * </pre>
 *
 * @author kimoon.lim
 * @since 2023-10-31
 */
@Getter
@Setter
public class WwdbRefundBaseDvo {
    private String kwGrpCoCd; // 법인 구분
    private String rfndAkNo; // 환불번호
    private String aftRfndAkNo; /* 채번용 키값 */
    private String rfndAkDtm; // 접수일자
    private String rfndAkPrtnrOgTpCd; // 환불요청파트너조직유형코드
    private String rfndAkPrtnrNo; // 환불요청파트너번호
    private String arfndYn; // 선환불여부
    private String cshRfndFnitCd; // 지급은행
    private String cshRfndAcnoEncr; // 계좌번호
    private String cshRfndAcownNm; // 예금주
    private String rfndCshAkSumAmt; // 환불현금요청합계금액
    private String rfndCardAkSumAmt; // 환불카드요청합계금액
    private String rfndBltfAkSumAmt; // 환불전금요청합계금액
    private String crdcdFeeSumAmt; // 신용카드수수료합계금액
    private String rfndAkStatCd; // 환불상태
    private String rfndProcsDvCd; // 환불처리구분코드
    private String rfndProcsCn; // 환불처리내용
    private String rfndProcsUsrId; // 환불처리사용자ID
    private String rveCoCd; // 수납회사코드
    //
    private String rveDt; /* 수납일자 */
    private String perfDt; /* 실적일자 */
    private String dsbDt; /* 지급일자*/
    private String procsDv; /* 처리구분 */
    private String procsCn; /* 처리내용 */
}
