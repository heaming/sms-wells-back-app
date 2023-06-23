package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemReceivingAndPayingConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemReceivingAndPayingDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReceiptsAndPaymentsDvo;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemReceivingAndPayingMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemReceivingAndPayingDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaItemReceivingAndPayingService {

    private final WsnaItemReceivingAndPayingConverter converter;
    private final WsnaItemReceivingAndPayingMapper mapper;

    public PagingResult<SearchRes> getReceiptsAndPaymentsPages(SearchReq dto, PageInfo pageInfo) {
        WsnaReceiptsAndPaymentsDvo dvo = this.converter.mapSearchReqToWsnaReceiptsAndPaymentsDvo(dto);

        return this.setPaging(dvo, pageInfo);

    }

    private PagingResult<SearchRes> setPaging(WsnaReceiptsAndPaymentsDvo dvo, PageInfo pageInfo) {

        boolean isNeed = pageInfo.isNeedTotalCount();
        if (isNeed) {
            long totalCount = this.mapper.selectReceiptsAndPaymentCount(dvo);
            pageInfo.setTotalCount(totalCount);
            pageInfo.setNeedTotalCount(Boolean.FALSE);
        }

        int pageIndex = pageInfo.getPageIndex();
        int pageSize = pageInfo.getPageSize();

        int startRow = (pageIndex - 1) * pageSize;
        dvo.setOffset(startRow);
        dvo.setSize(pageSize);

        List<SearchRes> pages = mapper.selectReceiptsAndPaymentsPages(dvo);
        return new PagingResult<>(pages, pageInfo);

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
