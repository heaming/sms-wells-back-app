package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * K-W-SV-U-0110M01 반품출고집계현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.06.29
 */
public class WsnaReturningGoodsOstrAgrgDto {

    @ApiModel(value = "WsnaReturningGoodsOstrAgrgDto-SearchReq")
    public record SearchReq(
        String startDate,
        String endDate,
        String svCnrCd,
        String rtngdProcsTpCd
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrAgrgDto-SearchWareReq")
    public record SearchWareReq(
        String ogTpCd,
        String ogId
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrAgrgDto-SearchRes")
    public record SearchRes(
        String pdClsfId,
        String pdClsfNm,
        String pdNm,
        String pdCd,
        String sapMatCd,
        Integer fnlESum,
        Integer fnlRSum,
        Integer fnlXSum
    ) {}

    @ApiModel(value = "WsnaReturningGoodsOstrAgrgDto-SearchWareRes")
    public record SearchWareRes(
        String codeId,
        String codeName,
        String wareNo
    ) {}

}
