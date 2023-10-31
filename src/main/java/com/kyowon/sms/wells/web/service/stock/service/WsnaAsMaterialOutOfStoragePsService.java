package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaAsMaterialOutOfStoragePsConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAsMaterialOutOfStoragePsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaAsMaterialOutOfStoragePsService {

    private final WsnaAsMaterialOutOfStoragePsMapper mapper;

    private final WsnaAsMaterialOutOfStoragePsConverter converter;

    public PagingResult<SearchRes> getAsMaterialOutOfStoragePsPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<SearchRes> pagingResult = converter
            .mapDvoToSearchResPages(mapper.selectAsMaterialOutOfStorages(dto, pageInfo));
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    public List<SearchRes> getAsMaterialOutOfStoragePsForExcelDownload(SearchReq dto) {
        return converter.mapDvoToSearchRes(mapper.selectAsMaterialOutOfStorages(dto));
    }
}
