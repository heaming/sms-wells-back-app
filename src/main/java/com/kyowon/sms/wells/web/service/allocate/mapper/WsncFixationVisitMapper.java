package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncFixationVisitDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncFixationVisitDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsncFixationVisitMapper {
    PagingResult<WsncFixationVisitDto.SearchRes> selectFixationVisits(
        WsncFixationVisitDto.SearchReq dto, PageInfo pageInfo
    );

    PagingResult<WsncFixationVisitDto.SearchRes> selectFixationVisits2(
        WsncFixationVisitDto.SearchReq dto, PageInfo pageInfo
    );

    List<WsncFixationVisitDto.SearchRes> selectFixationVisits3(
        WsncFixationVisitDto.SearchReq dto, PageInfo pageInfo
    );

    List<WsncFixationVisitDto.SearchRes> selectFixationVisits4(
        WsncFixationVisitDto.SearchReq dto, PageInfo pageInfo
    );

    PagingResult<WsncFixationVisitDto.SearchRes> selectFixationVisits5(
        WsncFixationVisitDto.SearchReq dto, PageInfo pageInfo
    );

    List<WsncFixationVisitDto.SearchRes> selectFixationVisits(
        WsncFixationVisitDto.SearchReq dto
    );

    PagingResult<WsncFixationVisitDto.SearchRes> selectFixationVisits2(
        WsncFixationVisitDto.SearchReq dto
    );

    List<WsncFixationVisitDto.SearchRes> selectFixationVisits3(
        WsncFixationVisitDto.SearchReq dto
    );

    List<WsncFixationVisitDto.SearchRes> selectFixationVisits4(
        WsncFixationVisitDto.SearchReq dto
    );

    List<WsncFixationVisitDto.SearchRes> selectFixationVisits6(
        WsncFixationVisitDto.SearchReq dto
    );

    WsncFixationVisitDto.SearchRegRes selectFixationVisit(
        WsncFixationVisitDto.SearchRegReq dto
    );

    int updateFixationVisit(WsncFixationVisitDvo dvo);

    int insertFixationVisit(WsncFixationVisitDvo dvo);
}
