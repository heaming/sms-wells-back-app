package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaPdctClsfDvRcpQtyInqrDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaPdctClsfDvRcpQtyInqrMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaPdctClsfDvRcpQtyInqrService {
    private final WctaPdctClsfDvRcpQtyInqrMapper mapper;

    public int getPdctClsfDvRcpQtyInqr(SearchReq dto) {
        return mapper.selectContractCount(dto);
    }

}
