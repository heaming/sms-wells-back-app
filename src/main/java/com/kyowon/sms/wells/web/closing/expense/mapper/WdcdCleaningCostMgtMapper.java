package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.CodeRes;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleaningCostMgtDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdCleaningCostMgtMapper {

    List<CodeRes> selectBuilDingCd();

    PagingResult<SearchRes> selectCleaningCost(SearchReq req, PageInfo pageInfo);

    List<SearchRes> selectCleaningCost(SearchReq req);

    int removeCleaningCost(String clingCostAdjRcpNo);
}
