package com.kyowon.sms.wells.web.service.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WsniParcelServiceRegDvo {
    private String tokkenNum; /* 토큰번호 */

    private String custId; /* 고객코드 */

    private String rcptYmd; /* 접수년월일 */

    private String custNo; /* 계약번호 */

    private String cntrSn; /* 계약일련번호 */

    private String rcptDv; /* 접수구분 */

    private String workdvCd; /* 작업구분코드  */

    private String reqdvCd; /* 요청구분코드 01:요청 02:취소 */

    private String mpckKey; /* 합포장키  */

    private String caldvCd; /* 정산구분코드 */

    private String frtdvCd; /* 운임구분코드 01: 선불, 02: 착불, 03: 신용 */

    private String cntritemCd; /* 계약품목코드  */

    private String boxtypeCd; /* 박스타입코드  01: 극소, 02: 소, 03: 중, 04: 대, 05: 특대  */

    private String boxQty; /* 박스 수량 */

    private String custMgmtdlcmCd; /*  */

    private String sendrNm; /* 고객관리거래처코드 */

    private String sendrtelNo1; /* 보내는분 전화번호1  */

    private String sendrtelNo2; /* 보내는분 전화번호2 */

    private String sendrtelNo3; /* 보내는분 전화번호3 */

    private String sendrcellNo1; /* 보내는분 휴대폰번호1  */

    private String sendrcellNo2; /* 보내는분 휴대폰번호2 */

    private String sendrcellNo3; /* 보내는분 휴대폰번호3 */

    private String sendrZipNo; /* 보내는분 우편번호 */

    private String sendrAddr; /* 보내는분 주소 */

    private String sendrAddrDtl; /* 보내는분 상세주소  */

    private String rcvrNm; /* 받는분 명  */

    private String rcvrtelNo1; /* 받는분 전화번호1  */

    private String rcvrtelNo2; /* 받는분 전화번호1  */

    private String rcvrtelNo3; /* 받는분 전화번호1  */
    private String rcvrcellNo1; /* 받는분 휴대폰번호1 */

    private String rcvrcellNo2; /* 받는분 휴대폰번호1 */

    private String rcvrcellNo3; /* 받는분 휴대폰번호1 */

    private String rcvrzipNo; /* 받는분 우편번호 */

    private String rcvrAddr; /* 받는분 주소 */

    private String rcvrdetailAddr; /* 받는분 상세주소 */

    private String invcNo; /* 운송장 번호 */

    private String oriinvcNo; /* 원운송장번호 */

    private String oriordNo; /* 원주문 번호  */

    private String prtSt; /* 운송장 출력상태  */

    private String reMark1; /* 비고1 */

    private String codYn; /* COD여부  */

    private String dlvDv; /* 택배구분 */

    private String mpckSeq; /* 합포장 순번  */

    private String gdsCd; /* 상품코드 */

    private String gdsNm; /* 상품명 */

    private String gdsQty; /* 상품수량 */

    private String unitCd; /* 단품코드 */

    private String unitNm; /* 단품명 */

    private String gdsAmt; /* 상품가액 */

    private String resultCd;

    private String resultDetail;

}
