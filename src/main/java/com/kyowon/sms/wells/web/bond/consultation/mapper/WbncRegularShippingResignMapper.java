package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;

@Mapper
public interface WbncRegularShippingResignMapper {
    List<SearchRes> selectRegularShippingResigns(SearchReq dto);

    int updateRegularShippingResignFinalConfirm(WbncAuthorityResignIzDvo dvo);
}
