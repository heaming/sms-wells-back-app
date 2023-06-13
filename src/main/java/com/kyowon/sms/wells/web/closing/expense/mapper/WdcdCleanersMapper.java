package com.kyowon.sms.wells.web.closing.expense.mapper;

import com.kyowon.sms.wells.web.closing.expense.dto.WdcdCleanersMgtDto.SearchReq;
import com.kyowon.sms.wells.web.closing.expense.dvo.WdcdCleanersDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WdcdCleanersMapper {

    PagingResult<WdcdCleanersDvo> selectCleanersBusinessManager(SearchReq req, PageInfo pageInfo);

    PagingResult<WdcdCleanersDvo> selectCleanersPersonInCharge(SearchReq req, PageInfo pageInfo);

    List<WdcdCleanersDvo> selectCleanersBusinessManager(SearchReq req);

    List<WdcdCleanersDvo> selectCleanersPersonInCharge(SearchReq req);

    int deleteCleanersManagement(String clinrRgnos);
}
