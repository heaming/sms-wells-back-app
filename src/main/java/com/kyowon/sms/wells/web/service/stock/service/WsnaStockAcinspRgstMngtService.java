package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.List;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockAcinspRgstMngtDvo;
import com.sds.sflex.common.common.dvo.ExcelFieldDvo;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaStockAcinspRgstMngtConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStockAcinspRgstMngtMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockAcinspRgstMngtDto.*;

import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WsnaStockAcinspRgstMngtService {

    private final WsnaStockAcinspRgstMngtMapper mapper;
    private final WsnaStockAcinspRgstMngtConverter converter;

    private final ExcelDownloadService excelDownloadService;

    public PagingResult<SearchRes> getStockAcinspRgstMngtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectStockAcinspRgstMngtPages(dto, pageInfo);
    }

    public SXSSFWorkbook getStockAcinspRgstMngtsForExcelDownload(SearchReq dto)
        throws Exception {
        List<SearchRes> results = mapper.selectStockAcinspRgstMngtPages(dto);

        List<ExcelFieldDvo> fields = List.of(
            ExcelFieldDvo.builder().headerName("신청상태")
                .fieldName("statusT")
                .width("15")
                .align(CommConst.EXCEL_ALIGN_CENTER)
                .build(),
            ExcelFieldDvo.builder().headerName("창고코드")
                .fieldName("wareNo")
                .width("15")
                .align(CommConst.EXCEL_ALIGN_CENTER)
                .build(),
            ExcelFieldDvo.builder().headerName("빌딩명")
                .fieldName("wareNm")
                .width("15")
                .align(CommConst.EXCEL_ALIGN_CENTER)
                .build(),
            ExcelFieldDvo.builder().headerName("SAP코드")
                .fieldName("sapCd")
                .width("25")
                .align(CommConst.EXCEL_ALIGN_CENTER)
                .build(),
            ExcelFieldDvo.builder().headerName("품목코드")
                .fieldName("itmPdCd")
                .width("15")
                .align(CommConst.EXCEL_ALIGN_CENTER)
                .build(),
            ExcelFieldDvo.builder().headerName("품목명")
                .fieldName("pdAbbrNm")
                .width("30")
                .align(CommConst.EXCEL_ALIGN_LEFT)
                .build(),
            ExcelFieldDvo.builder().headerName("기말재고")
                .fieldName("eotStoc")
                .width("10")
                .align(CommConst.EXCEL_ALIGN_RIGHT)
                .build(),
            ExcelFieldDvo.builder().headerName("실사재고")
                .fieldName("acinspQty")
                .width("22")
                .align(CommConst.EXCEL_ALIGN_RIGHT)
                .build(),
            ExcelFieldDvo.builder().headerName("재고차이")
                .fieldName("minusQty")
                .width("8")
                .align(CommConst.EXCEL_ALIGN_RIGHT)
                .build(),
            ExcelFieldDvo.builder().headerName("비고")
                .fieldName("acinspRmkCn")
                .width("30")
                .align(CommConst.EXCEL_ALIGN_CENTER)
                .build(),
            ExcelFieldDvo.builder().headerName("확정일자")
                .fieldName("cnfmdt")
                .width("10")
                .align(CommConst.EXCEL_ALIGN_CENTER)
                .build(),
            ExcelFieldDvo.builder().headerName("확정차이")
                .fieldName("cnfmPitmEotStocQty")
                .width("10")
                .align(CommConst.EXCEL_ALIGN_RIGHT)
                .build(),
            ExcelFieldDvo.builder().headerName("반영일자")
                .fieldName("iostRfdt")
                .width("8")
                .align(CommConst.EXCEL_ALIGN_CENTER)
                .build()

        );
        return excelDownloadService.createExcel(fields, results);
    }

    public List<WsnzWellsCodeWareHouseDvo> getWareHouses(SearchWareReq dto) {
        ValidAssert.notNull(dto);
        ValidAssert.hasText(dto.baseYm());
        ValidAssert.hasText(dto.wareDvCd());

        return this.mapper.selectWarehouse(dto);
    }

    @Transactional
    public int saveStockAcinspRgstStocApy(List<SaveReq> dtos) {
        ValidAssert.notEmpty(dtos);

        List<WsnaStockAcinspRgstMngtDvo> stocApyDvoList = new ArrayList<>();

        List<WsnaStockAcinspRgstMngtDvo> dvos = this.converter.mapAllSaveReqToWsnaStockAcinspRgstMngtDvo(dtos);

        int processCount = 0;

        for (WsnaStockAcinspRgstMngtDvo dvo : dvos) {

            stocApyDvoList = this.mapper.selectStocAcinspIz(dvo);

            int chkCnt = this.mapper.selectChkCountAcinsp(dvo);
            //수불 적용할 자료가 없습니다.
            BizAssert.isTrue(chkCnt > 0, "MSG_ALT_RVPY_APY_MTR_NTHNG");

            //재고 실사 입출고 수량 월별 재고에 적용
            this.mapper.insertStockAcinspIz(stocApyDvoList);

            //재고 실사 입출고 수량 시점 재고에 적용
            this.mapper.insertStockAcinspCstSvItmStocIz(stocApyDvoList);

            //수불적용일자 업데이트 IOST_RFDT
            this.mapper.updateStockAcinspIostRfdt(stocApyDvoList);

            processCount++;
        }

        return processCount;
    }

    @Transactional
    public int saveStockAcinspRgst(List<SaveAcinspReq> dtos) {
        ValidAssert.notEmpty(dtos);

        int processCount = 0;

        List<WsnaStockAcinspRgstMngtDvo> dvos = this.converter.mapAllSaveAcinspReqToWsnaStockAcinspRgstMngtDvo(dtos);

        for (WsnaStockAcinspRgstMngtDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getApyYm());
            ValidAssert.hasText(dvo.getItmPdCd());
            ValidAssert.hasText(dvo.getWareNo());

            processCount += this.mapper.insertStockAcinsp(dvo);

        }

        return processCount;
    }

    public int saveStockAcinspRgstCnfm(List<SaveAcinspCnfmReq> dtos) {

        ValidAssert.notEmpty(dtos);

        int processCount = 0;

        List<WsnaStockAcinspRgstMngtDvo> dvos = this.converter
            .mapAllSaveAcinspCnfmReqToWsnaStockAcinspRgstMngtDvo(dtos);

        for (WsnaStockAcinspRgstMngtDvo dvo : dvos) {

            List<WsnaStockAcinspRgstMngtDvo> reSearchDvo = this.mapper.selectAcinspRgstCnfm(dvo);

            int chkCount = this.mapper.selectChkCountCnfm(dvo);
            //이미 모든 자료가 확정 상태입니다.
            BizAssert.isTrue(chkCount > 0, "MSG_ALT_ALL_MTR_CNFM_STAT");

            this.mapper.updateStockAcinspIzCnfm(reSearchDvo);

            processCount++;

        }
        return processCount;

    }

    /**
     * 실사확정 취소버튼 클릭 이벤트
     * @param dtos
     * @return
     */
    public int deleteStockAcinspRgstCancel(List<SaveCancelReq> dtos) {
        ValidAssert.notEmpty(dtos);

        int processCount = 0;

        List<WsnaStockAcinspRgstMngtDvo> dvos = this.converter
            .mapAllDeleteAcinspReqToWsnaStockAcinspRgstMngtDvo(dtos);

        for (WsnaStockAcinspRgstMngtDvo dvo : dvos) {
            List<WsnaStockAcinspRgstMngtDvo> reDeleteDvo = this.mapper.selectDeleteAcinspRgstCancel(dvo);

            int chkCount = this.mapper.selectChkAcinspRgstCancel(dvo);

            //확정 된 자료가 없습니다.
            BizAssert.isTrue(chkCount > 0, "MSG_ALT_CNFM_MTR_NULL");

            this.mapper.updateStockAcinspIzCancel(reDeleteDvo);

            processCount++;

        }

        return processCount;

    }
}
