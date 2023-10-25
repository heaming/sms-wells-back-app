package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0192M01 개인창고출고관리 dvo
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.16
 */
@Getter
@Setter
public class WsnaIndividualWareOstrDvo {

    // 물류이관여부
    private String lgstTrsYn;

    // ROW 선택여부
    private String chk;
    // 입고창고
    private String wareNm;
    // SAP코드
    private String sapMatCd;
    // 품목코드
    private String itmPdCd;
    // 품목명
    private String pdAbbrNm;

    // 센터재고
    private BigDecimal hgrCrtlStocQty;
    // 확정 후 재고
    private BigDecimal totOutQty;

    // 관리단위
    private String mngtUnit;
    // 자재등급
    private String matGdCd;

    // 물류센터(파주), 물류 재고 관련
    private BigDecimal logisticStocQty;
    // 박스단위수량
    private BigDecimal boxUnitQty;
    // 재고수량(개인)
    private BigDecimal crtlStocQty;
    // 최초생성수량
    private BigDecimal cnfmQty;
    // 확정수량
    private BigDecimal aclOstrQty;
    // 생성수량
    private BigDecimal outQty;
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
    // 출고누계수량
    private BigDecimal ostrAggQty;

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
