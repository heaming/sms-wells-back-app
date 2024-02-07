package com.kyowon.sms.wells.web.service.stock.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0258M01 일자별 자재 입출고 관리
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.09.19
 */
public class WsnaByDayMaterialInOutSearchDto {

    @ApiModel(value = "WsnaByDayMaterialInOutSearchDto-SearchReq")
    public record SearchReq(
        String wareDvCd,
        String hgrWareNo,
        String itmKndCd,
        String itmGrpCd,
        List<String> itmPdCds,
        String ostrAkTpCd,
        String baseDateFrom,
        String baseDateTo,
        String ostrDtrmYn
    ) {}

    @ApiModel(value = "WsnaByDayMaterialInOutSearchDto-SearchRes")
    public record SearchRes(
        String strHopDt,
        String ostrAkTpNm,
        String ostrAkNo,
        String ostrAkSn,
        String ostrWareNm,
        String strWareNm,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String ostrStckQty,
        String strStckQty,
        String rgstDtOstrStckQty,
        String rgstDtstrStckQty,
        String ostrAkQty,
        String ostrDt1,
        String ostrQty1,
        String outYn,
        String outPeriod,
        String sysDt,
        String sysDtAgo,
        String rgstDt,
        String rgstDtAgo,
        String strOjWareNo,
        String ostrOjWareNo
    ) {}

    @ApiModel(value = "WsnaByDayMaterialInOutSearchDto-DeleteReq")
    public record DeleteReq(
        @NotBlank
        String ostrAkNo,
        @NotBlank
        String ostrAkSn
    ) {}
}
