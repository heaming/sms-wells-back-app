package com.kyowon.sms.wells.web.closing.sales.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbCancelBorControlStatusDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbCancelBorControlStatusDto.SearchRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbCancelBorControlStatusMapper;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WdcbCancelBorControlStatusService {
    private final WdcbCancelBorControlStatusMapper mapper;
    //    private final WdcbCancelBorControlStatusConverter converter;

    public PagingResult<SearchRes> getAdjustCancellationPages(SearchReq req) {
        return mapper.selectAdjustCancellationPages(req);
    }

    //    public List<SearchRes> getAdjustCancellationForExcelDownload(SearchReq req) {
    //        return this.converter.AdjustCancellationForExcelDownload(mapper.selectAdjustCancellationPages(req));
    //    }
}
