package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProductOutOfStorageSaveProductDvo;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * MD 상품 출고관리
 * </pre>
 *
 * @author junggheejin
 * @since 2023.09.24
 */
public class WsnaMdProductOutOfStorageMgtDto {

    @ApiModel(value = "WsnaMdProductOutOfStorageMgtDto-SearchReq")
    public record SearchReq(
        String startDt, // 계약시작일자

        String endDt, // 계약종료일자

        String findGb, // 조회구분

        String firstSppGb, // 첫배송여부구분

        String selCnt, // 조회제한건수

        String wkStartDt, // 작업시작일자

        String wkEndDt, // 작업종료일자

        String prtnrBzsCd, // 파트너업체코드

        String cntrDtlNo, // 계약상세번호

        String rcgvpKnm, // 고객명

        String serialNo, // 시리얼번호

        String cralLocaraTno, // 휴대지역전화번호

        String mexnoEncr, // 휴대전화국번호암호화

        String cralIdvTno // 휴대개별전화번호

    ) {}
    @ApiModel(value = "WsnaMdProductOutOfStorageMgtDto-SearchRes")
    public record SearchRes(
        String cstSvAsnNo,

        String cntrNo,

        String cntrSn,

        String cntrCstNo, // 계약자 고객번호

        String sellTpCd,

        String sellTpNm,

        String svBizHclsfCd,

        String svBizDclsfCd,

        String svBizDclsfNm,

        String wkPrgsStatCd,

        String wkPrgsStatNm,

        String istDt,

        String reqdDt,

        String rcgvpKnm,

        String cntrRcpFshDtm, //CONT_DT 계약일자

        String newAdrZip,

        String rnadr, //주소

        String rdadr, //주소 상세

        String cralLocaraTno, //휴대지역전화번호(휴대폰번호)

        String mexnoEncr, //휴대전화국번호암호화(휴대폰번호)

        String cralIdvTno, //휴대개별전화번호(휴대폰번호)

        String locaraTno, //지역전화번호 (전화번호)

        String exnoEncr, //전화국번호암호화(전화번호)

        String idvTno, //개별전화번호(전화번호)

        String rsgFshDt,

        String ostrCnfmDt,

        String ogId,

        String ogTpCd,

        String prtnrNo,

        String pdctPdCd,

        /* MD */
        String prtnrBzsNm,

        String pcsvCompDv,

        String pcsvCompNm,

        String sppIvcNo,

        String sppBzsPdId,

        /* 창고 */
        String wkWareNo,

        String wareMngtPrtnrNo,

        /* 상품 */

        int partCnt,

        String partCd1,

        String partNm1,

        String partQty1,

        String partCd2,

        String partNm2,

        String partQty2,

        String partCd3,

        String partNm3,

        String partQty3,

        String partCd4,

        String partNm4,

        String partQty4,

        String partCd5,

        String partNm5,

        String partQty5,

        String partCd6,

        String partNm6,

        String partQty6,

        String partCd7,

        String partNm7,

        String partQty7,

        String partCd8,

        String partNm8,

        String partQty8,

        String partCd9,

        String partNm9,

        String partQty9,

        String partCd10,

        String partNm10,

        String partQty10

    ) {}

    @ApiModel(value = "WsnaMdProductOutOfStorageMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String cstSvAsnNo,

        @NotBlank
        String cntrNo,

        @NotBlank
        String cntrSn,

        @NotBlank
        String svBizDclsfCd,

        @NotBlank
        String svBizHclsfCd,

        @NotBlank
        String prtnrNo,

        @NotBlank
        String ogTpCd,

        @NotBlank
        String ogId,

        @NotBlank
        String pdctPdCd,

        @NotBlank
        String wkWareNo,

        String sellTpCd,

        String istDt,

        List<WsnaMdProductOutOfStorageSaveProductDvo> products
    ) {}
    @ApiModel(value = "WsnaMdProductOutOfStorageMgtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String cstSvAsnNo,

        @NotBlank
        String cntrNo,

        @NotBlank
        String cntrSn,

        @NotBlank
        String svBizHclsfCd
    ) {}
}
