package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaNormalOutOfStorageAgrgDto {
    @ApiModel(value = "WsnaNormalOutOfStorageAgrgDto-SearchReq")
    public record SearchReq(
        String startStrHopDt,
        String endStrHopDt,
        String ostrAkTpCd,
        String ostrOjWareNo,
        String itmKndCd,
        String wareDvCd,
        String wareLocaraCd,
        String ostrCnfmYn
    ) {}

    @ApiModel(value = "WsnaNormalOutOfStorageAgrgDto-FindWarehouseRes")
    public record FindWarehouseRes(
        String codeId,
        String codeName
    ) {}
}
