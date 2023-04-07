package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;

public class WdcdMarketableSecuritiesExcdMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 대상자 조회결과
    @ApiModel(value = "WdcdMarketableSecuritiesExcdMgtDto-AdjustObjectReq")
    public record AdjustObjectReq(
        String baseYM
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 대상자 조회결과
    @ApiModel(value = "WdcdMarketableSecuritiesExcdMgtDto-AdjustObjectRes")
    public record AdjustObjectRes(
        String cardNum,
        String authDate,
        String ogTpNm,
        String cardAprno,
        String statNm,
        String requestAmount,
        String forTrdAmt,
        String cdcoCd,
        String useAmt,
        String adjOgNm,
        String useClsNm,
        String memoYn,
        String adjCrcdnoEncr,
        String opcsCardUseIzId,
        String mrcTobzCd,
        String mrcTobzNm,
        String mrcAdrCn,
        String mrscAccYn,
        String dtaDlYn,
        String opcsAdjSmryDvCd,
        String ojApyCn,
        String usrSmryCn,
        String adjYn,
        String mscrWhtxCfdcApnFileIdm,
        String opcsAdjExcdYn
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) /최종 원천세 정산 대상자
    @ApiModel(value = "WdcdMarketableSecuritiesExcdMgtDto-WithholdingTaxAdjustReq")
    public record WithholdingTaxAdjustReq(
        String baseYM

    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) /최종 원천세 정산 대상자
    @ApiModel(value = "WdcdMarketableSecuritiesExcdMgtDto-WithholdingTaxAdjustRes")
    public record WithholdingTaxAdjustRes(
        String cardNum,
        String authDate,
        String ogTpNm,
        String cardAprno,
        String statNm,
        String requestAmount,
        String forTrdAmt,
        String cdcoCd,
        String useAmt,
        String adjOgNm,
        String useClsNm,
        String memoYn,
        String adjCrcdnoEncr,
        String opcsCardUseIzId,
        String mrcTobzCd,
        String mrcTobzNm,
        String mrcAdrCn,
        String mrscAccYn,
        String dtaDlYn,
        String opcsAdjSmryDvCd,
        String ojApyCn,
        String usrSmryCn,
        String adjYn,
        String mscrWhtxCfdcApnFileId
    ) {

    }

    @ApiModel(value = "WdcdMarketableSecuritiesExcdMgtDto-EditReq")
    public record EditReq(

    ) {
    }
}
