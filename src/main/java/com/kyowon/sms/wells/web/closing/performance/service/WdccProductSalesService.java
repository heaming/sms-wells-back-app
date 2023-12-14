package com.kyowon.sms.wells.web.closing.performance.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import com.kyowon.sms.wells.web.closing.performance.dvo.WdccProductSalesSapMaterialsDvo;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductSalesDto.SearchSapMaterialsRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccProductSalesMapper;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdccProductSalesService {
    private final WdccProductSalesMapper mapper;

    /**
     * 상품별 매출현황
     * @param dto 검색조건
     * @return result 조회목록
     */
    public List<SearchRes> getBasicList(
        SearchReq dto
    ) {
        List<SearchRes> result = null;
        switch (dto.sellTpCd()) {
            case "1": // 일시불
                result = mapper.selectProductSalesSinglePayments(dto);
                break;
            case "6": // 정기배송
                result = mapper.selectProductSalesRegularDeliveries(dto);
                break;
            case "10": // 리스 할부
                result = mapper.selectProductSalesLeases(dto);
                break;
            default:
                BizAssert.isTrue(false, "MSG_ALT_ERR_CONTACT_ADMIN");
        }
        return result;
    }

    /**
     * 상품별 매출현황
     * @param dto 검색조건
     * @return result 조회목록
     */
    public List<SearchRentalRes> getRentalList(
        SearchReq dto
    ) {
        return mapper.selectProductSalesRentals(dto);
    }

    /**
     * 상품별 매출현황
     * @param dto 검색조건
     * @return result 조회목록
     */
    public List<SearchMembershipRes> getMembershipList(
        SearchReq dto
    ) {
        return mapper.selectProductSalesMemberships(dto);
    }

    public SearchSapMaterialsRes getProductSalesSapMaterials(
        SearchReq dto
    ) {
        String pdctSlSumAmt = "0";
        String pdctSlSumVat = "0";
        String prdtSlSumAmt = "0";
        String prdtSlSumVat = "0";
        String svdtSlSumAmt = "0";
        String svdtSlSumVat = "0";
        String etcSlSumAmt = "0";
        String etcSlSumVat = "0";

        final List<WdccProductSalesSapMaterialsDvo> dvos = mapper.selectProductSalesSapMaterials(dto);
        for (WdccProductSalesSapMaterialsDvo dvo : dvos) {
            switch (dvo.getSapMatEvlCd()) {
                case "Z1":
                    pdctSlSumAmt = convertAmtFormat(dvo.getSlSumAmt());
                    pdctSlSumVat = convertAmtFormat(dvo.getSlSumVat());
                    break;
                case "Z2":
                    prdtSlSumAmt = convertAmtFormat(dvo.getSlSumAmt());
                    prdtSlSumVat = convertAmtFormat(dvo.getSlSumVat());
                    break;
                case "Z7":
                    svdtSlSumAmt = convertAmtFormat(dvo.getSlSumAmt());
                    svdtSlSumVat = convertAmtFormat(dvo.getSlSumVat());
                    break;
                default:
                    etcSlSumAmt = convertAmtFormat(dvo.getSlSumAmt());
                    etcSlSumVat = convertAmtFormat(dvo.getSlSumVat());
            }
        }

        return new SearchSapMaterialsRes(
            pdctSlSumAmt, pdctSlSumVat, prdtSlSumAmt, prdtSlSumVat, svdtSlSumAmt, svdtSlSumVat, etcSlSumAmt, etcSlSumVat
        );
    }

    private String convertAmtFormat(final BigDecimal amt) {

        if (amt == null)
            return "0";

        DecimalFormat format = new DecimalFormat("###,###");
        return format.format(amt);
    }
}
