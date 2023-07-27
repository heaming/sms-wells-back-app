package com.kyowon.sms.wells.web.service.stock.service;

import java.math.BigDecimal;
import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationReturnDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemBaseInformationSearchDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemOrderQuantityDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemBaseInformationConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemBaseInformationMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemBaseInformationDto.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaItemBaseInformationService {

    private final WsnaItemBaseInformationMapper mapper;
    private final WsnaItemBaseInformationConverter converter;

    // 재고서비스
    private final WsnaItemStockItemizationService stockService;

    // (주)교원프라퍼티파주물류(Wells)
    private static final String SAP_PLNT_CD = "2108";
    // 프라파주창고(Wells)
    private static final String SAP_SAVE_LCT_CD = "21082082";
    private static final int SLICE_SIZE = 999;

    private static final String LGST_DV_CD = "1";

    public List<WsnaItemBaseInformationReturnDvo> getItemBaseInformations(SearchReq dto) {

        WsnaItemBaseInformationSearchDvo searchDvo = this.converter.mapSearchReqToWsnaItemBaseInformationSearchDvo(dto);

        List<WsnaItemBaseInformationReturnDvo> returnBaseDvo = this.mapper.selectItemBaseInformations(searchDvo);

        this.getRealTimeItemBaseLogisticStockQtys(returnBaseDvo);

        return returnBaseDvo;
    }

    public List<WsnaItemBaseInformationDvo> getItemBaseInformationsOutOf(SearchReq dto) {

        WsnaItemBaseInformationSearchDvo searchDvo = this.converter.mapSearchReqToWsnaItemBaseInformationSearchDvo(dto);

        String ostrWareDvCd = this.mapper.selectOstrWareDvCd(dto);

        searchDvo.setOstrWareDvCd(ostrWareDvCd);

        List<WsnaItemBaseInformationDvo> itemBaseDvo = this.mapper.selectItemBaseInformationsOutOf(searchDvo);

        //출고대상 창고가 물류센터일경우
        if (LGST_DV_CD.equals(ostrWareDvCd)) {
            // 실시간 물류 재고조회
            this.getRealTimeLogisticStockQtys(itemBaseDvo);
        }

        return itemBaseDvo;
    }

    public List<SearchAplcRes> getItemBaseInformationAplcLists(SearchAplcReq dto) {
        return this.mapper.selectItemBaseInformationAplcLists(dto);
    }

    public List<SearchWareRes> getItemBaseInformationWareDvCds(SearchReq dto) {
        return this.mapper.selectItemBaseInformationWareDvCds(dto);
    }

    private void getRealTimeItemBaseLogisticStockQtys(List<WsnaItemBaseInformationReturnDvo> dvos) {
        if (CollectionUtils.isNotEmpty(dvos)) {
            int size = dvos.size();
            int sliceSize = Math.floorDiv(size, SLICE_SIZE) + (Math.floorMod(size, SLICE_SIZE) > 0 ? 1 : 0);

            for (int i = 0; i < sliceSize; i++) {
                int startIndex = i * SLICE_SIZE;
                int endIndex = (i + 1) * SLICE_SIZE > size ? size : (i + 1) * SLICE_SIZE;
                List<WsnaItemBaseInformationReturnDvo> sliceDvos = dvos.subList(startIndex, endIndex);
                List<String> itmPds = sliceDvos.stream().map(WsnaItemBaseInformationReturnDvo::getItmPdCd).toList();

                //실시간 재고조회 서비스 조회호출
                List<RealTimeGradeStockResIvo> stocks = this.stockService
                    .getRealTimeGradeStocks(SAP_PLNT_CD, SAP_SAVE_LCT_CD, itmPds);
                this.setItemBaseTotalLogisticQty(stocks, sliceDvos);

            }

        }
    }

    private void getRealTimeLogisticStockQtys(List<WsnaItemBaseInformationDvo> dvos) {
        if (CollectionUtils.isNotEmpty(dvos)) {

            int size = dvos.size();
            int sliceSize = Math.floorDiv(size, SLICE_SIZE) + (Math.floorMod(size, SLICE_SIZE) > 0 ? 1 : 0);

            for (int i = 0; i < sliceSize; i++) {
                int startIndex = i * SLICE_SIZE;
                int endIndex = (i + 1) * SLICE_SIZE > size ? size : (i + 1) * SLICE_SIZE;

                List<WsnaItemBaseInformationDvo> sliceDvos = dvos.subList(startIndex, endIndex);
                List<String> itmPds = sliceDvos.stream().map(WsnaItemBaseInformationDvo::getItmPdCd).toList();

                //실시간 재고조회 서비스 조회호출
                List<RealTimeGradeStockResIvo> stocks = this.stockService
                    .getRealTimeGradeStocks(SAP_PLNT_CD, SAP_SAVE_LCT_CD, itmPds);

                this.setTotalLogisticQty(stocks, sliceDvos);

            }

        }
    }

    private void setItemBaseTotalLogisticQty(
        List<RealTimeGradeStockResIvo> stocks, List<WsnaItemBaseInformationReturnDvo> sliceDvos
    ) {

        if (CollectionUtils.isNotEmpty(stocks)) {
            int stockSize = stocks.size();

            for (WsnaItemBaseInformationReturnDvo dvo : sliceDvos) {
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
                dvo.setLgstQty(lgstQty);
            }

        }
    }

    private void setTotalLogisticQty(
        List<RealTimeGradeStockResIvo> stocks, List<WsnaItemBaseInformationDvo> sliceDvos
    ) {
        if (CollectionUtils.isNotEmpty(stocks)) {
            int stockSize = stocks.size();

            for (WsnaItemBaseInformationDvo dvo : sliceDvos) {
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
}
