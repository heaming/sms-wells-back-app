package com.kyowon.sms.wells.web.service.allocate.mapper;

import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncRpbLocaraCdMngtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncRpbLocaraCdMngtMapper {

    PagingResult<SearchRes> selectRpbLocaraCdMngtPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectRpbLocaraCdMngtPages(
        SearchReq dto
    );
}
