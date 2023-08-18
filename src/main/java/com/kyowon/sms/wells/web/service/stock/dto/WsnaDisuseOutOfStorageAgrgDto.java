package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaDisuseOutOfStorageAgrgDto {

    @ApiModel(value = "WsnaDisuseOutOfStorageAgrgDto-SearchReq")
    public record SearchReq(
        String startDt,

        String endDt,

        String ogId,

        String itmGdCd,

        String rtngdProcsTpCd,

        String useYn,

        String itmCdFrom,

        String itmCdTo,

        String itmKndCd
    ) {}

    @ApiModel(value = "WsnaDisuseOutOfStorageAgrgDto-SearchRes")
    public record SearchRes(
        String asMatItmGrpNm,
        String pdCd,
        String pdNm,
        String sapMatCd,
        String asSplUnitAmt,
        String itemGdESum, /* E등급 : 수량 */
        String itemGdECostSum, /* E등급 : 폐기금액 */
        String itemGdRSum, /* R등급 : 수량 */
        String itemGdRCostSum, /* R등급 : 폐기금액 */
        String itemGdXSum, /* X등급 : 수량 */
        String itemGdXCostSum, /* X등급 : 폐기금액 */
        String itemGdSum, /* 합계 : 수량 */
        String itemGdCostSum, /* 합계 : 폐기금액 */
        String etcInstSum, /* 회사설치 */
        String leaseSum, /* 금융리스 */
        String etcLeaseSum /* 회사+금융 */
    ) {}
}
