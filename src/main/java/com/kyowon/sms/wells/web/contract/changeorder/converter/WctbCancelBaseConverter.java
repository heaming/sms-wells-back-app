package com.kyowon.sms.wells.web.contract.changeorder.converter;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.FindCancelRes;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelBaseDto.FindSubDetailRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbCancelBaseDvo;

@Mapper(componentModel = "spring")
public interface WctbCancelBaseConverter {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctbCancelBaseDvo convertCancelBaseDvo(
        WctbCancelBaseDvo source,
        @MappingTarget
        WctbCancelBaseDvo target
    );

    FindSubDetailRes mapCancelBaseDvoToFindSubDetailRes(WctbCancelBaseDvo dvo);

    WctbCancelBaseDvo mapFindCancelReslResToCancelBaseDvo(FindCancelRes dto);
}
