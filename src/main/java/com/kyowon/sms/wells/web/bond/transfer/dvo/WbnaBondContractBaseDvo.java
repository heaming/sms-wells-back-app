package com.kyowon.sms.wells.web.bond.transfer.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * TB_CBBO_BND_CNTR_BAS
 * 채권계약기본 T
 * </pre>
 *
 * @author gilyong.han
 * @since 2023-04-17
 */
@Getter
@Setter
public class WbnaBondContractBaseDvo {
    private String bndCntrId; /* 채권계약ID */
    private String baseYm; /* 기준년월 */
    private String cntrNo; /* 계약번호 */
    private Integer cntrSn; /* 계약일련번호 */
    private String cntrDtlStatCd; /* 계약상세상태코드 */
    private String jbfCntrDtlStatCd; /* 직전계약상세상태코드 */
    private String coCd; /* 회사코드 */
    private String bzHdqDvCd; /* 사업본부구분코드 */
    private String bndBizDvCd; /* 채권업무구분코드 */
    private String bndClctnPrpDvCd; /* 채권추심속성구분코드 */
    private String bndClctnPrpRsonCd; /* 채권추심속성사유코드 */
    private String bndClctnPrpChdt; /* 채권추심속성변경일자 */
    private String clctamMngtYn; /* 집금관리여부 */
    private String fntBilYn; /* 이체청구여부 */
    private String charFwYn; /* 문자발송여부 */
    private String buDdtnYn; /* 부담공제여부 */
    private String dlqAdamtOcYn; /* 연체가산금발생여부 */
    private String bndTfRmkCn; /* 채권이관비고내용 */
    private String bndTfDt; /* 채권이관일자 */
    private String clctamPaCd; /* 집금파트코드 */
    private String bndAsnDt; /* 채권배정일자 */
    private String clctamPrtnrNo; /* 집금파트너번호 */
    private String bfClctamPrtnrNo; /* 이전집금파트너번호 */
    private String actcsDt; /* 수임일자 */
    private String cstNo; /* 고객번호 */
    private String cstNm; /* 고객명 */
    private String cralLocaraTno; /* 휴대지역전화번호 */
    @DBDecField
    @DBEncField
    private String mexnoEncr; /* 휴대전화국번호암호화 */
    private String cralIdvTno; /* 휴대개별전화번호 */
    private String bndCstZip; /* 채권고객우편번호 */
    private String bndCstAdrId; /* 채권고객주소ID */
    private String clctamDvCd; /* 집금구분코드 */
    private String dlqRckDt; /* 연체기산일자 */
    private Integer dlqMcn; /* 연체개월수 */
    private Long dlqAmt; /* 연체금액 */
    private Long dlqAddAmt; /* 연체가산금액 */
    private Long rsgBorAmt; /* 해지위약금액 */
    private Long thmChramAmt; /* 당월요금금액 */
    private Long ucAmt; /* 미수금액 */
    private Long dlqDpAmt; /* 연체입금금액 */
    private Long dlqAddDpAmt; /* 연체가산입금금액 */
    private Long rsgBorDpAmt; /* 해지위약입금금액 */
    private Long thmChramDpAmt; /* 당월요금입금금액 */
    private Long fulpyExpAmt; /* 완불예정금액 */
    private String bndNwDvCd; /* 채권신규구분코드 */
    private String bndAsnStatCd; /* 채권배정상태코드 */
    private String bndClnRsCd; /* 채권회수결과코드 */
    private String bndAsnMthCd; /* 채권배정방법코드 */
    private String clcoCd; /* 추심사코드 */
    private String clcoTfDt; /* 추심사이관일자 */
    private String clctnOjPrtnrNo; /* 추심대상파트너번호 */
    private String aprRqrNo; /* 승인요청자번호 */
    private String aprAkStatCd; /* 승인요청상태코드 */
    private String aprAkDtm; /* 승인요청일시 */
    private String aprPsicNo; /* 승인담당자번호 */
    private String aprDtm; /* 승인일시 */
    private String dtaDlYn; /* 데이터삭제여부 */
}
