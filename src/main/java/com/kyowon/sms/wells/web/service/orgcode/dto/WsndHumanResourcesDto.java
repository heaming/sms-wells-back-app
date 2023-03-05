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
        String sellInflwChnlDvCd,
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
        String wkcrNo,
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
        String cprtLssChoYn,
        String prrBizRgstYn,
        String pstnDvCd,
        String rsbDvCd,
        String rolDvCd,
        String prtnrGdCd,
        String paylvDvCd,
        String prtnrQlfDvCd,
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

    // 조직 Search Result Dto
    @ApiModel(value = "WsndHumanResourcesDto-SearchOrganizationRes")
    public record SearchOrganizationRes(
        String baseYm, // 기준년월
        String ogId, // 조직ID
        String ogCd, // 조직코드
        String ogNm, // 조직명
        String ogTpCd, // 조직유형코드
        String hgrOgId, // 상위조직ID
        String ogLevlDvCd, // 조직레벨구분코드
        String ogAbbrNm, // 조직약어명
        String vdtcrPdDvCd, // 화상교사상품구분코드
        String cpsnDtrcOgCd, // 강제지국조직코드
        String dtrcSbrncOgCd, // 지국지소조직코드
        String sbrncYn, // 지소여부
        String sbrncDvCd, // 지소구분코드
        String opDt, // 개설일자
        String cloYn, // 폐쇄여부
        String cloDt, // 폐쇄일자
        String hooOgTpCd, // 조직장조직유형코드
        String hooPrtnrNo, // 조직장파트너번호
        String hooPrtnrNm, // 조직장파트너명
        String bizSpptPrtnrNo, // 업무지원파트너번호
        String ogUpbrngPrtnrNo, // 조직육성파트너번호
        String bldCd, // 빌딩코드
        String bldNm, // 빌딩명
        String dgr1LevlOgId, // 1차레벨조직ID
        String dgr1LevlOgCd, // 1차레벨조직코드
        String dgr1LevlOgNm, // 1차레벨조직명
        String dgr1LevlDgPrtnrNo, // 1차레벨대표파트너번호
        String dgr1LevlDgPrtnrNm, // 1차레벨대표파트너명
        String dgr2LevlOgId, // 2차레벨조직ID
        String dgr2LevlOgCd, // 2차레벨조직코드
        String dgr2LevlOgNm, // 2차레벨조직명
        String dgr2LevlDgPrtnrNo, // 2차레벨대표파트너번호
        String dgr2LevlDgPrtnrNm, // 2차레벨대표파트너명
        String dgr3LevlOgId, // 3차레벨조직ID
        String dgr3LevlOgCd, // 3차레벨조직코드
        String dgr3LevlOgNm, // 3차레벨조직명
        String dgr3LevlDgPrtnrNo, // 3차레벨대표파트너번호
        String dgr3LevlDgPrtnrNm, // 3차레벨대표파트너명
        String dgr4LevlOgId, // 4차레벨조직ID
        String dgr4LevlOgCd, // 4차레벨조직코드
        String dgr4LevlOgNm, // 4차레벨조직명
        String dgr4LevlDgPrtnrNo, // 4차레벨대표파트너번호
        String dgr4LevlDgPrtnrNm, // 4차레벨대표파트너명
        String dgr5LevlOgId, // 5차레벨조직ID
        String dgr5LevlOgCd, // 5차레벨조직코드
        String dgr5LevlOgNm, // 5차레벨조직명
        String dgr5LevlDgPrtnrNo, // 5차레벨대표파트너번호
        String dgr5LevlDgPrtnrNm // 5차레벨대표파트너명
    ) {}
}
