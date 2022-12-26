package com.kyowon.sms.wells.web.service.allocate.mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncFixationVisitDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncFixationVisitDvo;
import org.apache.ibatis.annotations.Mapper;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncFixationVisitMapper {
    PagingResult<WsncFixationVisitDto.SearchFixationVisitRes> selectFixationVisits(
        WsncFixationVisitDto.SearchFixationVisitReq dto, PageInfo pageInfo
    );

    WsncFixationVisitDto.SearchFixationVisitRegRes selectFixationVisit(
        WsncFixationVisitDto.SearchFixationVisitRegReq dto
    );

    int updateFixationVisit(WsncFixationVisitDvo dvo);

    int insertFixationVisit(WsncFixationVisitDvo dvo);
}
