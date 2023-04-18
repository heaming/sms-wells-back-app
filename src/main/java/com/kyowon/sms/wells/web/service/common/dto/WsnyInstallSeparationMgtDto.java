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
        String svBizMclsfCd, /*서비스업무중분류코드*/
        String svBizDclsfCd, /*서비스업무세분류코드*/
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
        String svBizMclsfCd,
        @NotBlank
        String svBizDclsfCd,
        @NotBlank
        String izSn,
        String apyStrtdt,
        String apyEnddt,
        String wkCsAmt,
        String recapSvYn,
        String rmkCn
    ){}

    @ApiModel(value = "WsnyInstallSeparationMgtDto-DeleteReq")
    public record DeleteReq(
        @NotBlank
        String sellTpCd,
        @NotBlank
        String pdCd,
        @NotBlank
        String svBizMclsfCd,
        @NotBlank
        String svBizDclsfCd,
        @NotBlank
        String izSn
    ){}
}
