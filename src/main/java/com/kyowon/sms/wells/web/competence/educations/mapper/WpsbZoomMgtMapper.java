package com.kyowon.sms.wells.web.competence.educations.mapper;

import static com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.educations.dto.WpsbZoomMgtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.educations.dvo.WpsbZoomMgtDvo;

@Mapper
public interface WpsbZoomMgtMapper {
    List<SearchRes> getZooms(
        SearchReq dto
    );

    int insertZoom(WpsbZoomMgtDvo dvo);

    int updateZoom(WpsbZoomMgtDvo dvo);

    int removeZoom(WpsbZoomMgtDvo dvo);

}
