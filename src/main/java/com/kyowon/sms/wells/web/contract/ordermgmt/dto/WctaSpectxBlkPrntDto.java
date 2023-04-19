package com.kyowon.sms.wells.web.contract.ordermgmt.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;
import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WctaSpectxBlkPrntDto {
    @ApiModel("WctaSpectxBlkPrntDto-SearchRes")
    @Builder
    public record SearchRes(
        String spectxGrpNo,
        String sellTpCd,
        String cntrDtlNo,
        String cntrNo,
        String cntrSn,
        String cstNm,
        String spectxPrntY,
        String spectxPblDDvCd,
        String spectxPrdDvCd,
        String emadr,
        String faxLocaraTno,
        String faxExno,
        String faxIdvTno,
        String fstRgstUsrId,
        String epNo,
        String fstRgstD,
        String fstRgstDtm,
        String lstmmYn,
        String cstNo
    ) {}
    @ApiModel("WctaSpectxBlkPrntDto-SearchReq")
    @Builder
    public record SearchReq(
        @NotBlank
        @ValidDate
        String rgstStartDt,
        @NotBlank
        @ValidDate
        String rgstEndDt,
        String cntrNo,
        String cntrSn,
        String grpStartNo,
        String grpEndNo
    ) {}
    @ApiModel("WctaSpectxBlkPrntDto-SearchCntrRes")
    @Builder
    public record SearchCntrRes(
        String sellTpCd,
        String cntrCstNo,
        String cstKnm,
        String emadr,
        String spectxPblD,
        String cntrNo,
        String cntrSn,
        String dtlCntrNo
    ) {}

    @ApiModel("WctaSpectxBlkPrntDto-SaveReq")
    @Builder
    public record SaveReq(
        @NotBlank
        String rowState,
        @NotBlank
        String spectxGrpNo,
        @NotBlank
        String sellTpCd,
        String cntrDtlNo,
        @NotBlank
        String cntrNo,
        @NotBlank
        String cntrSn,
        String cstNm,
        String spectxPrntY,
        String spectxPblDDvCd,
        String spectxPrdDvCd,
        String emadr,
        String faxLocaraTno,
        String faxExno,
        String faxIdvTno,
        String fstRgstUsrId,
        String epNo,
        String fstRgstD,
        String fstRgstDtm,
        String lstmmYn,
        String cstNo
    ) {}
}
