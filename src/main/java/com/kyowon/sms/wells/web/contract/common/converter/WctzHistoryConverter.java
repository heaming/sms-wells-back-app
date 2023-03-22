package com.kyowon.sms.wells.web.contract.common.converter;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDetailChangeHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzCntrDtlStatChHistDvo;
import com.kyowon.sms.wells.web.contract.common.dvo.WctzContraceDetailHistDvo;

@Mapper(componentModel = "spring")
public interface WctzHistoryConverter {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctzCntrDetailChangeHistDvo convertCntrDetailToChangeHist(
        WctzCntrDetailChangeHistDvo source,
        @MappingTarget
        WctzCntrDetailChangeHistDvo target
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctzCntrDtlStatChHistDvo convertCntrDtlStatToChangeHist(
        WctzCntrDtlStatChHistDvo source,
        @MappingTarget
        WctzCntrDtlStatChHistDvo target
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctzContraceDetailHistDvo convertContractDetailToHist(
        WctzContraceDetailHistDvo source,
        @MappingTarget
        WctzContraceDetailHistDvo target
    );
}
