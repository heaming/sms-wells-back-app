package com.kyowon.sms.wells.web.service.allocate.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;

import io.swagger.annotations.ApiModel;

public class WsncBsPeriodCustomerTfDto {
    @ApiModel(value = "WsncBsPeriodCustomerTfDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String assignYm,
        String organizationId,
        String partnerNo,
        String partnerNoInput,
        String transferStatusCode,
        String emdName,
        String addressZip
    ) {}

    @ApiModel(value = "WsncBsPeriodCustomerTfDto-SearchRes")
    public record SearchRes(
        String asnOjYm,
        String tfStatCd,
        String cntrNo,
        String cntrSn,
        String rcgvpKnm,
        String assign,
        String svpdSapCd,
        String pdctPdCd,
        String svpdNmAbbr1,
        String svBizDclsfCd,
        String ctpvNm,
        String ctctyNm,
        String emdNm,
        String istNmnN,
        String locaraTno,
        String exnoEncr,
        String idvTno,
        String cralLocaraTno,
        String mexnoEncr,
        String cralIdvTno,
        String newAdrZip,
        String rnadr,
        String vstCnfmdt,
        String vstCnfmHh,
        String tfAkRmkCn,
        String bfchIchrOgNm,
        String bfchIchrPrtnrNo,
        String bfchIchrPrtnrKnm,
        String afchIchrOgNm,
        String afchIchrPrtnrNo,
        String afchIchrPrtnrKnm,
        String tfRqdt,
        String tfAkRsonCd,
        String tfOgNm,
        String tfPrtnrKnm,
        String tfFnCnfmdt,
        String tfFnOgNm,
        String tfFnPrtnrKnm,
        String cstSvAsnNo

    ) {
        public SearchRes {
            exnoEncr = DbEncUtil.dec(exnoEncr);
            mexnoEncr = DbEncUtil.dec(mexnoEncr);
        }
    }

    @ApiModel(value = "WsncBsPeriodCustomerTfDto-BranchsAndServiceCentersRes")
    public record BranchsAndServiceCentersRes(
        String ogId,
        String ogTpCd,
        String ogCd,
        String ogNm,
        String ogCdNm,
        String hgrOgId
    ) {

    }

    @ApiModel(value = "WsncBsPeriodCustomerTfDto-ManagersAndEngineersRes")
    public record ManagersAndEngineersRes(
        String ogTpCd,
        String prtnrNo,
        String prtnrNm,
        String prtnrNoNm,
        String cntrDt,
        String cltnDt
    ) {

    }
}
