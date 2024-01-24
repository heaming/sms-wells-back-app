package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRoutineBsPsicAssignStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRoutineBsPsicAssignStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRoutineBsPsicAssignStateMngrInfoDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncRoutineBsPsicAssignStateMapper {

    PagingResult<SearchRes> selectRoutineBsPsicAssignState(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectRoutineBsPsicAssignState(SearchReq dto);

    List<HashMap<String, String>> selectWellsManager(String dgr2LevlOgId);

    WsncRoutineBsPsicAssignStateMngrInfoDvo selectManagerInfo(SearchReq dto);

}
