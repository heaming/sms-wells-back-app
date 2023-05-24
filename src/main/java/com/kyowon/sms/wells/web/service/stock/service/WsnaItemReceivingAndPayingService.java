package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemReceivingAndPayingMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaItemReceivingAndPayingService {

    private final WsnaItemReceivingAndPayingMapper mapper;

    public PagingResult<SearchRes> getReceiptsAndPaymentsPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectReceiptsAndPaymentsPages(dto, pageInfo);
    }

    public List<SearchRes> getReceiptsAndPaymentssForExcelDownload(SearchReq dto) {
        return mapper.selectReceiptsAndPaymentsPages(dto);
    }

    public PagingResult<SearchDateRes> getDateReceivingAndPayings(
        SearchDateReq dto, PageInfo pageInfo
    ) {
        return mapper.selectDateReceivingAndPayings(dto, pageInfo);
    }

    public List<SearchDateRes> getDateReceivingAndPayingsForExcelDownload(SearchDateReq dto) {
        return mapper.selectDateReceivingAndPayings(dto);
    }
}
