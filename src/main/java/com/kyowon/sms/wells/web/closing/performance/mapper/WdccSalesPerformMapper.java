package com.kyowon.sms.wells.web.closing.performance.mapper;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformDto.*;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccContractDvo;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccSalesPerformDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdccSalesPerformMapper {
    WdccContractDvo selectContract(SearchReq dto);

    PagingResult<WdccSalesPerformDvo> selectRentalSalesPages(SearchReq dto, PageInfo pageInfo);

    List<WdccSalesPerformDvo> selectRentalSalesPages(SearchReq dto);

    PagingResult<WdccSalesPerformDvo> selectMembershipSalesPages(SearchReq dto, PageInfo pageInfo);

    List<WdccSalesPerformDvo> selectMembershipSalesPages(SearchReq dto);

    PagingResult<WdccSalesPerformDvo> selectRegularShippingSalesPages(SearchReq dto, PageInfo pageInfo);

    List<WdccSalesPerformDvo> selectRegularShippingSalesPages(SearchReq dto);

}
