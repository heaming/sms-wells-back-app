package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdCleanersMapper {

    PagingResult<SearchRes> selectCleanersBusinessManager(SearchReq req, PageInfo pageInfo);

    PagingResult<SearchRes> selectCleanersPersonInCharge(SearchReq req, PageInfo pageInfo);

    List<SearchRes> selectCleanersBusinessManager(SearchReq req);

    List<SearchRes> selectCleanersPersonInCharge(SearchReq req);

    int deleteCleanersManagement(String clinrRgnos);
}
