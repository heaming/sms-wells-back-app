package com.kyowon.sms.wells.web.fee.aggregate.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * BS 실적 및 수수료
 * </pre>
 *
 * @author gs.piit56
 * @since 2023.05.22
 */
public class WfeaBsFeeMgtDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // BS 실적 및 수수료 Search Request Dto
    @ApiModel(value = "WfeaBsFeeMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String perfYm, /*실적년월*/
        @NotBlank
        String ogTpCd, /*조직유형코드*/
        String prtnrNo, /*파트너번호*/
        @NotBlank
        String feeTcntDvCd, /*수수료차수구분코드*/
        String strtPdCd, /*시작상품코드*/
        String endPdCd, /*종료상품코드*/
        @NotBlank
        String strtVstDt, /*시작방문일자*/
        @NotBlank
        String endVstDt /*종료방문일자*/
    ) {}

    @ApiModel(value = "WfeaBsFeeMgtDto-SearchCheckReq")
    public record SearchCheckReq(
        @NotBlank
        String perfYm, /*실적년월*/
        @NotBlank
        String ogTpCd, /*조직유형코드*/
        @NotBlank
        String feeTcntDvCd, /*수수료차수구분코드*/
        @NotBlank
        String perfAgrgCrtDvCd /*실적집계생성구분코드*/
    ) {}

    @ApiModel(value = "WfeaBsFeeMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String perfYm, /*실적년월*/
        @NotBlank
        String ogTpCd, /*조직유형코드*/
        @NotBlank
        String feeTcntDvCd, /*수수료차수구분코드*/
        @NotBlank
        String perfAgrgCrtDvCd /*실적집계생성구분코드*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WfeaBsFeeMgtDto-SearchRes")
    public record SearchRes(
        String prtnrNo, /*파트너번호*/
        String ogCd, /*조직코드*/
        String prtnrKnm, /*파트너명*/
        String cntrNo, /*계약번호*/
        String bfOrdNo, /*이전계약번호*/
        String basePdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String svFeePdDvCd, /*bs상품군*/
        int svFeeBaseAmt, /*bs기본수수료*/
        int feeUprcAmt, /*집계수수료*/
        int feeCalcAmt, /*방문수수료*/
        String svBizDclsfCd, /*작업유형코드*/
        String vstRglvlGdCd, /*방문급지코드*/
        String vstRglvlGdNm, /*방문급지명*/
        String sellTpCd, /*판매구분*/
        String uswyNm, /*용도구분*/
        String prrVstYn, /*사전방문여부*/
        String vstDuedt, /*방문예정일자*/
        String wkExcnDt, /*방문일자*/
        String canYn /*취소여부*/
    ) {}

    @ApiModel(value = "WfeaBsFeeMgtDto-SearchCheckRes")
    public record SearchCheckRes(
        String ogTpCd /*조직유형코드*/
    ) {}
}
