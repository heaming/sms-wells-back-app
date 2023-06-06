package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsMngtSchdInqrDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsMngtSchdInqrMapper;
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
public class WsncBsMngtSchdInqrService {
    private final WsncBsMngtSchdInqrMapper mapper;

    public List<WsncBsMngtSchdInqrDto.SearchRes> getBsMngtSchdInqrAgrg(
        WsncBsMngtSchdInqrDto.SearchReq dto
    ) {
        return mapper.selectBsMngtSchdInqrAgrg(dto);
    }

    public PagingResult<WsncBsMngtSchdInqrDto.SearchRes> getBsMngtSchdInqrPages(
        WsncBsMngtSchdInqrDto.SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectBsMngtSchdInqrPages(dto, pageInfo);
    }
}
