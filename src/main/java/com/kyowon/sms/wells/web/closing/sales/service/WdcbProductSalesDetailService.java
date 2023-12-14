package com.kyowon.sms.wells.web.closing.sales.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.system.config.interceptor.ExcelResultHandler;
import com.sds.sflex.system.config.validation.BizAssert;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchMembershipRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchRentalRes;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchReq;
import com.kyowon.sms.wells.web.closing.sales.dto.WdcbProductSalesDetailDto.SearchSingleRes;
import com.kyowon.sms.wells.web.closing.sales.mapper.WdcbProductSalesDetailMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdcbProductSalesDetailService {
    private final WdcbProductSalesDetailMapper mapper;
    private final SqlSession priSqlSession;

    private final ExcelDownloadService excelDownloadService;

    /**
     * 매출상세조회(일시불,금융리스,정기구매)
     * @param dto
     * @return
     */
    public List<SearchSingleRes> getProductSalesSinglePaymentDetails(SearchReq dto) {

        List<SearchSingleRes> result = null;
        switch (dto.sellTpCd()) {
            case "1": // 일시불
                result = mapper.selectProductSalesSinglePaymentDetails(dto);
                break;
            case "6": // 정기배송
                result = mapper.selectProductSalesRegularDeliveryDetails(dto);
                break;
            case "10": // 리스 할부
                result = mapper.selectProductSalesLeaseDetails(dto);
                break;
            default:
                BizAssert.isTrue(false, "MSG_ALT_ERR_CONTACT_ADMIN");
        }

        return result;
    }

    /**
     * 매출상세조회(렌탈)
     * @param dto
     * @return
     */
    public List<SearchRentalRes> getProductSalesRentalDetails(SearchReq dto) {
        return mapper.selectProductSalesRentalDetails(dto);
    }

    /**
     * 매출상세조회(멤버십)
     * @param dto
     * @return
     */
    public List<SearchMembershipRes> getProductSalesMembershipDetails(SearchReq dto) {
        return mapper.selectProductSalesMembershipDetails(dto);
    }

    public void getProductSalesRentalDetailsBulkExcelDownload(
        ExcelBulkDownloadDto.DownloadReq req, HttpServletResponse response
    ) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

        priSqlSession.select(
            "com.kyowon.sms.wells.web.closing.sales.mapper.WdcbProductSalesDetailMapper.selectRentalBulkExcelDownload",
            req.parameter(),
            new ExcelResultHandler(workbook, req.columns(), req.searchCondition())
        );

        excelDownloadService.downloadBulkExcel(workbook, response);
    }
}
