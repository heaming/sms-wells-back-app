package com.kyowon.sms.wells.web.competence.education.mapper;

import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMgtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.education.dvo.WpsbZoomMgtDvo;

@Mapper
public interface WpsbZoomMgtMapper {
    List<SearchRes> selectZooms(
        SearchReq dto
    );

    int insertZoom(WpsbZoomMgtDvo dvo);

    int updateZoom(WpsbZoomMgtDvo dvo);

    int removeZoom(WpsbZoomMgtDvo dvo);

    int removeAllZoom();

    int deleteZoom(String hgrSvEducMnalId);

}
