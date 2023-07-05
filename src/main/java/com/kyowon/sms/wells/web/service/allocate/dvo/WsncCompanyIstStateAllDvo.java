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
public class WsncCompanyIstStateAllDvo {
    String cntrNo;
    String cntrSn;
    String ogId;
    String ogNm;
    String prtnrNo;
    String cscnCd;
    String deptNm1;
    String rcgvpKnm;
    String svcTpNm;
    String fnlPdCd; // 상품코드
    String basePdNm; // 상품명
    String svBizHclsfCd; // 작업그룹
    String svBizDclsfCd; // 작업유형상세
    String sapMatCd; // SAP코드
    String pdctPdCd;
    String basePdCd;
    String itmPdCd; // 품목코드
    String itmPdNm; // 부품명
    Long useQty; // 사용수량
    Long pdctUprc; // 실제원가
    Long pdctUprcSum; // 원가합계금액
    Long csmrUprcAmt; // 소비자가
    Long csmrUprcAmtSum; // 소비자가합계급액
    //    String refriDvCd; // 유무상
    String prtnrClsfCd; // 작업자 구분
    String ichrPrtnrNo; // 작업자 사번
    String prtnrKnm; // 작업자 성명
    String prtnrOgNm; // 작업자 소속
    String cstAdr; // 고객주소 상세
}
