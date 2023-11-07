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
        String ogTpCd
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // WELLS 수수료 기준금액 체크리스트 Search Result Dto
    @ApiModel(value = "WfeaFeeBaseAmtCheckListDto-SearchRes")
    public record SearchRes(
        String cntrDtlNo,
        String cntrRcpFshDtm,
        String sellOgTpCd,
        String copnDvCd,
        String og1Nm,
        String og2Nm,
        String og3Nm,
        String prtnrNo,
        String prtnrKnm,
        String pdCd,
        String pdNm,
        String ackmtPerfRt,
        String ackmtPerfAmt,
        String feeAckmtBaseAmt,
        String prcFeeAckmtBaseAmt,
        String pmotFeeAckmtBaseAmt,
        String fnlAmt,
        String saleTp,
        String imonIuse,
        String sellDscTpCd,
        String mchnChYn,
        String mchnChCopnDvCd,
        String mchnChTpCd,
        String mchnCpsApyr,
        String ojCntrNo1,
        String ojCntrNo2,
        String alncmpCd,
        String pmotCd,
        String fstRgstPrgId,
        String fstRgstUsrId,
        String fnlMdfcPrgId,
        String fnlMdfcUsrId

    ) {}

}
