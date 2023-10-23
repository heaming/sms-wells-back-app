package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialDailyTaskTypeOutOfStoragePsDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAsMaterialDailyTaskTypeOutOfStoragePsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WsnaAsMaterialDailyTaskTypeOutOfStoragePsService {
    private final WsnaAsMaterialDailyTaskTypeOutOfStoragePsMapper mapper;

    public PagingResult<SearchRes> getAsMaterialDailyTaskTypeOutOfStoragePss(SearchReq dto, PageInfo pageInfo){
        return mapper.selectAsMaterialDailyTaskTypeOutOfStoragePss(dto, pageInfo);
    }
}
