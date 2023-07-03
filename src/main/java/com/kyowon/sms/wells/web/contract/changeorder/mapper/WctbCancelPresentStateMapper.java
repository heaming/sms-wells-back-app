package com.kyowon.sms.wells.web.contract.changeorder.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelPresentStateDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelPresentStateDto.SearchRegularShippingRes;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelPresentStateDto.SearchRentalRes;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbCancelPresentStateDto.SearchReq;

@Mapper
public interface WctbCancelPresentStateMapper {

    List<SearchRentalRes> selectRentalCancelPresentStates(
            SearchReq dto
    );


    List<SearchRegularShippingRes> selectRegularShippingCancelPresentStates(
            SearchReq dto
    );

    List<SearchMembershipRes> selectMembershipCancelPresentStates(
            SearchReq dto
    );
}
