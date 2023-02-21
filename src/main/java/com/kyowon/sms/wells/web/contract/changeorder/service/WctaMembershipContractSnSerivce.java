package com.kyowon.sms.wells.web.contract.changeorder.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaMembershipContractSnDto.SearchReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctaMembershipContractSnDto.SearchRes;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctaMembershipContractSnMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaMembershipContractSnSerivce {

    private final WctaMembershipContractSnMapper mapper;

    @Transactional
    public List<SearchRes> getMembershipContractSn(SearchReq dto) {
        return mapper.selectMembershipContractSn(dto);
    }
}
