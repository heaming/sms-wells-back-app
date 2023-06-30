package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WwdbRefundPresentStateDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // wells 환불현황 Search Request Dto
    @Builder
    @ApiModel("WwdbRefundPresentStateDto-SearchReq")
    public record SearchReq(
        String ogId,
        String wkStartDtm,
        String wkEndDtm,
        String rfndDvCd
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // wells 환불현황 Search Result Dto
    @ApiModel("WwdbRefundPresentStateDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cstKnm,
        String sellTpNm,
        String pdNm,
        String tno,
        String mpno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String partAmt,
        String tcfee,
        String trcs,
        String etcCs,
        String rveIzAmt,
        String dpAmt,
        String cardDpAmt,
        String cshDpAmt,
        String rfndRqdt,
        String rfndDsbDt,
        String rfndAkAmt,
        String cshRfndFnitCd,
        String cshRfndFnitNm,
        String cshRfndAcnoEncr,
        String cardRfndFnitCd,
        String cardRfndFnitNm,
        String cardRfndCrcdnoEncr
    ) {
    }
}
