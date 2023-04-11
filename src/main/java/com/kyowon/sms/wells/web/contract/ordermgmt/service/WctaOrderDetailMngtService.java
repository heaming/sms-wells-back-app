package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import static com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaOrderDetailMngtDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaOrderDetailConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailMembershipPagesRequestDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailRentalPagesRequestDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaOrderDetailSinglePaymentPagesRequestDvo;
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
        WctaOrderDetailRentalPagesRequestDvo dvo = converter.mapSearchReqToWctaOrderDetailRentalPagesDvo(dto);

        // PagingResult<WctaOrderDetailRentalPagesDvo> pagingResultDvo = mapper.selectOrderDetailRentalPages(dvo, pageInfo);
        PagingResult<SearchRes> pagingResultDto = converter
            .mapWctaOrderDetailRentalPagesDvoToSearchRes(mapper.selectOrderDetailRentalPages(dvo, pageInfo));
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;
    }

    public List<SearchRes> getOrderDtlRentalExcels(SearchReq dto) {
        return converter.mapWctaOrderDetailRentalPagesExcelDvoToSearchRes(mapper.selectOrderDetailRentalPages(dto));
    }

    public PagingResult<SearchOrderDetailMshPagesRes> getOrderDetailMshPages(
        SearchOrderDetailMshPagesReq dto, PageInfo pageInfo
    ) {
        WctaOrderDetailMembershipPagesRequestDvo dvo = converter
            .mapSearchOrderDetailMshPagesReqToWctaOrderDetailMembershipPagesDvo(dto);

        PagingResult<SearchOrderDetailMshPagesRes> pagingResultDto = converter
            .mapWctaOrderDetailMembershipPagesDvoToSearchOrderDetailMshPagesRes(
                mapper.selectOrderDetailMshPages(dvo, pageInfo)
            );
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;
    }

    public List<SearchOrderDetailMshPagesRes> getOrderDetailMshExcels(SearchOrderDetailMshPagesReq dto) {
        return converter.mapWctaOrderDetailMembershipPagesExcelDvoToSearchOrderDetailMshPagesRes(
            mapper.selectOrderDetailMshPages(dto)
        );
    }

    public PagingResult<SearchOrderDetailSnglPmntPagesRes> getOrderDetailSpayCntrtPages(
        SearchOrderDetailSnglPmntPagesReq dto, PageInfo pageInfo
    ) {
        WctaOrderDetailSinglePaymentPagesRequestDvo dvo = converter
            .mapSearchOrderDetailSnglPmntPagesReqToWctaOrderDetailSinglePaymentPagesDvo(dto);

        PagingResult<SearchOrderDetailSnglPmntPagesRes> pagingResultDto = converter
            .mapWctaOrderDetailSinglePaymentPagesDvoToSearchOrderDetailSnglPmntPagesRes(
                mapper.selectOrderDetailSpayCntrtPages(dvo, pageInfo)
            );
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;
    }

    public List<SearchOrderDetailSnglPmntPagesRes> getOrderDetailSpayCntrtPagesExcelDownload(
        SearchOrderDetailSnglPmntPagesReq dto
    ) {
        return converter.mapWctaOrderDetailSinglePaymentPagesExcelDvoToSearchOrderDetailSnglPmntPagesRes(
            mapper.selectOrderDetailSpayCntrtPages(dto)
        );
    }

    public PagingResult<SearchOrderDetailRglrDlvrPagesRes> getOrderRegularShippingsPages(
        SearchOrderDetailRglrDlvrPagesReq dto, PageInfo pageInfo
    ) {
        PagingResult<SearchOrderDetailRglrDlvrPagesRes> pagingResultDto = converter
            .mapWctaOrderDetailRglrDlvrPagesDvoToSearchOrderDetailRglrDlvrPagesRes(
                mapper.selectOrderRegularShippingsPages(dto, pageInfo)
            );
        pagingResultDto.setPageInfo(pageInfo);

        return pagingResultDto;
    }

    public List<SearchOrderDetailRglrDlvrPagesRes> getOrderRegularShippingsExcels(
        SearchOrderDetailRglrDlvrPagesReq dto
    ) {
        return converter.mapWctaOrderDetailRglrDlvrPagesExcelDvoToSearchOrderDetailRglrDlvrPagesRes(
            mapper.selectOrderRegularShippingsPages(dto)
        );
    }
}
