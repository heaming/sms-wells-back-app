package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtCleanerDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtCleanerDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdCleaningCostMgtCleanerMapper {

    PagingResult<SearchRes> selectCleaningCostCleanerBusinessManager(SearchReq req, PageInfo pageInfo);

    PagingResult<SearchRes> selectCleaningCostCleanerPersonInCharge(SearchReq req, PageInfo pageInfo);

    List<SearchRes> selectCleaningCostCleanerBusinessManager(SearchReq req);

    List<SearchRes> selectCleaningCostCleanerPersonInCharge(SearchReq req);

    int removeCleanerCostCleanerManagement(String clinrRgnos);
}
