package com.kyowon.sms.wells.web.contract.ordermgmt.converter;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctbCancellationMtrSetDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctbCancellationMtrSetDvo;

@Mapper(componentModel = "spring")
public interface WctbCancellationMtrSetConverter {
    WctbCancellationMtrSetDvo mapSearchResToWctbCancellationMtrSetDvo(WctbCancellationMtrSetDto.SearchRes res);
}
