package com.kyowon.sms.wells.web.service.orgcode.service;

import com.kyowon.sms.wells.web.service.orgcode.mapper.WsndRglvlEgerPdlvMngtMapper;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.orgcode.dto.WsndRglvlEgerPdlvMngtDto.*;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public int saveRglvlEgerPdlvMngt(List<SaveEgerReq> dtos){
        int processCount = 0;

        for(SaveEgerReq dto : dtos){
            int result = 0;
            result += mapper.insertRglvlEgerPdlvMngts(dto);
            processCount += result;
        }

        return processCount;
    }

    @Transactional
    public int approvalRglvlEgerPdlvMngt(List<SaveEgerReq> dtos){
        int processCount = 0;

        for(SaveEgerReq dto : dtos){
            int result = 0;
            result += mapper.updateRglvlEgerPdlvMngts(dto);
            processCount += result;
        }

        return processCount;
    }
}
