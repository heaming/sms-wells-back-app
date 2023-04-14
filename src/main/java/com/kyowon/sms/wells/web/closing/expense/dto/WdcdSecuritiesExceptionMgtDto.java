package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;

public class WdcdSecuritiesExceptionMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 대상자 조회결과
    @ApiModel(value = "WdcdSecuritiesExceptionMgtDto-SearchAdjustObjectReq")
    public record SearchAdjustObjectReq(
        String baseYm,
        String entrpDvCd,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String dgr4LevlOgId
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 대상자 조회결과
    @ApiModel(value = "WdcdSecuritiesExceptionMgtDto-SearchAdjustObjectRes")
    public record SearchAdjustObjectRes(
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
    @ApiModel(value = "WdcdSecuritiesExceptionMgtDto-SearchWithholdingTaxAdjustReq")
    public record SearchWithholdingTaxAdjustReq(
        String baseYM,
        String entrpDvCd,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String dgr4LevlOgId
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) /최종 원천세 정산 대상자
    @ApiModel(value = "WdcdSecuritiesExceptionMgtDto-SearchWithholdingTaxAdjustRes")
    public record SearchWithholdingTaxAdjustRes(
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

    @ApiModel(value = "WdcdSecuritiesExceptionMgtDto-EditReq")
    public record EditReq(

    ) {
    }
}
