package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndependenceWareOstrDto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaIndependenceWareOstrConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndependenceWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaIndependenceWareOstrMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0193M01 독립창고 출고 관리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-21
 */

@Service
@RequiredArgsConstructor
public class WsnaIndependenceWareOstrService {

    private final WsnaIndependenceWareOstrMapper mapper;

    private final WsnaIndependenceWareOstrConverter converter;

    // 물량배정 서비스
    private final WsnaQomAsnService qomAsnService;

    // 재고서비스
    private final WsnaItemStockItemizationService stockService;

    // 메시지 서비스
    private final MessageResourceService messageService;

    // (주)교원프라퍼티파주물류(Wells)
    private static final String SAP_PLNT_CD = "2108";
    // 프라파주창고(Wells)
    private static final String SAP_SAVE_LCT_CD = "21082082";
    private static final int SLICE_SIZE = 999;

    private static final String OSTR_AK_TP_CD_QOM_ASN = "360";

    /**
     * 출고창고 조회
     *
     * @param apyYm (필수) 기준년월
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getIndependenceOstrWares(String apyYm) {
        ValidAssert.hasText(apyYm);

        return this.qomAsnService.getQomAsnOstrWares(apyYm);
    }

    /**
     * 품목 조회
     *
     * @return
     */
    public List<SearchPdRes> getIndependenceOstrWares() {
        return this.mapper.selectProducts();
    }

    /**
     * 입고창고 조회
     *
     * @param dto
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getIndependenceStrWares(SearchWareReq dto) {

        return this.mapper.selectIndependenceStrWares(dto);
    }

    /**
     * 독립창고 출고 관리 페이징 조회
     *
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<WsnaIndependenceWareOstrDvo> getIndependenceWareOstrsPaging(SearchReq dto, PageInfo pageInfo) {

        PagingResult<WsnaIndependenceWareOstrDvo> result = this.mapper.selectIndependenceWareOstrs(dto, pageInfo);

        List<WsnaIndependenceWareOstrDvo> dvos = result.getList();

        // 실시간 물류재고 조회 호출
        this.getRealTimeLogisticStockQtys(dvos);
        result.setList(dvos);

        return result;
    }

    /**
     * 독립창고 출고 관리 엑셀 다운로드
     *
     * @param dto
     * @return
     */
    public List<WsnaIndependenceWareOstrDvo> getIndependenceWareOstrsExcelDownload(SearchReq dto) {

        List<WsnaIndependenceWareOstrDvo> dvos = this.mapper.selectIndependenceWareOstrs(dto);
        // 실시간 물류재고 조회 호출
        this.getRealTimeLogisticStockQtys(dvos);

        return dvos;
    }

    /**
     * 실시간 물류 센터 재고 조회
     *
     * @param dvos
     */
    private void getRealTimeLogisticStockQtys(List<WsnaIndependenceWareOstrDvo> dvos) {
        if (CollectionUtils.isNotEmpty(dvos)) {
            String asnOjYm = dvos.get(0).getAsnOjYm();

            // 비고 - 직배창고 물량배정 물류to센터
            String rmkCn = this.messageService.getMessage("MSG_TXT_DIDY_WARE_QOM_ASN_LGST_CNR", new String[] {asnOjYm});

            int size = dvos.size();
            int sliceSize = Math.floorDiv(size, SLICE_SIZE) + (Math.floorMod(size, SLICE_SIZE) > 0 ? 1 : 0);

            for (int i = 0; i < sliceSize; i++) {
                int startIdx = i * SLICE_SIZE;
                int endIdx = (i + 1) * SLICE_SIZE > size ? size : (i + 1) * SLICE_SIZE;

                List<WsnaIndependenceWareOstrDvo> sliceDvos = dvos.subList(startIdx, endIdx);
                List<String> itmPds = sliceDvos.stream().map(WsnaIndependenceWareOstrDvo::getItmPdCd).toList();

                List<RealTimeGradeStockResIvo> stocks = this.stockService
                    .getRealTimeGradeStocks(SAP_PLNT_CD, SAP_SAVE_LCT_CD, itmPds);

                this.setTotalLogisticQty(stocks, sliceDvos, rmkCn);
            }
        }
    }

    /**
     * 물류 물량 셋팅
     *
     * @param stocks
     * @param sliceDvos
     * @param defaultRmkCn
     */
    private void setTotalLogisticQty(
        List<RealTimeGradeStockResIvo> stocks, List<WsnaIndependenceWareOstrDvo> sliceDvos, String defaultRmkCn
    ) {

        int stockSize = 0;
        if (CollectionUtils.isNotEmpty(stocks)) {
            stockSize = stocks.size();
        }

        for (WsnaIndependenceWareOstrDvo dvo : sliceDvos) {
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

            // 비고
            String rmkCn = dvo.getRmkCn();
            if (StringUtils.isEmpty(rmkCn)) {
                dvo.setRmkCn(defaultRmkCn);
            }
        }
    }

    /**
     * 독립창고 출고 관리 저장
     *
     * @param dtos
     * @return
     */
    @Transactional
    public int saveIndependenceWareOstrs(List<SaveReq> dtos) {

        int count = 0;

        List<WsnaIndependenceWareOstrDvo> dvos = this.converter.mapAllSaveReqToWsnaIndependenceWareOstrDvo(dtos);

        // 입고창고 필터링
        List<WsnaIndependenceWareOstrDvo> filterDvos = dvos.stream()
            .filter(distinctByKey(dvo -> dvo.getStrWareNo())).toList();

        for (WsnaIndependenceWareOstrDvo filterDvo : filterDvos) {
            // 출고요청번호
            String newOstrAkNo = this.mapper.selectOstrAkNoByQomAsn(filterDvo);
            if (StringUtils.isEmpty(newOstrAkNo)) {
                newOstrAkNo = this.mapper.selectNewOstrAkNo(OSTR_AK_TP_CD_QOM_ASN);
            }

            // 입고창고번호
            String strWareNo = filterDvo.getStrWareNo();

            // 입고창고에 해당하는 품목 리스트
            List<WsnaIndependenceWareOstrDvo> itms = dvos.stream().filter(dvo -> strWareNo.equals(dvo.getStrWareNo()))
                .toList();
            for (WsnaIndependenceWareOstrDvo dvo : itms) {
                String ostrAkNo = dvo.getOstrAkNo();
                if (StringUtils.isEmpty(ostrAkNo)) {
                    dvo.setOstrAkNo(newOstrAkNo);
                }

                // 출고요청 저장
                count += this.mapper.mergeItmOstrAkIz(dvo);

                // 물량배정 출고수량 업데이트
                int result = this.mapper.updateItmQomAsnIz(dvo);
                // 저장에 실패 하였습니다.
                BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            }
        }

        return count;
    }

    /**
     * distinct 함수
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
