package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaStockStatusControlConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStockStatusControlMapper;
import org.springframework.stereotype.Service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStatusControlDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaStockStatusControlService {

    private final WsnaStockStatusControlMapper mapper;
    private final WsnaStockStatusControlConverter converter;

    public PagingResult<SearchRes> getStockStatusControlPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectStockStatusControlPages(dto, pageInfo);
    }

    public List<SearchRes> getStockStatusControlsForExcelDownload(SearchReq dto) {
        return mapper.selectStockStatusControlPages(dto);
    }

    public List<SearchWarehouseRes> getStockStatusControlsWarehouse(SearchWarehouseReq dto) {
        return mapper.selectStockStatusControlWarehouse(dto);
    }

    public FindOgNmRes getOrganizationDeptName(String wareNo) {
        return mapper.selectOrganizationDeptName(wareNo);
    }

    public List<FinditmPdCdRes> getStockStatusItmPdCd(String itmKnd) {
        return mapper.selectStockStatusItmPdCd(itmKnd);
    }
}
