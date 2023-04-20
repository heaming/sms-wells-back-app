package com.kyowon.sms.wells.web.contract.salesstatus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.salesstatus.mapper.WcteSecProductMapper;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteSecProductDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WcteSecProductService {

    private final WcteSecProductMapper mapper;

    public PagingResult<SearchRes> getReservationPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectReservationPages(dto, pageInfo);
    }

    public List<SearchRes> getReservationsForExcelDownload(SearchReq dto) {
        return mapper.selectReservationPages(dto);
    }
}
