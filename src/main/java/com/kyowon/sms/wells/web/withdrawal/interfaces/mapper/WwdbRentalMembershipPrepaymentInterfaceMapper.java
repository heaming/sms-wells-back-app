package com.kyowon.sms.wells.web.withdrawal.interfaces.mapper;

import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchExpectedInfoReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchInfoReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchInfoRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbRentalMembershipPrepaymentInterfaceDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WwdbRentalMembershipPrepaymentInterfaceMapper {

    List<SearchInfoRes> selectRentalMembershipPrepaymentInfos(SearchInfoReq dto);

    List<WwdbRentalMembershipPrepaymentInterfaceDvo> selectRentalMembershipPrepaymentExpectedInfos(SearchExpectedInfoReq dto);
}
