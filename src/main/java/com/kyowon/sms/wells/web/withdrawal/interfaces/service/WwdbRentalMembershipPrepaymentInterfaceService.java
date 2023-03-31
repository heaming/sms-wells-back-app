package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import com.kyowon.sms.wells.web.withdrawal.interfaces.converter.WwdbRentalMembershipPrepaymentConverter;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchExpectedInfoReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchExpectedInfoRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchInfoReq;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbRentalMembershipPrepaymentInterfaceDto.SearchInfoRes;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dvo.WwdbRentalMembershipPrepaymentInterfaceDvo;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbRentalMembershipPrepaymentInterfaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WwdbRentalMembershipPrepaymentInterfaceService {
    private final WwdbRentalMembershipPrepaymentInterfaceMapper mapper;
    private final WwdbRentalMembershipPrepaymentConverter converter;

    public List<SearchInfoRes> getRentalMembershipPrepaymentInfos(SearchInfoReq dto) {
        return mapper.selectRentalMembershipPrepaymentInfos(dto);
    }

    public List<SearchExpectedInfoRes> getRentalMembershipPrepaymentExpectedInfos(SearchExpectedInfoReq dto) {

        return converter.mapWwdbRentalMembershipPrepaymentDvoToSearchRes(mapper.selectRentalMembershipPrepaymentExpectedInfos(dto));
    }
}
