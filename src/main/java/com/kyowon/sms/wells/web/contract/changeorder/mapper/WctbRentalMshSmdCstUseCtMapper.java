package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbRentalMshSmdCstUseCtDvo;

@Mapper
public interface WctbRentalMshSmdCstUseCtMapper {
    WctbRentalMshSmdCstUseCtDvo selectRentalMshSmdCstUseCt(WctbRentalMshSmdCstUseCtDvo dvo);
}
