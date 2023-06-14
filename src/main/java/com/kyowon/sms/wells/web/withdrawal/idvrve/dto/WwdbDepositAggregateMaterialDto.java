package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import io.swagger.annotations.ApiModel;

public class WwdbDepositAggregateMaterialDto {
    @ApiModel("WwdbDepositAggregateMaterialDto-SearchDepositAggregateMaterialReq")
    public record SearchDepositAggregateMaterialReq(
        String dvCd, // 업무구분
        String searchDt, // 기준년월
        String dpTpCd // 입금유형 (TODO: 추가된 조회 컬럼)
    ) {

    }
    @ApiModel("WwdbDepositAggregateMaterialDto-SearchDepositAggregateMaterialRes")
    public record SearchDepositAggregateMaterialRes(
        String dvNm,
        String dpTpCd,
        String sellTpCd,
        String totRowNum,
        String spayAmt,
        String rtlsAmt,
        String mbmsAmt,
        String coIstAmt,
        String mngtAmt,
        String rglrAmt,
        String filtAmt,
        String totAmt
        //        String totRat
    ) {

    }

    @ApiModel("WwdbDepositAggregateMaterialDto-SearchDepositAggregateMaterialTotalRes")
    public record SearchDepositAggregateMaterialTotalRes(
        String totSpay,
        String totRtls,
        String totMbms,
        String totCoIst,
        String totMngt,
        String totRglr,
        String totFilt,
        String tot,
        String spayPer,
        String rtlsPer,
        String mbmsPer,
        String coIstPer,
        String mngtPer,
        String rglrPer,
        String filtPer
    ) {

    }

}
