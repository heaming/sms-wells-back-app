package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0192M01 개인창고출고관리
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.16
 */
public class WsnaIndividualWareOstrDto {
    @Builder
    @ApiModel("WsnaIndividualWareOstrDto-SearchReq")
    public record SearchReq(
        String baseYm,
        String asnOjYm,
        String itmKndCd,
        String asnTnN,
        String ostrOjWareNo,
        String ostrWareNoM,
        String ostrWareNoD,
        String ostrDt,
        String itmKndCdD,
        String sapMatCdFrom,
        String sapMatCdTo
    ) {}

    @ApiModel("WsnaIndividualWareOstrDto-SearchRes")
    public record SearchRes(
        String wareNm,
        String itemCd,
        String pdAbbrNm,
        String mgtGdCd,
        String matGdCd,
        String pdPrpVal05,
        String mgtUntNm,
        String boxUnitQty,
        String mcbyAcuOstrQty,
        String oustQtyBak,
        String ostrWareNo,
        String strWareNo,
        String wareDvCd,
        String wareMngtPrtnrNo,
        String crtlStocQty,
        String itmQomAsnNo,
        String pdPrpVal19,
        String itemPart,
        String stocIzOnQty,
        String stocIzUpOnQty,
        String filtUseQty,
        String sapMatCd,
        String under20per,
        String toutQty,
        String nedQty,
        String rmks,
        String ostrIzOstrTpCd,
        String ostrIzOstrWareNo,
        String ostrIzOstrDt,
        String ostrIzItmOstrNo,
        String ostrIzOstrSn,
        String ostrIzSellRcpdt
    ) {}

    @ApiModel("WsnaIndividualWareOstrDto-LogisticReq")
    public record LogisticReq(
        @NotBlank
        String apyYm
    ) {}

    @ApiModel("WsnaIndividualWareOstrDto-LogisticRes")
    public record LogisticRes(
        @NotBlank
        String codeId,
        String codeName,
        String wareMngtPrtnrNo,
        String wareIchrNo,
        String wareDvCd,
        String hgrWareNo,
        String wareNmUp,
        String wareMngtPrtnrNoUp,
        String wareLocaraCdUp,
        String wareDvCdUp
    ) {}
    @ApiModel("WsnaIndividualWareOstrDto-ItmReq")
    public record ItmReq(
        String itmKndCd
    ) {}

    @ApiModel("WsnaIndividualWareOstrDto-ItemRes")
    public record ItmRes(
        String codeId,
        String codeName
    ) {}
}
