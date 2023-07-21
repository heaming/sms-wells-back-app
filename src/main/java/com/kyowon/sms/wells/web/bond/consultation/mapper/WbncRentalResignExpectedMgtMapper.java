package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.CheckReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.SaveConfirmReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;

@Mapper
public interface WbncRentalResignExpectedMgtMapper {
    List<WbncAuthorityResignIzDvo> selectRentalResignExpecteds(SearchReq dto);

    int selectcheckRentalResignExpectedByReq(CheckReq dto);

    int insertAuthorityResignRentals(@Param("baseDt")
    String baseDt);

    int insertAuthorityResignRegularShippings(@Param("baseDt")
    String baseDt);

    int updateRentalResignExpected(WbncAuthorityResignIzDvo dvo);

    int updateAuthorityResignRentalCnfms(SaveConfirmReq dto);

    int updateAuthorityResignRegularShippingCnfms(SaveConfirmReq dto);
}
