package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaItemOrderQuantityDto.SearchReq;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaItemOrderQuantityConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemOrderQuantityDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemOrderQuantitySearchDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaItemOrderQuantityMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0296M01 BS자재 발주수량 산출 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-28
 */

@Service
@RequiredArgsConstructor
public class WsnaItemOrderQuantityService {

    private final WsnaItemOrderQuantityMapper mapper;

    private final WsnaItemOrderQuantityConverter converter;

    // 재고서비스
    private final WsnaItemStockItemizationService stockService;

    // (주)교원프라퍼티파주물류(Wells)
    private static final String SAP_PLNT_CD = "2108";
    // 프라파주창고(Wells)
    private static final String SAP_SAVE_LCT_CD = "21082082";
    private static final int SLICE_SIZE = 999;

    /**
     * BS자재 발주수량 산출 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<WsnaItemOrderQuantityDvo> getItemOrderQuantityPaging(SearchReq dto, PageInfo pageInfo) {

        WsnaItemOrderQuantitySearchDvo searchDvo = this.converter.mapSearchReqToWsnaItemOrderQuantitySearchDvo(dto);

        boolean needTotalCount = pageInfo.isNeedTotalCount();
        // 총 건수를 조회해야 할 경우
        if (needTotalCount) {
            // 건수 조회
            long totalCount = this.mapper.selectItemOrderQuantityCount(searchDvo);
            pageInfo.setTotalCount(totalCount);
            pageInfo.setNeedTotalCount(Boolean.FALSE);
        }

        int pageIndex = pageInfo.getPageIndex();
        int pageSize = pageInfo.getPageSize();

        int startRow = (pageIndex - 1) * pageSize;
        searchDvo.setOffSet(startRow);
        searchDvo.setSize(pageSize);

        List<WsnaItemOrderQuantityDvo> itmOrdQtys = this.mapper.selectItemOrderQuantitiy(searchDvo);
        // 실시간 물류 재고조회
        this.getRealTimeLogisticStockQtys(itmOrdQtys);

        return new PagingResult<>(itmOrdQtys, pageInfo);
    }

    /**
     * BS자재 발주수량 산출 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<WsnaItemOrderQuantityDvo> getItemOrderQuantityExcelDownload(SearchReq dto) {
        WsnaItemOrderQuantitySearchDvo searchDvo = this.converter.mapSearchReqToWsnaItemOrderQuantitySearchDvo(dto);

        List<WsnaItemOrderQuantityDvo> itmOrdQtys = this.mapper.selectItemOrderQuantitiy(searchDvo);
        // 실시간 물류 재고조회
        this.getRealTimeLogisticStockQtys(itmOrdQtys);

        return itmOrdQtys;
    }

    /**
     * 실시간 물류 센터 재고 조회
     * @param dvos
     */
    private void getRealTimeLogisticStockQtys(List<WsnaItemOrderQuantityDvo> dvos) {

        if (CollectionUtils.isNotEmpty(dvos)) {

            int size = dvos.size();
            int sliceSize = Math.floorDiv(size, SLICE_SIZE) + (Math.floorMod(size, SLICE_SIZE) > 0 ? 1 : 0);

            for (int i = 0; i < sliceSize; i++) {
                int startIdx = i * SLICE_SIZE;
                int endIdx = (i + 1) * SLICE_SIZE > size ? size : (i + 1) * SLICE_SIZE;

                List<WsnaItemOrderQuantityDvo> sliceDvos = dvos.subList(startIdx, endIdx);
                List<String> itmPds = sliceDvos.stream().map(WsnaItemOrderQuantityDvo::getPdCd).toList();

                List<RealTimeGradeStockResIvo> stocks = this.stockService
                    .getRealTimeGradeStocks(SAP_PLNT_CD, SAP_SAVE_LCT_CD, itmPds);

                this.setTotalLogisticQty(stocks, sliceDvos);
            }
        }
    }

    /**
     * 물류 물량 셋팅
     * @param stocks
     * @param sliceDvos
     */
    private void setTotalLogisticQty(
        List<RealTimeGradeStockResIvo> stocks, List<WsnaItemOrderQuantityDvo> sliceDvos
    ) {

        if (CollectionUtils.isNotEmpty(stocks)) {

            int stockSize = stocks.size();

            for (WsnaItemOrderQuantityDvo dvo : sliceDvos) {
                String pdCd = dvo.getPdCd();

                BigDecimal thmQomAsnQty = dvo.getThmQomAsnQty();
                BigDecimal lgstQty = BigDecimal.ZERO;

                for (int i = 0; i < stockSize; i++) {
                    RealTimeGradeStockResIvo stock = stocks.get(i);

                    String stockPdCd = stock.getItmPdCd();

                    if (pdCd.equals(stockPdCd)) {
                        BigDecimal aGdQty = ObjectUtils.isEmpty(stock.getLgstAGdQty()) ? BigDecimal.ZERO
                            : stock.getLgstAGdQty();
                        BigDecimal bGdQty = ObjectUtils.isEmpty(stock.getLgstBGdQty()) ? BigDecimal.ZERO
                            : stock.getLgstBGdQty();
                        BigDecimal eGdQty = ObjectUtils.isEmpty(stock.getLgstEGdQty()) ? BigDecimal.ZERO
                            : stock.getLgstEGdQty();
                        BigDecimal rGdQty = ObjectUtils.isEmpty(stock.getLgstRGdQty()) ? BigDecimal.ZERO
                            : stock.getLgstRGdQty();

                        lgstQty = aGdQty.add(bGdQty).add(eGdQty).add(rGdQty);

                        stockSize--;
                        stocks.remove(stock);
                        break;
                    }
                }

                dvo.setLogisticCnrQty(lgstQty);
                dvo.setLogisticSum(thmQomAsnQty.add(lgstQty));
            }
        }
    }

}
