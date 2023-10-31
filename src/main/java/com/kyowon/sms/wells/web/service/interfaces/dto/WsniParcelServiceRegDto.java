package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsniParcelServiceRegDto {

    @ApiModel(value = "WsniParcelServiceRegDto-SearchAddressCleansingReq")
    @Builder
    public record SearchAddressCleansingReq(
        String tokenNum, /* 토큰번호 */
        String address /* 주소 */

    ) {}

    @ApiModel(value = "WsniParcelServiceRegDto-SearchAddressCleansingReq")
    @Builder
    public record SearchAddressCleansingRes(
        String resultCd, /* 결과코드 */

        String clsfCd, /* 도착지 코드 */

        String subclsfCd, /* 도착지 서브 코드 */

        String clsfAddr, /* 주소 약칭 */

        String clldlvbrAnnm, /* 배송집배점 명 */

        String clldlvemPnm, /* 배송SM명 */

        String clldlvempnickNm, /* SM분류코드 */

        String rspsDiv, /* 권역 구분 */

        String p2pCd /* P2P코드 */
    ) {}

    @ApiModel(value = "WsniParcelServiceRegDto-SearchOriginInvoiceReq")
    @Builder
    public record SearchOriginInvoiceReq(
        String custNo, /* 주소 약칭 */

        String cntrSn, /* 주소 약칭 */

        String reqdvCd /* SM분류코드 */
    ) {}

    @ApiModel(value = "WsniParcelServiceRegDto-SearchOriginInvoiceRes")
    @Builder
    public record SearchOriginInvoiceRes(
        String cntrNo,

        String cntrSn,

        String sendAvailYn,

        String oriinvcNo, /* 배송SM명 */

        String sendrNm, /* 배송SM명 */

        String sendrtelNo1, /* SM분류코드 */

        String sendrtelNo2, /* 권역 구분 */

        String sendrtelNo3, /* P2P코드 */

        String sendrcellNo1, /* P2P코드 */

        String sendrcellNo2, /* 결과코드 */

        String sendrcellNo3, /* 도착지 코드 */

        String sendrZipNo, /* 도착지 서브 코드 */

        String sendrAddr, /* 주소 약칭 */

        String sendrAddrDtl, /* 배송집배점 명 */

        String gdsCd, /* P2P코드 */

        String gdsNm, /* P2P코드 */

        String gdsQty, /* P2P코드 */

        String rcptYmd, /* 도착지 서브 코드 */

        String asIstOjNo /* AS 설치대상번호 */
    ) {}

    @ApiModel(value = "WsniParcelServiceRegDto-RegistParcelServiceReq")
    @Builder
    public record RegistParcelServiceReq(

        @JsonProperty("REQ_DV_CD")
        String reqdvCd, /* SM분류코드 */

        @JsonProperty("CNTR_NO")
        String custNo, /* 주소 약칭 */
        @JsonProperty("CNTR_SN")
        String cntrSn, /* 주소 약칭 */
        @JsonProperty("SENDR_ADDR")
        String sendrAddr, /* 주소 약칭 */
        @JsonProperty("SENDR_ADDR_DTL")
        String sendrAddrDtl, /* 배송집배점 명 */
        @JsonProperty("SENDR_TEL_NO1")
        String sendrtelNo1, /* SM분류코드 */
        @JsonProperty("SENDR_TEL_NO2")
        String sendrtelNo2, /* 권역 구분 */
        @JsonProperty("SENDR_TEL_NO3")
        String sendrtelNo3, /* P2P코드 */
        @JsonProperty("SENDR_CEL_NO1")
        String sendrcellNo1, /* P2P코드 */
        @JsonProperty("SENDR_CEL_NO2")
        String sendrcellNo2, /* 결과코드 */
        @JsonProperty("SENDR_CEL_NO3")
        String sendrcellNo3, /* 도착지 코드 */
        @JsonProperty("SENDR_ZIP_NO")
        String sendrZipNo, /* 도착지 서브 코드 */
        @JsonProperty("GDS_CD")
        String gdsCd, /* P2P코드 */
        @JsonProperty("GDS_NM")
        String gdsNm, /* P2P코드 */
        @JsonProperty("GDS_QTY")
        String gdsQty, /* P2P코드 */
        @JsonProperty("SENDR_NM")
        String sendrNm, /* P2P코드 */
        @JsonProperty("GDS_AMT")
        String gdsAmt, /* P2P코드 */
        @JsonProperty("RECP_ID")
        String recpId, /* 접수자 사번 */
        @JsonProperty("RECP_OG_TP_CD")
        String recpOgTpCd  /* 접수자 조직유형코드 */

    ) {}


    @ApiModel(value = "WsniParcelServiceRegDto-RegistParcelResult")
    @Builder
    public record RegistParcelResult(

        String resultCd, /* 결과코드 */

        String resultDetail /* 결과메시지 */
    ){}

}
