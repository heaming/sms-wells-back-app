package com.kyowon.sms.wells.web.service.visit.mapper;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbEngineerAsRevisitListSearchDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbEngineerAsRevisitListSearchDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbEngineerAsRevisitListSearchMapper {

    PagingResult<WsnbEngineerAsRevisitListSearchDvo> selectEngineerAsRevisitListSearchPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<WsnbEngineerAsRevisitListSearchDvo> selectEngineerAsRevisitListSearchPages(
        SearchReq dto
    );
}
