package com.kyowon.sms.wells.web.closing.performance.service;

import com.kyowon.sms.wells.web.closing.performance.dvo.WdccContractDvo;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccSalesPerformDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformDto.*;
import com.kyowon.sms.wells.web.closing.performance.converter.WdccSalesPerformConverter;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccSalesPerformMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WdccSalesPerformService {

    private final WdccSalesPerformMapper mapper;
    private final WdccSalesPerformConverter converter;

    public PagingResult<SearchRes> getSalesPerformancePresentStatePages(SearchReq dto, PageInfo pageInfo) {
        WdccContractDvo contractDvo = mapper.selectContract(dto);
        // 계약정보 없으면 에러
        BizAssert.isTrue(!ObjectUtils.isEmpty(contractDvo), "MSG_ALT_NO_CNTR_DTL_NO_FOUND");
        // 주문유형 2,3,6 아니면 에러
        BizAssert
            .isTrue(
                Arrays.asList(new String[] {"2", "3", "6"}).contains(contractDvo.getSellTpCd()),
                "MSG_ALT_NOT_SPPT_ORD_TP"
            );
        PagingResult<WdccSalesPerformDvo> result = null;
        PagingResult<SearchRes> pagingResult = new PagingResult<>();
        if (StringUtils.equals("2", contractDvo.getSellTpCd())) {
            result = mapper.selectRentalSalesPages(dto, pageInfo);
        } else if (StringUtils.equals("3", contractDvo.getSellTpCd())) {
            result = mapper.selectMembershipSalesPages(dto, pageInfo);
        } else if (StringUtils.equals("6", contractDvo.getSellTpCd())) {
            result = mapper.selectRegularShippingSalesPages(dto, pageInfo);
        }
        if (result != null) {
            pagingResult.setList(converter.mapAllWdccSalesPerformDvoToSearchRes(result.getList()));
            pagingResult.setPageInfo(result.getPageInfo());
        }
        return pagingResult;
    }

    public List<SearchRes> getSalesPerformancePresentStateForExcelDownload(SearchReq dto) {
        WdccContractDvo contractDvo = mapper.selectContract(dto);
        List<WdccSalesPerformDvo> result = null;
        if (StringUtils.equals("2", contractDvo.getSellTpCd())) {
            result = mapper.selectRentalSalesPages(dto);
        } else if (StringUtils.equals("3", contractDvo.getSellTpCd())) {
            result = mapper.selectMembershipSalesPages(dto);
        } else if (StringUtils.equals("6", contractDvo.getSellTpCd())) {
            result = mapper.selectRegularShippingSalesPages(dto);
        }
        return CollectionUtils.isEmpty(result) ? null : converter.mapAllWdccSalesPerformDvoToSearchRes(result);
    }
}
