package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0193M01 독립창고출고관리 dvo
 * </pre>
 *
 * @author inho.choi
 * @since 2023.03.28
 */
@Getter
@Setter
public class WsnaIndependenceWareOstrDvo {

    // 물류이관여부
    private String lgstTrsYn;

    // ROW 체크여부
    private String chk;
    // 창고명
    private String wareNm;
    // SAP코드
    private String sapMatCd;
    // 품목코드
    private String itmPdCd;
    // 품목명
    private String pdAbbrNm;
    // 관리단위코드
    private String mngtUnit;
    // 관리단위
    private String mngtUnitNm;
    // 자재등급
    private String matGdCd;

    // 상위재고, 물류 재고 관련
    private BigDecimal logisticStocQty;
    // 박스단위수량
    private BigDecimal boxUnitQty;
    // 재고수량
    private BigDecimal crtlStocQty;
    // 소요수량
    private BigDecimal useQty;
    // 물량배정확정 수량
    private BigDecimal cnfmQty;
    // 물량배정확정 BOX
    private BigDecimal cnfmBoxQty;
    // 물량배정출고 누계
    private BigDecimal mcbyAcuOstrQty;
    // 물량배정출고 BOX
    private BigDecimal mcbyAcuOstrBoxQty;

    // 박스수량
    private BigDecimal filterBoxQty;
    // 출고수량
    private BigDecimal outQty;
    // 출고BOX수량
    private BigDecimal outBoxQty;
    // 품목물량배정번호
    private String itmQomAsnNo;
    // 배정년월
    private String asnOjYm;
    // 출고창고번호
    private String ostrWareNo;
    // 입고창고번호
    private String strWareNo;
    // 창고관리파트너번호
    private String wareMngtPrtnrNo;
    // 조직유형코드
    private String ogTpCd;
    // 품목종류
    private String itmKndCd;
    // 비고
    private String rmkCn;
    // 배정회차수
    private BigDecimal asnTnN;
    // 창고구분
    private String wareDvCd;

    /**
     * 출고요청 관련
     */
    // 출고요청번호
    private String ostrAkNo;
    // 출고요청일련번호
    private Integer ostrAkSn;
    // 출고일자
    private String ostrDt;
    // 출고창고구분
    private String ostrWareDvCd;
    // 출고창고파트너번호
    private String ostrPrtnrNo;
    // 출고창고파트너조직유형
    private String ostrPrtnrOgTpCd;
}
