package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.*;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaIndividualWareOstrConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaIndividualWareOstrMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0192M01 개인창고 출고 관리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-21
 */

@Service
@RequiredArgsConstructor
public class WsnaIndividualWareOstrService {

    private final WsnaIndividualWareOstrMapper mapper;

    private final WsnaIndividualWareOstrConverter converter;

    // 물량배정 서비스
    private final WsnaQomAsnService qomAsnService;

    // 재고서비스
    private final WsnaItemStockItemizationService stockService;

    // (주)교원프라퍼티파주물류(Wells)
    private static final String SAP_PLNT_CD = "2108";
    // 프라파주창고(Wells)
    private static final String SAP_SAVE_LCT_CD = "21082082";
    private static final int SLICE_SIZE = 999;

    private static final String OSTR_AK_TP_CD_QOM_ASN = "360";

    /**
     * 출고창고 조회
     * @param apyYm (필수) 기준년월
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getIndividualOstrWares(String apyYm) {
        ValidAssert.hasText(apyYm);

        return this.qomAsnService.getQomAsnOstrWares(apyYm);
    }

    /**
     * 품목 조회
     * @return
     */
    public List<SearchPdRes> getIndividualProducts() {
        return this.mapper.selectProducts();
    }

    /**
     * 입고창고 조회
     * @param dto
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getIndividualStrWares(SearchWareReq dto) {

        return this.mapper.selectIndividualStrWares(dto);
    }

    /**
     * 개인창고 출고 관리 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<WsnaIndividualWareOstrDvo> getIndividualWareOstrsPaging(SearchReq dto, PageInfo pageInfo) {

        PagingResult<WsnaIndividualWareOstrDvo> results = this.mapper.selectIndividualWareOstrs(dto, pageInfo);
        // 개인창고 출고관리 데이터 리스트
        List<WsnaIndividualWareOstrDvo> dvos = results.getList();

        // 실시간 물류재고 조회 호출
        this.getRealTimeLogisticStockQtys(dvos);

        results.setList(dvos);

        return results;
    }

    /**
     * 개인창고 출고 관리 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<WsnaIndividualWareOstrDvo> getIndividualWareOstrsExcelDownload(SearchReq dto) {

        List<WsnaIndividualWareOstrDvo> dvos = this.mapper.selectIndividualWareOstrs(dto);
        // 실시간 물류재고 조회 호출
        this.getRealTimeLogisticStockQtys(dvos);

        return dvos;
    }

    /**
     * 실시간 물류 센터 재고 조회
     * @param dvos
     */
    private void getRealTimeLogisticStockQtys(List<WsnaIndividualWareOstrDvo> dvos) {
        if (CollectionUtils.isNotEmpty(dvos)) {

            int size = dvos.size();
            int sliceSize = Math.floorDiv(size, SLICE_SIZE) + (Math.floorMod(size, SLICE_SIZE) > 0 ? 1 : 0);

            for (int i = 0; i < sliceSize; i++) {
                int startIdx = i * SLICE_SIZE;
                int endIdx = (i + 1) * SLICE_SIZE > size ? size : (i + 1) * SLICE_SIZE;

                List<WsnaIndividualWareOstrDvo> sliceDvos = dvos.subList(startIdx, endIdx);
                List<String> itmPds = sliceDvos.stream().map(WsnaIndividualWareOstrDvo::getItmPdCd).toList();

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
        List<RealTimeGradeStockResIvo> stocks, List<WsnaIndividualWareOstrDvo> sliceDvos
    ) {
        int stockSize = 0;
        if (CollectionUtils.isNotEmpty(stocks)) {
            stockSize = stocks.size();
        }

        for (WsnaIndividualWareOstrDvo dvo : sliceDvos) {
            String itmPdCd = dvo.getItmPdCd();

            // 물류재고
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

            dvo.setLogisticStocQty(lgstQty);

            // 출고수량
            BigDecimal outQty = dvo.getOutQty();
            // 필터박스수량
            BigDecimal filterBoxQty = dvo.getFilterBoxQty();

            if (!BigDecimal.ZERO.equals(filterBoxQty) && !BigDecimal.ZERO.equals(outQty)) {
                long ostrQty = outQty.longValue();
                long filterQty = filterBoxQty.longValue();

                long outBoxQty = Math.floorDiv(ostrQty, filterQty)
                    + (Math.floorMod(ostrQty, filterQty) > 0 ? 1 : 0);

                dvo.setOutBoxQty(BigDecimal.valueOf(outBoxQty));
            } else {
                dvo.setOutBoxQty(BigDecimal.ZERO);
            }
        }
    }

    /**
     * 개인창고 출고관리 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int saveIndividualWareOstrs(List<SaveReq> dtos) {

        int count = 0;

        List<WsnaIndividualWareOstrDvo> dvos = this.converter.mapAllSaveReqToWsnaIndividualWareOstrDvo(dtos);

        String newOstrAkNo = this.mapper.selectNewOstrAkNo(OSTR_AK_TP_CD_QOM_ASN);

        for (WsnaIndividualWareOstrDvo dvo : dvos) {
            // 출고요청 저장
            String ostrAkNo = dvo.getOstrAkNo();
            if (StringUtils.isEmpty(ostrAkNo)) {
                dvo.setOstrAkNo(newOstrAkNo);
            }

            count += this.mapper.mergeItmOstrAkIz(dvo);

            // 물량배정 출고수량 업데이트
            int result = this.mapper.updateItmQomAsnIz(dvo);
            // 저장에 실패 하였습니다.
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
        }

        return count;
    }

}
