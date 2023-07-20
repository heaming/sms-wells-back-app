package com.kyowon.sms.wells.web.bond.consultation.dvo;

import java.math.BigDecimal;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.*;

/**
 * <pre>
 * TB_CBBO_WELLS_AUTH_RSG_IZ
 * WELLS직권해지내역 T
 * </pre>
 *
 * @author gilyong.han
 * @since 2023-07-18
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WbncAuthorityResignIzDvo {
    private String baseYm; /* 기준년월 */
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String sellDvCd; /* 판매구분코드 */
    private String sellTpCd; /* 판매유형코드 */
    private String cstNo; /* 고객번호 */
    private String pdCd; /* 상품코드 */
    private Long sellQty; /* 판매수량 */
    private Long sellAmt; /* 판매금액 */
    private Long sellAmtVat; /* 판매금액부가가치세 */
    private String bfsvcPrdCd; /* BS주기코드 */
    private String uswyTpCd; /* 용도유형코드 */
    private Long rentalRgstCost; /* 렌탈등록비 */
    private Long rentalRgstCostVat; /* 렌탈등록비부가가치세 */
    private Long rentalAmt1; /* 렌탈금액1 */
    private Long rentalPtrm1; /* 렌탈기간1 */
    private Long rentalAmt2; /* 렌탈금액2 */
    private Long rentalPtrm2; /* 렌탈기간2 */
    private Long totRentalAmt; /* 총렌탈금액 */
    private Long totRentalPtrm; /* 총렌탈기간 */
    private BigDecimal dscr; /* 할인율 */
    private Long dscAmt; /* 할인금액 */
    private Long totCntrAmt; /* 총계약금액 */
    private String lkCntrNo; /* 연계계약번호 */
    private String slDt; /* 매출일자 */
    private Integer rentalNmnN; /* 렌탈차월수 */
    private Long rentalDc; /* 렌탈일수 */
    private Long slDc; /* 매출일수 */
    private Long nomSlAmt; /* 정상매출금액 */
    private Long spmtSlAmt; /* 추가매출금액 */
    private Long nomDscAmt; /* 정상할인금액 */
    private Long spmtDscAmt; /* 추가할인금액 */
    private Long slCtrAmt; /* 매출조정금액 */
    private Long canCtrAmt; /* 취소조정금액 */
    private Long slSumAmt; /* 매출합계금액 */
    private Long slSumVat; /* 매출합계부가가치세 */
    private Long slAggAmt; /* 매출누계금액 */
    private Long slAggAmtVat; /* 매출누계금액부가가치세 */
    private Long dscAggAmt; /* 할인누계금액 */
    private Long ctrAggAmt; /* 조정누계금액 */
    private Long prmBtdAmt; /* 선납기초금액 */
    private Long prpdBtdAmt; /* 선수기초금액 */
    private Long totPrpdAmt; /* 총선수금액 */
    private Long slDpAmt; /* 매출입금금액 */
    private Long slDpAggAmt; /* 매출입금누계금액 */
    private Integer dlqMcn; /* 연체개월수 */
    private String slStpYn; /* 매출중지여부 */
    private Long totNpdAmt; /* 총미납금액 */
    private Long lstmmOcptCs; /* 전월점유비용 */
    private Long thmOcptCs; /* 당월점유비용 */
    private Long lstmmUcAmt; /* 전월미수금액 */
    private Long ucAmt; /* 미수금액 */
    private Long dlqAmt; /* 연체금액 */
    private Long slStpAmt; /* 매출중지금액 */
    private Long totNpdExpAmt; /* 총미납예정금액 */
    private Long nrtrnDbtExpAmt; /* 미반환채무예정금액 */
    private Long rtrnDbtExpAmt; /* 반환채무예정금액 */
    private Long totNpdFnlAmt; /* 총미납최종금액 */
    private Long nrtrnDbtFnlAmt; /* 미반환채무최종금액 */
    private Long rtrnDbtFnlAmt; /* 반환채무최종금액 */
    private String clctamDvCd; /* 집금구분코드 */
    private String clctamPrtnrNo; /* 집금파트너번호 */
    private String rqdt; /* 요청일자 */
    private String canDt; /* 취소일자 */
    private String exmptYn; /* 면책여부 */
    private String csmbCsExmptYn; /* 소모품비용면책여부 */
    private String reqdCsExmptYn; /* 철거비용면책여부 */
    private String reqdRcvryAkYn; /* 철거복구요청여부 */
    private Long pdUseDc; /* 상품사용일수 */
    private String pdGdCd; /* 상품등급코드 */
    private Long prmRfndAmt; /* 선납환불금액 */
    private Long prpdRfndAmt; /* 선수환불금액 */
    private Long rentalRgstCostRfndAmt; /* 렌탈등록비환불금액 */
    private Long rentalRgstCostRfndAmtVat; /* 렌탈등록비환불금액부가가치세 */
    private Long borAmt; /* 위약금액 */
    private Long totRfndAmt; /* 총환불금액 */
    private Long lsRntf; /* 분실손료 */
    private Long pBtdAmt; /* 포인트기초금액 */
    private Long pDpAmt; /* 포인트입금금액 */
    private Long pRfndAmt; /* 포인트환불금액 */
    private Long pSlAmt; /* 포인트매출금액 */
    private Long pEotAmt; /* 포인트기말금액 */
    private Long adamtBtdAmt; /* 가산금기초금액 */
    private Long adamtThmOcAmt; /* 가산금당월발생금액 */
    private Long adamtDpRplcAmt; /* 가산금입금대체금액 */
    private Long adamtEotAmt; /* 가산금기말금액 */
    private Long adamtDdctam; /* 가산금공제금액 */
    private Long adamtRfndAmt; /* 가산금환불금액 */
    private Long adamtThmAmt; /* 가산금당월금액 */
    private Long rentalResBorAmt; /* 렌탈잔여위약금액 */
    private Long rentalRgstCostBorAmt; /* 렌탈등록비위약금액 */
    private Long dscCsBorAmt; /* 할인비용위약금액 */
    private Long csmbCsBorAmt; /* 소모품비용위약금액 */
    private Long rstlBorAmt; /* 재약정위약금액 */
    private Long pBorAmt; /* 포인트위약금액 */
    private Long reqdCsBorAmt; /* 철거비용위약금액 */
    private Long etcBorAmt1; /* 기타위약금액1 */
    private Long etcBorAmt2; /* 기타위약금액2 */
    private Long thmAdnSvUcAmt; /* 당월부가서비스미수금액 */
    private Long rsgMmUcAmt; /* 해지월미수금액 */
    private String authRsgExcdRqrPrtnrNo; /* 직권해지제외요청자파트너번호 */
    private String authRsgExcdRsonCd; /* 직권해지제외사유코드 */
    private String authRsgExcdRsonCn; /* 직권해지제외사유내용 */
    private String authRsgExpYn; /* 직권해지예정여부 */
    private String authRsgDuedt; /* 직권해지예정일자 */
    private String authRsgExpRgstPrtnrNo; /* 직권해지예정등록파트너번호 */
    private String authRsgCnfmYn; /* 직권해지확정여부 */
    private String authRsgCnfmdt; /* 직권해지확정일자 */
    private String authRsgCnfmRgstPrtnrNo; /* 직권해지확정등록파트너번호 */
    private String dtaDlYn; /* 데이터삭제여부 */

    /** 직권해지관리 - 렌탈 해지예정 전용 컬럼 */
    private String cntrNoSn; /* 계약일련상세번호 */
    private String sellTpNm; /* 판매유형명 */
    private String cstKnm; /* 고객명 */
    private String excdYn; /* 제외여부 */
    private String authRsgExcdRsonNm; /* 제외사유명 */
    private Long totMchnNpdAmt; /* 총미납금액(결합기기) */
    private String prtnrKnm; /* 집금담당자명 */
    private String clctamYn; /* 집금자여부 */
    private Long rveAmt; /* 입금액 */
    private String rqrBaseYm; /* 최초제외월 */
    private Long acuRveAmt; /* 누적입금액 */
    private String bryyMmdd; /* 생년월일 */
    private String cntrTpCd; /* 계약구분코드 */
    private String cntrTpNm; /* 계약구분명 */
    private String pdNm; /* 제품명 */
    private String istDt; /* 설치일자 */
    private String cralLocaraTno; /* 계약자휴대지역전화번호 */
    @DBDecField
    @DBEncField
    private String mexnoEncr; /* 계약자휴대전화국번호암호화 */
    private String cralIdvTno; /* 계약자휴대개별전화번호 */
    private String ogCd; /* 판매자조직코드 */
    private String sellPrtnrNo; /* 판매자번호 */
    private String plarKnm; /* 플래너 */
    private String canRedfYn; /* 취소되물림여부 */

}
