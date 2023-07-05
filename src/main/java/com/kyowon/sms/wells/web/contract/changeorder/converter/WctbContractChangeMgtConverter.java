package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.FindBeforeChangeCheckReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SaveCancelReq;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractChangeDvo;

@Mapper(componentModel = "spring")
public interface WctbContractChangeMgtConverter {
    WctbContractChangeDvo findCheckReqToWctbContractChangeDvo(FindBeforeChangeCheckReq dto);

    WctbContractChangeDvo saveCancelReqToWctbContractChangeDvo(SaveCancelReq dto);

    WctbContractChangeDvo mapEditPartnerReqToWctbContractChangeMngtDvo(
        WctbContractChangeMngtDto.EditPartnerReq dto
    );
}
