package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaRentalProductPriceDvo;

@Mapper
public interface WctaRentalProductPriceMapper {
    List<WctaRentalProductPriceDvo> selectRentalFeeCalculation(WctaRentalProductPriceDvo dvo);
}
