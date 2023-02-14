package com.kyowon.sms.wells.web.service.orgcode.dto;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsndBusinessVehiclesMgtDto {
    @ApiModel(value = "WsndBusinessVehiclesMgtDto-SearchReq")
    public record SearchReq(
        String hgrOgId,
        String ogCd,
        String prtnrNo,
        String findGb
    ) {}

    @ApiModel(value = "WsndBusinessVehiclesMgtDto-SearchRes")
    public record SearchRes(
        String ogNm,
        String prtnrNo,
        String prtnrKnm,
        String rol,
        String entcoDt,
        String carno,
        String vhcKndCd,
        String vhcMngtTpCd,
        String vhcPymdt,
        String dsbEnddt,
        String insrAgeCd,
        String rflngCdnoEncr,
        String hipsCdnoEncr,
        String vhcDsbRmkCn,
        String ogCd,
        String vhcMngtNo,
        String vhcMngtSn,
        String vhcMngtPrtnrNo
    ) {
        public SearchRes {
            rflngCdnoEncr = DbEncUtil.dec(rflngCdnoEncr);
            hipsCdnoEncr = DbEncUtil.dec(hipsCdnoEncr);
        }
    }
}
