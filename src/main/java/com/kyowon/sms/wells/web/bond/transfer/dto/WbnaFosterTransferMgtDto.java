package com.kyowon.sms.wells.web.bond.transfer.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WbnaFosterTransferMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 위탁 이관 관리 Search Request Dto
    @Builder
    @ApiModel("WbnaFosterTransferMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        @NotBlank
        String bzHdqDvCd, /* 사업부 구분 */
        @NotBlank
        String clctamDvCd, /* 집금구분코드 */
        String bndNwDvCd, /* 신규구분 */
        String cstNo, /* 고객번호 */
        String cstNm, /* 고객명 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String pageId, /* 엑셀다운로드 이력용 pageId */
        String clctamPrtnrNo /* 집금담당자번호 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 위탁 이관 관리 Search Result Dto
    @Builder
    @ApiModel("WbnaFosterTransferMgtDto-SearchRes")
    public record SearchRes(
        String bzHdqDvCd, /* 사업부 */
        String baseYm, /* 기준년월 */
        String clctamPrtnrNm, /* 집금담당자명 */
        String clctamPrtnrNo, /* 집금담당자번호 */
        Double woCstCt, /* 전체 - 고객수 */
        Double woCntrCt, /* 전체 - 계약수 */
        Double woUcAmt, /* 전체 - 미수금 */
        Double woObjAmt, /* 전체 - 당월대상 */
        Double woDlqAmt, /* 전체 - 연체금액 */
        Double woThmChramAmt, /* 전체 - 당월요금 */
        Double woDlqAddAmt, /* 전체 - 연체가산금액 */
        Double woRsgBorAmt, /* 전체 - 위약금액 */
        Double rentalCstCt, /* 렌탈 - 고객수 */
        Double rentalCntrCt, /* 렌탈 - 계약수 */
        Double rentalUcAmt, /* 렌탈 - 미수금 */
        Double rentalObjAmt, /* 렌탈 - 당월대상 */
        Double rentalDlqAmt, /* 렌탈 - 연체금액 */
        Double rentalThmChramAmt, /* 렌탈 - 당월요금 */
        Double rentalDlqAddAmt, /* 렌탈 - 연체가산금액 */
        Double rentalRsgBorAmt, /* 렌탈 - 위약금액 */
        Double leaseCstCt, /* 금융리스 - 고객수 */
        Double leaseCntrCt, /* 금융리스 - 계약수 */
        Double leaseUcAmt, /* 금융리스 - 미수금 */
        Double leaseObjAmt, /* 금융리스 - 당월대상 */
        Double leaseDlqAmt, /* 금융리스 - 연체금액 */
        Double leaseThmChramAmt, /* 금융리스 - 당월요금 */
        Double leaseDlqAddAmt, /* 금융리스 - 연체가산금액 */
        Double leaseRsgBorAmt, /* 금융리스 - 위약금액 */
        Double mshCstCt, /* 일반멤버십 - 고객수 */
        Double mshCntrCt, /* 일반멤버십 - 계약수 */
        Double mshUcAmt, /* 일반멤버십 - 미수금 */
        Double mshObjAmt, /* 일반멤버십 - 당월대상 */
        Double mshDlqAmt, /* 일반멤버십 - 연체금액 */
        Double mshThmChramAmt, /* 일반멤버십 - 당월요금 */
        Double mshDlqAddAmt, /* 일반멤버십 - 연체가산금액 */
        Double mshRsgBorAmt, /* 일반멤버십 - 위약금액 */
        Double rglrSppCstCt, /* 홈케어멤버십 - 고객수 */
        Double rglrSppCntrCt, /* 홈케어멤버십 - 계약수 */
        Double rglrSppUcAmt, /* 홈케어멤버십 - 미수금 */
        Double rglrSppObjAmt, /* 홈케어멤버십 - 당월대상 */
        Double rglrSppDlqAmt, /* 홈케어멤버십 - 연체금액 */
        Double rglrSppThmChramAmt, /* 홈케어멤버십 - 당월요금 */
        Double rglrSppDlqAddAmt, /* 홈케어멤버십 - 연체가산금액 */
        Double rglrSppRsgBorAmt, /* 홈케어멤버십 - 위약금액 */
        Double hcrCstCt, /* 일시불 - 고객수 */
        Double hcrCntrCt, /* 일시불 - 계약수 */
        Double hcrUcAmt, /* 일시불 - 미수금 */
        Double hcrObjAmt, /* 일시불 - 당월대상 */
        Double hcrDlqAmt, /* 일시불 - 연체금액 */
        Double hcrThmChramAmt, /* 일시불 - 당월요금 */
        Double hcrDlqAddAmt, /* 일시불 - 연체가산금액 */
        Double hcrRsgBorAmt, /* 일시불 - 위약금액 */
        Double spayCstCt, /* 정기배송 - 고객수 */
        Double spayCntrCt, /* 정기배송 - 계약수 */
        Double spayUcAmt, /* 정기배송 - 미수금 */
        Double spayObjAmt, /* 정기배송 - 당월대상 */
        Double spayDlqAmt, /* 정기배송 - 연체금액 */
        Double spayThmChramAmt, /* 정기배송 - 당월요금 */
        Double spayDlqAddAmt, /* 정기배송 - 연체가산금액 */
        Double spayRsgBorAmt/* 정기배송 - 위약금액 */
    ) {}
    @Builder
    @ApiModel("WbnaFosterTransferMgtDto-SearchDetailRes")
    public record SearchDetailRes(
        String fstrCoNm, /* 위탁사명 */
        String clctamPrtnrNo, /* 담당자번호 */
        String clctamPrtnrNm, /* 담당자명 */
        String clctamDvd, /* 직전담당집금구분 */
        String prtnrKnm, /* 직전담당자 */
        String cntrNo, /* 계약번호 */
        Integer cntrSn, /* 계약일련번호 */
        String cstNm, /* 고객명 */
        String cstNo, /* 고객번호  */
        String pdNm, /* 상품구분 -- 계약번호 + 계약상세번호 로 상품정보 조회 */
        String dlqMcn, /* 연체개월 */
        Double ucAmt, /* 미수금 */
        Double trgAmt, /* 대상금액 */
        Double dlqAmt, /* 연체금액 */
        Double thmChramAmt, /* 당월요금 */
        Double dlqAddAmt, /* 연체가산금 */
        Double rsgBorAmt, /* 위약금액 */
        String bndClctnPrpDvNm, /* 속성 */
        String bndClctnPrpRsonNm, /* 사유 */
        String lwmTp, /* 법조치유형 */
        String lwmDtlTpCd, /* 법조치상세 */
        String lwmDt, /* 법조치일자 */
        String dfltDt, /* 채불등록일자 */
        String addr, /* 고객 주소 */
        String baseYm, /* 기준년월 */
        String clctamDvCd, /* 집금구분코드 */
        String fnlMdfcDtm/* 최종수정일시 */
    ) {}

    @ApiModel("WbnaFosterTransferMgtDto-SearchDetailSummaryRes")
    public record SearchDetailSummaryRes(
        String ucAmt, /*총 미수금*/
        String trgAmt, /*총 대상금액*/
        String dlqAmt, /*총 연체금액*/
        String thmChramAmt, /*총 당월요금*/
        String dlqAddAmt, /*총 연체가산금액*/
        String rsgBorAmt /*총 위약금액*/
    ) {}
    @ApiModel("WbnaFosterTransferMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        @NotBlank
        String clctamDvCd, /* 집금구분코드 */
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotBlank
        String cntrSn, /* 계약일련번호 */
        String clctamPrtnrNo, /* 집금파트너번호 */
        @NotBlank
        String rowState,
        @NotBlank
        String fnlMdfcDtm /* 최종수정일시 */
    ) {}
}
