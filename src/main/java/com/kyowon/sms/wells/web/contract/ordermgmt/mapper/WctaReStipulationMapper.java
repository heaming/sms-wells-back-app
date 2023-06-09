package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.SearchReq;
import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReStipulationDto.SearchRes;

@Mapper
public interface WctaReStipulationMapper {

    PagingResult<SearchRes> selectReStipulationCustomers(
        SearchReq dto,
        PageInfo pageInfo
    );

    Integer selectReStipulationCustomerCounts(
        SearchReq dto
    );
}
