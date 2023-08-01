package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbCancelBorControlStatusDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbCancelBorControlStatusDto.SearchRes;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdcbCancelBorControlStatusMapper {
    PagingResult<SearchRes> selectAdjustCancellationPages(SearchReq req);

}
