package com.kyowon.sms.wells.web.service.common.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnyInstallSeparationMgtDto {
    @ApiModel(value = "WsnyInstallSeparationMgtDto-SearchReq")
    public record SearchReq(
        String pdCd, /*상품코드*/
        String pdGr,
        String apyMtrChk

    ){}

    @ApiModel(value = "WsnyInstallSeparationMgtDto-SearchRes")
    public record SearchRes(
        String sellTpCd, /*판매유형코드*/
        String pdCd, /*상품코드*/
        String sepIstCsAtcCd, /*분리설치비용항목코드*/
        String sepIstCsDtlCd, /*분리설치비용상세코드*/
        String izSn, /*내역일련번호*/
        String apyStrtdt, /*적용시작일자*/
        String apyEnddt, /*적용종료일자*/
        String wkCsAmt, /*작업비용금액*/
        String recapSvYn, /*유상서비스여부*/
        String rmkCn /*비고내용*/
    ){}

    @ApiModel("WsnyInstallSeparationMgtDto-FindPdRes")
    public record FindProductsRes(
        String codeId,
        String codeName
    ){}

    @ApiModel(value = "WsnyInstallSeparationMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String sellTpCd,
        @NotBlank
        String pdCd,
        @NotBlank
        String sepIstCsAtcCd,
        @NotBlank
        String sepIstCsDtlCd,
        @NotBlank
        String izSn,
        String apyStrtdt,
        String apyEnddt,
        String wkCsAmt,
        String recapSvYn,
        String rmkCn
    ){}

    @ApiModel(value = "WsnyInstallSeparationMgtDto-RemoveReq")
    public record RemoveReq(
        @NotBlank
        String sellTpCd,
        @NotBlank
        String pdCd,
        @NotBlank
        String sepIstCsAtcCd,
        @NotBlank
        String sepIstCsDtlCd,
        @NotBlank
        String izSn
    ){}
}
