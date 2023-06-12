package com.kyowon.sms.wells.web.competence.voc.mapper;

import static com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.voc.dvo.WpshFalseVisitMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WpshFalseVisitMgtMapper {

    PagingResult<SearchRes> selectFalsehoodPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectFalsehoodPages(
        SearchReq dto
    );

    int insertFalsevisit(WpshFalseVisitMgtDvo dvo);

    int updateFalsevisit(WpshFalseVisitMgtDvo dvo);

    int removeFalsevisit(WpshFalseVisitMgtDvo dvo);
}
