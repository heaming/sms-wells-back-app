package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaSampleDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaReceiptsAndPaymentsConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaReceiptsAndPaymentsMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReceiptsAndPaymentsDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaReceiptsAndPaymentsService {

    private final WsnaReceiptsAndPaymentsMapper mapper;
    private final WsnaReceiptsAndPaymentsConverter converter;

    public PagingResult<SearchRes> getReceiptsAndPaymentsPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectReceiptsAndPaymentsPages(dto, pageInfo);
    }

    public List<SearchRes> getReceiptsAndPaymentssForExcelDownload(SearchReq dto) {
        return mapper.selectReceiptsAndPaymentsPages(dto);
    }
}
