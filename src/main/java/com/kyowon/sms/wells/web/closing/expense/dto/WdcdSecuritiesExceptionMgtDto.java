package com.kyowon.sms.wells.web.closing.expense.dto;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.NotBlank;

public class WdcdSecuritiesExceptionMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) / 대상자 조회결과
    @ApiModel(value = "WdcdSecuritiesExceptionMgtDto-SearchAdjustObjectReq")
    public record SearchAdjustObjectReq(
        @NotBlank
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
        String opcsCardUseIzId,    /*운영비카드사용내역ID*/
        String useDtm,                  /*사용일시*/
        String dgr1LevlOgId,        /*(hidden)총괄단조직ID*/
        String dgr2LevlOgId,        /*(hidden)지역단조직ID*/
        String dgr1LevlOgNm,        /*총괄단명*/
        String crcdnoEncr,            /*카드번호*/
        String mrcNm,                 /*가맹점명*/
        String mrcTobzNm,            /*가맹점업종명*/
        String mrcAdrCn,             /*가맹점주소내용*/
        String cardAprno,             /*카드승인번호*/
        String opcsAdjExcdYn,       /*운영비정산제외여부*/
        String opcsAdjSmryDvCd,    /*운영비정산적요구분코드*/
        String opcsAdjSmryDvNm,    /*운영비정산적요구분명*/
        String ojApyCn,              /*대상적용내용*/
        String usrSmryCn,            /*사용자적요내용*/
        String mrcTobzCd,            /*(hidden)가맹점업종코드*/
        String cdcoCd,                /*(hidden)카드사코드*/
        String opcsAdjBtn,       /*원천세정산버튼*/
        String adjCls,            /*정산여부*/
        String domTrdAmt,            /*사용금액*/
        String adjOgId,
        String domTrdAmt1
    ) {
        public SearchAdjustObjectRes {

            if (!StringUtils.isEmpty(crcdnoEncr)) {

                String regex = "(\\d{4})(\\d{4})(\\d{4})(\\d{3,4})";
                crcdnoEncr = crcdnoEncr.replaceAll(regex, "$1-$2-$3-$4");
            }
        }
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) /최종 원천세 정산 대상자
    @ApiModel(value = "WdcdSecuritiesExceptionMgtDto-SearchWithholdingTaxAdjustReq")
    public record SearchWithholdingTaxAdjustReq(
        @NotBlank
        String baseYm,
        String entrpDvCd,
        String opcsAdjNo
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 원천세 정산(유가증권 제외) /최종 원천세 정산 대상자
    @ApiModel(value = "WdcdSecuritiesExceptionMgtDto-SearchWithholdingTaxAdjustRes")
    public record SearchWithholdingTaxAdjustRes(
        String dgr1LevlOgNm,            /*총괄단*/
        String dgr2LevlOgNm,            /*지역단*/
        String dstOjpsNm,                /*성명*/
        String dstOjPrtnrNo,            /*번호*/
        String rsbDvNm,                  /*직책명*/
        String dstAmt,      /*운영비정산금액*/
        String dstWhtx,     /*원천세*/
        String erntx,        /*(hidden)소득세*/
        String rsdntx       /*(hidden)주민세*/
    ) {

    }

    @ApiModel(value = "WdcdSecuritiesExceptionMgtDto-SaveReq")
    public record SaveReq(
        String opcsCardUseIzId, // 운영비카드사용내역ID
        String opcsAdjExcdYn, // 정산제외여부
        String opcsAdjSmryDvCd, // 구분
        String ojApyCn, // 대상
        String purpSmryCn, // 목적
        String usrSmryCn // 구매품목 및 사용내역
    ) {
    }
}
