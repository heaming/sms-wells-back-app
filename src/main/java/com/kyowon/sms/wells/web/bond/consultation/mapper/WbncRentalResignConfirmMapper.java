package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignConfirmDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignConfirmDto.SearchRes;

@Mapper
public interface WbncRentalResignConfirmMapper {
    List<SearchRes> selectRentalResignConfirms(SearchReq dto);
}
