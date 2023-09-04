package com.kyowon.sms.wells.web.service.adrwork.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnfAsEnrgMatMngtDto {

    @ApiModel(value = "WsnfAsEnrgMatMngtDto-SearchReq")
    public record SearchReq(
        String pdGrpCd,
        String pdCd,
        String classA,
        String classB,
        String classC
    ) {}

    @ApiModel(value = "WsnfAsEnrgMatMngtDto-SearchRes")
    public record SearchRes(
        String pdCd,
        String pdNm,
        String itmPdCd,
        String itmPdNm,
        String sapMatCd,
        String cnslTpLcsfCdNm,
        String cnslCn,
        String itmRcmdRnk,
        String itmRcmdQty
    ) {
        //        public SearchRes {
        //            tel = cralLocaraTno + "-" + DbEncUtil.dec(mexnoEncr) + "-" + cralIdvTno;
        //        }
    }
}
