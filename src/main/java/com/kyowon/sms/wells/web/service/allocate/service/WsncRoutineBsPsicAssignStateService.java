package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRoutineBsPsicAssignStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRoutineBsPsicAssignStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRoutineBsPsicAssignStateMngrInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncRoutineBsPsicAssignStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncRoutineBsPsicAssignStateService {
    private final WsncRoutineBsPsicAssignStateMapper mapper;

    public PagingResult<SearchRes> getRoutineBsPsicAssignStates(SearchReq req, PageInfo pageInfo) {
        return mapper.selectRoutineBsPsicAssignState(req, pageInfo);
    }

    public List<SearchRes> getRoutineBsPsicAssignStatesForExcelDownload(SearchReq req) {
        return mapper.selectRoutineBsPsicAssignState(req);
    }

    public List<HashMap<String, String>> getWellsManager(String dgr2LevlOgId) {
        return mapper.selectWellsManager(dgr2LevlOgId);
    }

    public WsncRoutineBsPsicAssignStateMngrInfoDvo getManagerInfo(SearchReq req) {
        return mapper.selectManagerInfo(req);
    }
}
