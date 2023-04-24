package com.kyowon.sms.wells.web.service.visit.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0059M01 - 설치 위치 상세 관리
 * </pre>
 *
 * @author gs.piit129 천영화
 * @since 2022.12.08
 */
public class WsnbInstallLocationMgtDto {

    @ApiModel(value = "WsnbIstLctDtlMngtDto-SearchReq")
    public record SearchReq(
        String mngtDvCd, /* 관리구분코드 */
        String ogId, /*서비스센터ID */
        String egerId, /* 엔지니어ID */
        String rgsnYn, /* 퇴사여부 */
        String istDtTo, /* 설치일자 To */
        String istDtFrom, /* 설치일자 From */
        String pdGrpCd, /* 상위상품그룹코드 */
        String pdCd, /* 하위상품그룹코드 */
        String cstDvCd, /* 고객구분코드 */
        String cstNm, /* 고객 명 */
        String cntrNo /* 계약번호 */
    ) {}

    @ApiModel(value = "WsnbIstLctDtlMngtDto-SearchRes")
    public record SearchRes(
        String istDt, /* 설치일자 */
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String custNm, /* 고객명 */
        String sellTpNm, /* 유형명 */
        String basePdCd, /* 상품코드 */
        String sapMapCd, /* SAP코드 */
        String pdNm, /* 상품명 */
        String locaraTno, /* 전화번호1 */
        String exnoEncr, /* 전화번호2 */
        String idvTno, /* 전화번호3 */
        String cralLocaraTno, /* 휴대폰번호1 */
        String mexnoEncr, /* 휴대폰번호2 */
        String cralIdvTno, /* 휴대폰번호3 */
        String zip, /* 우편번호 */
        String adr, /* 주소 */
        String istLctDtlCn, /* 설치위치상세 */
        String ogNm, /* 소속센터 */
        String wkPrtnrNo, /* 등록자사번 */
        String prtnrKnm, /* 등록자명 */
        String regDtm /* 최초등록일자 */
    ) {}

    @ApiModel(value = "WsnbIstLctDtlMngtDto-CreateReq")
    public record CreateReq(
        @NotBlank
        String cntrNo, /* 계약번호 */
        @NotBlank
        String cntrSn, /* 계약일련번호 */
        @NotBlank
        String dtlsn, /* 상세일련번호 */
        String istLctDtlCn, /* 설치위치상세 */
        String wkPrtnrNo /* 작업파트너번호 */
    ) {}

    @ApiModel(value = "WsnbIstLctDtlMngtDto-FindProductRes")
    public record FindProductRes(
        String codeId,
        String codeName,
        String abbrName,
        String pdGrpCd
    ) {}

    @ApiModel(value = "WsnbIstLctDtlMngtDto-FindEngineerRes")
    public record FindEngineerRes(
        String ogTpCd,
        String prtnrNo,
        String prtnrKnm,
        String cltnDt,
        String ogId,
        String ogCd,
        String ogNm
    ) {}

    @ApiModel(value = "WsnbIstLctDtlMngtDto-FindCenterRes")
    public record FindCenterRes(
        String ogTpCd,
        String ogId,
        String ogCd,
        String ogNm,
        String hgrOgId
    ) {}

}
