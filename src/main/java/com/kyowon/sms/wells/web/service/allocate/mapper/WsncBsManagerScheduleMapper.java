package com.kyowon.sms.wells.web.service.allocate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsManagerScheduleDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 *
 * W-SV-U-0089M01 BS관리일정조회(일자별)
 *
 *
 * @author 37615 홍세기
 * @since 2023-06-12
 */

@Mapper
public interface WsncBsManagerScheduleMapper {
    List<WsncBsManagerScheduleDto.Aggregates> selectBsScheduleDateAgrg(
        WsncBsManagerScheduleDto.SearchReq dto
    );

    PagingResult<WsncBsManagerScheduleDto.SearchRes> selectBsScheduleDatePages(
        WsncBsManagerScheduleDto.SearchReq dto, PageInfo pageInfo
    );

    List<WsncBsManagerScheduleDto.Weekres> selectWeeksCodes(
        WsncBsManagerScheduleDto.Weekreq dto
    );

    PagingResult<WsncBsManagerScheduleDto.Detailres> selectBsScheduleDateDetail(
        WsncBsManagerScheduleDto.Detailreq dto
    );

}
