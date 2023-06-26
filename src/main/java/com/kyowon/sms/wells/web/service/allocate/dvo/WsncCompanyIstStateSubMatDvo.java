package com.kyowon.sms.wells.web.service.allocate.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * <pre>
 * 회사설치 (8888코드) 현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023-05-24
 */

@Getter
@Setter
public class WsncCompanyIstStateSubMatDvo {
    String cntrNo; // 계약번호
    String deptCd; // 부서
    String deptNm1; // 부서명
    String csnrCd; // 코스트센터
    String ogNm; // 본부
    String custNm; // 고객명
    String copnDvCd; // 관리유형
    String pdCd; // 상품코드
    String pdNm; // 상품명
    String svBizMclsfCd; // 작업그룹
    String svBizDclsfCd; // 작업유형상세
    // 작업유형명 BFSVC_WK_DV_CD
    String sapMatCd; // SAP코드
    String partPdCd; // 품목코드
    String partPdNm; // 부품명
    Long useQty; // 사용수량
    Long pdctUprc;// 실제원가
    Long pdctUprcSum;// 원가합계금액
    Long csmrUprcAmt; // 소비자가
    Long csmrUprcAmtSum;// 소비자가합계급액
    String refriDvCd;// 유무상
    String wrkPrtnrClsfCd; // 작업자 구분
    String wrkPrtnrNo; // 작업자 사번
    String wrkrPrtnrKnm; // 작업자 성명
    String wrkOgNm; // 작업자 소속
    String cstAdr; // 고객주소 상세 TB_SVST_SV_WK_OSTR_IZ
}
