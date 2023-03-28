package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaOrderDetailMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaOrderDetailMngtService {
    private final WctaOrderDetailMngtMapper mapper;

    public PagingResult<SearchRes> getOrderDetailRentalPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectOrderDetailRentalPages(dto, pageInfo);
    }

    public List<SearchRes> getOrderDtlRentalExcels(SearchReq dto) {
        return mapper.selectOrderDetailRentalPages(dto);
    }

    public PagingResult<SearchOrderDetailMshPagesRes> getOrderDetailMshPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectOrderDetailMshPages(dto, pageInfo);
    }

    public List<SearchOrderDetailMshPagesRes> getOrderDetailMshExcels(SearchReq dto) {
        return mapper.selectOrderDetailMshPages(dto);
    }
}
