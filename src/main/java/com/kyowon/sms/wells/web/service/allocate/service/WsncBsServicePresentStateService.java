package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsServicePresentStateMapper;
import static com.kyowon.sms.wells.web.service.allocate.dto.WsncBsServicePresentStateDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncBsServicePresentStateService {
    private final WsncBsServicePresentStateMapper mapper;

    public PagingResult<SearchResList> getBsServicePresentStateList(SearchReq dto, PageInfo pageInfo){
        PagingResult<SearchResList> dtos = mapper.selectBsServicePresentStates(dto, pageInfo);
        log.debug("getBsServicePresentStateList dtos >>>>>>>", dtos);
        return dtos;
    }

    public List<SearchResList> getBsServicePresentStateExcelDownload(SearchReq dto){
        return mapper.selectBsServicePresentStates(dto);
    }

    public List<SearchResInfo> getBsServicePresentStateInfo(SearchReq dto){
        return mapper.selectBsServicePresentStateInfo(dto);
    }
}
