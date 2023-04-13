package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaRentalProductPrcCalcDvo;

@Mapper
public interface WctaRentalProductPrcCalcMapper {
    List<WctaRentalProductPrcCalcDvo> selectRentalFeeCalculation(WctaRentalProductPrcCalcDvo dvo);
}
