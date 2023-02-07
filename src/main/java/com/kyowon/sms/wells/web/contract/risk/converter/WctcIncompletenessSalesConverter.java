package com.kyowon.sms.wells.web.contract.risk.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.contract.common.dto.WctzAddressDto.SearchAdrRes;
import com.kyowon.sms.wells.web.contract.common.dto.WctzTelephoneNumberDto.SearchMpnoRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SaveReq;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchInfoRes;
import com.kyowon.sms.wells.web.contract.risk.dto.WctcIncompletenessSalesDto.SearchRes;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIcptSellChHistDvo;
import com.kyowon.sms.wells.web.contract.risk.dvo.WctcIncompletenessSellIzDvo;

@Mapper(componentModel = "spring")
public interface WctcIncompletenessSalesConverter {
    @Mapping(source = "baseMpno.mpno", target = "baseMpno")
    @Mapping(source = "baseAdr.adr", target = "baseAdr")
    @Mapping(source = "baseAdr.rcgvpKnm", target = "baseRcgvpKnm")
    @Mapping(source = "ojAdr.adr", target = "ojAdr")
    @Mapping(source = "ojAdr.rcgvpKnm", target = "ojRcgvpKnm")
    SearchRes mapIncompletenessSaleInfosToSearchRes(
        SearchInfoRes dto, SearchMpnoRes baseMpno, SearchAdrRes baseAdr, SearchAdrRes ojAdr
    );

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WctcIncompletenessSellIzDvo mapSaveReqToIncompletenessSellIzDvo(SaveReq dto);

    WctcIcptSellChHistDvo mapIncompletenessSellIzDvoToHistDvo(WctcIncompletenessSellIzDvo dvo);
}
