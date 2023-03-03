package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * W-SV-U-0274M01 기타출고 사유내역
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.01.13
 */
public class WsnaEtcOutOfStorageRsonDto {
    @ApiModel(value = "WsnaEtcOutOfStorageRsonDto-SearchReq")
    public record SearchReq(
        String stOstrDt,
        String edOstrDt,
        String bilRsonCd,
        String pdGdCd,
        String itmKndCd,
        String startItemCd,
        String endItemCd,
        String ostrWareNo

    ) {}
    @ApiModel(value = "WsnaEtcOutOfStorageRsonDto-SearchRes")
    public record SearchRes(
        String sapMatCd, /*SAP코드*/
        String itemNm, /*품목명*/
        String ostrWareNo, /**/
        String itmPdCd, /*품목상품코드*/
        String wareNm, /*창고명*/
        String itmGdCd, /*등큽코드*/
        String ostrDt, /*출고일자*/
        String ostrRsonCd, /*청구사유*/
        String ostrQty, /*수량*/
        String csmrUprcAmt, /*소비자가*/
        String totalAmt, /*총금액*/
        //        String deptNm
        String sortDvVal,
        String rmkCn /*비고*/
    ) {}

    @ApiModel(value = "WsnaEtcOutOfStorageRsonDto-CenterRes")
    public record CenterRes(
        String codeId,
        String codeName,
        String wareMngtPrtnrNo
    ) {}

    @ApiModel(value = "WsnaEtcOutOfStorageRsonDto-BusinessRes")
    public record BusinessRes(
        String codeId,
        String codeName,
        String wareDvCd
    ) {}
}
