package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaReStipulationMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.SearchRes;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.BasInfoRes;

@Service
@RequiredArgsConstructor
public class WctaReStipulationService {

    private final WctaReStipulationMapper mapper;

    public PagingResult<SearchRes> getReStipulationCustomerPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectReStipulationCustomers(dto, pageInfo);
    }

    public Integer getReStipulationCustomerCounts(SearchReq dto){
        return mapper.selectReStipulationCustomerCounts(dto);
    }

    public List<BasInfoRes> getReStipulationStandardInfo(String cntrNo, Integer cntrSn) {
        return mapper.selectReStipulationStandardInfo(cntrNo, cntrSn);
    }
}
