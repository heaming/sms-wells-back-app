package com.kyowon.sms.wells.web.service.interfaces.dto;

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

        String rcptYmd /* 도착지 서브 코드 */
    ) {}

    @ApiModel(value = "WsniParcelServiceRegDto-RegistParcelServiceReq")
    @Builder
    public record RegistParcelServiceReq(
        String reqdvCd, /* SM분류코드 */

        String custNo, /* 주소 약칭 */

        String cntrSn, /* 주소 약칭 */

        String sendrAddr, /* 주소 약칭 */

        String sendrAddrDtl, /* 배송집배점 명 */

        String sendrtelNo1, /* SM분류코드 */

        String sendrtelNo2, /* 권역 구분 */

        String sendrtelNo3, /* P2P코드 */

        String sendrcellNo1, /* P2P코드 */

        String sendrcellNo2, /* 결과코드 */

        String sendrcellNo3, /* 도착지 코드 */

        String sendrZipNo, /* 도착지 서브 코드 */

        String gdsCd, /* P2P코드 */

        String gdsNm, /* P2P코드 */

        String gdsQty, /* P2P코드 */

        String sendrNm, /* P2P코드 */

        String gdsAmt, /* P2P코드 */
        String asnNo /* P2P코드 */
    ) {}

}
