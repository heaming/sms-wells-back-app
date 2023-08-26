package com.kyowon.sms.wells.web.service.stock.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 *
 * <pre>
 * K-W-SV-U-0265M01 입고 집계
 * </pre>
 *
 * @author 37758 이재훈
 * @since 2023.08.07
 */
public class WsnaInAggregateDto {
    @ApiModel("WsnaInAggregateDto-SearchReq")
    public record SearchReq(
        String strTpCd,
        String pdCdFrom,
        String pdCdTo,
        String sapCdFrom,
        String sapCdTo,
        @NotBlank
        String baseDateFrom,
        @NotBlank
        String baseDateTo,
        @NotBlank
        String itmKndCd,
        String pdCd,
        String pdGdCd,
        String useYn
    ) {}

    @ApiModel(value = "WsnaInAggregateDto-SearchRes")
    public record SearchRes(
        String matMngtDvCd,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String typ100002,
        String typ100003,
        String typ100000,
        String typ200001,
        String typ200002,
        String typ200465,
        String typ200009,
        String typ200006,
        String typ200024,
        String typ200017,
        String typ200005,
        String typ200934,
        String typ200003,
        String typ200010,
        String typ200012,
        String typ200466,
        String typ200014,
        String typ200013,
        String typ200609,
        String typ200127,
        String typ200467,
        String typ200608,
        String typ200007,
        String typ200371,
        String typ200008,
        String typ200926,
        String typ200015,
        String typ200986,
        String typ299999,
        String typ200000,
        String typ300001,
        String typ300002,
        String typ300003,
        String typ300004,
        String typ300005,
        String typ300006,
        String typ300007,
        String typ300008,
        String typ300009,
        String typ300010,
        String typ300011,
        String typ300012,
        String typ300013,
        String typ300014,
        String typ300015,
        String typ300016,
        String typ300017,
        String typ300018,
        String typ300019,
        String typ300020,
        String typ300021,
        String typ300022,
        String typ300023,
        String typ300024,
        String typ300025,
        String typ300026,
        String typ300027,
        String typ300028,
        String typ300029,
        String typ399999,
        String typ300000,
        String typ999999
    ) {}
}
