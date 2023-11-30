package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0295M01 BS소모품 배부기준 관리 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-30
 */

@Getter
@Setter
public class WsnaBsCsmbDeliveryBaseDvo {
    // 관리년월
    private String mngtYm;
    // 소모품상품코드
    private String csmbPdCd;
    // 품목명
    private String itmKnm;
    // 관리단위코드
    private String mngtUnitCd;
    // 발주구분코드
    private String goDvCd;
    // 발주단가
    private BigDecimal goUprc;
    // 박스단위수량
    private BigDecimal boxUnitQty;
    // 비고
    private String rmkCn;
    // 대상코드
    private String bfsvcCsmbDdlvOjCd;
    // 운영여부
    private String bfsvcCsmbDdlvOrtYn;
    // 유형코드
    private String bfsvcCsmbDdlvTpCd;
    // 산출기준코드
    private String bfsvcCsmbDdlvCmptBaseCd;
    // 상품그룹코드
    private String bfsvcCsmbDdlvOjPdGrpCd;
    // 계정유형코드
    private String bfsvcCsmbDdlvOjAccTpCd;
    // 단위계정수
    private BigDecimal bfsvcCsmbDdlvUnitAccN;
    // 단위수량
    private BigDecimal bfsvcCsmbDdlvUnitQty;
    // 신청제한수량
    private BigDecimal bfsvcCsmbAplcLmQty;
    // 정렬순서
    private BigDecimal sortOdr;
}
