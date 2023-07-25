package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaChangeOfRentalStatusConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaChangeOfRentalStatusMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaChangeOfRentalStatusDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaChangeOfRentalStatusService {

    private final WsnaChangeOfRentalStatusMapper mapper;
    private final WsnaChangeOfRentalStatusConverter converter;

    public PagingResult<SearchRes> getChangeOfRentalStatusPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectChangeOfRentalStatusPages(dto, pageInfo);
    }

    public List<SearchRes> getChangeOfRentalStatussForExcelDownload(SearchReq dto) {
        return mapper.selectChangeOfRentalStatusPages(dto);
    }

    public List<SearchItmPdCdRes> getChangeOfRentalStatusItmPdCd() {
        return mapper.selectChangeOfRentalStatusItmPdCd();
    }

    public List<SearchWarehouseRes> getChangeOfRentalStatusWarehouse(SearchWarehouseReq dto) {
        return mapper.selectChangeOfRentalStatusWarehouse(dto);
    }
}
