package com.kyowon.sms.wells.web.service.visit.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-S-0012 다건 작업오더, 정보변경 처리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.09
 */
@Setter
@Getter
public class WsnbMultipleTaskOrderDvo {
    /* key 생성을 위한 변수?  -> 변수 용도 fix 되면 처리 */

    String svBizDclsfCd; /* 서비스업무세분류코드 */
    String wkAcpteStatCd; /* 작업수락상태 */
    String mtrStatCd; /* 작업상태코드 */
    String wkAcpteDt; /* 작업수락일자 */
    String wkPrgsStatCd; /* 작업진행상태코드 */
    String rcgvpKnm; /* 수령자한글명 */
    String inChnlDvCd; /* 입력채널구분코드 */
    String svBizHclsfCd; /* 서비스업무대분류코드 */
    String rcpdt; /* 접수일자 */
    String asIstOjNo; /* AS설치대상번호 */
    String basePdCd; /* 상품코드 */
    String saleCd; /* TODO:확인필요*/
    String partCd; /* 자재 */
    String partQty; /* 수량 */
    String partAmt; /* 금액 */
    String cstSvAsnNo; /*고객서비스배정번호 */
    String vstCnfmdt; /* 방문확정일자 */
}
