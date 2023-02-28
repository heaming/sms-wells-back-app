package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

/**
 * <pre>
 * W-SV-U-0143M01 기타출고 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.03
 */
public class WsnaEtcOutOfStorageDto {

    @Builder
    @ApiModel("WsnaEtcOutOfStorageDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String ostrDt,
        String ostrSn,
        String ostrWareNo,
        String itmOstrNo,
        String strOjWareNo
    ) {}

    @ApiModel("WsnaEtcOutOfStorageDto-SearchRes")
    public record SearchRes(
        String itmOstrNo,
        String ostrTpCd,
        String ostrSn,
        String ostrWareNo,
        String sapMatCd,
        String itmPdCd,
        String pdAbbrNm,
        String itmGdCd,
        //        String onQty,
        String mngtUnitCd,
        String ostrQty,
        String ostrRsonCd,
        String rmkCn,
        String wareNm,
        String wareMngtPrtnrNo
    ) {}

    @Builder
    @ApiModel("WsnaEtcOutOfStorageDto-SaveReq")
    public record SaveReq(
        String itmOstrNo,
        String ostrSn,
        String ostrTpCd,
        String ostrWareNo,
        String sapMatCd,
        String itmPdCd,
        String pdAbbrNm,
        String itmGdCd,
        //        String onQty,
        String mngtUnitCd,
        String ostrQty,
        String ostrRsonCd,
        String rmkCn,
        String wareNm,
        String wareMngtPrtnrNo,
        String ostrDt,
        String strOjWareNo
    ) {}

    @Builder
    @ApiModel("WsnaEtcOutOfStorageDto-DeleteReq")
    public record DeleteReq(
        String itmOstrNo,
        String ostrSn,
        String ostrTpCd,
        String ostrWareNo,
        String sapMatCd,
        String itmPdCd,
        String pdAbbrNm,
        String itmGdCd,
        //        String onQty,
        String mngtUnitCd,
        String ostrQty,
        String ostrRsonCd,
        String rmkCn,
        String wareNm,
        String wareMngtPrtnrNo
    ) {}
    @ApiModel("WsnaEtcOutOfStorageDto-DeleteRes")
    public record DeleteRes(
        @NotBlank
        String itmOstrNo,
        String ostrTpCd,
        @NotBlank
        String ostrWareNo,
        String sapMatCd,
        String itmPdCd,
        String pdAbbrNm,
        String itmGdCd,
        //        String onQty,
        String mngtUnitCd,
        String ostrQty,
        String ostrRsonCd,
        String rmkCn,
        String wareNm,
        String wareMngtPrtnrNo
    ) {}
}
