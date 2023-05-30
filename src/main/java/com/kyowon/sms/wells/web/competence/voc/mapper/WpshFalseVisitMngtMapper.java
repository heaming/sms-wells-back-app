package com.kyowon.sms.wells.web.competence.voc.mapper;

import static com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMngtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.voc.dvo.WpshFalseVisitMngtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WpshFalseVisitMngtMapper {

    PagingResult<SearchRes> selectFalsehoodMgtPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectFalsehoodMgtPages(
        SearchReq dto
    );

    int insertFalsevisit(WpshFalseVisitMngtDvo dvo);

    int updateFalsevisit(WpshFalseVisitMngtDvo dvo);

    int removeFalsevisit(WpshFalseVisitMngtDvo dvo);
}
