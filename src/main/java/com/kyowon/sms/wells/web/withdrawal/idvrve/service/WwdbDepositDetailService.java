package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.io.IOException;

import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.system.config.interceptor.ExcelResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDetailDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbDepositDetailMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 입금내역조회(웰스입금상세) 서비스
 * </pre>
 *
 * @author ShinSoJeong
 * @since 2023-06-16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbDepositDetailService {

    private final WwdbDepositDetailMapper mapper;

    private final ExcelDownloadService excelDownloadService;
    private final SqlSession priSqlSession;

    /**
     * 입금내역 조회 / 페이징
     * @param dto
     * @param pageInfo 페이징
     * @return PagingResult<SearchRes>
     */
    @Transactional
    public PagingResult<SearchRes> getDepositDetailPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectDepositDetail(dto, pageInfo);
    }

    /**
     * 입금내역 대용량 엑셀 다운로드
     * @param req
     * @param response
     * @throws IOException
     */
    public void getDepositDetailExcels(ExcelBulkDownloadDto.DownloadReq req, HttpServletResponse response)
        throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        priSqlSession.select(
            "com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbDepositDetailMapper.selectDepositDetailBulk",
            req.parameter(),
            new ExcelResultHandler(workbook, req.columns(), req.searchCondition())
        );

        excelDownloadService.downloadBulkExcel(workbook, response);
    }

}
