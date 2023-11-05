package com.kyowon.sms.wells.web.closing.performance.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccOverduePenaltyDto.*;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverduePenaltyMapper;
import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.system.config.interceptor.ExcelResultHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccOverduePenaltyService {

    private final WdccOverduePenaltyMapper mapper;
    private final ExcelDownloadService excelDownloadService;
    private final SqlSession priSqlSession;

    /**
     * 매출채권/선수금 현황 - SAP상품구분코드 조회
     * @return
     */
    public List<FindCodeRes> getCode() {
        return mapper.selectCode();
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트/집계)
     * @param req
     * @return
     */
    public List<SearchPointAggregateRes> getPointAggregates(
        SearchReq req
    ) {
        return mapper.selectPointAggregates(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트/주문별)
     * @param req
     * @return
     */
    public List<SearchPointOrderRes> getPointOrders(
        SearchReq req
    ) {
        return mapper.selectPointOrders(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/일자별)
     * @param req
     * @return
     */
    public List<SearchAggregateDateRes> getAnticipationDates(SearchReq req) {
        return mapper.selectAnticipationDates(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/일시불)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationSinglePayments(SearchReq req) {
        return mapper.selectAnticipationSinglePayments(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/멤버십)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationMemberships(SearchReq req) {
        return mapper.selectAnticipationMemberships(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/정기배송)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationRegularShippings(SearchReq req) {
        return mapper.selectAnticipationRegularShippings(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/렌탈)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationRentals(SearchReq req) {
        return mapper.selectAnticipationRentals(req);
    }

    /**
     * 매출채권/선수금 현황 - 연체가산금(포인트 미선택(선수금)/리스)
     * @param req
     * @return
     */
    public List<SearchOrderRes> getAnticipationLeases(SearchReq req) {
        return mapper.selectAnticipationLeases(req);
    }

    /**
    * 영업선수금 주문별 bulk 엑셀다운로드
    * @param req
    * @param response
    * @return
    */
    public void getPointOrdersForBulkExcelDownload(
        ExcelBulkDownloadDto.DownloadReq req, HttpServletResponse response, String downLoadUrl
    )
        throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        String query = "";
        if ("pointOrders".equals(downLoadUrl)) {
            query = "com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverduePenaltyMapper.selectPointOrders";
        } else if ("anticipationSinglePayments".equals(downLoadUrl)) {
            query = "com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverduePenaltyMapper.selectAnticipationSinglePayments";
        } else if ("anticipationRentals".equals(downLoadUrl)) {
            query = "com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverduePenaltyMapper.selectAnticipationRentals";
        } else if ("anticipationLeases".equals(downLoadUrl)) {
            query = "com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverduePenaltyMapper.selectAnticipationLeases";
        } else if ("anticipationMemberships".equals(downLoadUrl)) {
            query = "com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverduePenaltyMapper.selectAnticipationMemberships";
        } else if ("anticipationRegularShippings".equals(downLoadUrl)) {
            query = "com.kyowon.sms.wells.web.closing.performance.mapper.WdccOverduePenaltyMapper.selectAnticipationRegularShippings";
        }
        priSqlSession.select(
            query,
            req.parameter(),
            new ExcelResultHandler(workbook, req.columns(), req.searchCondition())
        );

        excelDownloadService.downloadBulkExcel(workbook, response);
    }
}
