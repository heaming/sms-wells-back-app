package com.kyowon.sms.wells.web.service.allocate.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.sds.sflex.common.common.dto.ExcelBulkDownloadDto;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.system.config.interceptor.ExcelResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRoutineBsPsicAssignStateDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRoutineBsPsicAssignStateDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRoutineBsPsicAssignStateMngrInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncRoutineBsPsicAssignStateMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncRoutineBsPsicAssignStateService {
    private final WsncRoutineBsPsicAssignStateMapper mapper;
    private final ExcelDownloadService excelDownloadService;
    private final SqlSession priSqlSession;

    public PagingResult<SearchRes> getRoutineBsPsicAssignStates(SearchReq req, PageInfo pageInfo) {
        return mapper.selectRoutineBsPsicAssignState(req, pageInfo);
    }

    public List<SearchRes> getRoutineBsPsicAssignStatesForExcelDownload(SearchReq req) {
        return mapper.selectRoutineBsPsicAssignState(req);
    }

    public List<HashMap<String, String>> getWellsManager(String dgr2LevlOgId) {
        return mapper.selectWellsManager(dgr2LevlOgId);
    }

    public WsncRoutineBsPsicAssignStateMngrInfoDvo getManagerInfo(SearchReq req) {
        return mapper.selectManagerInfo(req);
    }

    public void getRoutineBsPsicAssignStateBulkExcelDownload(ExcelBulkDownloadDto.DownloadReq req, HttpServletResponse response) throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        this.priSqlSession.select(
            "com.kyowon.sms.wells.web.service.allocate.mapper.WsncRoutineBsPsicAssignStateMapper.selectRoutineBsPsicAssignStateBulkExcelDownload",
            req.parameter(),
            new ExcelResultHandler(workbook, req.columns(), req.searchCondition())
        );
        this.excelDownloadService.downloadBulkExcel(workbook, response);
    }
}
