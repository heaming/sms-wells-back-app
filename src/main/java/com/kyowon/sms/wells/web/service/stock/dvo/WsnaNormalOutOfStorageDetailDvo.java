package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0179P01 정상출고등록 상세 조회 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-01
 */

@Getter
@Setter
public class WsnaNormalOutOfStorageDetailDvo {

    // ROW 선택여부
    private String chk;
    // 출고요청유형코드
    private String ostrAkTpCd;
    // 출고유형코드
    private String ostrTpCd;
    // 입고유형코드
    private String strTpCd;
    // 출고창고번호
    private String ostrOjWareNo;
    // 입고창고번호
    private String strOjWareNo;
    // 출고요청등록일자
    private String ostrAkRgstDt;
    // 출고요청번호
    private String ostrAkNo;
    // 출고요청일련번호
    private Integer ostrAkSn;
    // 입고희망일자
    private String strHopDt;
    // 재고유형
    private String svpdMgtTyp;
    // SAP코드
    private String svpdSapCd;
    // 품목코드
    private String itmPdCd;
    // 품목명
    private String svpdNmKor;
    // 품목종류
    private String svpdItemKnd;
    // 품목위치
    private String itemLoc;

    // 등급
    private String itmGdCd;
    // 출고창고재고
    private BigDecimal qty;
    // 입고창고재고
    private BigDecimal reqStckQty;
    // 신청수량
    private BigDecimal ostrAkQty;
    // 확정수량
    private BigDecimal ostrCnfmQty;
    // original 확정수량
    private BigDecimal ostrCnfmQtyOrg;
    // 비고
    private String rmkCn;
    // 출고확정코드
    private String ostrCnfmCd;
    // 최근출고일자
    private String rectOstrDt;
    // 출고누계수량
    private BigDecimal ostrAggQty;
    // 출고수량
    private BigDecimal outQty;
    // original 출고수량
    private BigDecimal outQtyOrg;
    // 입고확정일자
    private String strConfDt;

    /**
     * 입/출고 창고 정보
     */
    // 입고창고구분
    private String strWareDvCd;
    // 입고창고상세구분
    private String strWareDtlDvCd;
    // 입고창고명
    private String strWareNm;
    // 입고창고파트너번호
    private String strPrtnrNo;
    // 입고창고파트너조직유형
    private String strOgTpCd;
    // 출고창고구분
    private String ostrWareDvCd;
    // 출고창고상세구분
    private String ostrWareDtlDvCd;
    // 출고창고명
    private String ostrWareNm;
    // 출고창고파트너번호
    private String ostrPrtnrNo;
    // 출고창고파트너조직유형
    private String ostrOgTpCd;

    // 박스단위수량
    private BigDecimal boxUnitQty;
    // 관리단위코드
    private String mngtUnitCd;
    // 관리단위
    private String mngtUnitNm;
    // 품목출고번호
    private String itmOstrNo;
    // 출고일련번호
    private Integer ostrSn;
    // 출고빈도
    private BigDecimal ostrTms;
    // 품목입고번호
    private String itmStrNo;
    // 입고일련번호
    private Integer strSn;
    // 센터평균출고량
    private BigDecimal avgOut;
}
