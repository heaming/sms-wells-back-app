package com.kyowon.sms.wells.web.bond.transfer.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WbnaBondPartTransferDto {
    /**
     * 파트 이관(집계) 검색
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부코드
     * @param clctamDvCd 집금구분코드
     * @param cstKnm 고객명
     * @param cstNo 고객번호
     * @param nwYn 신규여부
     */
    @ApiModel("WbnaBondPartTransferDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String bzHdqDvCd,
        String clctamDvCd,
        String cstKnm,
        String cstNo,
        String nwYn
    ) {}

    /**
     * 파트이관 집계 조회 결과
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부코드
     * @param clctamDvCd 집금구분코드
     * @param totCstCt 고객수
     * @param totCntrCt 계정수
     * @param woCstCt 고객수(전체)
     * @param woCntrCt 계약수(전체)
     * @param woObjAmt 대상금액(전체)
     * @param woDlqAmt 연체금액(전체)
     * @param woThmChramAmt 당월금액(전체)
     * @param woDlqAddAmt 연체가산금액(전체)
     * @param woRsgBorAmt 위약금액(전체)
     * @param cocnCstCt 고객수(전집)
     * @param cocnCntrCt 계약수(전집)
     * @param cocnObjAmt 대상금액(전집)
     * @param cocnDlqAmt 연체금액(전집)
     * @param cocnThmChramAmt 당월금액(전집)
     * @param cocnDlqAddAmt 연체가산금액(전집)
     * @param cocnRsgBorAmt 위약금액(전집)
     * @param hsmtrlCstCt 고객수(학습지)
     * @param hsmtrlCntrCt 계약수(학습지)
     * @param hsmtrlObjAmt 대상금액(학습지)
     * @param hsmtrlDlqAmt 연체금액(학습지)
     * @param hsmtrlThmChramAmt 당월금액(학습지)
     * @param hsmtrlDlqAddAmt 연체가산금액(학습지)
     * @param hsmtrlRsgBorAmt 위약금액(학습지)
     * @param hsmtrlBorCstCt 고객수(학습지-위약)
     * @param hsmtrlBorCntrCt 계약수(학습지-위약)
     * @param hsmtrlBorObjAmt 대상금액(학습지-위약)
     * @param hsmtrlBorDlqAmt 연체금액(학습지-위약)
     * @param hsmtrlBorThmChramAmt 당월금액(학습지-위약)
     * @param hsmtrlBorDlqAddAmt 연체가산금액(학습지-위약)
     * @param hsmtrlBorRsgBorAmt 위약금액(학습지-위약)
     * @param mchnRentalCstCt 고객수(기기렌탈)
     * @param mchnRentalCntrCt 계약수(기기렌탈)
     * @param mchnRentalObjAmt 대상금액(기기렌탈)
     * @param mchnRentalDlqAmt 연체금액(기기렌탈)
     * @param mchnRentalThmChramAmt 당월금액(기기렌탈)
     * @param mchnRentalDlqAddAmt 연체가산금액(기기렌탈)
     * @param mchnRentalRsgBorAmt 위약금액(기기렌탈)
     * @param mchnRentalBorCstCt 고객수(기기렌탈-위약)
     * @param mchnRentalBorCntrCt 계약수(기기렌탈-위약)
     * @param mchnRentalBorObjAmt 대상금액(기기렌탈-위약)
     * @param mchnRentalBorDlqAmt 연체금액(기기렌탈-위약)
     * @param mchnRentalBorThmChramAmt 당월금액(기기렌탈-위약)
     * @param mchnRentalBorDlqAddAmt 연체가산금액(기기렌탈-위약)
     * @param mchnRentalBorRsgBorAmt 위약금액(기기렌탈-위약)
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
        String cocnCstCt,
        String cocnCntrCt,
        String cocnObjAmt,
        String cocnDlqAmt,
        String cocnThmChramAmt,
        String cocnDlqAddAmt,
        String cocnRsgBorAmt,
        String hsmtrlCstCt,
        String hsmtrlCntrCt,
        String hsmtrlObjAmt,
        String hsmtrlDlqAmt,
        String hsmtrlThmChramAmt,
        String hsmtrlDlqAddAmt,
        String hsmtrlRsgBorAmt,
        String hsmtrlBorCstCt,
        String hsmtrlBorCntrCt,
        String hsmtrlBorObjAmt,
        String hsmtrlBorDlqAmt,
        String hsmtrlBorThmChramAmt,
        String hsmtrlBorDlqAddAmt,
        String hsmtrlBorRsgBorAmt,
        String mchnRentalCstCt,
        String mchnRentalCntrCt,
        String mchnRentalObjAmt,
        String mchnRentalDlqAmt,
        String mchnRentalThmChramAmt,
        String mchnRentalDlqAddAmt,
        String mchnRentalRsgBorAmt,
        String mchnRentalBorCstCt,
        String mchnRentalBorCntrCt,
        String mchnRentalBorObjAmt,
        String mchnRentalBorDlqAmt,
        String mchnRentalBorThmChramAmt,
        String mchnRentalBorDlqAddAmt,
        String mchnRentalBorRsgBorAmt
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
        String clctamDvCd,
        String bzHdqDvCd
    ) {}

    /**
     * 파티이관 집계 상세 조회 결과
     * @param baseYm 기준년월
     * @param bzHdqDvCd 사업부구분코드
     * @param cntrNo 계약번호
     * @param clctamDvCd 집금구분코드
     * @param prtnrKnm 전월담당자
     * @param cntrSn 계약일련번호
     * @param cstKnm 고객명
     * @param cstNo 고객번호
     * @param pdDvCd 상품구분코드
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
        String prtnrKnm,
        String cntrSn,
        String cstKnm,
        String cstNo,
        String pdDvCd,
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
