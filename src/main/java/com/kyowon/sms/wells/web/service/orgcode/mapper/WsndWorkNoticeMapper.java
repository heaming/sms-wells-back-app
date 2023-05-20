package com.kyowon.sms.wells.web.service.orgcode.mapper;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndWorkNoticeDto.*;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.orgcode.dvo.WsndWorkNoticeDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsndWorkNoticeMapper {
    PagingResult<SearchRes> selectWorkNotices(
        SearchReq dto, PageInfo pageInfo
    );

    List<SearchRes> selectWorkNotices(SearchReq dto);

    Optional<FindRes> selectWorkNoticeDetail(FindReq dto);

    List<SearchProductRes> selectProductsByProductGroup(String pdGrpCd);

    int insertWorkNotice(WsndWorkNoticeDvo dvo);

    int updateWorkNotice(WsndWorkNoticeDvo dvo);
}
