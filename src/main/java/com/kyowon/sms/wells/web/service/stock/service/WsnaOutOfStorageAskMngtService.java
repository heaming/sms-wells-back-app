package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaOutofStorageAskMngtConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskMngtDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaOutOfStorageAskMngtSearchDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageAskMngtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author songtaesung
 * @since 2022.12.26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaOutOfStorageAskMngtService {

    private final WsnaOutOfStorageAskMngtMapper mapper;

    private final WsnaOutofStorageAskMngtConverter converter;

    // 재고서비스
    private final WsnaItemStockItemizationService stockService;

    // (주)교원프라퍼티파주물류(Wells)
    private static final String SAP_PLNT_CD = "2108";
    // 프라파주창고(Wells)
    private static final String SAP_SAVE_LCT_CD = "21082082";
    private static final int SLICE_SIZE = 999;

    private static final String WARE_DV_CD_LOGISTICS_CENTER = "1"; // 창고구분코드 = 물류센터

    private final WsnaLogisticsOutStorageAskService logisticsservice;

    /**
     * 출고요청 관리 - 조회
     * @param dto : { strOjWareNo: 출고요청창고, ostrAkTpCd: 출고요청유형코드, startStrHopDt: 입고희망일자 시작일, endStrHopDt: 입고희망일자 종료일, wareDvCd: 출고요청 접수창고, wareLocaraCd: 창고지역코드 }
     * @return 조회결과
     */
    public PagingResult<SearchRes> getOutOfStorageAsks(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectOutOfStorageAsks(dto, pageInfo);
    }

    /**
     * 출고요청 관리- 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getOutOfStorageAsksExcelDownload(SearchReq dto) {
        return this.mapper.selectOutOfStorageAsks(dto);
    }

    /**
     * 출고요청 관리 - 상단영역 조회
     * @param dto : { session.userid : 사용자id , apyYm : 기준년월 }
     * @return 조회결과
     */
    public FindRes getOutOfStorageAskItems(FindReq dto) {
        return this.mapper.selectOutOfStorageAskItms(dto);
    }

    /**
     * 출고요청 관리 - 하단영역(그리드) 조회
     * @param dto : { session.userid : 사용자id , apyYm : 기준년월 }
     * @return 조회결과
     */
    public List<WsnaOutOfStorageAskMngtDvo> getOutOfStorageItemPages(
        SearchReq dto
    ) {
        WsnaOutOfStorageAskMngtSearchDvo searchDvo = this.converter.mapAllSearchReqToOutOfStorageAskMngtDvo(dto);

        String ostrWareDvCd = this.mapper.selectOstrWareDvCd(dto);
        searchDvo.setOstrWareDvCd(ostrWareDvCd);

        List<WsnaOutOfStorageAskMngtDvo> outOfDvo = this.mapper.selectOutOfStorageItms(searchDvo);

        if (WARE_DV_CD_LOGISTICS_CENTER.equals(ostrWareDvCd)) {
            this.getRealTimeLogisticStockQtys(outOfDvo);
        }

        return outOfDvo;
    }

    public List<WsnaOutOfStorageAskMngtDvo> getOutOfStorageItemExcelDownload(SearchReq dto) {

        WsnaOutOfStorageAskMngtSearchDvo searchExcelDvo = this.converter
            .mapAllSearchReqToExcelOutOfStorageAskMngtDvo(dto);

        String ostrWareDvCd = this.mapper.selectOstrWareDvCd(dto);
        searchExcelDvo.setOstrWareDvCd(ostrWareDvCd);

        List<WsnaOutOfStorageAskMngtDvo> outOfDvo = this.mapper.selectOutOfStorageItms(searchExcelDvo);

        if (WARE_DV_CD_LOGISTICS_CENTER.equals(ostrWareDvCd)) {
            this.getRealTimeLogisticStockQtys(outOfDvo);
        }

        return outOfDvo;
    }

    /**
     * 물류재고 조회(999건만 조회되도록 사이즈 조정)
     * @param dvos
     */
    private void getRealTimeLogisticStockQtys(List<WsnaOutOfStorageAskMngtDvo> dvos) {
        if (CollectionUtils.isNotEmpty(dvos)) {

            int size = dvos.size();
            int sliceSize = Math.floorDiv(size, SLICE_SIZE) + (Math.floorMod(size, SLICE_SIZE) > 0 ? 1 : 0);

            for (int i = 0; i < sliceSize; i++) {
                int startIndex = i * SLICE_SIZE;
                int endIndex = (i + 1) * SLICE_SIZE > size ? size : (i + 1) * SLICE_SIZE;

                List<WsnaOutOfStorageAskMngtDvo> sliceDvos = dvos.subList(startIndex, endIndex);
                List<String> itmPds = sliceDvos.stream().map(WsnaOutOfStorageAskMngtDvo::getItmPdCd).toList();

                //실시간 재고조회 서비스 조회호출
                List<RealTimeGradeStockResIvo> stocks = this.stockService
                    .getRealTimeGradeStocks(SAP_PLNT_CD, SAP_SAVE_LCT_CD, itmPds);

                this.setTotalLogisticQty(stocks, sliceDvos);

            }

        }
    }

    /**
     * 물류재고 조회
     * @param stocks
     * @param sliceDvos
     */
    private void setTotalLogisticQty(
        List<RealTimeGradeStockResIvo> stocks, List<WsnaOutOfStorageAskMngtDvo> sliceDvos
    ) {
        if (CollectionUtils.isNotEmpty(stocks)) {
            int stockSize = stocks.size();

            for (WsnaOutOfStorageAskMngtDvo dvo : sliceDvos) {
                String itmPdCd = dvo.getItmPdCd();
                BigDecimal lgstQty = BigDecimal.ZERO;

                for (int i = 0; i < stockSize; i++) {
                    RealTimeGradeStockResIvo stock = stocks.get(i);

                    String stockPdCd = stock.getItmPdCd();

                    if (itmPdCd.equals(stockPdCd)) {

                        lgstQty = stock.getLgstAGdQty();
                        stockSize--;
                        stocks.remove(stock);
                        break;
                    }

                }
                dvo.setWarehouseQty(lgstQty);
            }

        }
    }

    /**
     * 출고요청 관리 - 조건에 맞는 출고대상창고 목록 조회
     * @param dto : { session.userid : 사용자id , apyYm : 기준년월 }
     * @return 조회결과
     */
    public List<SearchOstrObjectWarehouseRes> getOstrObjectWarehouses(
        SearchOstrObjectWarehouseReq dto
    ) {
        return this.mapper.selectOstrObjectWarehouses(dto);
    }

    @Transactional
    public int removeOutOfStorageAskItems(List<RemoveReq> dtos) {
        int processCount = 0;
        // TODO: 삭제시 검증에 필요한 인터페이스(W-SV-I-0027) 개발 후 로직추가.
        List<WsnaOutOfStorageAskMngtDvo> removeListDvo = new ArrayList<>();

        for (RemoveReq dto : dtos) {
            WsnaOutOfStorageAskMngtDvo dvo = this.converter.mapDeleteReqToOutOfStorageAskMngtDvo(dto);
            processCount += this.mapper.deleteOutOfStorageAskItems(dvo);
            removeListDvo.add(dvo);
        }

        if (CollectionUtils.isNotEmpty(removeListDvo)) {
            WsnaOutOfStorageAskMngtDvo removeDvo = removeListDvo.get(0);
            String deleteOstrAkNo = removeDvo.getOstrAkNo();
            List<WsnaOutOfStorageAskMngtDvo> logisticsRemoveDvo = this.mapper.selectDtaDlYnOstrAkNo(deleteOstrAkNo);
            if (WARE_DV_CD_LOGISTICS_CENTER.equals(logisticsRemoveDvo.get(0).getOstrOjWareDvCd())) {
                List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                    .mapWsnaLogisticsOutStorageAskReqDvoToRemoveOutOfStorageAsks(logisticsRemoveDvo);
                logisticsservice.removeOutOfStorageAsks(dvo);
            }

        }

        return processCount;
    }

    @Transactional
    public int saveOutOfStorageAskItems(List<SaveReq> dtos) {
        int processCount = 0;
        String strOstrAkNo = null;

        List<WsnaOutOfStorageAskMngtDvo> insertListDvo = new ArrayList<>();
        List<WsnaOutOfStorageAskMngtDvo> updateListDvo = new ArrayList<>();

        SaveReq saveReq = dtos.get(0);

        log.info("saveReq --->>" + saveReq);
        log.info("saveReq.ostrAkRgstDt ---->" + saveReq.ostrAkRgstDt());

        if (StringUtils.isEmpty(saveReq.ostrAkNo())) {
            strOstrAkNo = this.mapper.selectNewOstrAkNo(
                new FindOstrAkNoReq(saveReq.ostrAkRgstDt(), saveReq.ostrAkTpCd(), saveReq.strOjWareNo())
            );

        }

        for (SaveReq dto : dtos) {
            WsnaOutOfStorageAskMngtDvo dvo = this.converter.mapSaveReqToOutOfStorageAskMngtDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    if (!StringUtils.isEmpty(strOstrAkNo)) {
                        dvo.setOstrAkNo(strOstrAkNo);
                    }
                    processCount += mapper.insertOutOfStorageAskItems(dvo);
                    insertListDvo.add(dvo);

                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateOutOfStorageAskItmes(dvo);
                    updateListDvo.add(dvo);

                }
            }

        }

        if (CollectionUtils.isNotEmpty(insertListDvo)) {
            WsnaOutOfStorageAskMngtDvo insertDvo = insertListDvo.get(0);
            String ostrAkNo = insertDvo.getOstrAkNo();
            List<WsnaOutOfStorageAskMngtDvo> logisticsDvo = this.mapper.selectLogisticsOutStorageAskInfo(ostrAkNo);
            //출고대상창고의 구분이 1(물류센터) 일경우
            if (WARE_DV_CD_LOGISTICS_CENTER.equals(logisticsDvo.get(0).getOstrOjWareDvCd())) {
                List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                    .mapAllCreateOutOfStorageAsksDvo(logisticsDvo);
                logisticsservice.createOutOfStorageAsks(dvo);
            }
        }

        if (CollectionUtils.isNotEmpty(updateListDvo)) {
            WsnaOutOfStorageAskMngtDvo updateDvo = updateListDvo.get(0);
            String updateOstrAkNo = updateDvo.getOstrAkNo();
            List<WsnaOutOfStorageAskMngtDvo> logisticsDvo = this.mapper
                .selectLogisticsOutStorageAskInfo(updateOstrAkNo);
            //출고대상창고의 구분이 1(물류센터) 일경우
            if (WARE_DV_CD_LOGISTICS_CENTER.equals(logisticsDvo.get(0).getOstrOjWareDvCd())) {

                List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                    .mapAllCreateOutOfStorageAsksDvo(logisticsDvo);
                logisticsservice.editOutOfStorageAsks(dvo);

            }

        }

        //TODO: 시쿼스 정의 및 인터페이스(W-SV-I-0027) 개발 후 로직추가.
        return processCount;
    }

}
