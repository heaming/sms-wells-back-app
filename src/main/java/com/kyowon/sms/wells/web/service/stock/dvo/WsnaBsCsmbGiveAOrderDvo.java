package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0014M01 BS소모품 발주수량 산출 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-30
 */

@Getter
@Setter
public class WsnaBsCsmbGiveAOrderDvo {
    // 관리년월
    private String mngtYm;
    // 발주구분코드
    private String goDvCd;
    // 발주구분
    private String goDvNm;
    // SAP코드
    private String svpdSapCd;
    // 소모품상품코드
    private String csmbPdCd;
    // 품목한글명
    private String itmKnm;
    // 관리단위코드
    private String mngtUnitCd;
    // 6개월 이전 배부수량
    private BigDecimal mms6bDdlvQty;
    // 5개월 이전 배부수량
    private BigDecimal mms5bDdlvQty;
    // 4개월 이전 배부수량
    private BigDecimal mms4bDdlvQty;
    // 3개월 이전 배부수량
    private BigDecimal mms3bDdlvQty;
    // 2개월 이전 배부수량
    private BigDecimal mms2bDdlvQty;
    // 1개월 이전 배부수량
    private BigDecimal mms1bDdlvQty;
    // 월평균 배부수량
    private BigDecimal mmAvDdlvQty;
    // 입고대기수량
    private BigDecimal strStnbQty;
    // 파주물류센터재고수량
    private BigDecimal pajuLgstCnrStocQty;
    // 성수물류센터재고수량
    private BigDecimal sgsuLgstCnrStocQty;
    // 전체재고수량
    private BigDecimal woStocQty;
    // 재고지속성월수
    private int stocPersMmN;
    // 예상소진일자
    private String etExsDt;
    // 발주단가
    private BigDecimal goUprc;
    // 필요수량
    private int ncstQty;
    // 발주수량
    private BigDecimal goQty;
    // 발주금액
    private BigDecimal goAmt;
    // 최소주문수량
    private BigDecimal minOrdQty;
    // 납기일수
    private BigDecimal pypdDc;
    // 비고내용
    private String rmkCn;
    // 등록여부
    private String rgstYn;
}
