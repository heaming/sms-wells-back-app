package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbMembershipBulkChangeDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbMembershipBulkChangeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbMembershipBulkChangeService {
    private final WctbMembershipBulkChangeMapper mapper;

    public List<SearchRes> getMembershipBulkChanges(String cntrNo, String cntrSn, String rfdt) {
        return mapper.selectMembershipBulkChanges(cntrNo, cntrSn, rfdt);
    }
}
