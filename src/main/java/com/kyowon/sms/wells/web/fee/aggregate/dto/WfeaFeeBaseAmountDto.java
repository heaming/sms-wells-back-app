package com.kyowon.sms.wells.web.fee.aggregate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WfeaFeeBaseAmountDto {

    // *********************************************************
    // Request Dto
    // *********************************************************
    //  WELLS 수수료 기준금액 체크리스트 Search Request Dto
    @Builder
    @ApiModel("WfeaFeeBaseAmtCheckListDto-SearchReq")

    public record SearchReq(
        //실적년월
        String perfYm,
        //조직유형
        String ogTp
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 수수료 기준금액 체크리스트 Search Result Dto
    @ApiModel(value = "WfeaFeeBaseAmtCheckListDto-SearchRes")
    public record SearchRes(
        String lccode,
        String lccrtt,
        String sellTpCd,
        String lccgub,
        String og1Nm,
        String og2Nm,
        String og3Nm,
        String prtnrNo,
        String prtnrKnm,
        String lcicde,
        String kaina1,
        String lcprat,
        String lcpamt,
        String lcgub5,
        String l115Bam1,
        String promBam1,
        String lcamt1,
        String etc3Etc4,
        String imonIuse,
        String lcflg4,
        String lcetc7,
        String lccbu1,
        String lcflag,
        String lcrate,
        String lccod1,
        String lccod2,
        String lcetc8,
        String lcck02,
        String lcepgm,
        String lcecde,
        String lcmpgm,
        String lcmcde

    ) {}

}
