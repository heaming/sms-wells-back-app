package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAsMaterialOutOfStoragePsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaAsMaterialOutOfStoragePsService {
    private final WsnaAsMaterialOutOfStoragePsMapper mapper;

    public PagingResult getAsMaterialOutOfStoragePsPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectAsMaterialOutOfStorages(dto, pageInfo);
    }

    public List getAsMaterialOutOfStoragePsForExcelDownload(SearchReq dto) {
        return mapper.selectAsMaterialOutOfStorages(dto);
    }
}
