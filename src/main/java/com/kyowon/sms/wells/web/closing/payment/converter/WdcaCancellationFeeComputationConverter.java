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
    @Mapping(source = "dvo.resRtlfeBorAmt", target = "resRtlfeBorAmt")
    @Mapping(source = "dvo.rgstCostDscBorAmt", target = "rgstCostDscBorAmt")
    @Mapping(source = "dvo.rentalDscBorAmt", target = "rentalDscBorAmt")
    @Mapping(source = "dvo.rstlBorAmt", target = "rstlBorAmt")
    @Mapping(source = "dvo.csmbCostBorAmt", target = "csmbCostBorAmt")
    @Mapping(source = "dvo.PBorAmt", target = "pBorAmt")
    @Mapping(source = "dvo.reqdCsBorAmt", target = "reqdCsBorAmt")
    @Mapping(source = "dvo.lsRntf", target = "lsRntf")
    @Mapping(source = "dvo.borAmt", target = "borAmt")
    SearchRes mapCancellationFeeComputationResultToSearchRes(WdcaCancellationFeeComputationResultDvo dvo);
}
