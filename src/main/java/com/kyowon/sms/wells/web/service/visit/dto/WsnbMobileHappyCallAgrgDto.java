package com.kyowon.sms.wells.web.service.visit.dto;

import io.swagger.annotations.ApiModel;

public class WsnbMobileHappyCallAgrgDto {
    @ApiModel("WsnbMobileHappyCallAgrgDto-SearchReq")
    public record SearchReq(

        String searchDateFrom,
        String searchDateTo,
        String ogId,
        String engId,
        String rgsnYn
    )
    {}
    @ApiModel("WsnbMobileHappyCallAgrgDto-SearchRes")
    public record SearchRes(

        String cntrSn,
        String cstKnm,
        String sapCd,
        String pdCd,
        String pdNm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String copnDv,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        String svDv,
        String wkGrpCd,
        String svBizDclsfCd,
        String rplyDt,
        String vstFshDt,
        String hpcallStpcN,
        String rslt01,
        String rslt02,
        String rslt03,
        String rslt04,
        String rslt05,
        String rslt06,
        String rslt07,
        String rslt08,
        String rslt09,
        String rslt10,
        String rslt11,
        String rslt12,
        String hpcallHdwrQstCn,
        String sendYn,
        String rplyYn,
        String rdnyYn
    )
    {}
}
