package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaAssignExcludeItemConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.WareRes;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAssignExcludeItemMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaAssignExcludeItemService {

    private final WsnaAssignExcludeItemMapper mapper;

    private final WsnaAssignExcludeItemConverter converter;

    public PagingResult<SearchRes> getAssignExcludeItems(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectAssignExcludeItems(dto, pageInfo);
    }

    public List<WareRes> getWarehouse(SearchReq dto){
        return mapper.selectWarehouse(dto);
    }

    public int saveAssignExcludeItems(List<SaveReq> list) {
        int cnt = 0;
        List<WsnaAssignExcludeItemDvo> dvoList = converter.mapAllSaveReqToWsnaAssignExcludeItemDvos(list);
        for (WsnaAssignExcludeItemDvo dvo : dvoList) {
            cnt += mapper.saveAssignExcludeItems(dvo);
        }
        return cnt;
    }
}
