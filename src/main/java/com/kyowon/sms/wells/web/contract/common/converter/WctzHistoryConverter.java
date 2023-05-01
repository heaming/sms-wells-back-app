package com.kyowon.sms.wells.web.contract.common.converter;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kyowon.sms.wells.web.contract.common.dvo.*;

@Mapper(componentModel = "spring")
public interface WctzHistoryConverter {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctzCntrDetailChangeHistDvo convertCntrDetailToChangeHist(
        WctzCntrDetailChangeHistDvo source,
        @MappingTarget
        WctzCntrDetailChangeHistDvo target
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctzTxinvRcpBaseChangeHistDvo convertTaxInvoiceReceiptBaseToChangeHist(
        WctzTxinvRcpBaseChangeHistDvo source,
        @MappingTarget
        WctzTxinvRcpBaseChangeHistDvo target
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctzCntrBasicChangeHistDvo convertCntrBasicToChangeHist(
        WctzCntrBasicChangeHistDvo source,
        @MappingTarget
        WctzCntrBasicChangeHistDvo target
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctzAcmpalContractIzHistDvo convertAcmpalCntrIzToChangeHist(
        WctzAcmpalContractIzHistDvo source,
        @MappingTarget
        WctzAcmpalContractIzHistDvo target
    );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    WctzContractWellsDetailHistDvo convertCntrWellsDetailToChangeHist(
        WctzContractWellsDetailHistDvo source,
        @MappingTarget
        WctzContractWellsDetailHistDvo target
    );
}
