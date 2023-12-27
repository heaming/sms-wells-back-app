package com.kyowon.sms.wells.web.closing.sales.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WdcbBusinessAtamAdjustMgtDto {
    /**
     * 대표고객코드 검색결과 dto
     * @param codeId 대표고객ID
     * @param codeName 대표고객명
     */
    @ApiModel("EdcbBusinessAtamAdjustMgtDto-SearchSapPdDvCdRes")
    public record SearchSapPdDvCdRes(
        String codeId,
        String codeName
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 영업선수금 정산 관리 Search Dto
    @ApiModel("EdcbBusinessAtamAdjustMgtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseYm,
        @NotBlank
        String dpKndCd,
        String cntrNo,
        String cntrSn,
        String sapAlrpySlpno,
        String sapPdDvCd
    ) {}
    // *********************************************************
    // Result Dto
    // *********************************************************
    // 영업선수금 정산 관리 Search Result Dto
    @ApiModel("EdcbBusinessAtamAdjustMgtDto-SearchTotalRes")
    public record SearchTotalRes(
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String sapPdDvCd,
        String sapPdDvNm,
        String dgCstId,
        String dgCstNm,
        String baseYm,
        String slBndAlrpyAmt,
        String dpBlam,
        String ucAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 영업선수금 정산 관리 Search Result Dto
    @ApiModel("EdcbBusinessAtamAdjustMgtDto-SearchDetailRes")
    public record SearchDetailRes(
        String sellTpCd,
        String sellTpCdNm,
        String sellTpDtlCd,
        String sellTpDtlCdNm,
        String sapPdDvCd,
        String sapPdDvNm,
        String dgCstId,
        String dgCstNm,
        String baseYm,
        String cntrNo,
        String cntrSn,
        String cstNo,
        String CstKnm,
        String SlBndAlrpyDt,
        String SlBndAlrpySlipTrsNo,
        String SapAlrpySlpno,
        String RveNo,
        String RveSn,
        String DpClDt,
        String RveAmt,
        String SapDpSlpno,
        String SlRcogDt,
        String SlBndOcAmt,
        String SapSlSlpno,
        String slBndAlrpyAmt,
        String dpBlam,
        String ucAmt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 영업선수금정산관리-전표초기화 Search Result Dto
    @ApiModel("EdcbBusinessAtamAdjustMgtDto-SearchSlpnoRes")
    public record SearchSlpnoRes(
        String sellTpCd,
        String sellTpDtlCd,
        String sapAlrpySlpno,
        String slBndAlrpyAmt,
        String bktxt
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 영업선수금정산관리-전표초기화 Save Dto
    @ApiModel("EdcbBusinessAtamAdjustMgtDto-SaveSlpnoReq")
    public record SaveSlpnoReq(
        String sellTpCd,
        String sellTpDtlCd,
        String sapAlrpySlpno,
        String slBndAlrpyAmt,
        String bktxt,
        String fnlMdfcDtm
    ) {}
}
