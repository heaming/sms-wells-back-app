package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;

public class WdcdMarketableSecuritieExceptionMgttDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-FindCodeReq")
    public record FindCodeReq(
        String baseYm,
        String dgr1LevlOgId
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-FindCodeRes")
    public record FindCodeRes(
        String dgr2LevlOgId,  /*2차레벨조직ID*/
        String dgr2LevlOgNm,  /*2차레벨조직명*/
        String hooOgTpCd,     /*조직장조직유형코드*/
        String hooPrtnrNo,     /*조직장파트너번호*/
        String bldCd,           /*빌딩코드*/
        String bldNm           /*빌딩명*/
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-SearchSubjectReq")
    public record SearchSubjectReq(
        String baseYm,
        String rsbDvCd,
        String mainOgId,
        String dgr2LevlOgId,
        String bldCd,
        String subOgTpCd,
        String subPrtnrNo,
        String mainDgr1LevlOgId,
        String mainDgr2LevlOgId,
        String mainDgr3LevlOgId
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-SearchSubjectRes")
    public record SearchSubjectRes(
        String baseYm,               /*(hidden)기준월*/
        String dgr1LevlOgNm,       /*1차레벨조직명-총괄단*/
        String dgr2LevlOgNm,       /*2차레벨조직명-지역단*/
        String dgr1LevlOgId,       /*2차레벨조직명-총괄단*/
        String dgr2LevlOgId,       /*3차레벨조직명-센터*/
        String dgrLevlOgId,          /*(hidden) 조직ID 총괄단*/
        String dgrLevlDgPrtnrNo,    /*(hidden) 파트너 번호 - 총괄단*/
        String ogTpCd,              /*(hidden)2차레벨조직유형코드-지역단*/
        String ogId,                 /*(hidden)정산조직ID*/
        String dstOjOgTpCd,            /*(hidden)배분대상조직유형코드*/
        String prtnrNo,              /*배분대상파트너번호*/
        String prtnrKnm,             /*배분대상자명*/
        String pstnDvCd,            /*(hidden)직급구분코드*/
        String rsbDvCd,             /*(hidden)직책구분코드*/
        String rsbDvNm,           /*직책명*/
        String perfVal,              /*배분대상자실적금액*/
        String dstAmt,             /*배분금액*/
        String dstWhtx,            /*(hidden)배분원천세*/
        String erntx,               /*(hidden)배분소득세*/
        String rsdntx,              /*(hidden)배분주민세*/
        String cardUseAmt,   /*(hidden)카드사용금액*/
        String adjFshDstAmt,  /*(hidden)정산완료배분합계금액*/
        String adjYn,  /*(hidden)정산여부*/
        String bldCd,  /* 빌딩코드 */
        String bldNm  /*빌딩명 */
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
        String bldCd,
        String rsbDvCd,
        String baseYm,
        String adjOgId,
        String opcsAdjNo,
        String adjPrtnrNo /* 정산파트너번호-부모화면에서 리턴 받음*/
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-SearchFinalSettlementRes")
    public record SearchFinalSettlementRes(
        String opcsAdjNo,           /*(hidden)운영비정산번호*/
        String adjOgId,             /*(hidden)정산조직ID*/
        String ogTpCd,              /*(hidden)조직유형코드*/
        String adjPrtnrNo,          /*(hidden)정산파트너번호*/
        String dgr1LevlOgNm,       /*1차레벨조직명*/
        String dgr2LevlOgNm,       /*2차레벨조직명*/
        String dgr1LevlOgId,       /*1차레벨조직명*/
        String dgr2LevlOgId,       /*2차레벨조직명*/
        String dstOjpsNm,           /*배분대상자명*/
        String dstOjOgTpCd,       /*(hidden)배분대상조직유형코드*/
        String dstOjPrtnrNo,       /*배분대상파트너번호*/
        String pstnDvCd,            /*직급구분코드*/
        String rsbDvCd,             /*(hidden)직책구분코드*/
        String dstOjpsPerfAmt,     /*(hidden)배분대상자실적금액*/
        String dstAmt,               /*운영비 정산금액*/
        String dstWhtx,              /*원천세*/
        String erntx,                 /*(hidden)소득세*/
        String rsdntx,                /*(hidden)주민세*/
        String mscrYn,               /*(hidden)유가증권여부*/
        String bldCd,                /*빌딩코드*/
        String bldNm,                /*빌딩명*/
        String rsbDvNm,               /*직책명*/
        String baseYm
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-SaveReq")
    public record SaveReq(
        String opcsAdjNo, // 운영비정산번호
        String dstOjOgTpCd,
        String ogTpCd,
        String dstOjPrtnrNo,
        String dstOjpsNm,
        String dstOjpsPerfAmt,
        String dstAmt,
        String dstWhtx,
        String erntx,
        String rsdntx,
        String pdstOpt, /*배분옵션 선택 값*/
        String cardUseAmt, // 카드사용금액
        String pstnDvCd,
        String adjOgId,
        String baseYm,
        String adjPrtnrNo,
        String opcsCardUseIzId,
        String deleted
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 정산대상
    @ApiModel(value = "WdcdMarketableSecuritieExceptionMgttDto-AccCardInfoDetailRes")
    public record AccCardInfoDetailRes(
        String baseYm,       /*기준년월*/
        String pstnDvCd,    /*직급구분코드*/
        String rsbDvCd,     /*직책코드*/
        String rsbDvNm,     /*직책명*/
        String ogId,         /*정산조직ID*/
        String adjOgNm,     /*정산조직명*/
        String adjPrtnrNo,  /*정산파트너번호*/
        String adjUsrNm,    /*정산사용자명*/
        String opcsAdjNo,   /*운영비정산번호  KEY*/
        String adjYn,        /*정산여부*/
        String befCardLimAmt, /*이전카드한도금액*/
        String befCardUseAmt, /*이전카드사용금액*/
        String befCardResAmt, /*이전카드잔여금액*/
        String cardAddAmt,  /*카드이월금액*/
        String cardLimAmt, /*카드한도금액*/
        String cardUseAmt, /*카드사용금액*/
        String cardResAmt /*카드잔여금액*/
    ) {
    }
}
