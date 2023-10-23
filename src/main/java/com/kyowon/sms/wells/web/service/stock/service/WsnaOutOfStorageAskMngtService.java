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
import com.sds.sflex.system.config.validation.ValidAssert;

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

    /**
     * 출고요청 관리 엑셀다운로드
     * @param dto
     * @return
     */
    public List<WsnaOutOfStorageAskMngtDvo> getOutOfStorageItemExcelDownload(SearchReq dto) {

        WsnaOutOfStorageAskMngtSearchDvo searchExcelDvo = this.converter
            .mapAllSearchReqToExcelOutOfStorageAskMngtDvo(dto);

        String ostrWareDvCd = this.mapper.selectOstrWareDvCd(dto);
        searchExcelDvo.setOstrWareDvCd(ostrWareDvCd);

        List<WsnaOutOfStorageAskMngtDvo> outOfDvo = this.mapper.selectOutOfStorageItms(searchExcelDvo);
        // 출고창고구분코드가 물류일경우 물류센터 재고 조회 서비스 호출
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

    /**
     * 출고요청 관리 삭제
     * @param dtos
     * @return
     */
    @Transactional
    public int removeOutOfStorageAskItems(List<RemoveReq> dtos) {
        int processCount = 0;
        List<WsnaOutOfStorageAskMngtDvo> removeListDvo = new ArrayList<>();

        for (RemoveReq dto : dtos) {
            WsnaOutOfStorageAskMngtDvo dvo = this.converter.mapDeleteReqToOutOfStorageAskMngtDvo(dto);
            processCount += this.mapper.deleteOutOfStorageAskItems(dvo);
            removeListDvo.add(dvo);
        }

        //삭제한 데이터의 출고요청 순번만 리스트형태로 변형
        List<String> removeOstrAkSns = removeListDvo.stream().map(WsnaOutOfStorageAskMngtDvo::getOstrAkSn).distinct()
            .toList();

        if (CollectionUtils.isNotEmpty(removeListDvo)) {
            WsnaOutOfStorageAskMngtDvo removeDvo = removeListDvo.get(0);
            String deleteOstrAkNo = removeDvo.getOstrAkNo();
            List<WsnaOutOfStorageAskMngtDvo> logisticsRemoveDvo = this.mapper
                .selectDtaDlYnOstrAkNo(deleteOstrAkNo, removeOstrAkSns);
            //출고요청창고구분코드가 2(서비스센터)이면서 출고대상창고가 물류창고인경우 택배가아닌경우
            if (WARE_DV_CD_LOGISTICS_CENTER.equals(logisticsRemoveDvo.get(0).getOstrOjWareDvCd())
                && "2".equals(logisticsRemoveDvo.get(0).getOstrAkWareDvCd())
                && !"01".equals(logisticsRemoveDvo.get(0).getOvivTpCd())) {
                List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                    .mapWsnaLogisticsOutStorageAskReqDvoToRemoveOutOfStorageAsks(logisticsRemoveDvo);
                logisticsservice.removeOutOfStorageAsks(dvo);

                //출고요청창고구분코드가 2(서비스센터)이면서 출고대상창고가 물류창고이며 배차형태가 택배일경우
            } else if (WARE_DV_CD_LOGISTICS_CENTER.equals(logisticsRemoveDvo.get(0).getOstrOjWareDvCd())
                && "2".equals(logisticsRemoveDvo.get(0).getOstrAkWareDvCd())
                && "01".equals(logisticsRemoveDvo.get(0).getOvivTpCd())) {

                List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                    .mapDeliveryWsnaLogisticsOutStorageAskReqDvoToRemoveOutOfStorageAsks(logisticsRemoveDvo);
                logisticsservice.removeOutOfStorageAsksWithPcsv(dvo);

            } else if (WARE_DV_CD_LOGISTICS_CENTER.equals(logisticsRemoveDvo.get(0).getOstrOjWareDvCd())
                && "3".equals(logisticsRemoveDvo.get(0).getOstrAkWareDvCd())) {
                //출고요청창고구분코드가 3(영업센터)이면서 출고대상창고가 물류창고인경우
                List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                    .mapBusinessWsnaLogisticsOutStorageAskReqDvoToRemoveOutOfStorageAsks(logisticsRemoveDvo);
                logisticsservice.removeOutOfStorageAsksWithPcsv(dvo);
            }

        }

        return processCount;
    }

    /**
     * 출고요청 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int saveOutOfStorageAskItems(List<SaveReq> dtos) {
        int processCount = 0;
        ValidAssert.notEmpty(dtos);
        String strOstrAkNo = null;

        List<String> createOstrAkNos = new ArrayList<>();

        SaveReq saveReq = dtos.get(0);

        List<WsnaOutOfStorageAskMngtDvo> dvos = this.converter.mapAllListSaveReqToOutOfStorageAskMngtDvo(dtos);

        //상태가 신규인건
        List<WsnaOutOfStorageAskMngtDvo> createListDvos = dvos.stream()
            .filter(dvo -> CommConst.ROW_STATE_CREATED.equals(dvo.getRowState())).toList();

        //상태가 업데이트인건
        List<WsnaOutOfStorageAskMngtDvo> updateListDvos = dvos.stream()
            .filter(dvo -> CommConst.ROW_STATE_UPDATED.equals(dvo.getRowState())).toList();

        //출고요청창고구분코드 조회
        String akWareDvCd = this.mapper.selectAkWareDvCd(saveReq.strOjWareNo());
        //요청한 창고구분코드 조회
        String wareDvCd = this.mapper.selectLogisticsOstrDvCd(saveReq.ostrOjWareNo());
        //분기처리를 위한 배차유형코드
        String ovivTpCd = saveReq.ovivTpCd();

        /* 출고대상창고가 물류창고인경우 */
        if ("1".equals(wareDvCd)) {
            if (createListDvos.size() > 0) {
                List<String> lgstWkMthdCds = createListDvos.stream().map(WsnaOutOfStorageAskMngtDvo::getChkLgstWkMthdCd)
                    .distinct()
                    .toList();

                for (String lgstWkMthdCd : lgstWkMthdCds) {
                    List<WsnaOutOfStorageAskMngtDvo> items = dvos.stream()
                        .filter(dvo -> lgstWkMthdCd.equals(dvo.getChkLgstWkMthdCd()))
                        .toList();
                    String newOstrAkNo = this.mapper.selectLogisticsNewOstrAkNo(items.get(0));
                    createOstrAkNos.add(newOstrAkNo);
                    for (WsnaOutOfStorageAskMngtDvo item : items) {
                        item.setOstrAkNo(newOstrAkNo);

                        processCount += mapper.insertOutOfStorageAskItems(item);

                    }

                }
                //create가 있을경우
                if (CollectionUtils.isNotEmpty(createListDvos)) {

                    //서비스센터이면서 배차유형코드가 01(배송)
                    if ("2".equals(akWareDvCd) && "01".equals(ovivTpCd)) {
                        //create 물류 조회
                        List<WsnaOutOfStorageAskMngtDvo> createDeliveryLogisticsDvo = this.mapper
                            .selectDeliveryLogisticsOutStorageAskInfo(createOstrAkNos);
                        List<WsnaLogisticsOutStorageAskReqDvo> deliveryDvo = this.converter
                            .mapAlldeliveryCreateOutOfStorageAsksDvo(createDeliveryLogisticsDvo);
                        logisticsservice.createOutOfStorageAsksWithPcsv(deliveryDvo);
                        //서비스센터이면서 배차유형코드가 01(배송)이 아닌 건들
                    } else if ("2".equals(akWareDvCd) && !"01".equals(ovivTpCd)) {
                        List<WsnaOutOfStorageAskMngtDvo> createLogisticsDvo = this.mapper
                            .selectLogisticsOutStorageAskInfo(createOstrAkNos);
                        List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                            .mapAllCreateOutOfStorageAsksDvo(createLogisticsDvo);
                        logisticsservice.createOutOfStorageAsks(dvo, false);
                    } else {
                        //영업센터일경우
                        List<WsnaOutOfStorageAskMngtDvo> businessCreateLogisticsDvo = this.mapper
                            .selectBusinessLogisticsOutStorageAskInfo(createOstrAkNos);
                        List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                            .mapAllbusinessCreateOutOfStorageAsksDvo(businessCreateLogisticsDvo);
                        logisticsservice.createOutOfStorageAsksWithPcsv(dvo);
                    }

                }
            }

            //update가 있을경우
            if (updateListDvos.size() > 0) {
                List<String> updateOstrAkNos = updateListDvos.stream().map(WsnaOutOfStorageAskMngtDvo::getOstrAkNo)
                    .distinct()
                    .toList();
                for (WsnaOutOfStorageAskMngtDvo updatesDvo : updateListDvos) {
                    processCount += mapper.updateOutOfStorageAskItmes(updatesDvo);

                }

                //업데이트가 서비스센터일 경우
                if ("2".equals(akWareDvCd) && "01".equals(ovivTpCd)) {
                    List<WsnaOutOfStorageAskMngtDvo> updateDeliveryLogisticsDvo = this.mapper
                        .selectDeliveryLogisticsOutStorageAskInfo(updateOstrAkNos);
                    List<WsnaLogisticsOutStorageAskReqDvo> deliveryDvo = this.converter
                        .mapAlldeliveryCreateOutOfStorageAsksDvo(updateDeliveryLogisticsDvo);
                    logisticsservice.editOutOfStorageAsksWithPcsv(deliveryDvo);

                } else if ("2".equals(akWareDvCd) && !"01".equals(ovivTpCd)) {
                    List<WsnaOutOfStorageAskMngtDvo> updateLogisticsDvo = this.mapper
                        .selectLogisticsOutStorageAskInfo(updateOstrAkNos);
                    List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                        .mapAllCreateOutOfStorageAsksDvo(updateLogisticsDvo);
                    logisticsservice.editOutOfStorageAsks(dvo);

                } else {
                    //영업센터일 경우
                    List<WsnaOutOfStorageAskMngtDvo> businessUpdateLogisticsDvo = this.mapper
                        .selectBusinessLogisticsOutStorageAskInfo(updateOstrAkNos);
                    List<WsnaLogisticsOutStorageAskReqDvo> dvo = this.converter
                        .mapAllbusinessCreateOutOfStorageAsksDvo(businessUpdateLogisticsDvo);
                    logisticsservice.editOutOfStorageAsksWithPcsv(dvo);
                }

            }
            //출고대상창고가 물류창고가 아닌경우
        } else {

            if (StringUtils.isEmpty(saveReq.ostrAkNo())) {
                strOstrAkNo = this.mapper.selectNewOstrAkNo(
                    new FindOstrAkNoReq(saveReq.ostrAkRgstDt(), saveReq.ostrAkTpCd(), saveReq.strOjWareNo())
                );

            }
            for (WsnaOutOfStorageAskMngtDvo createDvo : createListDvos) {
                if (!StringUtils.isEmpty(strOstrAkNo)) {
                    createDvo.setOstrAkNo(strOstrAkNo);
                }
                processCount += mapper.insertOutOfStorageAskItems(createDvo);

            }

            for (WsnaOutOfStorageAskMngtDvo updateDvo : updateListDvos) {
                processCount += mapper.updateOutOfStorageAskItmes(updateDvo);
            }

        }
        return processCount;
    }

}
