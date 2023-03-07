package com.kyowon.sms.wells.web.contract.interfaces.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractInstallHistoryDto;
import com.kyowon.sms.wells.web.contract.interfaces.dvo.WctiContractInstallHistoryDvo;

@Mapper(componentModel = "spring")
public interface WctiContractInstallHistoryConverter {
    List<WctiContractInstallHistoryDto.SearchRes> mapWctiContractInstallHistoryDvoToSearchRes(List<WctiContractInstallHistoryDvo> dvo);

    @Mapping(source = "cntrNo", target = "CNTR_NO")
    @Mapping(source = "cntrSn", target = "CNTR_SN")
    @Mapping(source = "cralLocaraTno", target = "CRAL_LOCARA_TNO")
    @Mapping(source = "mexno", target = "MEXNO")
    @Mapping(source = "cralIdvTno", target = "CRAL_IDV_TNO")
    @Mapping(source = "locaraTno", target = "LOCARA_TNO")
    @Mapping(source = "exno", target = "EXNO")
    @Mapping(source = "idvTno", target = "IDV_TNO")
    @Mapping(source = "chDtm", target = "CH_DTM")
    @Mapping(source = "fnlMdfcUsrId", target = "FNL_MDFC_USR_ID")
    @Mapping(source = "fnlMdfcUsrNm", target = "FNL_MDFC_USR_NM")
    WctiContractInstallHistoryDto.SearchRes wctiContractDetailDvoToSearchRes(WctiContractInstallHistoryDvo dvo);
}
