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
        String sellTpDtlCd,
        String dgCstId,
        String sapPdDvNm,
        String baseYm,
        String slBndAlrpyAmt,
        String slBndAlrpySlipTrsNo,
        String sapAlrpySlpno,
        String dpBlam,
        String slBndBlam
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 영업선수금 정산 관리 Search Result Dto
    @ApiModel("EdcbBusinessAtamAdjustMgtDto-SearchDetailRes")
    public record SearchDetailRes(
        String sellTpCd,
        String sellTpDtlCd,
        String sapPdDvNm,
        String dgCstId,
        String baseYm,
        String cntrNo,
        String cstKnm,
        String slBndAlrpyDt,
        String slBndAlrpyAmt,
        String slBndAlrpySlipTrsNo,
        String sapAlrpySlpno,
        String rveNo,
        String rveSn,
        String dpClDt,
        String rveAmt,
        String sapDpSlpno,
        String dpBlam,
        String slRcogDt,
        String slBndOcAmt,
        String sapSlSlpno,
        String slBndBlam
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
