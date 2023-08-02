package com.kyowon.sms.wells.web.closing.sales.dto;

import io.swagger.annotations.ApiModel;

public class WdcbSalesPerformanceDetailDto {
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 멤버십매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchMembershipRes")
    public record SearchMembershipRes(
        String cstKnm,
        String cntrDtlNo,
        String slClYm,
        String pdCd,
        String pdNm,
        String svPrd,
        String lciuseNm,
        String cntrDt,
        String slRcogDt,
        String rentalRgstCost,
        String dscAmt,
        String canDt,
        String prmTn,
        String prmMcn,
        String lcpsYrmn,
        String lcpeYrmn,
        String prmDscr,
        String lcpmo1,
        String lcpmo2,
        String totPrmAmt,
        String rentalTn,
        String rentalDc,
        String lcentDt,
        String nomSlAmt,
        String nomDscAmt,
        String lcoutDt,
        String spmtSlAmt,
        String spmtDscAmt,
        String lcwanDt,
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
        String ovrCtrDpAmt,
        String prpdSlAmt,
        String eotAtam,
        String slBndAlrpyAmt,
        String slDpAggAmt,
        String thmUcBlam,
        String lcam51,
        String lcam53,
        String lcam55,
        String lcam61,
        String lcam63,
        String lcam65,
        String btdDlqAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt,
        String lcvisDt,
        String dlqMcn,
        String eotDlqAmt,
        String lcam67,
        String lcam68,
        String lcam69,
        String actcsDt,
        String clctamDvNm,
        String prtnrKnm,
        String prtnrNo
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 리스매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchLeaseRes")
    public record SearchLeaseRes(
        String cstKnm,
        String cntrDtlNo,
        String slClYm,
        String pdCd,
        String pdNm,
        String cntrDt,
        String slRcogDt,
        String svPrd,
        String lciuseNm,
        String rentalRgstCost,
        String istmAmt,
        String rentalPtrm,
        String svAmt,
        String rentalTn,
        String rentalDc,
        String canDt,
        String nomSlAmt,
        String fshDt,
        String slAggAmt,
        String dscAggAmt,
        String ctrAggAmt,
        String lcmam1,
        String lcmam2,
        String lcmam3,
        String lcmam4,
        String lcmam5,
        String lcmam6,
        String lcmam7,
        String lcmam8,
        String lcmam9,
        String lcmam10,
        String lcmam11,
        String lcam31,
        String lcam32,
        String lcam33,
        String lcam35,
        String ovrCtrDpAmt,
        String lcam3t,
        String mlgEotPrpdAmt,
        String eotAtam,
        String btdDlqAmt,
        String thmOcDlqAddAmt,
        String thmCtrDlqAddAmt,
        String thmDlqAddDpSumAmt,
        String thmDlqAddRfndSumAmt,
        String eotDlqAddAmt,
        String lccnt1,
        String lccnt2,
        String dlqMcn,
        String eotDlqAmt,
        String actcsDt,
        String clctamDvNm,
        String prtnrKnm,
        String prtnrNo,
        String lcsndDt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 렌탈매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchRentalRes")
    public record SearchRentalRes(
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
    // 정기배송매출 상세내역 Search Result Dto
    @ApiModel("WdcbSalesPerformanceDetailDto-SearchRegularRes")
    public record SearchRegularRes(
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
        String rcpPkgYn, /* 접수기준-패키지 여부 (LD3000P@LCPKYN) 맵핑없어서 패키지코드존재여부로 체크함 TODO.재문의 확인필요 */
        String rcpPkgCd, /* 접수기준-(패키지 번호:LCPKAG) */
        String rcpPkgNm, /* 접수기준-(패키지명:LCPKAG_NM) */
        String pkgYn, /* 현재기준-패키지 여부 (LD3000P@LCST07) 맵핑없어서 패키지코드존재여부로 체크함 TODO.재문의 확인필요 */
        String pkgCd, /* 현재기준-(패키지 번호:LCST12) */
        String pkgNm, /* 현재기준-(패키지명:LCST12_NM) */
        String pkgTpNm, /* 패키지 유형 명 */
        String mchnSellTpNm, /* 기기정보-판매유형(원주문) (업무구분:LCJTYP,LCJTYP_NM) */
        String mchnCntrNo, /* 기기정보-계약번호  ORYRCD */
        String mchnCntrSn, /* 기기정보-계약번호일련번호 ORYRCD(LCJYER-LCJCOD) */
        String mchnRcgvpKnm, /* 기기정보- 수령자한글명 ORCNAM(LCCNAM) */
        String mchnPdCd, /* 기기정보- 기기상품 - ORICDE(LCICDE) */
        String mchnPdNm, /* 기기정보- 기기상품명 - ORINA3 (LCICDE,KAINA1) */
        String rglrSppPrcDvCd, /* 판매유형(LD30.LCST05) - 결합/분리 */
        Long sellAmt, /* 판매금액 */
        Long rentalAmt, /* 추가금액 */
        Long dscAmt, /* 할인금액 */
        Long mmIstmAmt, /* 월청구금액 */
        String cntrDt, /* 접수일자 */
        String sppDt, /* 최초배송일자 */
        String lcsleDt, /* 매출일자 */
        // *********************************************************
        // 매출사항
        // *********************************************************
        String slOccYm, /* 매출발생월(LD50.LCDFLG) */
        String sppYn, /* 배송여부 @TO-DO */
        Integer rentalTn, /* 진행차월 */
        Integer sppTn, /* 배송차월 */
        Integer rentalDc, /* 사용일수(일) */
        String canDt, /* 취소일자 */
        Long nomSlAmt, /* 정상매출금액 */
        Long nomDscAmt, /* 정상할인금액 */
        String fshDt, /* 완료일자 @TO-DO 이행 반영 필요 */
        Long spmtSlAmt, /* 추가매출금액 */
        Long spmtDscAmt, /* 추가할인금액 */
        Long slCtrAmt, /* 매출조정금액 */
        Long thmSlSumAmt, /* 매출금액 */
        Long slSumVat, /* 매출VAT */
        Long slAggAmt, /* 매출누계금액 */
        Long dscAggAmt, /* 할인누계금액 */
        Long ctrAggAmt, /* 조정누계금액 */
        Long ucAmt, /* 매출잔액 */
        // *********************************************************
        // 선수금액
        // *********************************************************
        Long btdAtam, /* 기초금액 */
        Long thmAtamDpAmt, /* 선수입금액 */
        Long thmAtamRfndAmt, /* 선수환불금액 */
        Long prpdSlAmt, /* 매출대체금액 */
        Long slBndAlrpyAmt, /* 매출입금액 */
        Long slDpAggAmt, /* 입금누계금액 */
        Long ovrCtrDpAmt, /* 선수대체금액 */
        Long eotAtam, /* 선수잔액 */
        Long thmUcBlam, /* 미수총액 */
        Long eotDlqAmt, /* 연체금액 */
        // *********************************************************
        // 청구미수
        // *********************************************************
        Long btdUcAmt, /* 기초금액 */
        Long thmExpAmt, /* 당월예정금액 @TO-DO */
        Long thmAddAmt, /* 당월추가금액 @TO-DO */
        Long thmDpAmt, /* 당월입금액 @TO-DO */
        Long thmAdjAmt, /* 당월조정금액 @TO-DO */
        Long eotUcAmt, /* 청구잔액 @TO-DO */
        Long nextMonAmt1, /* 차월금액 @TO-DO */
        Long nextMonAmt2, /* 차차월금액 @TO-DO */
        // *********************************************************
        // 연체사항
        // *********************************************************
        Integer dlqMcn, /* 연체개월 */
        Integer dlqAcuMcn, /* 연체누적개월수 */
        String slStpYn, /* 매출중지여부 */
        String actcsDt, /* 수임일자 */
        String rcpAoffceCd, /* 접수사무소코드 */
        String rcpAoffceCdNm, /* 접수사무소명 @TO-DO 사무소명이 집금담당자의 근무지정보이면 집금파트너상세 Table.집금상담사근무지코드(CLCTAM_CSLR_WRKP_CD)를 읽어야 함 */
        String clctamDvCd, /* 집금구분코드 */
        String clctamDvCdNm, /* 집금구분코드명 */
        String clctamPrtnrNo, /* 집금파트너번호 */
        String clctamPrtnrNm, /* 집금파트너이름 */
        // *********************************************************
        // 기타정보
        // *********************************************************
        String dpTpCd, /* 이체구분코드(LC31.LCCHK1) */
        String dpTpCdNm, /* 이체구분코드명 */
        String mpyBsdt, /* 이체일자 */
        String fnitAprRsCd /* 금융기관승인결과코드 */
    ) {}
}
