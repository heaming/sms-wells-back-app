package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.closing.performance.converter.WdccSalesPerformConverter;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccSalesPerformDto.*;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccContractDvo;
import com.kyowon.sms.wells.web.closing.performance.dvo.WdccSalesPerformDvo;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccSalesPerformMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 매출채권_선수금 현황 서비스
 * </pre>
 *
 * @author gugyeongu
 * @since 2023-08-03
 */
@Service
@RequiredArgsConstructor
public class WdccSalesPerformService {

    private final WdccSalesPerformMapper mapper;
    private final WdccSalesPerformConverter converter;

    /**
     * 매출실적현황 조회
     * @param dto 조회조건
     * @param pageInfo 페이지정보
     * @return 매출실적현황 목록(페이징포함)
     */
    public PagingResult<SearchRes> getSalesPerformancePresentStatePages(SearchReq dto, PageInfo pageInfo) {
        WdccContractDvo contractDvo = mapper.selectContract(dto);
        // 계약정보 없으면 에러
        BizAssert.isTrue(!ObjectUtils.isEmpty(contractDvo), "MSG_ALT_NO_CNTR_DTL_NO_FOUND");
        // 주문유형 2,3,6 아니면 에러
        BizAssert
            .isTrue(
                Arrays.asList("2", "3", "6").contains(contractDvo.getSellTpCd()),
                "MSG_ALT_NOT_SPPT_ORD_TP"
            );
        PagingResult<WdccSalesPerformDvo> result = null;
        PagingResult<SearchRes> pagingResult = new PagingResult<>();
        if (StringUtils.equals("2", contractDvo.getSellTpCd())) { //렌탈
            result = mapper.selectRentalSalesPages(dto, pageInfo);
        } else if (StringUtils.equals("3", contractDvo.getSellTpCd())) { //맴버십
            result = mapper.selectMembershipSalesPages(dto, pageInfo);
        } else if (StringUtils.equals("6", contractDvo.getSellTpCd())) { //정기배송
            result = mapper.selectRegularShippingSalesPages(dto, pageInfo);
        }
        if (result != null) {
            pagingResult.setList(converter.mapAllWdccSalesPerformDvoToSearchRes(result.getList()));
            pagingResult.setPageInfo(result.getPageInfo());
        }
        return pagingResult;
    }

    /**
     * 매출실적현황 조회(엑셀다운로드)
     * @param dto 조회조건
     * @return 매출실적현황 목록
     */
    public List<SearchRes> getSalesPerformancePresentStateForExcelDownload(SearchReq dto) {
        WdccContractDvo contractDvo = mapper.selectContract(dto);
        List<WdccSalesPerformDvo> result = null;
        if (StringUtils.equals("2", contractDvo.getSellTpCd())) { //렌탈
            result = mapper.selectRentalSalesPages(dto);
        } else if (StringUtils.equals("3", contractDvo.getSellTpCd())) { //맴버십
            result = mapper.selectMembershipSalesPages(dto);
        } else if (StringUtils.equals("6", contractDvo.getSellTpCd())) { //정기배송
            result = mapper.selectRegularShippingSalesPages(dto);
        }
        return CollectionUtils.isEmpty(result) ? null : converter.mapAllWdccSalesPerformDvoToSearchRes(result);
    }

    /**
     * 검색 조건에 대항하는 계약 정보를 조회
     * @param dto 검색조건
     * @return 계약정보
     */
    public SearchContractReq getContract(SearchReq dto) {
        return converter.mapWdccContractDvoToSearchContractReq(mapper.selectContract(dto));
    }

    /**
     * 매출 실적 현황 - 일시불(기본정보) 조회
     * @param dto 조회조건
     * @return 일시불(기본정보)
     */
    public SearchSinglePaymentBaseRes getSinglePaymentBaseInfo(SearchSinglePaymentReq dto) {
        return mapper.selectSinglePaymentBaseInfo(dto);
    }

    /**
     * 매출 실적 현황 - 일시불(매출실적) 조회
     * @param dto 조회조건
     * @param pageInfo 페이지정보
     * @return 매출실적 목록
     */
    public PagingResult<SearchSinglePaymentSalesRes> getSinglePaymentSalesPaging(
        SearchSinglePaymentReq dto, PageInfo pageInfo
    ) {
        return mapper.selectSinglePaymentSales(dto, pageInfo);
    }

    /**
     * 매출 실적 현황 - 일시불(입금내역)
     * @param dto 조회조건
     * @param pageInfo 페이지정보
     * @return 입금내역 목록
     */
    public PagingResult<SearchSinglePaymentDepositsRes> getSinglePaymentDepositsPaging(
        SearchSinglePaymentReq dto, PageInfo pageInfo
    ) {
        return mapper.selectSinglePaymentDeposits(dto, pageInfo);
    }

    /**
     * 매출 실적 현황 - 일시불(매출실적)(엑셀다운로드)
     * @param dto 조회조건
     * @return 입금내역 목록
     */
    public List<SearchSinglePaymentSalesRes> getSinglePaymentSalesExcelDownload(SearchSinglePaymentReq dto) {
        return mapper.selectSinglePaymentSales(dto);
    }

    /**
     * 매출 실적 현황 - 일시불(입금내역)(엑셀다운로드)
     * @param dto 조회조건
     * @return 입금내역 목록
     */
    public List<SearchSinglePaymentDepositsRes> getSinglePaymentDepositsExcelDownload(SearchSinglePaymentReq dto) {
        return mapper.selectSinglePaymentDeposits(dto);
    }
}
