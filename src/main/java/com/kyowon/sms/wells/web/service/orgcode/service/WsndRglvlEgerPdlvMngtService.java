package com.kyowon.sms.wells.web.service.orgcode.service;

import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndRglvlEgerPdlvMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRglvlEgerPdlvMngtDto.*;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsndRglvlEgerPdlvMngtService {
    private final WsndRglvlEgerPdlvMngtMapper mapper;

    public PagingResult<SearchRes> getRglvlEgerPdlvMngtPages(SearchReq dto, PageInfo pageInfo){
        PagingResult<SearchRes> dtos = mapper.selectRglvlEgerPdlvMngtPages(dto, pageInfo);
        return dtos;
    }

    public List<SearchRes> getRglvlEgerPdlvMngtExcelDownload(SearchReq dto){
        return mapper.selectRglvlEgerPdlvMngtPages(dto);
    }
}
