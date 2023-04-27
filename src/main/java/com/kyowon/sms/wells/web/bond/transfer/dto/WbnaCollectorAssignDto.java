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
        String baseYm, // TODO 화면에서 사용할지 여부 확인 후 필수 추가 작업 필요
        @NotBlank
        String bzHdqDvCd,
        @NotBlank
        String clctamDvCd,
        String cstNo,
        String bndNwDvCd,
        String clctamPrtnrNo
    ) {}

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

    @ApiModel("WbnaCollectorAssignDto-SearchDetailReq")
    public record SearchDetailReq(
        String baseYm, // TODO 화면에서 사용할지 여부 확인 후 필수 추가 작업 필요
        @NotBlank
        String bzHdqDvCd,
        @NotBlank
        String clctamDvCd,
        @NotBlank
        String clctamPrtnrNo
    ) {}

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
        String cstKnm, /* 고객명         */
        String cstNo, /* 고객번호        */
        String pdDvKnm, /* 상품구분 명        */
        String pdDvCd, /* 상품구분        */
        Integer dlqMcn, /* 연체개월        */
        Long objAmt, /* 대상금액        */
        Long dlqAmt, /* 연체금액        */
        Long thmChramAmt, /* 당월요금        */
        Long dlqAddDpAmt, /* 연체가산금       */
        Long rsgBorAmt, /* 위약금액        */
        String lwmTpCd, /* 법조치유형       */
        String lwmDtlTpCd, /* 법조치상세       */
        String lwmDt, /* 법조치일자       */
        String dfltDt, /* 채불등록일자      */
        String addr /* 주소           */
    ) {}

    @ApiModel("WbnaCollectorAssignDto-SearchSummaryRes")
    public record SearchSummaryRes(
        String baseYm, /* 기준년월		*/
        String bzHdqDvCd, /*사업본부구분코드*/
        String clctamDvCd, /*집금구분코드*/
        String clctamPrtnrNo, /* 담당자번호		*/
        String objAmt, /* 대상금액        */
        String dlqAmt, /* 연체금액        */
        String thmChramAmt, /* 당월요금        */
        String dlqAddDpAmt, /* 연체가산금       */
        String rsgBorAmt /* 위약금액        */
    ) {}

    /**
     * 집금자 정보 수정
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부구분코드
     * @param cntrNo 계약번호
     * @param cntrSn 계약일련번호
     * @param cstNo 고객번호
     * @param clctamPrtnrNo 집금담당자번호
     */
    @ApiModel("WbnaCollectorAssignDto-EditReq")
    public record EditReq(
        String bndCntrId, // TODO bndCntrId, baseYm 기본키가 2개 항목으로 변경 되었음 이거 기준으로 작업 가능하면 나머지 조건 삭제
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
        String clctamPrtnrNo
    ) {}
}
