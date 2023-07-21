package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaReturningGoodsUseMcntAgrgConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsUseMcntAgrgDto;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaReturningGoodsUseMcntAgrgMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaReturningGoodsUseMcntAgrgService {
    private final WsnaReturningGoodsUseMcntAgrgMapper mapper;
    private final WsnaReturningGoodsUseMcntAgrgConverter converter;

    public List<WsnaReturningGoodsUseMcntAgrgDto.SearchRes> getReturningGoodsUseMcntAgrg(
        WsnaReturningGoodsUseMcntAgrgDto.SearchReq dto
    ) {
        return mapper.selectReturningGoodsUseMcntAgrg(dto);
    }

    public List<WsnaReturningGoodsUseMcntAgrgDto.SearchRes> getReturningGoodsUseMcntAgrgForExcelDownload(
        WsnaReturningGoodsUseMcntAgrgDto.SearchReq dto
    ) {
        return mapper.selectReturningGoodsUseMcntAgrg(dto);
    }

}
