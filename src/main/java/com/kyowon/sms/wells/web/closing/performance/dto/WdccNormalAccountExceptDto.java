package com.kyowon.sms.wells.web.closing.performance.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdccNormalAccountExceptDto {
    @ApiModel("WdccNormalAccountExceptDto-SearchReq")
    public record SearchReq(
        String pdCd,
        String regDtFrom,
        String regDtTo
    ) {}

    @ApiModel("WdccNormalAccountExceptDto-SearchContractReq")
    public record SearchContractReq(
        String cntrNo,
        String cntrSn,
        String regDtFrom,
        String regDtTo
    ) {}

    @ApiModel("WdccNormalAccountExceptDto-SearchRes")
    public record SearchRes(
        String nomAccExcdId,
        String sellTpCd,
        String sellTpDtlCd,
        String pdHclsfId,
        String pdMclsfId,
        String pdCd,
        String pdNm,
        String pmotCd,
        String pmotNm,
        String cntrStrtdt,
        String cntrEnddt,
        String nomAccExcdRsonCn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstUserNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlUserNm
    ) {}

    @ApiModel("WdccNormalAccountExceptDto-SearchCntrRes")
    public record SearchCntrRes(
        String nomAccExcdId,
        String cntrNo,
        String cntrSn,
        String cntrDtl,
        String cntrExcdRsonCn,
        String nomAccExcdRsonCn,
        String fstRgstDtm,
        String fstRgstUsrId,
        String fstUserNm,
        String fnlMdfcDtm,
        String fnlMdfcUsrId,
        String fnlUserNm
    ) {}

    @ApiModel("WdccNormalAccountExceptDto-SaveReq")
    public record SaveReq(
        String sellTpCd,
        String sellTpDtlCd,
        String pdHclsfId,
        String pdMclsfId,
        String pdCd,
        String pmotCd,
        String cntrStrtdt,
        String cntrEnddt,
        String cntrNo,
        String cntrSn,
        String cntrExcdRsonCn,
        String nomAccExcdRsonCn,
        String nomAccExcdId,
        String rowState
    ) {}
}
