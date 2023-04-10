package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;

public class WdcdCalculateSecuritiesExceptionMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdCalculateSecuritiesExceptionMgtDto-CodeRes")
    public record CodeRes(
        String bldCd,
        String bldNm
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdCalculateSecuritiesExceptionMgtDto-SubjectReq")
    public record SubjectReq(
        String pstnDvCd,
        String dstOjpsNm,
        String dgr3LevlOgNm,
        String bldCd
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdCalculateSecuritiesExceptionMgtDto-SubjectRes")
    public record SubjectRes(
        String dgr2LevlOgNm,
        String ogNm,
        String bldNm,
        String dstOjOgTpCd,     /*배분대상조직유형코드*/
        String dstOjpsNm,            /*성명*/
        String signrPrtnrNo,        /*번호*/
        String pstnDvCd,          /*직책*/
        String useAmt          /*금액*/
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdCalculateSecuritiesExceptionMgtDto-finalWithholdingTaxSettlementReq")
    public record finalWithholdingTaxSettlementReq(
        String pstnDvCd,
        String dstOjpsNm,
        String dgr3LevlOgNm,
        String bldCd
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdCalculateSecuritiesExceptionMgtDto-finalWithholdingTaxSettlementRes")
    public record finalWithholdingTaxSettlementRes(
        String ogNm,
        String bldNm,
        String dstOjOgTpCd,  /*배분대상조직유형코드*/
        String dstOjpsNm,     /*성명*/
        String signrPrtnrNo,  /*번호*/
        String pstnDvCd,      /*직책*/
        String adjCnfmAmt,   /*운영비 확정금액*/
        String dstWhtx      /*원천세*/
    ) {

    }
}
