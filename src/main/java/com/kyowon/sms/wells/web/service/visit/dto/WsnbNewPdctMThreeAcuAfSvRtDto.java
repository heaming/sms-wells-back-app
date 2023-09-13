package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

import javax.validation.constraints.NotBlank;

/**======================================
 *
 * <pre>
 * K-W-SV-U-0245M01    실적_신제품 M+3 누적 A/S율
 * </pre>
 *
 * @author gs.piit231
 * @since 2023.09.07
**======================================*/
public class WsnbNewPdctMThreeAcuAfSvRtDto {
    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String baseY,
        String svType,
        String badDivide,
        String pdGrp,
        String pdCd
    ) {}

    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-SearchRes")
    public record SearchRes(
        String atcNm,
        String dYear,
        String m01,
        String m02,
        String m03,
        String m04,
        String m05,
        String m06,
        String m07,
        String m08,
        String m09,
        String m10,
        String m11,
        String m12,
        String totalCnt,
        String maxVal,
        String minVal,
        String avgVal
    ) {}

    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-PdListReq")
    public record PdListReq(
        String pdGrp,
        String pdCd
    ){}

    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-PdListRes")
    public record PdListRes(
        String svpdPdCd,
        String svpdItemCd,
        String svpdPartCd,
        String svpdNmKor,
        String svpdNmAbbr1,
        String svpdNmCust,
        String svpdItemKnd,
        String svpdItemKndNm,
        String svpdItemGr,
        String svpdItemGrNm,
        String svpdClsfId,
        String svpdHclsfId,
        String svpdMclsfId,
        String svpdLclsfId,
        String svpdDclsfId,
        String svpdSellTpCd,
        String svpdUseYn,
        String svpdApldFr,
        String svpdApldTo,
        String svpdSapCd,
        String svpdSapGrp,
        String svpdSapLevel,
        String svpdSapClass,
        String svpdPdctClssRsc,
        String svpdStdPr,
        String svpdImgUrl,
        String svpdRmks,
        String svpdCommGb,
        String svpdCommGbNm,
        String svpdLpGb,
        String svpdMgtTyp,
        String svpdMgtTypNm,
        String svpdSftyQty,
        String svpdSize,
        String svpdMgtUnt,
        String svpdMgtUntNm,
        String svpdLt,
        String svpdDelUnt,
        String svpdDelUntNm,
        String svpdBoxQty,
        String svpdPltQty,
        String svpdBarcodeYn,
        String svpdBaseGb,
        String svpdBaseColorGb,
        String svpdRefurYn,
        String svpdQtylmtYn,
        String svpdDisposalCost,
        String svpdTotWei,
        String svpdMoq,
        String svpdTurnoverTgt,
        String svpdPdTpCd,
        String svpdBarCd,
        String svpdBarCd18,
        String svpdPickQty,
        String svpdServiceUseYn,
        String svpdPartGr,
        String svpdHmcrUseYn,
        String svpdQrType,
        String sellStrtdt,
        String sellEnddt
    ) {}

    @Builder
    @ApiModel(value = "WsnbNewPdctMThreeAcuAfSvRtDto-SaveReq")
    public record SaveReq(
        @NotBlank
        String rowState, /* 상태 */
        String svpdPdCd,
        String svpdNmAbbr1,
        String svpdNmKor,
        String sellStrtdt,
        String sellEnddt
    ) {}
}
