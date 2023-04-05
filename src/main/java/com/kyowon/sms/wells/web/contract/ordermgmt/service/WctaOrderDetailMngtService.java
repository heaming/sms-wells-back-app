package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDetailConverter;
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
    private final WctaOrderDetailConverter converter;

    public PagingResult<SearchRes> getOrderDetailRentalPages(SearchReq dto, PageInfo pageInfo) {
        return converter
            .mapWctaOrderDetailRentalPagesDvoToSearchRes(mapper.selectOrderDetailRentalPages(dto, pageInfo));
    }

    public List<SearchRes> getOrderDtlRentalExcels(SearchReq dto) {
        return converter.mapWctaOrderDetailRentalPagesExcelDvoToSearchRes(mapper.selectOrderDetailRentalPages(dto));
    }

    public PagingResult<SearchOrderDetailMshPagesRes> getOrderDetailMshPages(
        SearchOrderDetailMshPagesReq dto, PageInfo pageInfo
    ) {
        return converter
            .mapWctaOrderDetailMembershipPagesDvoToSearchOrderDetailMshPagesRes(
                mapper.selectOrderDetailMshPages(dto, pageInfo)
            );
    }

    public List<SearchOrderDetailMshPagesRes> getOrderDetailMshExcels(SearchOrderDetailMshPagesReq dto) {
        return converter.mapWctaOrderDetailMembershipPagesExcelDvoToSearchOrderDetailMshPagesRes(
            mapper.selectOrderDetailMshPages(dto)
        );
    }

    public PagingResult<SearchOrderDetailRglrDlvrPagesRes> getOrderRegularShippingsPages(
        SearchOrderDetailRglrDlvrPagesReq dto, PageInfo pageInfo
    ) {
        return converter
            .mapWctaOrderDetailRglrDlvrPagesDvoToSearchOrderDetailRglrDlvrPagesRes(
                mapper.selectOrderRegularShippingsPages(dto, pageInfo)
            );
    }

    public List<SearchOrderDetailRglrDlvrPagesRes> getOrderRegularShippingsExcels(
        SearchOrderDetailRglrDlvrPagesReq dto
    ) {
        return converter.mapWctaOrderDetailRglrDlvrPagesExcelDvoToSearchOrderDetailRglrDlvrPagesRes(
            mapper.selectOrderRegularShippingsPages(dto)
        );
    }
}
