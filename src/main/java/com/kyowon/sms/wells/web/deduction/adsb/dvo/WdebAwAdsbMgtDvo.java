package com.kyowon.sms.wells.web.deduction.adsb.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdebAwAdsbMgtDvo {
    /* EDU순주문계약월마감 */
    String cntrStatCd; /* 계약상태코드 */
    String sellEtcAmt; /* 판매기타금액 */
    String tkAmt; /* 인수금액 */
    String booSellTpCd;
    String stlmTpCd; /* 결제유형코드 */
    String istmMcn; /* 할부개월수 */
    String suscMcn; /* 구독개월수 */
    String parCstNo; /* 부모고객번호 */
    String ochCstNo; /* 자녀고객번호 */
    String nwPerfYn; /* 신규실적여부 */
    String cntrRelId; /* 계약관계id */

    /* 웰스순주문계약월마감 */
    String sellDscDvCd; /* 판매할인구분코드 */
    String sellDscrCd; /* 판매할인율코드 */
    String recapDutyPtrmN; /* 유상의무기간수 */
    String pmotUswyDvCd; /* 프로모션용도구분코드 */
    String bfsvcPrdCd; /* bs주기코드 */
    String mchnChYn; /* 기기변경여부 */
    String sellDscTpCd; /* 판매할인유형코드 */
    String mchnChTpCd; /* 기기변경유형코드 */
    String rstlYn; /* 재약정여부 */
    String feeAckmtDvCd; /* 수수료인정구분코드 */
    String hdqOgId; /* 본부조직id */
    String vtselSodbtChnlDvCd;/* 방판총판채널구분코드 */
    String ackmtPerfCt; /* 인정실적건수 */
    String booSellYn; /* 예약판매여부 */
    String stplPtrmMcn; /* 약정기간개월수 */

    /*EDU/WELLS순주문계약 공통*/
    String baseYm; /* 기준년월 */
    String perfYm; /* 실적년월 */
    String feeTcntDvCd; /* 수수료차수구분코드 */
    String cntrNo; /* 계약번호 */
    String cntrSn; /* 계약일련번호 */
    String cntrPerfCrtDvCd;/* 계약실적생성구분코드 */
    String ntorCnfmStatCd; /* 순주문확정상태코드 */
    String coCd; /* 회사코드 */
    String ogTpCd; /* 조직유형코드 */
    String prtnrNo; /* 파트너번호 */
    String pdCd; /* 상품코드 */
    String refPdClsfVal; /* 참조상품분류값 */
    String perfTpCd; /* 실적유형코드 */
    String sellTpCd; /* 판매유형코드 */
    String ojPrcsdt; /* 대상처리일자 */
    String dtaDlYn; /* 데이터삭제여부 */
    String feeCpsnRedfId; /* 수수료강제되물림id */
    String rcpdt; /* 접수일자 */
    String slDt; /* 매출일자 */
    String canDt; /* 취소일자 */
    String cvtPerfAmt; /* 환산실적금액 */
    String sellAmt; /* 판매금액 */
    String slAmt; /* 매출금액 */
    String redfAdsbOjAmt; /* 되물림재지급대상금액 */
    String vat; /* 부가가치세 */
    String ackmtPerfRt; /* 인정실적율 */
    String ackmtPerfAmt; /* 인정실적금액 */

    String redfAdsbOcYm; /* 발생년월(반영년월) */
    String rfltYm; /*dto에서 받아오는 반영년월*/

    String lifeYn; /* 상조 여부 */

}
