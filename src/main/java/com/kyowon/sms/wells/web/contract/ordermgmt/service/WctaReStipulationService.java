package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaReStipulationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.SearchRes;

@Service
@RequiredArgsConstructor
public class WctaReStipulationService {

    private final WctaReStipulationMapper mapper;

    public PagingResult<SearchRes> getReStipulationCustomerPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectReStipulationCustomers(dto, pageInfo);
    }
}
