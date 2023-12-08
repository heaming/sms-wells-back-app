package com.kyowon.sms.wells.web.closing.performance.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WdccSalesBondDvo {
    private String slClYm; // 실적년월
    private String slClDt; // 실적일자
    private String sellTpCd; // 판매유형
    private String sellTpCdNm; // 판매유형코드명
    private String sellTpDtlCd; // 판매유형상세
    private String sellTpDtlCdNm; // 판매유형상세코드명
    private String sapPdDvCd; // SAP상품구분코드
    private String sapPdAtcNm; // SAP상품코드명
    private String cntrNo; // 계약번호
    private String cstNo; // 고객번호
    private String cstKnm; // 고객명
    private String pdCd; // 상품코드
    private String pdNm; // 상품명
    private String slRcogDt; // 매출일자
    private String preUcAmt1;
    private String preUcAmt2;
    private String nomSlAmt; // 정상매출
    private String nomSlAmt1; // 렌탈등록비
    private String nomSlAmt2; // 렌탈할인
    private String nomSlAmt3; // 렌탈등록비차액
    private String nomSlAmt4; // 등록환불
    private String nomSlAmt5; // 원금매출
    private String nomSlAmt6; // 원금매출
    private String slCanAmt; // 매출취소
    private String totSlAmt; // 매출합계
    private String totDpAmt;
    private String slBndAlrpyAmt; // 렌탈입금
    private String ucBlam1;
    private String ucBlam2;
    private String borRemAmt; // 위약잔여
    private String borRemAmt1;
    private String borRemAmt2;
    private String borAdjAmt; // 위약조정
    private String borAdjAmt1;
    private String borAdjAmt2;
    private String borAdjAmt3;
    private String dpCngAmt; // 선수대체
    private String dpCngAmt1; // 선수대체입금
    private String dfaProcsAmt; // 대손금액
    private String slClYm2;
    private String btdDlqAddAmt;
    private String thmOcDlqAddAmt;
    private String thmCtrDlqAddAmt;
    private String thmDlqAddDpSumAmt;
    private String thmDlqAddRfndSumAmt;
    private String eotDlqAddAmt;
    private String w1Am011;
    private String w1Am012;
    private String w1Am013;
    private String oriSlAmt;
    private String itrSlAmt;
    private String svcSlAmt;
    private String slAdjAmt;
    private String w1Am081;
    private String w1Am082;
    private String w1Am09;
    private String w1Am10;
    private String w1Am50;
    private String w1Am121;
    private String w1Am122;
    private String w1Am123;
    private String canSlAmt;
    private String w1Am05;
    private String w1Am07;
    private String w1Am08;
    private String feeSlAmt;
    private String boutNorSlAmt;
    private String boutCanSlAmt;
    private String cntrDpAmt;
    private String instDpAmt;
    private String eotUcAmt;
    private String crpUcAmt;
    private String boutDpAmt;
    private String etcDpAmt;
    private String interContNomSlAmt;
    private String interContCanSlAmt;
    private String interContDpAmt;
    private String interContDpAmt1;
    private String interContDpAmt2;
    private String bfThmUcBlam;
    private String bfEotDlqAddAmt;
    private String bfEotBorAmt;
    private String w1Am191;
    private String w1Am192;
    private String w1Am193;
    private String w1Am194;
    private String w1Am195;
    private String w1Am196;
    private String thmUcBlam;
    private String eotBorAmt;
    private String totBtdUcAmt; // 전기이월
    private String totEotUcAmt; // 기말미수-미수금액
}
