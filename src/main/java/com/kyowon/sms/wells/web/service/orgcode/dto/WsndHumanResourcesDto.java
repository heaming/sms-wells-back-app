package com.kyowon.sms.wells.web.service.orgcode.dto;

import io.swagger.annotations.ApiModel;

public class WsndHumanResourcesDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 인사기본정보 Search Request Dto
    @ApiModel(value = "WsndHumanResourcesDto-SearchReq")
    public record SearchReq(
        String mngrDvCd,
        String dgr1LevlOgId,
        String dgr2LevlOgId,
        String dgr3LevlOgId,
        String searchText
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 인사기본정보 Search Result Dto
    @ApiModel(value = "WsndHumanResourcesDto-SearchRes")
    public record SearchRes(
        String baseYm,
        String ogTpCd,
        String prtnrNo,
        String prtnrClsfCd,
        String cikVal,
        String sfkVal,
        String copnDvCd,
        String lnfDvCd,
        String prtnrKnm,
        String prtnrEnm,
        String ogId,
        String ogCd,
        String ogNm,
        String hmnrscDeptCd,
        String hmnrscEmpno,
        String sapDlpnrCd,
        String sapDlpnrDtlIzRfltDt,
        String wkGrpCd,
        String wkGrpNm,
        String wkcrCd,
        String rcrtWrteDt,
        String fstCntrDt,
        String fnlCltnDt,
        String rcntrDt,
        String cltnDt,
        String cntrDt,
        String bzStatCd,
        String prfmtDmtnDvCd,
        String prfmtDt,
        String dmtnDt,
        String ccpsYn,
        String cltnChoYn,
        String prrBizRgstYn,
        String pstnDvCd,
        String rsbDvCd,
        String rolDvCd,
        String prtnrGdCd,
        String perfExcdOjYn,
        String rdsDsbExcdOjYn,
        String hgrOgId,
        String ogLevlDvCd,
        String ogAbbrNm,
        String vdtcrPdDvCd,
        String cpsnDtrcOgCd,
        String dtrcSbrncOgCd,
        String sbrncYn,
        String sbrncDvCd,
        String opDt,
        String cloYn,
        String cloDt,
        String hooOgTpCd,
        String hooPrtnrNo,
        String hooPrtnrNm,
        String bizSpptPrtnrNo,
        String ogUpbrngPrtnrNo,
        String bldCd,
        String bldNm,
        String dgr1LevlOgId,
        String dgr1LevlOgCd,
        String dgr1LevlOgNm,
        String dgr1LevlDgPrtnrNo,
        String dgr1LevlDgPrtnrNm,
        String dgr2LevlOgId,
        String dgr2LevlOgCd,
        String dgr2LevlOgNm,
        String dgr2LevlDgPrtnrNo,
        String dgr2LevlDgPrtnrNm,
        String dgr3LevlOgId,
        String dgr3LevlOgCd,
        String dgr3LevlOgNm,
        String dgr3LevlDgPrtnrNo,
        String dgr3LevlDgPrtnrNm,
        String dgr4LevlOgId,
        String dgr4LevlOgCd,
        String dgr4LevlOgNm,
        String dgr4LevlDgPrtnrNo,
        String dgr4LevlDgPrtnrNm,
        String dgr5LevlOgId,
        String dgr5LevlOgCd,
        String dgr5LevlOgNm,
        String dgr5LevlDgPrtnrNo,
        String dgr5LevlDgPrtnrNm
    ) {}
}
