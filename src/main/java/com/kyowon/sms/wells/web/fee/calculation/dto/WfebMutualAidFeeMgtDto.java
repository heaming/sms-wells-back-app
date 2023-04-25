package com.kyowon.sms.wells.web.fee.calculation.dto;

public class WfebMutualAidFeeMgtDto {
    public record SearchAidReq(
        String baseYm,
        String strtDt,
        String endDt,
        String pdCd
    ) {}

    public record SaveReq(
        String baseYm
    ) {}


    public record AidIndividual(
        String baseYm,
        String cntrStat,
        String ogCd,
        String PrtnrNo,
        String PrtnrKnm,
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
        String baseYm,
        String ogId3,
        String ogId2,
        String ogId1,
        String prtnrNo
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
