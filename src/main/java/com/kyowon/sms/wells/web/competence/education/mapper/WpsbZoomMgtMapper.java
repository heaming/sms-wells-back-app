package com.kyowon.sms.wells.web.competence.education.mapper;

import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto;
import com.kyowon.sms.wells.web.competence.education.dvo.WpsbZoomMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WpsbZoomMgtMapper {
    PagingResult<SearchRes> selectZoomMgtPages(
        SearchReq dto,
        PageInfo pageInfo
    );

    List<SearchRes> selectZoomMgtPages(
        SearchReq dto
    );

    int insertZoomMgt(WpsbZoomMgtDvo dvo);

    int updateZoomMgt(WpsbZoomMgtDvo dvo);

    String selectZoomMgtMnalId(WpsbZoomMgtDto.SaveReq dto);

    int removeZoomMgt(WpsbZoomMgtDvo dvo);

    int removeAllZoomMgt();

    int deleteZoomMgt();

}
