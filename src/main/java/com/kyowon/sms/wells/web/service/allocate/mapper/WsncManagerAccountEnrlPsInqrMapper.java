package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagerAccountEnrlPsInqrDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WsncManagerAccountEnrlPsInqrMapper {
    // 총괄단 집계
    List<SearchGnrdvAgrgRes> selectManagerAccountEnrlPss(SearchReq searchReq);
    // 매니저 계정 및 재적 현황
    List<SearchMngerAccEnrlPsRes> selectMngerAccEnrlPss(SearchReq searchReq);
}
