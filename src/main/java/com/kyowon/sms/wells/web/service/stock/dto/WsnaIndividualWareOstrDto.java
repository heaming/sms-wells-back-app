package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

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
        String wareNo, /*창고*/
        String wareNm, /*창고명*/
        String itmPdCd, /*품목상품코드*/
        String pdAbbrNm, /*상품약어명*/
        String mgtGdCd, /*관리단위코드*/
        String matGdCd, /*자재등급코드*/
        String pdPrpVal05,
        String mgtUntNm, /*관리단위명*/
        String boxUnitQty, /*박스단위수량*/
        String mcbyAcuOstrQty, /*월별누적출고수량*/
        String outQtyBak,
        String ostrWareNo,
        String strWareNo,
        String wareDvCd,
        String wareMngtPrtnrNo,
        String asnIzOutBoxQty,
        String crtlStocQty,
        String itmQomAsnNo,
        String pdPrpVal19,
        String itemPart,
        String stocIzOnQty,
        String stocIzUpOnQty,
        String filtUseQty,
        String sapMatCd,
        String under20per,
        String toutQty, /**/
        String nedQty, /*소요수량*/
        String rmks, /*비고*/
        String ostrIzOstrTpCd, /*출고유형*/
        String ostrIzOstrWareNo, /*출고창고번호*/
        String ostrIzOstrDt, /*출고일자*/
        String ostrIzItmOstrNo, /*품목출고번호*/
        String ostrIzOstrSn, /*출고일련번호*/
        String ostrIzSellRcpdt, /*판매접수일자*/
        String cfrmQty, /*확정수량*/
        String cfrmBoxQty,
        String outQty, /*출고수량*/
        String accBoxQty, /*물량배정출고박스수량*/
        String outBoxQty, /*출고박스수량*/
        String asnOjYm
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

    @ApiModel("WsnaIndividualWareOstrDto-CreateReq")
    public record CreateReq(
        String ostrAkNo,
        String ostrAkSn,
        String ostrAkTpCd,
        String ostrOjWareNo,
        String strOjWareNo,
        String ostrAkRgstDt,
        String strHopDt,
        String itmPdCd,
        String itmGdCd,
        String mngtUnitCd,
        String ostrAkQty,
        String rmkCn,
        String dtaDlYn
    ){}
}
