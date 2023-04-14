package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;

public class WdcdMarketableSecuritieExceptionMgttDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-FindCodeRes")
    public record FindCodeRes(
        String bldCd,
        String bldNm
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-SearchSubjectReq")
    public record SearchSubjectReq(
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
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-SearchSubjectRes")
    public record SearchSubjectRes(
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
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-SearchFinalSettlementReq")
    public record SearchFinalSettlementReq(
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
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-SearchFinalSettlementRes")
    public record SearchFinalSettlementRes(
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
