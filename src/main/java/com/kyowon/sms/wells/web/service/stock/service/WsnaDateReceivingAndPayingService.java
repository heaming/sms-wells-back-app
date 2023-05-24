package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaDateReceivingAndPayingConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaDateReceivingAndPayingMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaDateReceivingAndPayingDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaDateReceivingAndPayingService {

    private final WsnaDateReceivingAndPayingMapper mapper;
    private final WsnaDateReceivingAndPayingConverter converter;

    public PagingResult<SearchRes> getDateReceivingAndPayings(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectDateReceivingAndPayings(dto, pageInfo);
    }

    public List<SearchRes> getDateReceivingAndPayingsForExcelDownload(SearchReq dto) {
        return mapper.selectDateReceivingAndPayings(dto);
    }
}
