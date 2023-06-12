package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsMngrSchdDto;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsMngrSchdMapper;
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
public class WsncBsMngrSchdService {
    private final WsncBsMngrSchdMapper mapper;

    public List<WsncBsMngrSchdDto.SearchRes> getBsMngrSchdAgrg(
        WsncBsMngrSchdDto.SearchReq dto
    ) {
        return mapper.selectBsMngrSchdAgrg(dto);
    }

    public PagingResult<WsncBsMngrSchdDto.SearchRes> getBsMngrSchdPages(
        WsncBsMngrSchdDto.SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectBsMngrSchdPages(dto, pageInfo);
    }
}
