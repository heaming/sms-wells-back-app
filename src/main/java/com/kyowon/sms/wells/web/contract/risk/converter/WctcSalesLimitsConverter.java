package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.common.dto.WctzAddressDto.SearchAdrRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzTelephoneNumberDto.SearchMpnoRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzTelephoneNumberDto.SearchTnoRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SaveBlacklistReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistInfoRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcSalesLimitsDto.SearchBlacklistRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcSellLimitOjIzDvo;

@Mapper(componentModel = "spring")
public interface WctcSalesLimitsConverter {
    @Mapping(source = "cntrMpno.mpno", target = "cntrMpno")
    @Mapping(source = "cntrTno.tno", target = "cntrTno")
    @Mapping(source = "cntrAdr.adrZip", target = "cntrZip")
    @Mapping(source = "cntrAdr.adr", target = "cntrAdr")
    @Mapping(source = "istllMpno.mpno", target = "istllMpno")
    @Mapping(source = "istllTno.tno", target = "istllTno")
    @Mapping(source = "istllAdr.rcgvpKnm", target = "istllKnm")
    @Mapping(source = "istllAdr.adrZip", target = "istllZip")
    @Mapping(source = "istllAdr.adr", target = "istllAdr")
    @Mapping(source = "prtnrMpno.mpno", target = "prtnrMpno")
    SearchBlacklistRes mapBlacklistInfosToSearchBlacklistRes(
        SearchBlacklistInfoRes dto,
        SearchMpnoRes cntrMpno, SearchTnoRes cntrTno, SearchAdrRes cntrAdr,
        SearchMpnoRes istllMpno, SearchTnoRes istllTno, SearchAdrRes istllAdr,
        SearchMpnoRes prtnrMpno
    );

    @Mapping(source = "cntrTno.tno", target = "cntrTno")
    @Mapping(source = "cntrAdr.adrZip", target = "cntrZip")
    @Mapping(source = "cntrAdr.adr", target = "cntrAdr")
    @Mapping(source = "istllTno.tno", target = "istllTno")
    @Mapping(source = "istllAdr.rcgvpKnm", target = "istllKnm")
    @Mapping(source = "istllAdr.adrZip", target = "istllZip")
    @Mapping(source = "istllAdr.adr", target = "istllAdr")
    SearchBlacklistRes mapBlacklistInfosToSearchBlacklistRes(
        SearchBlacklistInfoRes dto,
        SearchTnoRes cntrTno, SearchAdrRes cntrAdr,
        SearchTnoRes istllTno, SearchAdrRes istllAdr
    );

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WctcSellLimitOjIzDvo mapSaveBlacklistReqToWctcSellLimitOjIzDvo(SaveBlacklistReq dto);
}
