package com.kyowon.sms.wells.web.deduction.adsb.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdebAgainDisbursementDetailDto {

    @Builder
    @ApiModel("WdebAgainDisbursementDetailDto-SearchAgainDisbursementReq")
    public record SearchAgainDisbursementReq(
        @NotBlank
        String pstnDv, // 직급구분
        String prtnrNo, // 파트너번호
        String ogTpCd // 조직유형코드

    ) {}

    @ApiModel("WdebAgainDisbursementDetailDto-SearchAgainDisbursementRes")
    public record SearchAgainDisbursementRes(
        String redfAdsbOcYm, /*발생월(되물림재지급발생년월)*/
        String perfYm, /*실적월(실적년월)*/
        String perfDvCd, /*실적구분코드*/
        String perfDvNm, /*구분(직급구분코드)*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String cntrNoSn, /*계약상세번호*/
        String cstKnm, /*고객명(고객한글명)*/
        String adsbAmt, /*재지급액*/
        String redfAdsbTpCd, /*적요(되물림재지급유형코드)*/
        String sellTpCd, /*판매유형코드*/
        String sellTpNm /*상품(판매유형코드)*/
    ) {}
}
