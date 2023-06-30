package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrAgrgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaReturningGoodsOstrAgrgMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * K-W-SV-U-0110M01 반품출고집계현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.06.29
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaReturningGoodsOstrAgrgService {
    private final WsnaReturningGoodsOstrAgrgMapper mapper;

    public PagingResult<SearchRes> getReturningGoodsOstrAgrg(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectReturningGoodsOstrAgrg(dto, pageInfo);
    }

    public List<SearchRes> getReturningGoodsOstrAgrg(SearchReq dto) {
        return mapper.selectReturningGoodsOstrAgrg(dto);
    }

}
