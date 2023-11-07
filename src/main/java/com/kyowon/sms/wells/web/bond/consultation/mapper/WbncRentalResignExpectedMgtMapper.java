package com.kyowon.sms.wells.web.bond.consultation.mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRentalResignExpectedMgtDto.*;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WbncRentalResignExpectedMgtMapper {
    List<WbncAuthorityResignIzDvo> selectRentalResignExpecteds(SearchReq dto);

    int selectcheckRentalResignExpectedByReq(CheckReq dto);

    int insertAuthorityResignRentals(@Param("baseDt") String baseDt);

    int insertAuthorityResignRegularShippings(@Param("baseDt") String baseDt);

    int updateRentalResignExpected(WbncAuthorityResignIzDvo dvo);

    int updateRentalResignExpectedList(List<WbncAuthorityResignIzDvo> list);

    int updateAuthorityResignRentalCnfms(SaveConfirmReq dto);

    int updateAuthorityResignRegularShippingCnfms(SaveConfirmReq dto);

    List<WbncAuthorityResignIzDvo> selectRentalResignConfirms(@Param("baseDt") String baseDt);

    Integer selectRentalResignExpectedSmsCount(SmsCheckReq dto);

    int updateRentalResignExpectedCancel(SaveCancelReq dto);

    int updateOvrdAthrTrmtPreExcdAdmn(@Param("baseDt") String baseDt);

    int insertRentalCntrResignExpctCancel(SaveCancelReq dto);
}
