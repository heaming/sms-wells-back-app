package com.kyowon.sms.wells.web.competence.voc.mapper;

import com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto.SearchBranchManagerReq;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto.SearchBranchManagerRes;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto.SearchReq;
import com.kyowon.sms.wells.web.competence.voc.dto.WpshFalseVisitMgtDto.SearchRes;
import com.kyowon.sms.wells.web.competence.voc.dvo.WpshFalseVisitMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    SearchBranchManagerRes selectBranchManager(SearchBranchManagerReq req);
}
