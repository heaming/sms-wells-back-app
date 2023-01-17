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
        String sapMatCd,
        String itmPdCd,
        String itmNm,
        String itmGdCd,
        String ostrDt,
        String ostrQty,
        String whlsUprcAmt,
        String totalAmt,
        String deptNm2,
        String ostrRsonCd,
        String sumQty,
        String wareNm,
        String rmkCn,
        String sortDvVal,
        String cdCntn
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
