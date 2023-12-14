package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbSalesPerformanceDetailDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 멤버십매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String cstNo,
        String cstKnm,
        String cntrNo,
        String cntrSn,
        String slClYm,
        String sellTpCd,
        String sellTpNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String pdCd,
        String pdNm,
        String dscAmt,
        String svTpCdNm,
        String cntrDt,
        String cntrCnfmDt,
        String cntrCanDt,
        String lcsleDt,
        String prmTn,
        String prmMcn,
        String prmStrtYm,
        String prmEndYm,
        String prmDscr,
        String prmDscAmt,
        String prmAmt1,
        String prmMcn1,
        String prmAmt2,
        String prmMcn2,
        String totPrmAmt,
        String rentalTn,
        String vstNmnN,
        String rentalDc,
        String slDc,
        String jDt,
        String nomSlAmt,
        String nomDscAmt,
        String mshWdwalDt,
        String spmtSlAmt,
        String spmtDscAmt,
        String fshDt,
        String thmSlSumAmt,
        String slSumVat,
        String slCtrAmt,
        String dscAggAmt,
        String ctrAggAmt,
        String slAggAmt,
        String prmBlamBtdAmt,
        String prmDpAmt,
        String prmRfndAmt,
        String prmRplcAmt,
        String prmSlAmt,
        String prmExpAmt,
        String btdAtam,
        String thmAtamDpAmt,
        String thmAtamRfndAmt,
        String atamRplcProcsAmt,
        String prpdSlAmt,
        String eotAtam,
        String slBndAlrpyAmt,
        String slDpAggAmt,
        String eotUcAmt,
        String dscSlAmt,
        String dscDpAmt,
        String dscUcAmt,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt,
        String svPrd,
        String vstDt,
        String dlqMcn,
        String dlqAcuMcn,
        String eotDlqAmt,
        String dfaAmt,
        String dfaDpAmt,
        String actcsDt,
        String rcpAoffceCd,
        String rcpAoffceCdNm,
        String clctamDvCd,
        String clctamDvCdNm,
        String clctamPrtnrNo,
        String clctamPrtnrNm,
        String dpTpCd,
        String dpTpCdNm,
        String mpyBsdt,
        String fnitAprRsCd,
        String rentalAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 리스매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchLeaseRes")
    public record SearchLeaseRes(
        // *********************************************************
        // 고객기본정보
        // *********************************************************
        String cstNo, /* 고객번호 */
        String cstKnm, /* 고객명 */
        String cntrDtlNo, /* 계약상세번호 */
        String slClYm, /* 매출년월 */
        // *********************************************************
        // 계약제품
        // *********************************************************
        String sellTpCd, /* 판매유형 */
        String sellTpCdNm, /* 판매유형코드명 */
        String sellTpDtlCd, /* 판매유형상세 */
        String sellTpDtlCdNm, /* 판매유형상세코드명 */
        String pdCd, /* 상품코드 */
        String pdNm, /* 상품명 */
        String adnSv, /* 부가서비스정보 */
        String cntrDt, /* 접수일(LC50.LCCRTY) ASIS 계약일자 */
        String lcsleDt, /* 매출일자 */
        Integer svPrd, /* 주기(개월) */
        String svTpCdNm, /* 용도구분명 */
        Long rentalRgstCost, /* 렌탈등록비(LC50.LCTAMT) */
        Long dscAmt, /* 등록비할인금액(LC50.LCRAMT) */
        Long cntrTam, /* 렌탈총액 */
        Integer rentalPtrm, /* 렌탈기간1(LC50.LCMON1) */
        Long rentalAmt, /* 월 렌탈/리스금액1(LC50.LCAMT1) */
        Long rentalDscAmt, /* 월 렌탈할인금액1(LC50.LCRAM1) */
        Integer rentalPtrm2, /* 렌탈기간2(LC50.LCMON2) */
        Long rentalAmt2, /* 월 렌탈/리스금액2(LC50.LCAMT2) */
        Long rentalDscAmt2, /* 월 렌탈할인금액2(LC50.LCRAM2) */
        Long svAmt, /* 서비스료(LC50.LCSMT1) */
        Long stplDscAmt, /* 재약정할인금액(LC50.LCRAM3)*/
        // *********************************************************
        // 선납사항
        // *********************************************************
        String prmTn, /* 회차 */
        Integer prmMcn, /* 선납개월 */
        String prmStrtYm, /* 선납시작년월 */
        String prmEndYm, /* 선납종료년월 */
        Integer prmDscr, /* 할인율 */
        Long prmDscAmt, /* 할인금액 */
        Long prmAmt1, /* 선납금액1 @TO-DO 컬럼 추가 또는 입금데이터 확인 필요(LCPAM1) */
        String prmMcn1, /* 선납기간1 @TO-DO 컬럼 추가 또는 입금데이터 확인 필요(LCPMO1) */
        Long totPrmAmt, /* 총액 */
        String cttPsicNm, /* 선납만료컨택담당 @TO-DO */
        String cttPsicId, /* 선납만료컨택담당 @TO-DO */
        // *********************************************************
        // 매출사항
        // *********************************************************
        Integer rentalTn, /* 렌탈차월 */
        Integer rentalDc, /* 렌탈일수 */
        Integer slDc, /* 매출일수 */
        Integer useDc, /* 사용일수 LC50.LCCDAY */
        String canDt, /* 취소일자(LC50.LCCANY) */
        Long nomSlAmt, /* 정상매출금액 */
        Long nomDscAmt, /* 정상할인금액 */
        String fshDt, /* 완료일자(LC50.LCWANY) */
        Long spmtSlAmt, /* 추가매출금액 */
        Long spmtDscAmt, /* 추가할인금액 */
        Long slCtrAmt, /* 매출조정금액 */
        Long thmSlSumAmt, /* 매출금액 */
        Long slSumVat, /* 매출VAT */
        Long slAggAmt, /* 매출누계금액 */
        Long intAggAmt, /* 이자누계액 */
        Long sumSlAggAmt, /*  */
        Long sumDscAggAmt, /* 할인누계액 */
        Long intDscAggAmt, /* 이자할인누계금액 */
        Long sumCtrAggAmt, /* 조정누계액 */
        Long intCtrAggAmt, /* 이자조정누계금 */
        Long ucAmt, /* 매출잔액 */
        // *********************************************************
        // 선납선수
        // *********************************************************
        Long btdAtam, /* 기초금액 */
        Long atamDpAmt, /* 선수입금액 */
        Long atamRfndAmt, /* 선수환불금액 */
        Long atamSlAmt, /* 매출대체금액 */
        Long slBndAlrpyAmt, /* 매출입금액(LC50.LCAM38) */
        Long slDpAggAmt, /* 매출입금누계(LC50.LCAM39) */
        Long intDpAmt, /* 이자입금(LC50.LCCM38) */
        Long intDpAggAmt, /* 이자입금누계(LC50.LCCM39) */
        Long sumSlDpAggAmt, /* 입금누계 */
        Long thmUcBlam, /* 미수금액(LC50.LCMAMT) */
        Long ovrCtrDpAmt, /* 선수대체금액(LC50.LCAM34) */
        Long atamTotAmt, /* 선수총액 (LCAM36 선수기말 + LCAM75 포인트기말) */
        Long prmBlamEotAmt, /* 선납잔액 */
        Long mlgEotPrpdAmt, /* PR잔액 */
        Long eotAtam, /* 선수잔액 */
        // *********************************************************
        // 청구사항(ASIS 원금, 이자, 서비스 개별관리 → TOBE 통합) @TO-DO 테이블 컬럼 추가 필요
        // *********************************************************
        Long bilBtdAmt, /* 기초금액 */
        Long bilSlAmt, /* 발생금액 */
        Long bilDpAmt, /* 입금금액(입금 - 환불) */
        Long bilAddAmt, /* 추가금액 */
        Long bilAdjAmt, /* 조정금액 */
        Long bilEotAmt, /* 기말금액 */
        // *********************************************************
        // 위약금
        // *********************************************************
        Long btdBorAmt, /* 위약금기초금액 */
        Long ocBorAmt, /* 위약금발생금액 */
        Long lsRntf, /* 분실손료 */
        Long borDpAmt, /* 위약금입금액 */
        Long borAdjAmt, /* 위약금공제금액 */
        Long eotBorAmt, /* 위약금기말금액 */
        // *********************************************************
        // 연체가산
        // *********************************************************
        Long btdDlqAddAmt, /* 가산금기초금액 */
        Long thmOcDlqAddAmt, /* 당월가산금발생금액 */
        Long thmCtrDlqAddAmt, /* 당월조정연체가산금액 */
        Long thmDlqAddDpSumAmt, /* 당월연체가산입금합계금액 */
        Long thmDlqAddRfndSumAmt, /* 당월연체가산환불합계금액 */
        Long eotDlqAddAmt, /* 기말연체가산금액 */
        // *********************************************************
        // 연체사항
        // *********************************************************
        Long keepAwAmt, /* 유지수당 LC50.LCCNT1 * ASIS 월마감시 0 셋팅 */
        Long keepAwTotAmt, /* 유지수당누계금액 LC50.LCCNT2 * ASIS 월마감시 0 셋팅 */
        Integer dlqMcn, /* 연체개월 */
        Integer dlqAcuMcn, /* 연체누적개월수 */
        Long eotDlqAmt, /* 연체금액(C50.LCDAMT) */
        Long dfaAmt, /* 대손금액 */
        Long dfaDpAmt, /* 대손입금액(CW5400P.CWAMT1) */
        String slStpYn, /* 매출중지여부 */
        Long slStpAmt, /* 매출중지금액(LC50.LCAM96) */
        String actcsDt, /* 수임일자 */
        String rcpAoffceCd, /* 접수사무소코드 */
        String clctamDvCd, /* 집금구분코드 */
        String clctamDvCdNm, /* 집금구분코드명 */
        String clctamPrtnrNo, /* 집금파트너번호 */
        String clctamPrtnrNm, /* 집금파트너이름 */
        String clcoTfDt, /* 발송년월 */
        // *********************************************************
        // 기타정보
        // *********************************************************
        String exnTp, /* 만료유형(LC31.LCMGU2) @TO-DO 계약에서 코드 확인해주기로 함(앱코드만 존재) */
        String redfDt, /* 되물림일자(LC503.LCSMLY) */
        String adsbDt, /* 재지급일자(LC503.LCSJGY) */
        String alncmpCd, /* 제휴사코드(LC31.LCETC8) */
        String alncmpCdNm, /* 제휴구분명 */
        String sellDscDvCd, /* 할인구분(LC31.LCETC3) */
        String sellDscDvCdNm, /* 할인구분명 */
        String stplPtrm, /* 약정기간(LC31.LCGUB3) */
        String dpTpCd, /* 이체구분코드(LC31.LCCHK1) */
        String dpTpCdNm, /* 이체구분명 */
        String mpyBsdt, /* 이체일자 */
        String fnitAprRsCd /* 금융기관승인결과코드 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchRentalRes")
    public record SearchRentalRes(
        String cntrNo,
        String cntrSn,
        String slClYm,
        String cstNo,
        String cstKnm,
        String sellTpCd,
        String sellTpNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String islease,
        String pdCd,
        String pdNm,
        String adnSv,
        String cntrDt,
        String lcsleDt,
        String svPrd,
        String svTpCdNm,
        String rentalRgstCost,
        String dscAmt,
        String cntrTam,
        String rentalPtrm,
        String rentalAmt,
        String rentalDscAmt,
        String rentalPtrm2,
        String rentalAmt2,
        String rentalDscAmt2,
        String svAmt,
        String stplDscAmt,
        String prmTn,
        String prmMcn,
        String prmStrtYm,
        String prmEndYm,
        String prmDscr,
        String prmDscAmt,
        String prmAmt1,
        String prmMcn1,
        String totPrmAmt,
        String rentalTn,
        String rentalDc,
        String slDc,
        String useDc,
        String cntrCanDt,
        String nomSlAmt,
        String nomDscAmt,
        String fshDt,
        String spmtSlAmt,
        String spmtDscAmt,
        String slCtrAmt,
        String thmSlSumAmt,
        String slSumVat,
        String slAggAmt,
        String intAggAmt,
        String sumSlAggAmt,
        String dscAggAmt,
        String ctrAggAmt,
        String slBlam,
        String btdAtam,
        String atamDpAmt,
        String atamRfndAmt,
        String prpdSlAmt,
        String slBndAlrpyAmt,
        String slDpAggAmt,
        String eotUcAmt,
        String atamRplcProcsAmt,
        String atamTotAmt,
        String prmBlamEotAmt,
        String mlgEotPrpdAmt,
        String eotAtam,
        String btdBilUcAmt,
        String thmBilOcAmt,
        String thmBilDpAmt,
        String thmBilCtrAmt,
        String thmBilSpmtAmt,
        String eotBilUcAmt,
        String btdBorAmt,
        String ocBorAmt,
        String lsRntf,
        String borDpAmt,
        String borAdjAmt,
        String eotBorAmt,
        String borAmt,
        String btdDlqAddAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt,
        String keepAwAmt,
        String keepAwTotAmt,
        String dlqMcn,
        String dlqAcuMcn,
        String eotDlqAmt,
        String dfaAmt,
        String dfaDpAmt,
        String slStpYn,
        String slStpAmt,
        String actcsDt,
        String rcpAoffceCd,
        String rcpAoffceCdNm,
        String clctamDvCd,
        String clctamDvCdNm,
        String clctamPrtnrNo,
        String clctamPrtnrNm,
        String clcoTfDt,
        String exnTp,
        String redfDt,
        String adsbDt,
        String alncmpCd,
        String alncmpCdNm,
        String sellDscDvCd,
        String sellDscDvCdNm,
        String stplPtrm,
        String dpTpCd,
        String dpTpCdNm,
        String mpyBsdt,
        String fnitAprRsCd,
        String pvdaAmt, /*이자매출*/
        String eotBilUcSumAmt /*발생미수금액*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 정기배송매출 상세내역 Search Result Dto

    /**
     * 정기배송매출 결과
     * @param cstNo 고객번호
     * @param cstKnm 고객명
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param slClYm 매출년월
     * @param sellTpCd 판매유형
     * @param sellTpNm 판매유형명
     * @param sellTpDtlCd 판매유형상세
     * @param sellTpDtlCdNm 판매유형상세명
     * @param pdCd 상품코드
     * @param pdNm 상품명
     * @param rcpPkgYn 접수기준 패키지여부
     * @param rcpPkgCd 접수기준 패키지
     * @param rcpPkgNm 접수기준 패키지명
     * @param pkgYn 현재기준-패키지 여부
     * @param pkgCd 현재기준-패키지
     * @param pkgNm 현재기준-패키지명
     * @param pkgTpNm 현재기준-패키지군
     * @param mchnSellTpNm 기기정보-판매유형
     * @param mchnCntrNo 기기정보-계약번호
     * @param mchnCntrSn 기기정보-계약번호일련번호
     * @param mchnRcgvpKnm 기기정보- 수령자한글명
     * @param mchnPdCd 기기정보- 기기상품
     * @param mchnPdNm 기기정보- 기기상품명
     * @param rglrSppPrcDvCd 판매유형
     * @param sellAmt 판매금액
     * @param rentalAmt 추가금액
     * @param dscAmt 할인금액
     * @param mmIstmAmt 월청구금액
     * @param cntrDt 접수일자
     * @param sppDt 최초배송일자
     * @param lcsleDt 매출일자
     * @param slOccYn 매출발생월
     * @param sppYn 배송여부
     * @param rentalTn 진행차월
     * @param sppTn 배송차월
     * @param rentalDc 사용일수
     * @param cntrCanDt 취소일자
     * @param nomSlAmt 정상매출금액
     * @param nomDscAmt 정상할인금액
     * @param fshDt 완료일자
     * @param spmtSlAmt 추가매출금액
     * @param spmtDscAmt 추가할인금액
     * @param slCtrAmt 매출조정금액
     * @param thmSlSumAmt 매출금액
     * @param slSumVat 매출VAT
     * @param slAggAmt 매출누계금액
     * @param dscAggAmt 할인누계금액
     * @param ctrAggAmt 조정누계금액
     * @param slBlam 매출잔액
     * @param btdAtam 기초금액
     * @param thmAtamDpAmt 선수입금액
     * @param thmAtamRfndAmt 선수환불금액
     * @param prpdSlAmt 매출대체금액
     * @param slBndAlrpyAmt 매출입금액
     * @param slDpAggAmt 입금누계금액
     * @param atamRplcProcsAmt 선수대체금액
     * @param eotAtam 선수잔액
     * @param eotUcAmt 미수총액
     * @param eotDlqAmt 연체금액
     * @param btdBilUcAmt 기초금액
     * @param thmBilOcAmt 당월예정금액
     * @param thmBilSpmtAmt 당월추가금액
     * @param thmBilDpAmt 당월입금액
     * @param thmBilCtrAmt 당월조정금액
     * @param eotBilUcAmt 청구잔액
     * @param nmnBilUcExpAmt 차월금액
     * @param tsmBilUcExpAmt 차차월금액
     * @param dlqMcn 연체개월
     * @param dlqAcuMcn 연체누적개월수
     * @param slStpYn 매출중지여부
     * @param actcsDt 수임일자
     * @param rcpAoffceCd 접수사무소코드
     * @param rcpAoffceCdNm 접수사무소명
     * @param clctamDvCd 집금구분코드
     * @param clctamDvCdNm 집금구분코드명
     * @param clctamPrtnrNo 집금파트너번호
     * @param clctamPrtnrNm 집금파트너명
     * @param dpTpCd 이체구분코드
     * @param dpTpCdNm
     * @param mpyBsdt 이체일자
     * @param fnitAprRsCd 금융기관승인결과코드
     * @param eotBilUcSumAmt 발생미수합계
     */
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchRegularRes")
    public record SearchRegularRes(
        String cstNo,
        String cstKnm,
        String cntrNo,
        String cntrSn,
        String slClYm,
        String sellTpCd,
        String sellTpNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String pdCd,
        String pdNm,
        String rcpPkgYn,
        String rcpPkgCd,
        String rcpPkgNm,
        String pkgYn,
        String pkgCd,
        String pkgNm,
        String pkgTpNm,
        String mchnSellTpNm,
        String mchnCntrNo,
        String mchnCntrSn,
        String mchnRcgvpKnm,
        String mchnPdCd,
        String mchnPdNm,
        String rglrSppPrcDvCd,
        String sellAmt,
        String rentalAmt,
        String dscAmt,
        String mmIstmAmt,
        String cntrDt,
        String sppDt,
        String lcsleDt,
        String slOccYn,
        String sppYn,
        String rentalTn,
        String sppTn,
        String rentalDc,
        String cntrCanDt,
        String nomSlAmt,
        String nomDscAmt,
        String fshDt,
        String spmtSlAmt,
        String spmtDscAmt,
        String slCtrAmt,
        String thmSlSumAmt,
        String slSumVat,
        String slAggAmt,
        String dscAggAmt,
        String ctrAggAmt,
        String slBlam,
        String btdAtam,
        String thmAtamDpAmt,
        String thmAtamRfndAmt,
        String prpdSlAmt,
        String slBndAlrpyAmt,
        String slDpAggAmt,
        String atamRplcProcsAmt,
        String eotAtam,
        String eotUcAmt,
        String eotDlqAmt,
        String btdBilUcAmt,
        String thmBilOcAmt,
        String thmBilSpmtAmt,
        String thmBilDpAmt,
        String thmBilCtrAmt,
        String eotBilUcAmt,
        String nmnBilUcExpAmt,
        String tsmBilUcExpAmt,
        String dlqMcn,
        String dlqAcuMcn,
        String slStpYn,
        String actcsDt,
        String rcpAoffceCd,
        String rcpAoffceCdNm,
        String clctamDvCd,
        String clctamDvCdNm,
        String clctamPrtnrNo,
        String clctamPrtnrNm,
        String dpTpCd,
        String dpTpCdNm,
        String mpyBsdt,
        String fnitAprRsCd,
        String eotBilUcSumAmt
    ) {}
}
