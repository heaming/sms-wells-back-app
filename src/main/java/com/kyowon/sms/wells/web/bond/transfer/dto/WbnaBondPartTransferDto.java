package com.kyowon.sms.wells.web.bond.transfer.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WbnaBondPartTransferDto {
    /**
     * 파트 이관(집계) 검색
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부코드
     * @param clctamDvCd 집금구분코드
     * @param cstNm 고객명
     * @param cstNo 고객번호
     * @param bndNwDvCd 채권신규구분코드
     */
    @ApiModel("WbnaBondPartTransferDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String bzHdqDvCd,
        String clctamDvCd,
        String cstNm,
        String cstNo,
        String phoneNumber,
        String bndNwDvCd
    ) {}

    /**
     * 파트 이관(집계) 검색 결과
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업본부코드
     * @param clctamDvCd 직금구분코드
     * @param totCstCt 전체 고객수
     * @param totCntrCt 전체 계약수
     * @param woCstCt 전체 고객수
     * @param woCntrCt 전체 계약수
     * @param woObjAmt 전체 대상금액
     * @param woDlqAmt 전체 연체금액
     * @param woThmChramAmt 전체 당월금액
     * @param woDlqAddAmt 전체 연체가산금액
     * @param woRsgBorAmt 전체 위약금액
     * @param rentalCstCt 렌탈 고객수
     * @param rentalCntrCt 렌탈 계약수
     * @param rentalObjAmt 렌탈 대상금액
     * @param rentalDlqAmt 렌탈 연체금액
     * @param rentalThmChramAmt 렌탈 당월금액
     * @param rentalDlqAddAmt 렌탈 연체가산금액
     * @param rentalRsgBorAmt 렌탈 위약금액
     * @param leaseCstCt 금융리스 고객수
     * @param leaseCntrCt 금융리스 계약수
     * @param leaseObjAmt 금융리스 대상금액
     * @param leaseDlqAmt 금융리스 연체금액
     * @param leaseThmChramAmt 금융리스 당월금액
     * @param leaseDlqAddAmt 금융리스 연체가산금액
     * @param leaseRsgBorAmt 금융리스 위약금액
     * @param mshCstCt 일반맴버십 고객수
     * @param mshCntrCt 일반맴버십 계약수
     * @param mshObjAmt 일반맴버십 대상금액
     * @param mshDlqAmt 일반맴버십 연체금액
     * @param mshThmChramAmt 일반맴버십 당월금액
     * @param mshDlqAddAmt 일반맴버십 연체가산금액
     * @param mshRsgBorAmt 일반맴버십 위약금액
     * @param rglrSppCstCt 정기배송 고객수
     * @param rglrSppCntrCt 정기배송 계약수
     * @param rglrSppObjAmt 정기배송 대상금액
     * @param rglrSppDlqAmt 정기배송 연체금액
     * @param rglrSppThmChramAmt 정기배송 당월금액
     * @param rglrSppDlqAddAmt 정기배송 연체가산금액
     * @param rglrSppRsgBorAmt 정기배송 위약금액
     * @param hcrCstCt 홈케어맴버십 고객수
     * @param hcrCntrCt 홈케어맴버십 계약수
     * @param hcrObjAmt 홈케어맴버십 대상금액
     * @param hcrDlqAmt 홈케어맴버십 연체금액
     * @param hcrThmChramAmt 홈케어맴버십 당월금액
     * @param hcrDlqAddAmt 홈케어맴버십 연체가산금액
     * @param hcrRsgBorAmt 홈케어맴버십 위약금액
     * @param spayCstCt 일시불 고객수
     * @param spayCntrCt 일시불 계약수
     * @param spayObjAmt 일시불 대상금액
     * @param spayDlqAmt 일시불 연체금액
     * @param spayThmChramAmt 일시불 당월금액
     * @param spayDlqAddAmt 일시불 연체가산금액
     * @param spayRsgBorAmt 일시불 위약금액
     */
    @ApiModel("WbnaBondPartTransferDto-SearchRes")
    public record SearchRes(
        String baseYm,
        String bzHdqDvCd,
        String clctamDvCd,
        String totCstCt,
        String totCntrCt,
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
     * 파티이관 집계 상세 조회 조건
     * @param baseYm 기준년월
     * @param clctamDvCd 집금구분코드
     * @param bzHdqDvCd 사업부구분코드
     */
    @ApiModel("WbnaBondPartTransferDto-SearchDetailReq")
    public record SearchDetailReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String bzHdqDvCd,
        String clctamDvCd,
        String cstNm,
        String cstNo,
        String bndNwDvCd
    ) {}

    @ApiModel("WbnaPartTransferDto-SearchDetailSummaryRes")
    public record SearchDetailSummaryRes(
        String baseYm,
        String bzHdqDvCd,
        String clctamDvCd,
        String objAmt,
        String dlqAmt,
        String thmChramAmt,
        String dlqAddDpAmt,
        String rsgBorAmt
    ) {}

    /**
     * 파티이관 집계 상세 조회 결과
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부구분코드
     * @param cntrNo 계약번호
     * @param clctamDvCd 집금구분코드
     * @param bfPrtnrKnm 전월담당자
     * @param cntrSn 계약일련번호
     * @param cstNm 고객명
     * @param cstNo 고객번호
     * @param bndBizDvCd 상품구분코드
     * @param dlqMcn 연체개월
     * @param objAmt 대상금액
     * @param dlqAmt 연체금액
     * @param thmChramAmt 당월금액
     * @param dlqAddDpAmt 연체가산금액
     * @param rsgBorAmt 위약금액
     * @param lwmTpCd 법조치유형
     * @param lwmDtlTpCd 법조치상세
     * @param lwmDt 봅조치일자
     * @param dfltDt 체불등록일자
     * @param addr 주소
     */
    @ApiModel("WbnaBondPartTransferDto-SearchDetailRes")
    public record SearchDetailRes(
        String baseYm,
        String bzHdqDvCd,
        String cntrNo,
        String clctamDvCd,
        String bfPrtnrKnm,
        String cntrSn,
        String cstNm,
        String cstNo,
        String bndBizDvCd,
        String dlqMcn,
        String objAmt,
        String dlqAmt,
        String thmChramAmt,
        String dlqAddDpAmt,
        String rsgBorAmt,
        String lwmTpCd,
        String lwmDtlTpCd,
        String lwmDt,
        String dfltDt,
        String addr
    ) {}

    /**
     * 파트이관 생성 조건
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부구분코드
     * @param clctamDvCd 채권구분코드
     */
    @ApiModel("WbnaBondPartTransferDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String bzHdqDvCd,
        @NotBlank
        String clctamDvCd
    ) {}

    /**
     * 파트이관 집금구분 갱신 조건
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부구분코드
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param cstNo 고객번호
     * @param clctamDvCd 집금구분코드
     */
    @ApiModel("WbnaBondPartTransferDto-EditReq")
    public record EditReq(
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
        String clctamDvCd
    ) {}
}
