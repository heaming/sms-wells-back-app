package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0010M01 소모품 배부현황(빌딩별) dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-04
 */

@Getter
@Setter
public class WsnaBuildingBsConsumableDvo {
    // 신청상태
    String reqYn;
    // 빌딩코드
    String bldCd;
    // 빌딩명
    String bldNm;
    // 책임파트너번호
    String rsppPrtnrNo;
    // 방문계정수
    BigDecimal vstCstN;
    // 빌딩코드
    String strWareNo;
    // 소모품 상품코드
    String csmbPdCd;
    // SAP 코드
    String sapMatCd;
    // 유형코드
    String bfsvcCsmbDdlvTpCd;
    // 배부수량
    BigDecimal bfsvcCsmbDdlvQty;
    // 고정단위수량
    BigDecimal fxnDdlvUnitQty;
    // 신청단위수량
    BigDecimal aplcDdlvUnitQty;

    // 관리년월
    String mngtYm;
    // 업무시작일자
    String bizStrtdt;
    // 업무시작시간
    String bizStrtHh;
    // 업무종료일자
    String bizEnddt;
    // 업무종료시간
    String bizEndHh;

    // 배부대상코드
    String bfsvcCsmbDdlvOjCd;
    // 배부상태코드
    String bfsvcCsmbDdlvStatCd;
    // 출고요청번호
    String ostrAkNo;
    // 출고요청일련번호
    int ostrAkSn;
    // 조직유형코드
    String ogTpCd;
    // 파트너번호
    String prtnrNo;

    // 고정품목코드
    String fxnPdCd;
    // 고정포장단위
    String fxnPckngUnit;
    // 고정품목명
    String fxnPdNm;
    // 고정 SAP코드
    String fxnSapMatCd;
    // 신청품목코드
    String aplcPdCd;
    // 신청포장단위
    String aplcPckngUnit;
    // 신청품목명
    String aplcPdNm;
    // 신청 SAP코드
    String aplcSapMatCd;
    // 수량
    BigDecimal qty;

    // 빌딩코드
    List<String> bldCds;
    // PIVOT 조건
    private String pivotInStr;
    // PIVOT 컬럼
    private String pivotColumns;
}
