package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsManagerScheduleDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsManagerScheduleMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 *
 *
 * W-SV-U-0089M01 BS관리일정조회
 *
 *
 * @author 37615 홍세기
 * @since 2023-06-06
 */
@Service
@RequiredArgsConstructor
public class WsncBsManagerScheduleService {
    private final WsncBsManagerScheduleMapper mapper;

    public List<WsncBsManagerScheduleDto.Aggregates> getBsScheduleDateAgrg(
        WsncBsManagerScheduleDto.SearchReq dto
    ) {
        return mapper.selectBsScheduleDateAgrg(dto);
    }

    public PagingResult<WsncBsManagerScheduleDto.SearchRes> getBsScheduleDatePages(
        WsncBsManagerScheduleDto.SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectBsScheduleDatePages(dto, pageInfo);
    }
}
