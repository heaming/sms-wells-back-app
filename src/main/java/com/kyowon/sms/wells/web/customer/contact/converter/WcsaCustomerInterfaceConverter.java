package com.kyowon.sms.wells.web.customer.contact.converter;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoDvo;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoReqDvo;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerInfoReq;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerRes;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaInterfaceResultDvo;

@Mapper(componentModel = "spring")
public interface WcsaCustomerInterfaceConverter {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "searchType", constant = "C01")
    @Mapping(source = "CST_NO", target = "cstNo")
    ZcsaCustomerInfoReqDvo copy(SearchCustomerInfoReq dto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dvo.rsCd", target = "RS_CD")
    @Mapping(source = "dvo.rsMsg", target = "RS_MSG")
    SearchCustomerRes copy(WcsaInterfaceResultDvo dvo);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dvo.cstNo", target = "CST_NO")
    @Mapping(source = "dvo.itgCstNo", target = "ITG_CST_NO")
    @Mapping(source = "dvo.cikVal", target = "CIK_VAL")
    @Mapping(source = "dvo.sfkVal", target = "SFK_VAL")
    @Mapping(source = "dvo.copnDvCd", target = "COPN_DV_CD")
    @Mapping(source = "dvo.copnDvCdNm", target = "COPN_DV_CD_NM")
    @Mapping(source = "dvo.cstInflwPhDvCd", target = "CST_INFLW_PH_DV_CD")
    @Mapping(source = "dvo.cstInflwPhDvCdNm", target = "CST_INFLW_PH_DV_CD_NM")
    @Mapping(source = "dvo.cstKnm", target = "CST_KNM")
    @Mapping(source = "dvo.lnfDvCd", target = "LNF_DV_CD")
    @Mapping(source = "dvo.lnfDvCdNm", target = "LNF_DV_CD_NM")
    @Mapping(source = "dvo.bryyMmdd", target = "BRYY_MMDD")
    @Mapping(source = "dvo.sexDvCd", target = "SEX_DV_CD")
    @Mapping(source = "dvo.sexDvCdNm", target = "SEX_DV_CD_NM")
    @Mapping(source = "dvo.emadr", target = "EMADR")
    @Mapping(source = "dvo.bzrno", target = "BZRNO")
    @Mapping(source = "dvo.crpSpmtDrmNm", target = "CRP_SPMT_DRM_NM")
    @Mapping(source = "dvo.locaraTno", target = "LOCARA_TNO")
    @Mapping(source = "dvo.exnoEncr", target = "EXNO")
    @Mapping(source = "dvo.idvTno", target = "IDV_TNO")
    @Mapping(source = "dvo.cralLocaraTno", target = "CRAL_LOCARA_TNO")
    @Mapping(source = "dvo.mexnoEncr", target = "MEXNO")
    @Mapping(source = "dvo.cralIdvTno", target = "CRAL_IDV_TNO")
    @Mapping(source = "dvo.adrId", target = "ADR_ID")
    @Mapping(source = "dvo.newAdrZip", target = "ZIP")
    @Mapping(source = "dvo.rnadr", target = "BAS_ADR")
    @Mapping(source = "dvo.rdadr", target = "DTL_ADR")
    @Mapping(source = "dvo.prtnrNo", target = "PRTNR_NO")
    @Mapping(source = "dvo.sapEmpno", target = "SAP_EMPNO")
    @Mapping(source = "dvo.fmlDscOjYn", target = "FML_DSC_OJ_YN")
    @Mapping(source = "dvo.hsCtfYn", target = "HS_CTF_YN")
    @Mapping(source = "dvo.empYn", target = "EMP_YN")
    @Mapping(source = "dvo.cstGdCd", target = "CST_GD_CD")
    @Mapping(source = "dvo.nusdRsonCd", target = "NUSD_RSON_CD")
    @Mapping(source = "dvo.dtaDlYn", target = "DTA_DL_YN")
    @Mapping(source = "dvo.fstRgstDtm", target = "FST_RGST_DTM")
    @Mapping(source = "dvo.fnlMdfcDtm", target = "FNL_MDFC_DTM")
    @Mapping(source = "dvo2.rsCd", target = "RS_CD")
    @Mapping(source = "dvo2.rsMsg", target = "RS_MSG")
    SearchCustomerRes mapCreateCustomerDtlToCustomerRes(ZcsaCustomerInfoDvo dvo, WcsaInterfaceResultDvo dvo2);

}
