package com.kyowon.sms.wells.web.service.visit.dto;

import org.apache.commons.lang.StringUtils;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsnbCustomerPsByProductDto {
    @ApiModel(value = "WsnbCustomerPsByProductDto-SearchReq")
    public record SearchReq(
        String pdGrpCd,
        String pdCd,
        Integer instOver,
        String sellTpCd,
        String prdMngtTpCd,
        String bfsvcPrdCd
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
        String brchOgNm,
        String ogNm,
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
        String hmnrscDeptCd,
        String prtnrKnm,
        String prtnrNo,
        String dlqAcuMsn,
        String trnOverRtOjYn,
        String cmnPartDvCd,
        String ordnyHvMatYn
    ) {
        public SearchRes {
            mexnoEncr = StringUtils.isNotEmpty(mexnoEncr) ? DbEncUtil.dec(mexnoEncr) : mexnoEncr;
            exnoEncr = StringUtils.isNotEmpty(exnoEncr) ? DbEncUtil.dec(exnoEncr) : exnoEncr;
        }
    }
}
