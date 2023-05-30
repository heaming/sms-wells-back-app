package com.kyowon.sms.wells.web.competence.education.mapper;

import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMngtDto.SearchReq;
import static com.kyowon.sms.wells.web.competence.education.dto.WpsbZoomMngtDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.competence.education.dvo.WpsbZoomMngtDvo;

@Mapper
public interface WpsbZoomMngtMapper {
    List<SearchRes> selectZooms(
        SearchReq dto
    );

    int insertZoom(WpsbZoomMngtDvo dvo);

    int updateZoom(WpsbZoomMngtDvo dvo);

    int removeZoom(WpsbZoomMngtDvo dvo);

    int removeAllZoom();

    int deleteZoom(String hgrSvEducMnalId);

}
