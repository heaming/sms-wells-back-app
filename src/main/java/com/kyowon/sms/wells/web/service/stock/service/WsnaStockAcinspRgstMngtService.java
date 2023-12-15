package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockAcinspRgstMngtDto.*;

import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaStockAcinspRgstMngtConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockAcinspRgstMngtDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStockAcinspRgstMngtMapper;
import com.sds.sflex.common.common.dvo.ExcelFieldDvo;
import com.sds.sflex.common.common.service.ExcelDownloadService;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0139M01 월별 재고실사 등록 관리
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.07.26
 */

@Service
@RequiredArgsConstructor
public class WsnaStockAcinspRgstMngtService {

    private final WsnaStockAcinspRgstMngtMapper mapper;
    private final WsnaStockAcinspRgstMngtConverter converter;

    private final ExcelDownloadService excelDownloadService;

    /**
     * 월별재고 실사 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getStockAcinspRgstMngtPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectStockAcinspRgstMngtPages(dto, pageInfo);
    }

    /**
     * 월별재고실사 대용량 엑셀다운로드
     * @param dto
     * @return
     * @throws Exception
     */
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

    /**
     * 창고 조회
     * @param dto
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getWareHouses(SearchWareReq dto) {
        ValidAssert.notNull(dto);
        ValidAssert.hasText(dto.baseYm());
        ValidAssert.hasText(dto.wareDvCd());

        return this.mapper.selectWarehouse(dto);
    }

    /**
     * 재고적용
     * @param dtos
     * @return
     */
    @Transactional
    public int saveStockAcinspRgstStocApy(List<SaveReq> dtos) {
        ValidAssert.notEmpty(dtos);

        List<WsnaStockAcinspRgstMngtDvo> stocApyDvoList;

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

    /**
     * 월별재고실사 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int saveStockAcinspRgst(List<SaveAcinspReq> dtos) {
        ValidAssert.notEmpty(dtos);

        int processCount = 0;

        List<WsnaStockAcinspRgstMngtDvo> dvos = this.converter.mapAllSaveAcinspReqToWsnaStockAcinspRgstMngtDvo(dtos);

        for (WsnaStockAcinspRgstMngtDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getApyYm());
            ValidAssert.hasText(dvo.getItmPdCd());
            ValidAssert.hasText(dvo.getWareNo());

            //재조회 확정시점입고차이수량 , 재조회 확정시점출고차이수량
            WsnaStockAcinspRgstMngtDvo reDvo = this.mapper.selectReAcinspRgst(dvo);

            dvo.setReCnfmPitmOstrGapQty(reDvo.getReCnfmPitmOstrGapQty());
            dvo.setReCnfmPitmStrGapQty(reDvo.getReCnfmPitmStrGapQty());

            processCount += this.mapper.insertStockAcinsp(dvo);

        }

        return processCount;
    }

    /**
     * 실사 확정처리
     * @param dtos
     * @return
     */
    @Transactional
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

            //창고상세구분코드
            String chkWareDtlDvCd = reSearchDvo.get(0).getWareDtlDvCd();

            //조회해온 창고상세구분코드가 조직창고, 영업센터인경우 분기처리
            if ("20".equals(chkWareDtlDvCd) || "30".equals(chkWareDtlDvCd)) {
                this.mapper.updateStockAcinspIzCnfm(reSearchDvo);
            } else {
                this.mapper.updateStockAcinspIzCnfmIndv(reSearchDvo);
            }

            processCount++;

        }
        return processCount;

    }

    /**
     * 실사확정 취소버튼 클릭 이벤트
     * @param dtos
     * @return
     */
    @Transactional
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

    /**
     * 신청취소버튼 클릭 이벤트
     * @param dtos
     * @return
     */
    @Transactional
    public int removeManagerStockControl(List<RemoveReq> dtos) {
        ValidAssert.notEmpty(dtos);

        int processCount = 0;

        List<WsnaStockAcinspRgstMngtDvo> dvos = this.converter
            .mapAllDeleteApplAcinspReqToWsnaStockAcinspRgstMngtDvo(dtos);
        for (WsnaStockAcinspRgstMngtDvo dvo : dvos) {

            int chkCount = this.mapper.deleteApplAcinsp(dvo);
            //저장에 실패하였습니다.
            BizAssert.isTrue(chkCount > 0, "MSG_ALT_SVE_ERR");

            processCount++;
        }
        return processCount;
    }
}
