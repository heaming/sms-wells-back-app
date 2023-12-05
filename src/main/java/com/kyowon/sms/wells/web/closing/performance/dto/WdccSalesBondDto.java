package com.kyowon.sms.wells.web.closing.performance.dto;

import io.swagger.annotations.ApiModel;

public class WdccSalesBondDto {

    /**
     * 목록 조회 요청
     * @param slClYm 기준월
     * @param agrgDv 집계구분(1차 작업 시 설정 정보)
     * @param sellTpCd 판매유형
     * @param sellTpDtlCd 판매유형상세
     * @param sellChnlDtl 판매채널상세코드(1차 작업 시 설정 정보)
     * @param sapPdDvCd SAP상품구분코드
     * @param cntrNo 계약번호
     * @param cntrSn 계약상세번호
     */
    @ApiModel(value = "WdccDelinquentDto-SearchReq")
    public record SearchReq(
        String slClYm,
        String agrgDv,
        String sellTpCd,
        String sellTpDtlCd,
        String sellChnlDtl,
        String sapPdDvCd,
        String cntrNo,
        String cntrSn
    ) {}

    /**
     * 목록 조회 결과
     * @param slClYm 기준년월
     * @param slClDt
     * @param sellTpCd 판매유형코드
     * @param sellTpCdNm 판매유형코드명
     * @param sellTpDtlCd 판매유형상세코드
     * @param sellTpDtlCdNm 판매유형상세코드명
     * @param sapPdDvCd SAP상품코드
     * @param sapPdAtcNm SAP상품코드명
     * @param cntrNo 계약번호
     * @param cstNo 고객번호
     * @param cstKnm 고객명
     * @param pdCd 상품코드
     * @param pdNm 상품명
     * @param slRcogDt 미정
     * @param preUcAmt1 미정
     * @param preUcAmt2 미정
     * @param nomSlAmt 정상매출
     * @param nomSlAmt1 렌탈등록비
     * @param nomSlAmt2 렌탈할인
     * @param nomSlAmt3 렌탈등록비차액
     * @param nomSlAmt4 등록환불
     * @param nomSlAmt5 원금매출
     * @param nomSlAmt6 원금매출
     * @param slCanAmt 미정
     * @param totSlAmt 매출합계
     * @param totDpAmt 매출대사
     * @param slBndAlrpyAmt 매출입금
     * @param ucBlam1 미정
     * @param ucBlam2 미정
     * @param borRemAmt  위약잔여
     * @param borRemAmt1 미정
     * @param borRemAmt2 미정
     * @param borAdjAmt 위약조정
     * @param borAdjAmt1 미정
     * @param borAdjAmt2 미정
     * @param borAdjAmt3 미정
     * @param dpCngAmt 선수전환
     * @param dpCngAmt1 미정
     * @param dfaProcsAmt 대손
     * @param slClYm2 미정
     * @param btdDlqAddAmt  미정
     * @param thmOcDlqAddAmt 미정
     * @param thmCtrDlqAddAmt 미정
     * @param thmDlqAddDpSumAmt 미정
     * @param thmDlqAddRfndSumAmt 미정
     * @param eotDlqAddAmt 기말연체가산금
     * @param w1Am011 미정
     * @param w1Am012 미정
     * @param w1Am013 미정
     * @param oriSlAmt 원금매출
     * @param itrSlAmt 이자매출
     * @param svcSlAmt 서비스매출
     * @param slAdjAmt 매출조정
     * @param w1Am081 미정
     * @param w1Am082 미정
     * @param w1Am09 미정
     * @param w1Am10 미정
     * @param w1Am50 미정
     * @param w1Am121 미정
     * @param w1Am122 미정
     * @param w1Am123 미정
     * @param canSlAmt 취소매출
     * @param w1Am05 미정
     * @param w1Am07 미정
     * @param w1Am08 미정
     * @param feeSlAmt 수수료매출금액
     * @param boutNorSlAmt 상품권_정상매출
     * @param boutCanSlAmt  상품권_취소매출
     * @param cntrDpAmt 계약금입금
     * @param instDpAmt 할부금입금
     * @param eotUcAmt 기말미수잔액
     * @param crpUcAmt 법인미수금
     * @param boutDpAmt 상품권입금
     * @param etcDpAmt 기타선수대체
     * @param interContNomSlAmt 사간거래_정상매출금액
     * @param interContCanSlAmt 사간거래_취소매출금액
     * @param interContDpAmt 사간거래입금
     * @param interContDpAmt1 미정
     * @param interContDpAmt2 미정
     * @param bfThmUcBlam 대손금액
     * @param bfEotDlqAddAmt 미정
     * @param bfEotBorAmt 미정
     * @param w1Am191 미정
     * @param w1Am192 미정
     * @param w1Am193 미정
     * @param w1Am194 미정
     * @param w1Am195 미정
     * @param w1Am196 미정
     * @param thmUcBlam 기말미수잔액
     * @param eotBorAmt 당월위약금
     * @param totBtdUcAmt 전기이월
     * @param totEotUcAmt 기말미수/미수금액
     */
    @ApiModel(value = "WdccDelinquentDto-SearchRes")
    public record SearchRes(
        String slClYm,
        String slClDt,
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String sapPdDvCd,
        String sapPdAtcNm,
        String cntrNo,
        String cstNo,
        String cstKnm,
        String pdCd,
        String pdNm,
        String slRcogDt,
        String preUcAmt1,
        String preUcAmt2,
        String nomSlAmt,
        String nomSlAmt1,
        String nomSlAmt2,
        String nomSlAmt3,
        String nomSlAmt4,
        String nomSlAmt5,
        String nomSlAmt6,
        String slCanAmt,
        String totSlAmt,
        String totDpAmt,
        String slBndAlrpyAmt,
        String ucBlam1,
        String ucBlam2,
        String borRemAmt,
        String borRemAmt1,
        String borRemAmt2,
        String borAdjAmt,
        String borAdjAmt1,
        String borAdjAmt2,
        String borAdjAmt3,
        String dpCngAmt,
        String dpCngAmt1,
        String dfaProcsAmt,
        String slClYm2,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt,
        String w1Am011,
        String w1Am012,
        String w1Am013,
        String oriSlAmt,
        String itrSlAmt,
        String svcSlAmt,
        String slAdjAmt,
        String w1Am081,
        String w1Am082,
        String w1Am09,
        String w1Am10,
        String w1Am50,
        String w1Am121,
        String w1Am122,
        String w1Am123,
        String canSlAmt,
        String w1Am05,
        String w1Am07,
        String w1Am08,
        String feeSlAmt,
        String boutNorSlAmt,
        String boutCanSlAmt,
        String cntrDpAmt,
        String instDpAmt,
        String eotUcAmt,
        String crpUcAmt,
        String boutDpAmt,
        String etcDpAmt,
        String interContNomSlAmt,
        String interContCanSlAmt,
        String interContDpAmt,
        String interContDpAmt1,
        String interContDpAmt2,
        String bfThmUcBlam,
        String bfEotDlqAddAmt,
        String bfEotBorAmt,
        String w1Am191,
        String w1Am192,
        String w1Am193,
        String w1Am194,
        String w1Am195,
        String w1Am196,
        String thmUcBlam,
        String eotBorAmt,
        String totBtdUcAmt,
        String totEotUcAmt
    ) {}
}
