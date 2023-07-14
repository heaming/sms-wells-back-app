package com.kyowon.sms.wells.web.bond.consultation.dvo;

import lombok.*;

/**
 * <pre>
 * TB_CBBO_BND_CNTC_EXCD_OJ_IZ
 * 채권접촉제외대상내역 T
 * </pre>
 *
 * @author gilyong.han
 * @since 2023-07-12
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WbncBondContactExcludeObjectIzDvo {
    private String bndCntcExcdOjId; /* 채권접촉제외대상ID */
    private String ctntExcdBndBizCd; /* 연락제외채권업무코드 */
    private String ctntExcdOjTpCd; /* 연락제외대상유형코드 */
    private String ctntExcdMediTpCd; /* 연락제외매체유형코드 */
    private String clctamOgTpCd; /* 집금조직유형코드 */
    private String clctamPrtnrNo; /* 집금파트너번호 */
    private String cstNo; /* 고객번호 */
    private String clctnOjOgTpCd; /* 추심대상조직유형코드 */
    private String clctnOjPrtnrNo; /* 추심대상파트너번호 */
    private String locaraTno; /* 지역전화번호 */
    private String exnoEncr; /* 전화국번호암호화 */
    private String idvTno; /* 개별전화번호 */
    private String apyStrtdt; /* 적용시작일자 */
    private String apyEnddt; /* 적용종료일자 */
    private String ctntExcdRsonDvCd; /* 연락제외사유구분코드 */
    private String excdMoCn; /* 제외메모내용 */
    private String bndClctnMsgBooId; /* 채권추심메시지예약ID */
    private String dtaDlYn; /* 데이터삭제여부 */

    /** 미납요금 안내/촉구 대상 제외관리 전용 컬럼 */
    private String fwDvNm; /* 발송구분명 */
}
