package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaInstAssignSetDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaInstAssignSetDto.SearchRes;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaInstAssignSetDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WsnaInstAssignSetMapper {

    PagingResult<SearchRes> selectInstAssignSet(SearchReq dto, PageInfo pageInfo);

    int selectInstAssignSeq(WsnaInstAssignSetDvo dvo);

    int selectInstAssignCnt(WsnaInstAssignSetDvo dvo);

    int insertInstAssignBaseSet(WsnaInstAssignSetDvo dvo);

    int insertInstAssignDtlSet(WsnaInstAssignSetDvo dvo);

    int updateInstAssignBaseSet(WsnaInstAssignSetDvo dvo);

    int deleteInstAssignSet(WsnaInstAssignSetDvo dvo);

}
