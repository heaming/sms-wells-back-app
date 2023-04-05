package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdCleaningCostMgtMapper {

    PagingResult<SearchRes> selectCleaningCostBusinessManager(SearchReq req, PageInfo pageInfo);

    PagingResult<SearchRes> selectCleaningCostPersonInCharge(SearchReq req, PageInfo pageInfo);

    List<SearchRes> selectCleaningCostBusinessManager(SearchReq req);

    List<SearchRes> selectCleaningCostPersonInCharge(SearchReq req);

    int removeCleaningCost(String clinrRgnos);
}
