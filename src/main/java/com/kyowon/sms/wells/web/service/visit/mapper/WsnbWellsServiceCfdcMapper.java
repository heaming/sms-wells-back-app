package com.kyowon.sms.wells.web.service.visit.mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.SearchReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.SearchRes;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.HistoryReq;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbWellsServiceCfdcDto.HistoryRes;

import java.util.List;

@Mapper
public interface WsnbWellsServiceCfdcMapper {
    PagingResult<SearchRes> selectWellsServiceConfirmations(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectWellsServiceConfirmations(SearchReq dto);

    PagingResult<HistoryRes> selectWellsServiceConfirmationHistoriesForKakao(
        HistoryReq dto, PageInfo pageInfo
    );

    PagingResult<HistoryRes> selectWellsServiceConfirmationHistoriesForEmail(
        HistoryReq dto, PageInfo pageInfo
    );
}
