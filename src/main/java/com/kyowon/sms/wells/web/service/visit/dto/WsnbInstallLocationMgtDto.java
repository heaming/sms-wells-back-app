package com.kyowon.sms.wells.web.service.visit.dto;

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
        String hgrPdGrop, /* 상위상품그룹코드 */
        String lorPdGrop, /* 하위상품그룹코드 */
        String cstDvCd, /* 고객구분코드 */
        String cstNm, /* 고객 명 */
        String cstNo /* 고객 번호 */
    ) {}

    @ApiModel(value = "WsnbIstLctDtlMngtDto-SearchRes")
    public record SearchRes(
        String istDt, /* 설치일자 */
        String cntrNo, /* 계약번호 */
        String custNm, /* 고객명 */
        String sellTpCd, /* 유형코드 */
        String pdNm, /* 상품명 */
        String telNo, /* 전화번호 */
        String cralTelNo, /* 휴대폰번호 */
        String zip, /* 우편번호 */
        String adr, /* 주소 */
        String istLctDtlCn, /* 설치위치상세 */
        String ogNm, /* 소속센터 */
        String prtnrKnm, /* 등록자명 */
        String fstRgstUsrId, /* 최초등록자사번 */
        String fstRgstDtm /* 최초등록일자 */
    ) {}

    @ApiModel(value = "WsnbIstLctDtlMngtDto-CreateReq")
    public record CreateReq(
        String cntrNo, /* 계약번호 */
        String cntrSn, /* 계약일련번호 */
        String dtlsn, /* 상세일련번호 */
        String istLctDtlCn, /* 설치위치상세 */
        String wkPrtnrNo /* 작업파트너번호 */
    ) {}

}
