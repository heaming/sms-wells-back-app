package com.kyowon.sms.wells.web.closing.expense.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.docs.dto.AttachFileDto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WdcdOperatingCostMgtDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @Builder
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        String entrpDvCd,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SearchAmountRes")
    public record SearchAmountRes(
        String baseYm,        /*기준년월*/
        String pstnDvCd,     /*직급구분코드*/
        String rsbDvNm,      /*직책명*/
        String adjOgId,      /*정산조직ID*/
        String adjOgNm,      /*정산조직명*/
        String ogTpCd,       /*조직유형코드*/
        String adjPrtnrNo,   /*정산파트너번호*/
        String adjUsrNm,     /*정산사용자명*/
        String opcsAdjNo,    /*운영비정산번호*/
        String adjYn,         /*정산여부*/
        String befCardResAmt,  /*이월잔액*/
        String thmCardLimAmt,   /*당월지급*/
        String cardLimAmt,  /*당월이용가능금액*/
        String cardUseAmt,  /*당월사용금액*/
        String cardCanAmt,  /*당월취소금액*/
        String cardResAmt  /*잔액*/
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SearchSummaryReq")
    public record SearchSummaryReq(
        @NotBlank
        String baseYm,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 운영비 금액 현황
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SearchSummaryRes")
    public record SearchSummaryRes(
        String opcsCardId,
        String opcsAdjNo,
        String opcsAdjCnt,
        String opcsSmryNCnt,
        String opcsWhtxCfdcApnFileId
    ) {

    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 유가증권 원천세 확인서
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String aprDt,
        String registration
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 유가증권 원천세 확인서
    @ApiModel(value = "WwdcdOperatingCostMgtDto-SaveRes")
    public record SaveRes(
        @NotBlank
        String aprDt,
        String registration
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 유가증권 원천세 확인서
    @ApiModel(value = "WwdcdOperatingCostMgtDto-FileReq")
    public record FileReq(
        @NotBlank
        String aprDt,
        String registration
    ) {
    }

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 운영비 등록관리 / 유가증권 원천세 확인서
    @ApiModel(value = "WwdcdOperatingCostMgtDto-FileReq")
    public record EditReq(
        String opcsCardId,
        List<AttachFileDto.AttachFile> attachOpcsWhtxCfdcApnFileId
    ) {
    }
}
