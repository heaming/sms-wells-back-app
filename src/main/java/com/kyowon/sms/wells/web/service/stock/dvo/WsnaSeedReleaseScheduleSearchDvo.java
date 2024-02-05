package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import com.sds.sflex.system.config.annotation.DBDecField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 조회 결과 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-03
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleSearchDvo {

    // 입금여부
    private String dpYn;
    // 출고여부
    private String ostrYn;
    // 유/무상
    private String refriDiv;
    // 배송구분
    private String shipDiv;
    // 접수구분
    private String receiptDiv;
    // 계약번호
    private String cntrNo;
    // 고객명
    private String cstNm;
    // 배송번호
    private String sppOrdNo;
    // 기기모델
    private String mchnModel;
    // 기기고객번호
    private String mchnCstNo;
    // 기기고객명
    private String mchnCstNm;
    // 현재패키지
    private String ctrlPkg;
    // 배송패키지
    private String shipPkg;

    /**
     * 모종 1~10 정보
     */
    // 모종1
    private String sding1;
    // 수량1
    private BigDecimal qty1;
    // 모종2
    private String sding2;
    // 수량2
    private BigDecimal qty2;
    // 모종3
    private String sding3;
    // 수량3
    private BigDecimal qty3;
    // 모종4
    private String sding4;
    // 수량4
    private BigDecimal qty4;
    // 모종5
    private String sding5;
    // 수량5
    private BigDecimal qty5;
    // 모종6
    private String sding6;
    // 수량6
    private BigDecimal qty6;
    // 모종7
    private String sding7;
    // 수량7
    private BigDecimal qty7;
    // 모종8
    private String sding8;
    // 수량8
    private BigDecimal qty8;
    // 모종9
    private String sding9;
    // 수량9
    private BigDecimal qty9;
    // 모종10
    private String sding10;
    // 수량10
    private BigDecimal qty10;

    // 기기철거일자
    private String mchnDemDt;
    // 접수일자
    private String receiptDt;
    // 방문일자
    private String vstDt;
    // 출고예정일자
    private String ostrScheDt;
    // BS완료일자
    private String bsFshDt;
    // 입금일자
    private String dpDt;
    // 출고확정일자
    private String ostrCnfmDt;
    // 모종수령창고
    private String sdingRcgWareNm;
    // 방문센터
    private String vstCenter;
    // 방문담당
    private String vstIchr;

    // 담당휴대지역전화번호
    private String ichrCralLocaraTno;
    // 담당전화국번호암호화
    @DBDecField
    private String ichrMexnoEncr;
    // 담당휴대개별전화번호
    private String ichrCralIdvTno;

    // 고객휴대지역전화번호
    private String cstCralLocaraTno;
    // 고객전화국번호암호화
    @DBDecField
    private String cstMexnoEncr;
    // 고객휴대개별전화번호
    private String cstCralIdvTno;
    // 고객우편번호
    private String cstZip;
    // 고객주소
    private String cstAdr;

    // 유무상구분코드
    private String refriDvCd;
    // 계약일련번호
    private int cntrSn;
    // 서비스업무대분류코드
    private String svBizHclsfCd;
    // 서비스업무세분류코드
    private String svBizDclsfCd;
    // 배송계획일련번호
    private int sppPlanSn;

    // 모종1 상품코드
    private String sdingPdCd1;
    // 파종일자1
    private String sowDt1;
    // 모종2 상품코드
    private String sdingPdCd2;
    // 파종일자2
    private String sowDt2;
    // 모종3 상품코드
    private String sdingPdCd3;
    // 파종일자3
    private String sowDt3;
    // 모종4 상품코드
    private String sdingPdCd4;
    // 파종일자4
    private String sowDt4;
    // 모종5 상품코드
    private String sdingPdCd5;
    // 파종일자5
    private String sowDt5;
    // 모종6 상품코드
    private String sdingPdCd6;
    // 파종일자6
    private String sowDt6;
    // 모종7 상품코드
    private String sdingPdCd7;
    // 파종일자7
    private String sowDt7;
    // 모종8 상품코드
    private String sdingPdCd8;
    // 파종일자8
    private String sowDt8;
    // 모종9 상품코드
    private String sdingPdCd9;
    // 파종일자9
    private String sowDt9;
    // 모종10 상품코드
    private String sdingPdCd10;
    // 파종일자10
    private String sowDt10;

    // 모종패키지상품코드
    private String sdingPkgPdCd;
    // 관리자구분코드
    private String mngrDvCd;
    // 입금예정자명
    private String dpEpttNm;
    // 조직유형
    private String ogTpCd;
    // 파트너번호
    private String prtnrNo;
    // 계약주소지ID
    private String cntrAdrpcId;
    // 유상비용금액
    private BigDecimal recapCsAmt;
    // 모종기기상품코드
    private String sdingMcnrPdCd;
    // 배송구분코드
    private String sppDvCd;
    // 고객서비스배정번호
    private String cstSvAsnNo;
    // 계약상세번호
    private String cntrDtlNo;
    // 기기계약상세번호
    private String mchnCstDtlNo;
    // 방문예정일자
    private String vstDuedt;
    // 서비스상품코드
    private String svPdCd;
    // 패키지 구분코드
    private String pkgDvCd;
}
