package com.kyowon.sms.wells.web.closing.performance.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchProductRes;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccProductAccountDto.SearchTotalRes;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccProductAccountMapper;
import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.system.config.interceptor.ExcelResultHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WdccProductAccountService {
    private final WdccProductAccountMapper mapper;
    private final ExcelDownloadService excelDownloadService;
    private final SqlSession priSqlSession;

    /**
     * 상품별 계정 현황 상세내역 다운로드
     * @param dto
     * @return
     */
    public List<SearchTotalRes> getProductAccountTotals(SearchReq dto) {
        return mapper.selectProductAccountTotals(dto);
    }

    /**
     * 상품별 계정 현황(상품)
     * @param dto
     * @return
     */
    public List<SearchProductRes> getProductAccounts(SearchReq dto) {
        return mapper.selectProductAccounts(dto);
    }

    /**
     * 상품별 계정 현황 상세내역 다운로드
     * @param req
    * @param response
     * @return
     */
    public void getProductAccountsExcelDownload(ExcelBulkDownloadDto.DownloadReq req, HttpServletResponse response)
        throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        priSqlSession.select(
            "com.kyowon.sms.wells.web.closing.performance.mapper.WdccProductAccountMapper.selectProductAccountsExcelDownload",
            req.parameter(),
            new ExcelResultHandler(workbook, req.columns(), req.searchCondition())
        );

        excelDownloadService.downloadBulkExcel(workbook, response);
    }
}
