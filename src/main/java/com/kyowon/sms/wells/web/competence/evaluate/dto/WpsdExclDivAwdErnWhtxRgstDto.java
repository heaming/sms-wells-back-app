package com.kyowon.sms.wells.web.competence.evaluate.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WpsdExclDivAwdErnWhtxRgstDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 우수사업부 시상소득원천세 등록 Search Request Dto
    @Builder
    @ApiModel("WpsdExclDivAwdErnWhtxRgstDto-SearchReq")
    public record SearchReq(
        String perfYm
    ) {
    }

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 우수사업부 시상소득원천세 등록 Search Result Dto
    @ApiModel("WpsdExclDivAwdErnWhtxRgstDto-SearchRes")
    public record SearchRes(
        String perfYm,
        String prtnrNo,
        String awdDvCd,
        String awdPerfSn,
        String awdNm,
        String awdIntbsAmt,
        String awdErnWhtx
    ) {
    }
}
