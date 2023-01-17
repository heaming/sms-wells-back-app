package com.kyowon.sms.wells.web.contract.orderstatus.converter;

import static com.kyowon.sms.wells.web.contract.orderstatus.dto.WctdExpiredRetentionCntrDto.*;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WctdExpiredRetentionCntrConverter {
    SearchRes mapCntrResAndMshCntrResToSearchRes(
        SearchCntrRes cntrs,
        FindMshCntrRes mshCntrs
    );
}
