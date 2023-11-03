package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaIndividualWareOstrDto.*;

import java.math.BigDecimal;
import java.util.Comparator;
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
import com.kyowon.sms.wells.web.service.stock.converter.WsnaIndividualWareOstrConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaIndividualWareOstrDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsDeliveryKssDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaIndividualWareOstrMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
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

    // 메시지 서비스
    private final MessageResourceService messageService;

    // 물량배정 서비스
    private final WsnaQomAsnService qomAsnService;

    // 재고서비스
    private final WsnaItemStockItemizationService stockService;

    // 물류 출고 서비스
    private final WsnaLogisticsOutStorageAskService lgstService;

    // HQ관련 서비스
    private final WsnaLogisticsDeliveryAskService deliveryService;

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
     * 개인창고 출고 관리 조회
     * @param dto
     * @return
     */
    public List<WsnaIndividualWareOstrDvo> getIndividualWareOstrs(SearchReq dto) {

        // 개인창고 출고관리 데이터 리스트
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
                    break;
                }
            }

            dvo.setLogisticStocQty(lgstQty);
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

        // 입고창고 필터링
        List<WsnaIndividualWareOstrDvo> filterDvos = dvos.stream()
            .filter(distinctByKey(dvo -> dvo.getStrWareNo()))
            .sorted(Comparator.comparing(WsnaIndividualWareOstrDvo::getWareNm)).toList();

        for (WsnaIndividualWareOstrDvo filterDvo : filterDvos) {
            // 출고요청번호
            String newOstrAkNo = this.mapper.selectOstrAkNoByQomAsn(filterDvo);
            if (StringUtils.isEmpty(newOstrAkNo)) {
                newOstrAkNo = this.mapper.selectNewOstrAkNo(OSTR_AK_TP_CD_QOM_ASN);
            }

            // 입고창고번호
            String strWareNo = filterDvo.getStrWareNo();

            // 입고창고에 해당하는 품목 리스트
            List<WsnaIndividualWareOstrDvo> itms = dvos.stream().filter(dvo -> strWareNo.equals(dvo.getStrWareNo()))
                .toList();
            for (WsnaIndividualWareOstrDvo dvo : itms) {
                String ostrAkNo = dvo.getOstrAkNo();
                if (StringUtils.isEmpty(ostrAkNo)) {
                    dvo.setOstrAkNo(newOstrAkNo);
                }

                // 물류재고 체크
                this.validLogisticStockQty(dvo);

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
     * 물류창고 재고 유효성 체크
     * @param dvo
     */
    private void validLogisticStockQty(WsnaIndividualWareOstrDvo dvo) {

        // 물류재고
        BigDecimal logisticQty = dvo.getLogisticStocQty();
        // 출고수량
        BigDecimal outQty = dvo.getOutQty();
        // 출고누계수량
        BigDecimal ostrAggQty = this.mapper.selectItemByOstrAggQty(dvo);

        // 출고수량 + 출고누계수량 > 물류재고
        boolean isNotValid = outQty.add(ostrAggQty).compareTo(logisticQty) > 0;

        if (isNotValid) {
            String wareNm = dvo.getWareNm();
            String wareTxt = this.messageService.getMessage("MSG_TXT_WARE");
            String itmPdCd = dvo.getItmPdCd();
            String itmTxt = this.messageService.getMessage("MSG_TXT_OF_ITM");

            StringBuilder sb = new StringBuilder();
            sb.append(wareNm).append(" ").append(wareTxt).append(" ").append(itmPdCd).append(" ").append(itmTxt);

            // {0} 상위창고 재고수량이 부족합니다.
            BizAssert.isFalse(true, "MSG_ALT_HGR_WARE_STOC_STG", new String[] {sb.toString()});
        }
    }

    /**
     * distinct 함수
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 물류 이관 데이터 조회
     * @param dto
     * @return
     */
    public List<WsnaIndividualWareOstrDvo> getLogisticsTransferDatas(SearchTranferReq dto) {

        // 물류 이관 데이터 조회
        List<WsnaIndividualWareOstrDvo> dvos = this.mapper.selectLogisticsTransferDatas(dto);

        // 실시간 물류재고 조회 호출
        this.getRealTimeLogisticStockQtys(dvos);

        return dvos;
    }

    /**
     * 물류 전송
     * @param dto
     * @return
     */
    @Transactional(timeout = 300)
    public int createIndividualLogisticsTransfer(CreateReq dto) {

        WsnaLogisticsDeliveryKssDvo dvo = this.converter.mapCreateReqToWsnaLogisticsDeliveryKssDvo(dto);

        // KSS물량배정송신전문 데이터 생성
        int count = this.deliveryService.createKssQomAsnSendTemp(dvo);
        // 적용 대상 데이터가 없습니다.
        BizAssert.isFalse(count == 0, "MSG_ALT_NO_APPY_OBJ_DT");

        List<WsnaLogisticsOutStorageAskReqDvo> dvos = this.mapper.selectIndividualLogisticsTransfer(dvo);

        // 물류 출고처리
        this.lgstService.createQomOutOfStorageAsks(dvos);

        return count;
    }

}
