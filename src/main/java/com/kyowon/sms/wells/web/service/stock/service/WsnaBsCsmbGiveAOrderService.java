package com.kyowon.sms.wells.web.service.stock.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaBsCsmbGiveAOrderConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.CreateReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.ProdutCodeRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsCsmbGiveAOrderDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsCsmbGiveAOrderDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBsCsmbGiveAOrderMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0014M01 BS소모품 발주수량 산출 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-11-30
 */

@Service
@RequiredArgsConstructor
public class WsnaBsCsmbGiveAOrderService {

    private final WsnaBsCsmbGiveAOrderMapper mapper;
    private final WsnaBsCsmbGiveAOrderConverter converter;
    private final WsnaItemStockItemizationService stockService;
    // 교원프라퍼티파주물류
    private static final String SAP_PLNT_CD = "2108";
    // 파주창고
    private static final String PAJU_SAP_SAVE_LCT_CD = "21082082";
    // 성수창고
    private static final String SGSU_SAP_SAVE_LCT_CD = "21082090";

    /**
    * 품목 조회
    * @param itmKndCd
    * @return
    */
    public List<ProdutCodeRes> getProductCodesByItmKndCd(String itmKndCd) {
        return mapper.selectProductCodesByItmKndCd(itmKndCd);
    }

    /**
     * BS소모품 발주수량 산출 조회
     * @param dto
     * @return
     */
    public List<WsnaBsCsmbGiveAOrderDvo> getBsCsmbGiveAOrderQty(SearchReq dto) {
        String curDt = DateUtil.getNowDayString();

        // 현재년월 <= 관리년월
        if (Integer.parseInt(curDt.substring(0, 6)) <= Integer.parseInt(dto.mngtYm())) {
            return this.getBeforeBsCsmbGiveAOrderQty(dto);
        } else {
            // 관리년월이 현재년월 이전인 경우 저장된 데이터로 보여줌
            return mapper.selectBsCsmbGiveAOrderQty(dto);
        }
    }

    /**
     * BS소모품 발주수량 산출내역 조회 (미래일자인 경우)
     * @param dto
     * @return
     */
    private List<WsnaBsCsmbGiveAOrderDvo> getBeforeBsCsmbGiveAOrderQty(SearchReq dto) {

        String baseDay = DateUtil.getNowDayString();
        String nowMonth = DateUtil.getNowDayString().substring(0, 6);
        // 관리년월
        String mngtYm = dto.mngtYm();
        if (!nowMonth.equals(mngtYm)) {
            baseDay = mngtYm + "01";
        }

        // 성수재고 (0: 포함, 1: 제외)
        String excludeYn = dto.sgsuExcludeYn();

        List<WsnaBsCsmbGiveAOrderDvo> dvos = mapper.selectBeforeBsCsmbGiveAOrderQty(dto);

        // 품목 리스트
        List<String> itmPds = dvos.stream().map(WsnaBsCsmbGiveAOrderDvo::getCsmbPdCd).toList();
        if (CollectionUtils.isNotEmpty(itmPds)) {

            // 파주 재고 조회
            List<RealTimeGradeStockResIvo> pajuStocks = stockService
                .getRealTimeGradeStocks(SAP_PLNT_CD, PAJU_SAP_SAVE_LCT_CD, itmPds);

            dvos.forEach(dvo -> {
                pajuStocks.forEach(stock -> {
                    if (dvo.getCsmbPdCd().equals(stock.getItmPdCd())) {

                        // A등급 재고만 셋팅
                        dvo.setPajuLgstCnrStocQty(stock.getLgstAGdQty());
                    }
                });
            });

            // 성수재고 제외가 아닌 경우
            if (!"1".equals(excludeYn)) {
                List<RealTimeGradeStockResIvo> sgsuStocks = stockService
                    .getRealTimeGradeStocks(SAP_PLNT_CD, SGSU_SAP_SAVE_LCT_CD, itmPds);

                dvos.forEach(dvo -> {
                    sgsuStocks.forEach(stock -> {
                        if (dvo.getCsmbPdCd().equals(stock.getItmPdCd())) {
                            // A등급만 셋팅
                            dvo.setSgsuLgstCnrStocQty(stock.getLgstAGdQty());
                        }
                    });
                });
            }
        }

        for (WsnaBsCsmbGiveAOrderDvo dvo : dvos) {
            // 전체 재고 = 입고대기 + 파주재고 + 성수재고
            BigDecimal woStocQty = dvo.getPajuLgstCnrStocQty().add(dvo.getSgsuLgstCnrStocQty())
                .add(dvo.getStrStnbQty());
            // 월평균 배부수량
            BigDecimal mmAvDdlvQty = dvo.getMmAvDdlvQty();
            // 전체재고 셋팅
            dvo.setWoStocQty(woStocQty);

            // 재고지속월, 필요수량 셋팅
            if (BigDecimal.ZERO == woStocQty || BigDecimal.ZERO == mmAvDdlvQty) {
                dvo.setStocPersMmN(0);
                dvo.setNcstQty(0);
            } else {
                dvo.setStocPersMmN(
                    Math.round(woStocQty.divide(mmAvDdlvQty, 1, BigDecimal.ROUND_CEILING).floatValue())
                );
                // 월평균배부수량 * 3 - 최종재고
                BigDecimal ncstQty = mmAvDdlvQty.multiply(BigDecimal.valueOf(3L)).subtract(woStocQty);
                dvo.setNcstQty(BigDecimal.ZERO.compareTo(ncstQty) > 0 ? 0 : ncstQty.intValue());
            }

            // 최소주문수량
            BigDecimal minOrdQty = dvo.getMinOrdQty();
            // 필요수량
            int ncstQty = dvo.getNcstQty();

            // 발주수량 셋팅
            if (BigDecimal.ZERO == minOrdQty || ncstQty == 0) {
                dvo.setGoQty(BigDecimal.ZERO);

                // 최소수량이 필요수량보다 클 경우
            } else if (minOrdQty.intValue() >= ncstQty) {
                // 최소수량만큼 발주수량을 셋팅
                dvo.setGoQty(minOrdQty);
            } else {
                // 최소수량 < 필요수량
                int i = 1;
                while (minOrdQty.intValue() <= ncstQty) {
                    // 최소수량
                    minOrdQty = minOrdQty.multiply(BigDecimal.valueOf(i++));
                }
                // 최소수량만큼 발주수량을 셋팅
                dvo.setGoQty(minOrdQty);
            }

            // 발주수량
            BigDecimal goQty = dvo.getGoQty();
            // 발주단가
            BigDecimal goUprc = dvo.getGoUprc();

            // 발주금액 셋팅
            dvo.setGoAmt(goQty.multiply(goUprc));

            // 재고지속월
            int stocPersMmN = dvo.getStocPersMmN();

            try {
                // 예상소진일자 셋팅
                dvo.setEtExsDt(DateUtil.addMonths(baseDay, stocPersMmN));
            } catch (ParseException e) {
                throw new BizException(e);
            }
        }

        return dvos;
    }

    /**
     * BS 소모품 발주수량 산출 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int createBsCsmbGiveAOrderQty(List<CreateReq> dtos) {

        int processCount = 0;

        List<WsnaBsCsmbGiveAOrderDvo> dvos = converter.mapAllCreateReqToWsnaBsCsmbGiveAOrderDvo(dtos);

        for (WsnaBsCsmbGiveAOrderDvo dvo : dvos) {
            processCount += mapper.mergeBsCsmbGiveAOrderQty(dvo);
        }

        return processCount;
    }

}
