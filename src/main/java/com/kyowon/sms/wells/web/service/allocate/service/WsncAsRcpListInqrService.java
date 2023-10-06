package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncAsRcpListInqrConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsRcpListDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsRcpListDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncAsRcpListInqrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncAsRcpListInqrService {

    private final WsncAsRcpListInqrMapper mapper;

    private final WsncAsRcpListInqrConverter converter;

    public PagingResult<SearchRes> getAsRcpListInqrPages(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        PagingResult<SearchRes> pagingResult = converter
            .mapAllDvoToSearchRes(mapper.selectAsRcpListInqrs(dto, pageInfo));
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    public List<SearchRes> getAsRcpListInqrForExcelDownload(
        SearchReq dto
    ) {
        return converter.mapAllDvoToSearchRes(mapper.selectAsRcpListInqrs(dto));
    }

}
