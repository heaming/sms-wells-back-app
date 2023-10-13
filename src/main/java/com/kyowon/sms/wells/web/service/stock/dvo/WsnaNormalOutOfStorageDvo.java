package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0142M01 정상출고 관리 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-01
 */

@Setter
@Getter
public class WsnaNormalOutOfStorageDvo {

    // 품목출고번호
    private String itmOstrNo;
    // 출고일련번호
    private Integer ostrSn;
    // 출고요청번호
    private String ostrAkNo;
    // 출고요청일련번호
    private Integer ostrAkSn;
    // 품목입고번호
    private String itmStrNo;
    // 입고일련번호
    private Integer strSn;
    // 출고유형
    private String ostrTpCd;
    // 입고유형
    private String strTpCd;
    // 관리단위
    private String mngtUnitCd;

    // 품목코드
    private String itmPdCd;
    // 등급
    private String itmGdCd;
    // 출고수량
    private BigDecimal outQty;
    // 품목종류
    private String svpdItemKnd;
    // 박스단위수량
    private BigDecimal boxUnitQty;

    // 출고창고번호
    private String ostrOjWareNo;
    // 출고창고구분
    private String ostrWareDvCd;
    // 출고창고파트너번호
    private String ostrPrtnrNo;
    // 출고창고파트너조직유형
    private String ostrOgTpCd;

    // 입고창고번호
    private String strOjWareNo;
    // 입고창고구분
    private String strWareDvCd;
    // 입고창고파트너번호
    private String strPrtnrNo;
    // 입고창고파트너조직유형
    private String strOgTpCd;

    // 출고일자
    private String ostrDt;
    // 비고
    private String rmkCn;
}
