package com.kyowon.sms.wells.web.bond.transfer.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SaveReq;
import com.kyowon.sms.wells.web.bond.transfer.dto.WbnaFosterTransferMgtDto.SearchReq;
import com.kyowon.sms.wells.web.bond.transfer.dvo.WbnaBondContractBaseDvo;

@Mapper(componentModel = "spring")
public interface WbnaFosterTransferMgtConverter {
    WbnaBondContractBaseDvo mapSearchReqToBondContractBaseDvo(SearchReq dto);

    @Mapping(source = "fnlMdfcDtm", target = "orglFnlMdfcDtm")
    WbnaBondContractBaseDvo mapSaveReqToBondContractBaseDvo(SaveReq dto);
}
