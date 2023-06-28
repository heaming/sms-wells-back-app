package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundPresentStateDvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbRefundPresentStateConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbRefundPresentStateMapper;

import static com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundPresentStateDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
public class WwdbRefundPresentStateService {

    private final WwdbRefundPresentStateMapper mapper;
    private final WwdbRefundPresentStateConverter converter;

    public PagingResult<SearchRes> getRefundPresentStatePages(SearchReq dto, PageInfo pageInfo) {

        PagingResult<WwdbRefundPresentStateDvo> result = this.mapper.selectRefundPresentStatePages(dto, pageInfo);
        PagingResult<SearchRes> converterResult = this.converter.WwdbRefundPresentStateDvoToSearchRes(result);

        converterResult.getPageInfo().setPageIndex(result.getPageInfo().getPageIndex());
        converterResult.getPageInfo().setPageSize(result.getPageInfo().getPageSize());
        converterResult.getPageInfo().setTotalCount(result.getPageInfo().getTotalCount());

        return converterResult;
    }

    public List<SearchRes> getRefundPresentStatesForExcelDownload(SearchReq dto) {
        return this.converter.WwdbRefundPresentStateDvoToListSearchRes(this.mapper.selectRefundPresentStatePages(dto));
    }
}
