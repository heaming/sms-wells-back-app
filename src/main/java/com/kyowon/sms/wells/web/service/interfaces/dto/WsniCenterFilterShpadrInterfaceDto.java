package com.kyowon.sms.wells.web.service.interfaces.dto;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsniCenterFilterShpadrInterfaceDto {

    @ApiModel(value = "WsniCenterFilterShpadrInterfaceDto-SearchReq")
    public record SearchReq(
        String cntrNo
    ) {}

    @ApiModel(value = "WsniCenterFilterShpadrInterfaceDto-SearchRes")
    public record SearchRes(
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String newAdrZip,
        String rnadr,
        String rdadr,
        String fstRgstUsrId
    ) {
        public SearchRes {
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }
}
