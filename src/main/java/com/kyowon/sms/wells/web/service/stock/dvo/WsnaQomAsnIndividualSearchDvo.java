package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0190M01 개인창고 물량배정 조회 결과 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-11
 */

@Getter
@Setter
public class WsnaQomAsnIndividualSearchDvo {

    // SAP코드
    private String sapCd;
    // 품목코드
    private String itmPdCd;
    // 품목명
    private String itmPdNm;
    // 관리단위
    private String mngtUnit;
    // 자재등급
    private String matGdCd;

    // 입고창고번호
    private String wareNo;
    // 사번
    private String prtnrNo;
    // 성명
    private String prtnrNm;
    // 입고창고
    private String wareNm;

    // 센터창고수량
    private BigDecimal centerQty;

    /**
     * 배정수량
     */
    // 일반
    private BigDecimal geQty;
    // 법인
    private BigDecimal crpQty;
    // 총 배정
    private BigDecimal totalQty;

    // 가중치 적용
    private BigDecimal apyQty;
    // 누적출고
    private BigDecimal ostrQty;
    // BS완료
    private BigDecimal bsQty;
    // 현재재고
    private BigDecimal stocQty;
    // 금주예정
    private BigDecimal thwkQty;
    // 차주예정
    private BigDecimal borrQty;
    // 확정수량
    private BigDecimal cnfmQty;
    // 박스수량
    private BigDecimal boxQty;

    // 당월BS FULL
    private BigDecimal bsFullQty;
    // 당월BS 배정수량
    private BigDecimal bsAsnQty;
    // 재고(조직)
    private BigDecimal stockOgQty;
    // 재고(개인)
    private BigDecimal stockIndvQty;
    // 소요수량
    private BigDecimal nedQty;
    // 박스단위수량
    private BigDecimal boxUnitQty;

    /**
     * 창고빌딩정보
     */
    // 빌딩코드
    private String bldCd;
    // 빌딩명
    private String bldNm;
    // 지역전화번호
    private String locaraTno;
    // 전화국번호암호화
    @DBDecField
    private String exnoEncr;
    // 개별전화번호
    private String idvTno;
    // 우편번호
    private String adrZip;
    // 주소1
    private String rnadr;
    // 주소2
    private String rdadr;

}
