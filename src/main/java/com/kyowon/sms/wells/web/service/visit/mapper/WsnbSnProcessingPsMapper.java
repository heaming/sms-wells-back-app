package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbSnProcessingPsDto.*;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbSnProcessingPsMapper {
    PagingResult<SearchCntrs> selectSnProcessingPsCntrs(SearchReq dto, PageInfo pageInfo);

    List<SearchCntrs> selectSnProcessingPsCntrs(SearchReq dto);

    List<SearchRatio> selectSnProcessingPsRatio(SearchReq dto);

    List<SearchPuPartPdRes> selectSnProcessingPsPuPartPds(SearchPuPartPdReq dto);

    SearchCstSignCn selectSnProcessingPsCstSignCn(String cstSvAsnNo);
}
