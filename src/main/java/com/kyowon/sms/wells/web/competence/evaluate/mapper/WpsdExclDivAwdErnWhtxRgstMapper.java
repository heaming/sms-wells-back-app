package com.kyowon.sms.wells.web.competence.evaluate.mapper;

import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExclDivAwdErnWhtxRgstDto.SearchReq;
import com.kyowon.sms.wells.web.competence.evaluate.dto.WpsdExclDivAwdErnWhtxRgstDto.SearchRes;
import com.kyowon.sms.wells.web.competence.evaluate.dvo.WpsdExclDivAwdErnWhtxRgstDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WpsdExclDivAwdErnWhtxRgstMapper {

    PagingResult<SearchRes> selectExclDivAwdErnWhtxRgstPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    int insertExclDivAwdErnWhtxRgst(WpsdExclDivAwdErnWhtxRgstDvo dvo);
}
