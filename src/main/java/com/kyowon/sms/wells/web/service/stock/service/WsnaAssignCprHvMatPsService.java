package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignCprHvMatPsDto.*;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAssignCprHvMatPsMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsnaAssignCprHvMatPsService {
    private final WsnaAssignCprHvMatPsMapper mapper;

    public PagingResult<SearchRes> getAssignCprHvMatPsPages(SearchReq dto, PageInfo pageInfo){
        PagingResult<SearchRes> dtos = mapper.selectAssignCprHvMatPss(dto, pageInfo);
        return dtos;
    }

    public List<SearchRes> getAssignCprHvMatPsExcelDownload(SearchReq dto){
        return mapper.selectAssignCprHvMatPss(dto);
    }
}
