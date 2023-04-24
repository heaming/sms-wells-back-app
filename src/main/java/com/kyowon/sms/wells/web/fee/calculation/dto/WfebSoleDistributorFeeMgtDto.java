package com.kyowon.sms.wells.web.fee.calculation.dto;

public class WfebSoleDistributorFeeMgtDto {
    public record BaseReq(
        String strtYm,
        String endYm,
        String perfYm,
        String cancelStrtYm,
        String cancelEndYm
    ) {}

    public record Performance(
        String hdqOgId,
        String hdqOgNm,
        String sellPrtnrNo,
        String sellPrtnrNm,
        String cntrNo,
        String cntrSn,
        String pdCd,
        String pdNm,
        String cntrCstNo,
        String cntrCstNm,
        String dscDvCd,
        String dscTpCd,
        String pmotCd,
        String relPdCd,
        String uswyDvCd,
        String mngtTpCd,
        String bfsvcPrdCd,
        String rcpDt,
        String slDt,
        String canDt,
        Integer ackmtPerfAmt,
        String ackmtPerfCt,
        Integer feeAmt
    ) {}

    public record Fee(
        String ogId,
        String ogNm,
        String prtnrNo,
        String prtnrNm,
        Integer w050001Amt,
        Integer w050002Amt,
        Integer w050003Amt,
        Integer w050004Amt,
        Integer w050005Amt,
        Integer feeSumAmt,
        Integer d01Amt,
        Integer d08Amt,
        Integer ddtnSumAmt,
        Integer acpyAmt
    ) {}

}
