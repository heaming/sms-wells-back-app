package com.kyowon.sms.wells.web.withdrawal.pchssl.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WwdcSalesConfirmDvo {
    private String ogCd;
    private String prtnrNo;
    private String prtnrKnm;
    private String cntrDtlNo;
    private String cntrNo;
    private String cntrSn;
    private String cstKnm;
    private String sellTpCd; /* 업무구분코드 */
    private String pdNm; /* 상품명 */
    private String sellAmt; /* 판매금액 */
    private String rentalPtrm; /* 렌탈기간 */
    private String rentalTn; /* 렌탈 차월 : 렌탈 회차 */
    private String mmIstmAmt; /* 월납부액 : 월 할부금 */
    private String slAmt; /* 매출액 */
    private String pvdaAmt; /* 현할차금액*/
    private String useDt; /* 렌탈일수 */
    private String crtErrCn; /* 오류내역 */
    private String slRcogPrdCd; /* 매출인식기준 */
    private String slRcogPrdDvCd; /* 매출인식주기*/
    private String ostrDtm; /* 출고일자 */
    private String istDtm; /* 설치일자 */
    private String svDt; /* 서비스일자 */
    private String cntrCanDtm; /* 계약취소일자 */
    private String slRcogDt; /* 매출인식일자 */
    private String fnlMdfcDtm; /* 변경일자 */
    private String fnlMdfcUsrId; /* 변경자 */
    private String state; /* 상태값 컬럼 추가 : 버튼 상태에 따른 업데이트 처리 */
}
