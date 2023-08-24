package com.kyowon.sms.wells.web.service.visit.mapper;

import static com.kyowon.sms.wells.web.service.visit.dto.WsnbMobileHappycallSearchDto.SearchReq;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbMobileHappycallSearchDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnbMobileHappycallSearchMapper {

    PagingResult<WsnbMobileHappycallSearchDvo> selectMobileHappycallSearchPages(
        SearchReq dto, PageInfo pageInfo
    );

    List<WsnbMobileHappycallSearchDvo> selectMobileHappycallSearchPages(
        SearchReq dto
    );
}
