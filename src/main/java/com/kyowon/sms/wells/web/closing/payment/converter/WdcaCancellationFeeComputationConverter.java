package com.kyowon.sms.wells.web.closing.payment.converter;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kyowon.sms.wells.web.closing.payment.dto.WdcaCancellationFeeComputationDto.SearchReq;
import com.kyowon.sms.wells.web.closing.payment.dto.WdcaCancellationFeeComputationDto.SearchRes;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaCancellationFeeComputationDvo;
import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaCancellationFeeComputationResultDvo;

@Mapper(componentModel = "spring", imports = {StringUtils.class})
public interface WdcaCancellationFeeComputationConverter {
    WdcaCancellationFeeComputationDvo mapSaveReqToWdcaCancellationFeeComputationDvo(SearchReq dto);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "dvo.resRtlfeBorAmt", target = "RES_RTLFE_BOR_AMT")
    @Mapping(source = "dvo.rgstCostDscBorAmt", target = "RGST_COST_DSC_BOR_AMT")
    @Mapping(source = "dvo.rentalDscBorAmt", target = "RENTAL_DSC_BOR_AMT")
    @Mapping(source = "dvo.rstlBorAmt", target = "RSTL_BOR_AMT")
    @Mapping(source = "dvo.csmbCostBorAmt", target = "CSMB_COST_BOR_AMT")
    @Mapping(source = "dvo.PBorAmt", target = "P_BOR_AMT")
    @Mapping(source = "dvo.reqdCsBorAmt", target = "REQD_CS_BOR_AMT")
    @Mapping(source = "dvo.lsRntf", target = "LS_RNTF")
    @Mapping(source = "dvo.borAmt", target = "BOR_AMT")
    SearchRes mapCancellationFeeComputationResultToSearchRes(WdcaCancellationFeeComputationResultDvo dvo);
}
