package com.kyowon.sms.wells.web.contract.interfaces.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractDetailDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractDetailDvo;

@Mapper(componentModel = "spring")
public interface WctiContractDetailConverter {
    List<WctiContractDetailDto.SearchRes> mapWctiContractDetailDvoToSearchRes(List<WctiContractDetailDvo> dvo);

    @Mapping(source = "cntrNo", target = "CNTR_NO")
    @Mapping(source = "cntrSn", target = "CNTR_SN")
    @Mapping(source = "cntrDtlStatCd", target = "CNTR_DTL_STAT_CD")
    @Mapping(source = "cntrDtlStatNm", target = "CNTR_DTL_STAT_NM")
    @Mapping(source = "pdHclsfId", target = "PD_HCLSF_ID")
    @Mapping(source = "pdHclsfNm", target = "PD_HCLSF_NM")
    @Mapping(source = "pdMclsfId", target = "PD_MCLSF_ID")
    @Mapping(source = "pdMclsfNm", target = "PD_MCLSF_NM")
    @Mapping(source = "pdLclsfId", target = "PD_LCLSF_ID")
    @Mapping(source = "pdLclsfNm", target = "PD_LCLSF_NM")
    @Mapping(source = "basePdCd", target = "BASE_PD_CD")
    @Mapping(source = "basePdNm", target = "BASE_PD_NM")
    @Mapping(source = "cntrtNm", target = "CNTRT_NM")
    @Mapping(source = "cntrDt", target = "CNTR_DT")
    @Mapping(source = "cralLocaraTno", target = "CRAL_LOCARA_TNO")
    @Mapping(source = "mexno", target = "MEXNO")
    @Mapping(source = "cralIdvTno", target = "CRAL_IDV_TNO")
    @Mapping(source = "locaraTno", target = "LOCARA_TNO")
    @Mapping(source = "exno", target = "EXNO")
    @Mapping(source = "idvTno", target = "IDV_TNO")
    @Mapping(source = "cntrAdrpcId", target = "CNTR_ADRPC_ID")
    @Mapping(source = "cntrAdr", target = "CNTR_ADR")
    @Mapping(source = "cntrDtlAdr", target = "CNTR_DTL_ADR")
    @Mapping(source = "bryyMmdd", target = "BRYY_MMDD")
    @Mapping(source = "sexDvCd", target = "SEX_DV_CD")
    @Mapping(source = "istllNm", target = "ISTLL_NM")
    WctiContractDetailDto.SearchRes wctiContractDetailDvoToSearchRes(WctiContractDetailDvo dvo);
}
