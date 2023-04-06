package com.kyowon.sms.wells.web.contract.orderstatus.dto;

import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst.PeriodType;
import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WctdSelfConversionDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    //  자가전환 조회 Search Request Dto

    @ApiModel("WctdSelfConversionDto-SearchReq")
    public record SearchReq(
        PeriodType periodType,
        @NotBlank
        @ValidDate
        String startDt,

        @NotBlank
        @ValidDate
        String endDt,
        String pdHclsfId,
        String pdMclsfId,
        String pdCd,
        String pdNm,
        String ogCd,
        String copnDvCd

    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    //  자가전환 조회 Search Result Dto
    @ApiModel("WctdSelfConversionDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        int cntrSn,
        String copnDvCd,
        String istDt,
        String cntrCstNo,
        String cstKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String rcgvpKnm,
        String rcgvpCralLocaraTno,
        String rcgvpMexnoEncr,
        String rcgvpCralIdvTno,
        String pdCd,
        String pdNm,
        String sdingPdNm,
        int fnlAmt,
        int recapDutyPtrmN,
        String lastBsDt,
        int eotUcAmt,
        int eotDlqAmt,
        int dlqAcuMcn,
        String cntrPdStrtdt,
        String cntrPdEnddt,
        String mchnChYn,
        String reRetalYn,
        String mchnChTpCd,
        String chCntrNo,
        int chCntrSn,
        String chPdCd,
        String chPdNm,
        String stplRcpDtm,
        String stplStrtdt,
        String stplCanDtm
        ) {
    }
}
