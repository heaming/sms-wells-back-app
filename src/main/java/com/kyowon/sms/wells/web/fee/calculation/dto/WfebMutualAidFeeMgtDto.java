package com.kyowon.sms.wells.web.fee.calculation.dto;

import javax.validation.constraints.NotBlank;

public class WfebMutualAidFeeMgtDto {
    public record SearchAidReq(
        @NotBlank
        String baseYm,
        String strtDt,
        String endDt,
        String pdCd
    ) {}

    public record CreateAidReq(
        @NotBlank
        String baseYm
    ) {}


    public record AidIndividual(
        String baseYm,
        String cntrStat,
        String ogCd,
        String prtnrNo,
        String prtnrKnm,
        String cdCntn,
        String brmgrPrtnrNo,
        String welsCntrNo,
        String pdNm,
        String cntrPdStrtdt,
        String lifCntrNo,
        String lifPdNm,
        String rcpdt,
        String cntrDt,
        String canDt,
        Integer sellFee,
        String totDsbOjDvCd,
        String lifCntrOcTn,
        Integer slOcAcuAmt,
        Integer dpAcuAmt,
        String flpymTn,
        Integer preAmtSum,
        Integer curAmt,
        String cnfmYn,
        String feeDsbYm,
        String feeRedfYm
    ) {}

    public record AidGroup(
        String baseYm,
        String cntrStat,
        String ogCd,
        String brmgrPrtnrNo,
        String prtnrKnm,
        String cdCntn,
        Integer brchCt,
        Integer brchAmt,
        String feeDsbYm,
        String feeRedfYm
    ) {}

    public record SearchAidOrderReq(
        @NotBlank
        String baseYm,
        String cntrStat, // 실적구분
        String alncCd, // 제휴구분
        String rsbDvCd, // 직책유행
        String dgr2LevlOgId, // 조직레벨 1~3
        String dgr3LevlOgId,
        String dgr4LevlOgId,
        String prtnrNo, // 번호
        String prtnrNm
    ) {}
    public record AidOrder(
        String alncNm,
        String welsCntrNo,
        String lifCntrNo,
        String cstKnm,
        String ogCd,
        String prtnrNo,
        String prtnrKnm,
        String cdCntn,
        String brmgrPrtnrNo,
        String rcpdt,
        String cntrDt,
        String dpDt,
        String wdwlDt,
        String canDt,
        String feeDsbYm,
        String cntrStat
    ) {}

}
