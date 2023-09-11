package com.kyowon.sms.wells.web.service.stock.dto;

import io.swagger.annotations.ApiModel;

public class WsnaAssignCprHvMatPsDto {
    @ApiModel("WsnaAssignCprHvMatPsDto-SearchReq")
    public record SearchReq(
        String cntrNo,
        String cntrSn,
        String pdctPdCd,
        String svPdCd,
        String sapItemCdFrom,
        String sapItemCdTo,
        String strtSapCd,
        String endSapCd
    ){}
    @ApiModel("WsnaAssignCprHvMatPsDto-SearchRes")
    public record SearchRes(
        String apyYm,
        String wareNo,
        String wareDvCd,
        String wareDtlDvCd,
        String wareLocaraCd,
        String wareLocaraSn,
        String hgrWareNo
    ){}
}
