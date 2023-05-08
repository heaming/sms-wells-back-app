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
    @ApiModel("EbnaFosterTransferMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /* 기준년월 */
        @NotBlank
        String bzHdqDvCd, /* 사업부 구분 */
        String bndNwDvCd, /* 신규구분 */
        String cstNo, /* 고객번호 */
        String cstNm, /* 고객명 */
        String cralLocaraTno, /* 휴대지역전화번호 */
        String mexnoEncr, /* 휴대전화국번호암호화 */
        String cralIdvTno, /* 휴대개별전화번호 */
        String bndClctnPrpDvCd, /* 채권추심속성구분코드 */
        String cntrNo, /* 계약번호 */
        Integer cntrSn /* 계약일련번호 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 위탁 이관 관리 Search Result Dto
    @Builder
    @ApiModel("EbnaFosterTransferMgtDto-SearchRes")
    public record SearchRes(
        String bzHdqDvCd,
        String baseYm,
        String clcoCd,
        Double totCstCt,
        Double totCntrCt,
        Double woCstCt,
        Double woCntrCt,
        Double woObjAmt,
        Double woDlqAmt,
        Double woThmChramAmt,
        Double woDlqAddAmt,
        Double woRsgBorAmt,
        Double rentalCstCt,
        Double rentalCntrCt,
        Double rentalObjAmt,
        Double rentalDlqAmt,
        Double rentalThmChramAmt,
        Double rentalDlqAddAmt,
        Double rentalRsgBorAmt,
        Double leaseCstCt,
        Double leaseCntrCt,
        Double leaseObjAmt,
        Double leaseDlqAmt,
        Double leaseThmChramAmt,
        Double leaseDlqAddAmt,
        Double leaseRsgBorAmt,
        Double mshCstCt,
        Double mshCntrCt,
        Double mshObjAmt,
        Double mshDlqAmt,
        Double mshThmChramAmt,
        Double mshDlqAddAmt,
        Double mshRsgBorAmt,
        Double rglrSppCstCt,
        Double rglrSppCntrCt,
        Double rglrSppObjAmt,
        Double rglrSppDlqAmt,
        Double rglrSppThmChramAmt,
        Double rglrSppDlqAddAmt,
        Double rglrSppRsgBorAmt,
        Double hcrCstCt,
        Double hcrCntrCt,
        Double hcrObjAmt,
        Double hcrDlqAmt,
        Double hcrThmChramAmt,
        Double hcrDlqAddAmt,
        Double hcrRsgBorAmt,
        Double spayCstCt,
        Double spayCntrCt,
        Double spayObjAmt,
        Double spayDlqAmt,
        Double spayThmChramAmt,
        Double spayDlqAddAmt,
        Double spayRsgBorAmt
    ) {}
    @Builder
    @ApiModel("EbnaFosterTransferMgtDto-SearchDetailRes")
    public record SearchDetailRes(
        String fstrCoNm, /* 위탁사명 */
        String clctnOjPrtnrNo, /* 담당자명 */
        String jbfClctamDvCd, /* 직전담당집금구분 */
        String jbfClctamPrtnrNo, /* 직전담당자 */
        String cntrNo, /* 계약번호 */
        Integer cntrSn, /* 계약일련번호 */
        String cstNm, /* 고객명 */
        String cstNo, /* 고객번호  */
        String pdNm, /* 상품구분 -- 계약번호 + 계약상세번호 로 상품정보 조회 */
        String dlqMcn, /* 연체개월 */
        Double trgAmt, /* 대상금액 */
        Double dlqAmt, /* 연체금액 */
        Double thmChramAmt, /* 당월요금 */
        Double dlqAddAmt, /* 연체가산금 */
        Double rsgBorAmt, /* 위약금액 */
        String lwmTpCd, /* 법조치유형 */
        String lwmDtlTpCd, /* 법조치상세 */
        String lwmDt, /* 법조치일자 */
        String dfltDt, /* 채불등록일자 */
        String addr/* 고객 주소 */
    ) {}

    @ApiModel("WbnaFosterTransferMgtDto-SearchDetailSummaryRes")
    public record SearchDetailSummaryRes(
        String trgAmt, /*총 대상금액*/
        String dlqAmt, /*총 연체금액*/
        String thmChramAmt, /*총 당월요금*/
        String dlqAddAmt, /*총 연체가산금액*/
        String rsgBorAmt /*총 위약금액*/
    ) {}
}
