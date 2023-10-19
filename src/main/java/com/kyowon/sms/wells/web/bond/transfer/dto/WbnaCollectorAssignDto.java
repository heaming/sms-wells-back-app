package com.kyowon.sms.wells.web.bond.transfer.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WbnaCollectorAssignDto {
    /**
     * 집금자배정 검색
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부코드
     * @param clctamDvCd 집금구분코드
     * @param cstNo 고객번호
     * @param bndNwDvCd 신규구분코드
     * @param clctamPrtnrNo 집금담당자
     */
    @ApiModel("WbnaCollectorAssignDto-SearchReq")
    public record SearchReq(
        String baseYm,
        @NotBlank
        String bzHdqDvCd,
        @NotBlank
        String clctamDvCd,
        String cstNo,
        String bndNwDvCd,
        String clctamPrtnrNo
    ) {}

    /**
     * 집금자배정 결과
     * @param bzHdqDvCd 사업본부구분코드
     * @param clctamPrtnrNo 담당자번호
     * @param clctamPrtnrKnm 담당자명
     * @param asnRat 배정비율
     * @param woCstCt 전체 계약자수
     * @param woCntrCt 전체 계약수
     * @param woObjAmt 전체 대상금액
     * @param woDlqAmt 전체 연체금액
     * @param woThmChramAmt 전체 당월요금
     * @param woDlqAddAmt 전체 연체가산금
     * @param woRsgBorAmt 전체 위약금액
     * @param rentalCstCt 렌탈 계약수
     * @param rentalCntrCt 렌탈 계약수
     * @param rentalObjAmt 렌탈 대상금액
     * @param rentalDlqAmt 렌탈 연체금액
     * @param rentalThmChramAmt 렌탈 당월요금
     * @param rentalDlqAddAmt 렌탈 연체가산금
     * @param rentalRsgBorAmt 렌탈 위약금액
     * @param leaseCstCt 리스 계약자수
     * @param leaseCntrCt 리스 계약수
     * @param leaseObjAmt 리스 대상금액
     * @param leaseDlqAmt 리스 연체금액
     * @param leaseThmChramAmt 리스 당월요금
     * @param leaseDlqAddAmt 리스 연체가산금
     * @param leaseRsgBorAmt 리스 위약금액
     * @param mshCstCt 일반멥버십 계약자수
     * @param mshCntrCt 일반멥버십 계약수
     * @param mshObjAmt 일반멥버십 대상금액
     * @param mshDlqAmt 일반멥버십 연체금액
     * @param mshThmChramAmt 일반멥버십 당월요금
     * @param mshDlqAddAmt 일반멥버십 연체가산금
     * @param mshRsgBorAmt 일반멥버십 위약금액
     * @param rglrSppCstCt 정기배송 계약자수
     * @param rglrSppCntrCt 정기배송 계약수
     * @param rglrSppObjAmt 정기배송 대상금액
     * @param rglrSppDlqAmt 정기배송 연체금액
     * @param rglrSppThmChramAmt 정기배송 당월요금
     * @param rglrSppDlqAddAmt 정기배송 연체가산금
     * @param rglrSppRsgBorAmt 정기배송 위약금액
     * @param hcrCstCt 홈케어맴버십 계약자수
     * @param hcrCntrCt 홈케어맴버십 계약수
     * @param hcrObjAmt 홈케어맴버십 대상금액
     * @param hcrDlqAmt 홈케어맴버십 연체금액
     * @param hcrThmChramAmt 홈케어맴버십 당월요금
     * @param hcrDlqAddAmt 홈케어맴버십 연체가산금
     * @param hcrRsgBorAmt 홈케어맴버십 위약금액
     * @param spayCstCt 일시불 계약자수
     * @param spayCntrCt 일시불 계약수
     * @param spayObjAmt 일시불 대상금액
     * @param spayDlqAmt 일시불 연체금액
     * @param spayThmChramAmt 일시불 당월요금
     * @param spayDlqAddAmt 일시불 연체가산금
     * @param spayRsgBorAmt 일시불 위약금액
     */
    @ApiModel("WbnaCollectorAssignDto-SearchRes")
    public record SearchRes(
        String bzHdqDvCd, /*사업부코드*/
        String clctamPrtnrNo, /* 집금담당자번호			*/
        String clctamPrtnrKnm, /* 집금담당자명			*/
        String asnRat, /* 배정비중            */
        String woCstCt,
        String woCntrCt,
        String woObjAmt,
        String woDlqAmt,
        String woThmChramAmt,
        String woDlqAddAmt,
        String woRsgBorAmt,
        String rentalCstCt,
        String rentalCntrCt,
        String rentalObjAmt,
        String rentalDlqAmt,
        String rentalThmChramAmt,
        String rentalDlqAddAmt,
        String rentalRsgBorAmt,
        String leaseCstCt,
        String leaseCntrCt,
        String leaseObjAmt,
        String leaseDlqAmt,
        String leaseThmChramAmt,
        String leaseDlqAddAmt,
        String leaseRsgBorAmt,
        String mshCstCt,
        String mshCntrCt,
        String mshObjAmt,
        String mshDlqAmt,
        String mshThmChramAmt,
        String mshDlqAddAmt,
        String mshRsgBorAmt,
        String rglrSppCstCt,
        String rglrSppCntrCt,
        String rglrSppObjAmt,
        String rglrSppDlqAmt,
        String rglrSppThmChramAmt,
        String rglrSppDlqAddAmt,
        String rglrSppRsgBorAmt,
        String hcrCstCt,
        String hcrCntrCt,
        String hcrObjAmt,
        String hcrDlqAmt,
        String hcrThmChramAmt,
        String hcrDlqAddAmt,
        String hcrRsgBorAmt,
        String spayCstCt,
        String spayCntrCt,
        String spayObjAmt,
        String spayDlqAmt,
        String spayThmChramAmt,
        String spayDlqAddAmt,
        String spayRsgBorAmt
    ) {}

    /**
     * 집금자배정 상세 요청
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업본부구분코드
     * @param clctamDvCd 집금구분코드
     * @param clctamPrtnrNo 담당자번호
     */
    @ApiModel("WbnaCollectorAssignDto-SearchDetailReq")
    public record SearchDetailReq(
        String baseYm,
        @NotBlank
        String bzHdqDvCd,
        @NotBlank
        String clctamDvCd,
        @NotBlank
        String clctamPrtnrNo
    ) {}
    /**
     * 집금자배정 상세 응답
     * @param bndCntrId 계약ID
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업본부구분코드
     * @param cntrSn 계약일련번호
     * @param clctamPrtnrNo 담당자번호
     * @param clctamPrtnrKnm 담당자명
     * @param lstmmClctamDvCd 전월담당집금구분
     * @param bfClctamPrtnrNo 전월담당자번호
     * @param bfClctamPrtnrKnm 전월담당자
     * @param cntrNo 계약번호
     * @param cstNm 고객명
     * @param cstNo 고객번호
     * @param pdDvKnm 상품구분명
     * @param pdDvCd 상품구분
     * @param dlqMcn 연체개월
     * @param objAmt 대상금액
     * @param dlqAmt 연체금액
     * @param thmChramAmt 당월요금
     * @param dlqAddAmt 연체가산금
     * @param rsgBorAmt 위약금액
     * @param lwmTpCd 법조치유형
     * @param lwmDtlTpCd 법조치상세
     * @param lwmDt 법조치일자
     * @param dfltDt 채불등록일자
     * @param addr 주소
     * @param cujCd 법원코드
     * @param cujNm 법원명
     * @param indno 사건번호
     */
    @ApiModel("WbnaCollectorAssignDto-SearchDetailRes")
    public record SearchDetailRes(
        String bndCntrId, /* 계약ID		*/
        String baseYm, /* 기준년월		*/
        String bzHdqDvCd, /*사업본부구분코드*/
        String cntrSn, /* 계약일련번호      */
        String clctamPrtnrNo, /* 담당자번호		*/
        String clctamPrtnrKnm, /* 담당자명		*/
        String lstmmClctamDvCd, /* 전월담당집금구분   */
        String bfClctamPrtnrNo, /* 전월담당자 번호      */
        String bfClctamPrtnrKnm, /* 전월담당자       */
        String cntrNo, /* 계약번호        */
        String cstNm, /* 고객명         */
        String cstNo, /* 고객번호        */
        String pdDvKnm, /* 상품구분 명        */
        String pdDvCd, /* 상품구분        */
        Integer dlqMcn, /* 연체개월        */
        Long objAmt, /* 대상금액        */
        Long dlqAmt, /* 연체금액        */
        Long thmChramAmt, /* 당월요금        */
        Long dlqAddAmt, /* 연체가산금       */
        Long rsgBorAmt, /* 위약금액        */
        String lwmTpCd, /* 법조치유형       */
        String lwmDtlTpCd, /* 법조치상세       */
        String lwmDt, /* 법조치일자       */
        String dfltDt, /* 채불등록일자      */
        String addr, /* 주소           */
        String cujCd,
        String cujNm,
        String indno
    ) {}
    /**
     * 집금담당자 상세 합계 응답
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업본부구분코드
     * @param clctamDvCd 집금구분코드
     * @param clctamPrtnrNo 담당자번호
     * @param objAmt 대상금액
     * @param dlqAmt 연체금액
     * @param thmChramAmt 당월요금
     * @param dlqAddAmt 연체가산금
     * @param rsgBorAmt 위약금액
     */
    @ApiModel("WbnaCollectorAssignDto-SearchSummaryRes")
    public record SearchSummaryRes(
        String baseYm, /* 기준년월		*/
        String bzHdqDvCd, /*사업본부구분코드*/
        String clctamDvCd, /*집금구분코드*/
        String clctamPrtnrNo, /* 담당자번호		*/
        String objAmt, /* 대상금액        */
        String dlqAmt, /* 연체금액        */
        String thmChramAmt, /* 당월요금        */
        String dlqAddAmt, /* 연체가산금       */
        String rsgBorAmt /* 위약금액        */
    ) {}

    /**
     * 집금자 정보 수정
     * @param bndCntrId 계약ID
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부구분코드
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param cstNo 고객번호
     * @param clctamPrtnrNo 집금담당자번호
     * @param clctamDvCd 집금구분
     */
    @ApiModel("WbnaCollectorAssignDto-EditReq")
    public record EditReq(
        String bndCntrId,
        @NotBlank
        String baseYm,
        @NotBlank
        String bzHdqDvCd,
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        @NotBlank
        String cstNo,
        @NotBlank
        String clctamPrtnrNo,
        String clctamDvCd
    ) {}

    /**
     * 집금담당자 배정 생성
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부
     * @param clctamDvCd 집금구분
     */
    @ApiModel("WbnaCollectorAssignDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String bzHdqDvCd,
        @NotBlank
        String clctamDvCd
    ) {}
}
