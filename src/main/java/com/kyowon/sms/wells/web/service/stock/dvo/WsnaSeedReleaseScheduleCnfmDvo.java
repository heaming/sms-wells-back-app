package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 확정 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-04
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleCnfmDvo {

    // 계약번호
    private String cntrNo;
    // 계약일련번호
    private int cntrSn;
    // 서비스업무대분류코드
    private String svBizHclsfCd;
    // 서비스업무세분류코드
    private String svBizDclsfCd;
    // 배송주문번호
    private String sppOrdNo;
    // 배송일련번호
    private int sppPlanSn;
    // 고객명
    private String cstNm;
    // 모종기기계약번호
    private String sdingMcnrCntrNo;
    // 모종기기상품코드
    private String sdingMcnrPdCd;
    // 모종패키지상품코드
    private String sdingPkgPdCd;

    /**
     * 모종 1~10 정보
     */
    // 모종1
    private String sdingPdCd1;
    // 수량1
    private BigDecimal sdingQty1;
    // 파종일자1
    private String sdingSowDt1;
    // 모종2
    private String sdingPdCd2;
    // 수량2
    private BigDecimal sdingQty2;
    // 파종일자2
    private String sdingSowDt2;
    // 모종3
    private String sdingPdCd3;
    // 수량3
    private BigDecimal sdingQty3;
    // 파종일자3
    private String sdingSowDt3;
    // 모종4
    private String sdingPdCd4;
    // 수량4
    private BigDecimal sdingQty4;
    // 파종일자4
    private String sdingSowDt4;
    // 모종5
    private String sdingPdCd5;
    // 수량5
    private BigDecimal sdingQty5;
    // 파종일자5
    private String sdingSowDt5;
    // 모종6
    private String sdingPdCd6;
    // 수량6
    private BigDecimal sdingQty6;
    // 파종일자6
    private String sdingSowDt6;
    // 모종7
    private String sdingPdCd7;
    // 수량7
    private BigDecimal sdingQty7;
    // 파종일자7
    private String sdingSowDt7;
    // 모종8
    private String sdingPdCd8;
    // 수량8
    private BigDecimal sdingQty8;
    // 파종일자8
    private String sdingSowDt8;
    // 모종9
    private String sdingPdCd9;
    // 수량9
    private BigDecimal sdingQty9;
    // 파종일자9
    private String sdingSowDt9;
    // 모종10
    private String sdingPdCd10;
    // 수량10
    private BigDecimal sdingQty10;
    // 파종일자10
    private String sdingSowDt10;

    // 접수일자
    private String rcpDt;
    // 방문일자
    private String vstDt;
    // 출고예정일자
    private String sppDuedt;
    // 출고확정일자
    private String sppCnfmdt;
    // 관리자구분코드
    private String mngrDvCd;
    // 조직유형코드
    private String ogTpCd;
    // 담당파트너번호
    private String ichrPrtnrNo;
    // 계약주소지ID
    private String cntrAdrpcId;
    // 유무상구분
    private String refriDvCd;
    // 유상비용금액
    private BigDecimal recapCsAmt;
    // 입금예정자명
    private String dpEpttNm;
    // 입금일자
    private String dpDt;
    // 배송구분
    private String sppDvCd;
    // 데이터삭제여부
    private String dtaDlYn;

    // 고객서비스배정번호
    private String cstSvAsnNo;
    // 방문예정일자
    private String vstDuedt;

    // 고객휴대지역전화번호
    private String cstCralLocaraTno;
    // 고객전화국번호
    private String cstMexnoEncr;
    // 고객휴대개별전화번호
    private String cstCralIdvTno;
    // 서비스상품코드
    private String svPdCd;
}
