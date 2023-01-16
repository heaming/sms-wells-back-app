package com.kyowon.sms.wells.web.contract.orderstatus.converter;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.SearchRes;

import org.mapstruct.Mapper;

import com.kyowon.sms.wells.web.contract.orderstatus.dvo.WctdExpiredRetentionCntrDvo;

@Mapper(componentModel = "spring")
public interface WctdExpiredRetentionCntrConverter {
    SearchRes mapWctdExpiredRetentionCntrDvoToSearchRes(WctdExpiredRetentionCntrDvo dvo);
}
