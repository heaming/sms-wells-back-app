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

        String sellTpCd, /*판매유형코드*/

        String sellTpNm, /*판매유형코드명 */

        String svBizHclsfCd, /*서비스업무대분류코드*/

        String svBizDclsfCd, /*서비스업무세분류코드*/

        String svBizDclsfNm, /* 서비스업무세분류명 */

        String wkPrgsStatCd, /*작업진행상태코드*/

        String wkPrgsStatNm, /*작업진행상태코드명*/

        String istDt,  /*설치일자*/

        String reqdDt, /*철거일자*/

        String rcgvpKnm, /*고객명*/

        String cntrRcpFshDtm, //CONT_DT 계약일자

        String newAdrZip, /* 우편번호 */

        String rnadr, //주소

        String rdadr, //주소 상세

        String cralLocaraTno, //휴대지역전화번호(휴대폰번호)

        String mexnoEncr, //휴대전화국번호암호화(휴대폰번호)

        String cralIdvTno, //휴대개별전화번호(휴대폰번호)

        String locaraTno, //지역전화번호 (전화번호)

        String exnoEncr, //전화국번호암호화(전화번호)

        String idvTno, //개별전화번호(전화번호)

        String rsgFshDt, /*취소일자*/

        String ostrCnfmDt,

        String ogId, /* 조직ID */

        String ogTpCd, /* 조직유형코드 */

        String prtnrNo, /* 파트너번호 */

        String pdctPdCd, /* 제품상품코드 */

        /* MD */
        String prtnrBzsNm, /* 파트너업체명 */

        String pcsvCompDv, /*택배사구분*/

        String pcsvCompNm, /*택배사구분명 */

        String sppIvcNo, /*송장번호*/

        String sppBzsPdId, /*SR번호*/

        /* 창고 */
        String wkWareNo, /*작업창고번호*/

        String wareMngtPrtnrNo,  /* 창고관리파트너번호 */

        /* 상품 */

        int partCnt,  /* 투입부품모델수 */

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
        String cstSvAsnNo,  /*고객요청번호*/

        @NotBlank
        String cntrNo, /*계약번호*/

        @NotBlank
        String cntrSn, /*계약일련번호*/

        @NotBlank
        String svBizDclsfCd, /*서비스업무세분류코드*/

        @NotBlank
        String svBizHclsfCd,  /*서비스업무대분류코드*/

        @NotBlank
        String prtnrNo, /* 파트너번호 */

        @NotBlank
        String ogTpCd, /* 조직유형코드 */

        @NotBlank
        String ogId, /* 조직ID */

        @NotBlank
        String pdctPdCd, /* 제품상품코드 */

        @NotBlank
        String wkWareNo, /*작업창고번호*/

        String sellTpCd, /*판매유형코드*/

        String istDt,   /*설치일자*/

        List<WsnaMdProductOutOfStorageSaveProductDvo> products
    ) {}
    @ApiModel(value = "WsnaMdProductOutOfStorageMgtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String cstSvAsnNo,  /*고객요청번호*/

        @NotBlank
        String cntrNo, /*계약번호*/

        @NotBlank
        String cntrSn, /*계약일련번호*/

        @NotBlank
        String svBizHclsfCd /*서비스업무대분류코드*/
    ) {}
}
