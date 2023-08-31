package com.kyowon.sms.wells.web.service.adrwork.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;

public class WsnfAsEnrgMatMngtDto {

    @ApiModel(value = "WsnfAsEnrgMatMngtDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String pdCd,
        String dateType,
        String dateValueFromDt,
        String dateValueToDt,
        String svTpCd,
        String prtnrNo,
        String ogId
    ) {}

    @ApiModel(value = "WsnfAsEnrgMatMngtDto-SearchRes")
    public record SearchRes(
        String ichrPrtnrNo,
        String cntrCstNo,
        String cntrNo,
        String istllKnm,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String adrId,
        String zip,
        String adr,
        String cnslMoCn,
        String cnslTpHclsfCd,
        String cnslTpMclsfNm,
        String svCnrOgId,
        String ichrOgTpCd,
        String rcpdt,
        String vstDuedt,
        String arvDt,
        String vstCnfmdt,
        String adrDvCd,
        String itmPdCd,
        String pdAbbrNm,
        String svBizHclsfNm,
        String prtnrNm,
        String ogNm,
        String cstSvAsnNo,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String itmPdNm,
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
