package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaOrderDetailService {
    private final WctaOrderDetailMapper mapper;

    public WctaOrderDetailDto.SearchRes getRelatedContracts(SearchReq dto) {
        // 기존 고객정보
        List<SearchPextCstInfoRes> searchPextCstInfoResList = mapper.selectRelatedContractsPextCstInfo(dto);
        // 신규 고객정보
        List<SearchNewCstInfoRes> searchNewCstInfoResList = mapper.selectRelatedContractsNewCstInfo(dto);

        return new WctaOrderDetailDto.SearchRes(searchPextCstInfoResList, searchNewCstInfoResList);
    }

    public List<SearchCustomerBaseRes> getCustomerBase(SearchReq dto) {
        return mapper.selectOrderDetailCustomerBase(dto);
    }

    public List<SearchContractListsRes> getContractLists(SearchReq dto) {
        return mapper.selectOrderDetailContractLists(dto);
    }
}
