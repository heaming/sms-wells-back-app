package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncFixationVisitDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncFixationVisitDvo;
import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncFixationVisitMapper {
    PagingResult<WsncFixationVisitDto.SearchRes> selectFixationVisits(
        WsncFixationVisitDto.SearchReq dto, PageInfo pageInfo
    );

    WsncFixationVisitDto.SearchRegRes selectFixationVisit(
        WsncFixationVisitDto.SearchRegReq dto
    );

    int updateFixationVisit(WsncFixationVisitDvo dvo);

    int insertFixationVisit(WsncFixationVisitDvo dvo);
}
