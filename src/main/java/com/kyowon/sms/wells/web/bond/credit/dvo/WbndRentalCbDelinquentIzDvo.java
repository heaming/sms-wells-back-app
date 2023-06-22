package com.kyowon.sms.wells.web.bond.credit.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.*;

/**
 * <pre>
 * TB_CBBO_WELLS_RENTAL_CB_DLQ_IZ
 * WELLS렌탈CB연체내역 T
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-06-15
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WbndRentalCbDelinquentIzDvo {

    private String cntrNo; /* 계약번호 */
    private String cstNo; /* 고객번호 */
    private String rentalCbRgstDvCd; /* 렌탈CB등록구분코드 */
    private Long dlqAmt; /* 연체금액 */
    private Long dlqBlam; /* 연체잔액 */
    private String fstOcDt; /* 최초발생일자 */
    private String ocBsdt; /* 발생기준일자 */
    private String ocCrtDt; /* 발생생성일자 */
    private String ocFileCrtDt; /* 발생파일생성일자 */
    private String chgFileCrtDt; /* 변동파일생성일자 */
    private String rlsFileCrtDt; /* 해제파일생성일자 */
    private String bfRlsDt; /* 이전해제일자 */
    private String rlsDt; /* 해제일자 */
    private String dlDt; /* 삭제일자 */
    private String clctamOgTpCd; /* 집금조직유형코드 */
    private String clctamPrtnrNo; /* 집금파트너번호 */
    private String niceFwExcdYn; /* 나이스발송제외여부 */
    private String niceFwDt; /* 나이스발송일자 */
    private String notyFwOjYn; /* 알림발송대상여부 */
    private String notyTrsDt; /* 알림전송일자 */
    private String notyRcvDt; /* 알림수신일자 */
    private String notyRcvYn; /* 알림수신여부 */
    private String notakRsCd; /* 알림톡결과코드 */
    private String smsFwRcvStatCd; /* SMS발송수신상태코드 */
    private String dtaDlYn; /* 데이터삭제여부 */

    /** 렌탈CB 관리 - 대상 조회용 컬럼 */
    private String baseYm;/*기준년월*/
    private String selGbn;/*조회구분*/
    private String cralLocaraTno;/*휴대지역전화번호*/
    @DBEncField
    @DBDecField
    private String mexnoEncr;/*휴대전화국번호암호화*/
    private String cralIdvTno;/*휴대개별전화번호*/
    private String cstKnm;/*고객명*/
    private String copnDvCd;/*법인격구분코드*/
    private String prtnrKnm;/*집금담당자명*/
    private String dsphTno;/*발신번호*/
    private String rgstSchDt;/*등록예정일자-알림톡발송일자+4일*/
    private String cntrDtlSn;/*계약상세번호*/
    private String sellTpNm;/*상품구분*/
    private String pdClsfNm;/*상품명*/
    private String slRcogDt;/*매출일자*/
    private String mpyBsdt;/*자동이체 약정일자*/
    private String fntDvNm;/*결제수단*/

    /** 렌탈CB납입정보(팝업) 조회용 컬럼 */
    private String copnDvNm; /*법인격구분명*/
    private String fwbooDtm; /*알림톡발송일자*/
    private String resultYn; /*알림톡성공여부*/
    private String notyFwOjDt;/*알림톡발송제외일자*/

    /** 렌탈CB 관리 - 연체이력 조회용 컬럼*/
    private String msgFwDt; /*알림톡 발송일자*/
    private String fntStplDt; /*납입기한일자*/
}
