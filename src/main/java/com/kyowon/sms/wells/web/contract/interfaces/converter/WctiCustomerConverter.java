package com.kyowon.sms.wells.web.contract.interfaces.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiCustomerDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiCustomerDvo;

@Mapper(componentModel = "spring")
public interface WctiCustomerConverter {
    List<WctiCustomerDto.SearchRes> mapWctiCustomerDvoToSearchRes(List<WctiCustomerDvo> dvo);

    @Mapping(source = "cstDv", target = "CST_DV")
    @Mapping(source = "cstNo", target = "CST_NO")
    @Mapping(source = "itgCstNo", target = "ITG_CST_NO")
    @Mapping(source = "cstKnm", target = "CST_KNM")
    @Mapping(source = "cralLocaraTno", target = "CRAL_LOCARA_TNO")
    @Mapping(source = "mexno", target = "MEXNO")
    @Mapping(source = "cralIdvTno", target = "CRAL_IDV_TNO")
    @Mapping(source = "locaraTno", target = "LOCARA_TNO")
    @Mapping(source = "exno", target = "EXNO")
    @Mapping(source = "idvTno", target = "IDV_TNO")
    @Mapping(source = "emadr", target = "EMADR")
    @Mapping(source = "bryyMmdd", target = "BRYY_MMDD")
    @Mapping(source = "sexDvCd", target = "SEX_DV_CD")
    WctiCustomerDto.SearchRes wctiCustomerDvoToSearchRes(WctiCustomerDvo dvo);
}
