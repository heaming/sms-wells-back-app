package com.kyowon.sms.wells.web.fee.control.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfedManagerVisitFeeDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  Search Request Dto
    @Builder
    @ApiModel("WfedManagerVisitFeeDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm, /*기준년월*/
        @NotBlank
        String inqrDv, /*조회구분*/

        String ogLevlDvCd1, /* 조직레벨1 */
        String ogLevlDvCd2, /* 조직레벨2 */
        String ogLevlDvCd3, /* 조직레벨3 */
        String prtnrNo /* 파트너번호 */
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  Search Result Dto
    @ApiModel("WfedManagerVisitFeeDto-SearchRes")
    public record SearchRes(

        String dgr1LevlOgId, /*총괄단*/
        String dgr2LevlOgId, /*지역단*/
        String dgr3LevlOgId, /*지점*/
        String prtnrNo, /* 파트너번호 */
        String prtnrKnm, /* 파트너명 */
        String cntrNo, /*계약번호*/
        String cntrCstNo, /*고객번호*/
        String cstKnm, /*고객명*/
        String basePdCd, /*상품코드*/
        String pdNm, /*상품명*/
        String svFeePdDvCd, /*bs상품군*/
        int svFeeBaseAmt, /*bs기본수수료*/
        int feeUprcAmt, /*집계수수료*/
        int feeCalcAmt, /*방문수수료*/
        String vstRglvlGdCd, /*방문급지*/
        String vstRglvlGdNm, /*방문급지*/
        String wkExcnDt, /*방문일자*/
        String canYn /* 취소여부 */

    ) {}
}
