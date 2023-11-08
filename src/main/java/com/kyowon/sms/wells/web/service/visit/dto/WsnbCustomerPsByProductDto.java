package com.kyowon.sms.wells.web.service.visit.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsnbCustomerPsByProductDto {
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
        String sapMatCd,
        String pdCd,
        String pdNm,
        String newAdrZip,
        String brchOgId,
        String brchOgCd,
        String brchOgNm,
        String svcOgNm,
        String addr,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String istDt,
        String canDt,
        String reqdDt,
        String sellTpNm,
        String pdUswyCd,
        String bcNo,
        String svStpDt,
        Integer cntrPtrm,
        Integer extFrisuAsMcn,
        Integer svPrd,
        String rcgvpKnm,
        String ogNm,
        String prtnrKnm,
        String prtnrNo,
        String dlqAcuMcn,
        String trnOvrRtOjYn,
        String cmnPartDvCd,
        String ordnyHvMatYn
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr;
        }
    }
}
