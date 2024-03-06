package com.kyowon.sms.wells.web.service.visit.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsnbCustomerPsByProductDto {

    @ApiModel(value = "WsnbCustomerPsByProductDto-SearchReq")
    public record SvVstPrdCd(
        String code,
        String codeName
    ) {}

    @ApiModel(value = "WsnbCustomerPsByProductDto-SearchReq")
    public record SearchReq(
        String baseDt,
        String bfsvcPrdCd,
        String cancelOrReqd,
        Integer instOver,
        String itmKndCd,
        String pdCd,
        String pdGrpCd,
        String sellTpCd,
        String prdMngtTpCd
    ) {}

    @ApiModel(value = "WsnbCustomerPsByProductDto-SearchRes")
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String rcgvpKnm,
        String sapMatCd,
        String pdCd,
        String pdNm,
        String newAdrZip,
        String brchOgId,
        String brchOgNm,
        String ogCd,
        String addr,
        String adrDvCd,
        String adrId,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String rsgFshDt,
        String cntrCanDtm,
        String istDt,
        String reqdDt,
        String sellTpCd,
        String sellTpNm,
        String sellTpDtlCd,
        String sellTpDtlNm,
        String prdNm,
        String bcNo,
        String svStpDt,
        Integer stplPtrm,
        Integer frisuAsPtrmN,
        Integer frisuBfsvcPtrmN,
        String dlqAcuMcn,
        String ogNm,
        String prtnrNo,
        String prtnrKnm
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr;
        }
    }
}
