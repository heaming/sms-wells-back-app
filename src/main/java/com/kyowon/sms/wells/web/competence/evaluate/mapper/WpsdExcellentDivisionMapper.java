package com.kyowon.sms.wells.web.competence.evaluate.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExcellentDivisionDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WpsdExcellentDivisionMapper {

    PagingResult<HashMap<String, Object>> selectExcellentDivisionPages(
        @Param("param") SearchReq dto,
        PageInfo pageInfo,
        @Param("config") List<HashMap<String, Object>> config,
        @Param("target") List<HashMap<String, Object>> target
    );

    List<HashMap<String, Object>> selectExcellentDivisionPages(
        @Param("param") SearchReq dto,
        @Param("config") List<HashMap<String, Object>> config,
        @Param("target") List<HashMap<String, Object>> target
    );

    List<SearchContestRes> selectContestGroupList(SearchContestReq req);

    List<HashMap<String, Object>> selectGridConfigList(SearchReq req);

    List<HashMap<String, Object>> selectTargetList(SearchReq req);
}
