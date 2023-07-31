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
        String perfYm,
        @NotBlank
        String ogTpCd,
        String prtnrNo,
        @NotBlank
        String feeTcntDvCd,
        String strtPdCd,
        String endPdCd,
        @NotBlank
        String strtVstDt,
        @NotBlank
        String endVstDt
    ) {}

    @ApiModel(value = "WfeaBsFeeMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String perfYm,
        @NotBlank
        String ogTpCd,
        @NotBlank
        String feeTcntDvCd,
        @NotBlank
        String perfAgrgCrtDvCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    @ApiModel(value = "WfeaBsFeeMgtDto-SearchRes")
    public record SearchRes(
        String prtnrNo,
        String ogCd,
        String prtnrKnm,
        String cntrNo, /*계약번호*/
        String basePdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String svFeePdDvCd, /*bs상품군*/
        String svFeePdDvNm, /*bs상품군명*/
        int svFeeBaseAmt, /*bs기본수수료*/
        int feeCalcAmt, /*방문수수료*/
        String svBizDclsfCd, /*작업유형코드*/
        String vstRglvlGdCd, /*방문급지코드*/
        String vstRglvlGdNm, /*방문급지명*/
        String sellTpNm, /*판매구분*/
        String uswyNm, /*용도구분*/
        String prrVstYn, /*사전방문여부*/
        String vstDuedt, /*방문예정일자*/
        String wkExcnDt, /*방문일자*/
        String canYn /*취소여부*/
    ) {}
}
