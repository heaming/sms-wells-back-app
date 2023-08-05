package com.kyowon.sms.wells.web.service.stock.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaReturningGoodsOstrConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaReturningGoodsOstrMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0001M01 반품출고 등록
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaReturningGoodsOstrService {

    final String RETURN_INSIDE = "261"; // 반품출고(내부)
    final String RETURN_OUTSIDE = "262"; // 반품출고(외부)
    final String WARE_DV_CD_LOGISTICS_CENTER = "1"; // 창고구분코드 = 물류센터

    final String RETURN_DISUSE = "212"; //폐기출고

    private final WsnaReturningGoodsOstrMapper mapper;

    private final WsnaItemStockItemizationService itemStockservice;

    private final WsnaReturningGoodsOstrConverter converter;

    private final WsnaLogisticsInStorageAskService logisticsService;

    public List<SearchWarehouseRes> getWareHouses(SearchWarehouseReq dto) {
        return this.mapper.selectWareHouses(dto);
    }

    public SearchRes getReturningGoodsOstrs(SearchReq dto) {
        ItemOutOfStorage itemOutOfStorage = this.mapper.selectItemOutOfStorage(dto);
        List<ReturningGoods> returningGoods = this.converter
            .mapAllReturningGoodsDvoToReturningGoods(
                this.mapper.selectReturningGoodsOstrs(itemOutOfStorage.itmOstrNo())
            );

        return new SearchRes(itemOutOfStorage, returningGoods);
    }

    public Boolean isClosed(String itmOstrNo) {
        String sellReceiptDate = this.mapper.selectIsClosedByPk(itmOstrNo);
        return StringUtil.isNotEmpty(sellReceiptDate);
    }

    @Transactional
    public int saveReturningGoodsOstrs(List<SaveReq> dtos) throws ParseException {
        int processCount = 0;
        int serialNumber = 0;

        SaveReq saveReq = dtos.get(0);

        String itmOstrNo = this.mapper.selectNextItmOstrNo(new FindItmOstrNoReq(saveReq.ostrTpCd(), saveReq.ostrDt()));
        String itmStrNo = null;

        if (isReturning(saveReq.ostrTpCd()) && StringUtil.isNotBlank(saveReq.strWareNo())) {
            itmStrNo = this.mapper
                .selectNextItmStrNo(new FindItmStrNoReq(saveReq.ostrTpCd(), saveReq.ostrDt(), saveReq.strWareNo()));
        }

        for (SaveReq dto : dtos) {
            serialNumber += 1;
            WsnaReturningGoodsDvo dvo = this.converter.mapSaveReqToReturningGoodsDvo(dto);

            dvo.setItmOstrNo(itmOstrNo);
            dvo.setOstrSn(String.valueOf(serialNumber));
            dvo.setItmStrNo(itmStrNo);
            dvo.setStrSn(String.valueOf(serialNumber));

            // 품목출고내역 insert
            int result = this.mapper.insertItemForwardingHistory(dvo);

            result += this.mapper.insertItemReceivingHistory(dvo);

            if (isReturnToLogistics(dvo.getOstrTpCd(), dvo.getStrWareDvCd())) {
                // TODO: 반품(내부)이고 입고 창고가 물류센터인 경우 - 반품요청 중계 테이블 Insert
                // TODO: 품목재고내역관리 서비스(W-SV-S-0087)의 품목재고내역 등록 메소드(saveItemStockIzRgsts)를 호출 - 반품출고
                String logisticsItmOstrNo = dvo.getItmOstrNo();
                log.debug(logisticsItmOstrNo);
                String ostrSn = dvo.getOstrSn();
                List<WsnaReturningGoodsDvo> logisticsDvo = this.mapper
                    .selectLogisticsReturningGoodsAskInfo(logisticsItmOstrNo, ostrSn);

                List<WsnaLogisticsInStorageAskReqDvo> returnDvo = this.converter
                    .mapAllReturningGoodsDvoToLogisticsInStorageAskReqDvo(logisticsDvo);

                logisticsService.createInStorageAsks(returnDvo);

                WsnaItemStockItemizationReqDvo returnOstrDvo = setReturningOstrWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );
                result += itemStockservice.createStock(returnOstrDvo);

                WsnaItemStockItemizationReqDvo returnMoveDvo = setReturningMoveWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );
                result += itemStockservice.saveStockMovement(returnMoveDvo);

                WsnaItemStockItemizationReqDvo returnStrDvo = setReturningStrWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );

                result += itemStockservice.createStock(returnStrDvo);
            } else if (isReturning(dvo.getOstrTpCd())) {
                // 반품(내부/외부)이고 입고창고가 물류센터가 아닌 경우 - 품목입고내역 insert
                //                result = this.mapper.insertItemReceivingHistory(dvo);
                // TODO: 품목재고내역 등록 메소드(saveItemStockIzRgsts)를 호출 - 반품출고

                WsnaItemStockItemizationReqDvo returnOstrDvo = setReturningOstrWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );
                result += itemStockservice.createStock(returnOstrDvo);

                // TODO: 품목재고내역 이동 메소드(saveItemStockIzMmts)를 호출 - 재고이동
                WsnaItemStockItemizationReqDvo returnMoveDvo = setReturningMoveWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );
                result += itemStockservice.saveStockMovement(returnMoveDvo);
                // TODO: 품목재고내역 등록 메소드(saveItemStockIzRgsts)를 호출 - 입고

                WsnaItemStockItemizationReqDvo returnStrDvo = setReturningStrWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );

                result += itemStockservice.createStock(returnStrDvo);
            } else {
                // TODO: 품목재고내역 등록 메소드(saveItemStockIzRgsts)를 호출 - 폐기출고
                WsnaItemStockItemizationReqDvo returnDisuseDvo = setReturningDisuseWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );

                result += itemStockservice.createStock(returnDisuseDvo);
            }

            //            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            processCount += result;
        }

        return processCount;
    }

    @Transactional
    public int removeReturningGoodsOstrs(List<RemoveReq> dtos) throws ParseException {
        int processCount = 0;

        for (RemoveReq dto : dtos) {
            WsnaReturningGoodsDvo dvo = this.converter.mapRemoveReqToReturningGoodsDvo(dto);

            // 반품출고(내부/외부)인 경우
            if (isReturning(dvo.getOstrTpCd())) {
                // 입고창고가 물류센터인 경우
                if (WARE_DV_CD_LOGISTICS_CENTER.equals(dvo.getStrWareDvCd())) {
                    // TODO: 물류센터에 출고 요청 취소(삭제) Interface 를 위한 중계테이블에 Insert
                    List<WsnaReturningGoodsDvo> returnListDvo = this.mapper.selectLogisticsRemoveReturn(dvo);

                    List<WsnaLogisticsInStorageAskReqDvo> returnDvo = this.converter
                        .mapAllRemoveReturningGoodsDvoToLogisticsInStorageAskReqDvo(returnListDvo);

                    logisticsService.removeInStorageAsks(returnDvo);

                    // TODO: 품목재고내역 삭제 메소드(saveItemStockIzDls)를 호출 - 입고창고의 입고재고수량 삭제
                    WsnaItemStockItemizationReqDvo returnRemoveDvo = setReturningRemoveWsnaItemStockItemizationDtoSaveReq(
                        dvo
                    );

                    itemStockservice.removeStock(returnRemoveDvo);

                    // TODO: 품목재고내역 이동 메소드(saveItemStockIzMmts)를 호출 - 입고창고의 이동재고수량 삭제

                    WsnaItemStockItemizationReqDvo moveDvo = setReturningRemoveMoveWsnaItemStockItemizationDtoSaveReq(
                        dvo
                    );

                    itemStockservice.saveStockMovement(moveDvo);
                    //                    BizAssert.isTrue(moveResult == 1, "MSG_ALT_DEL_ERR");

                    this.mapper.deleteItemReceivingHistory(dvo); // 품목입고내역삭제

                } else {
                    // TODO: 품목재고내역 삭제 메소드(saveItemStockIzDls)를 호출 - 입고창고의 입고재고수량 삭제
                    WsnaItemStockItemizationReqDvo returnRemoveDvo = setReturningRemoveWsnaItemStockItemizationDtoSaveReq(
                        dvo
                    );

                    itemStockservice.removeStock(returnRemoveDvo);
                    //                    BizAssert.isTrue(removeResult == 1, "MSG_ALT_DEL_ERR");

                    // TODO: 품목재고내역 이동 메소드(saveItemStockIzMmts)를 호출 - 입고창고의 이동재고수량 삭제

                    WsnaItemStockItemizationReqDvo moveDvo = setReturningRemoveMoveWsnaItemStockItemizationDtoSaveReq(
                        dvo
                    );

                    itemStockservice.saveStockMovement(moveDvo);
                    //                    BizAssert.isTrue(moveResult == 1, "MSG_ALT_DEL_ERR");

                    this.mapper.deleteItemReceivingHistory(dvo); // 품목입고내역삭제
                }
            }

            // TODO: 품목재고내역 삭제 메소드(saveItemStockIzDls)를 호출 - 출고창고의 출고재고수량 복원
            WsnaItemStockItemizationReqDvo ostrRemoveDvo = setReturningOstrRemoveWsnaItemStockItemizationDtoSaveReq(
                dvo
            );

            itemStockservice.removeStock(ostrRemoveDvo);
            //            BizAssert.isTrue(ostrResult == 1, "MSG_ALT_DEL_ERR");

            int result = this.mapper.deleteItemForwardingHistory(dvo); // 품목출고내역삭제

            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }

        return processCount;
    }

    public Boolean isReturning(String ostrTpCd) {
        return RETURN_INSIDE.equals(ostrTpCd) || RETURN_OUTSIDE.equals(ostrTpCd);
    }

    public Boolean isReturnToLogistics(String ostrTpCd, String strWareDvCd) {
        return RETURN_INSIDE.equals(ostrTpCd) && WARE_DV_CD_LOGISTICS_CENTER.equals(strWareDvCd);
    }

    /*품목재고내역관리 서비스(W-SV-S-0087)의 품목재고내역 등록 메소드(saveItemStockIzRgsts)를 호출 - 반품출고*/
    protected WsnaItemStockItemizationReqDvo setReturningOstrWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getOstrDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getOstrDt());
        reqDvo.setWareDv(vo.getWareDvCd());
        reqDvo.setWareNo(vo.getOstrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        reqDvo.setIostTp("261");
        reqDvo.setWorkDiv("A");
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMngtUnitCd());
        reqDvo.setItemGd(vo.getItmGdCd());
        reqDvo.setQty(String.valueOf(vo.getOstrQty()));
        return reqDvo;
    }

    protected WsnaItemStockItemizationReqDvo setReturningMoveWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsDvo vo
    ) {
        WsnaItemStockItemizationReqDvo moveDvo = new WsnaItemStockItemizationReqDvo();
        moveDvo.setProcsYm(vo.getOstrDt().substring(0, 6));
        moveDvo.setProcsDt(vo.getOstrDt());
        moveDvo.setWareDv(vo.getStrWareDvCd());
        moveDvo.setWareNo(vo.getStrWareNo());
        moveDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        moveDvo.setIostTp("991");
        moveDvo.setWorkDiv("A");
        moveDvo.setItmPdCd(vo.getItmPdCd());
        moveDvo.setMngtUnit(vo.getMngtUnitCd());
        moveDvo.setItemGd(vo.getItmGdCd());
        moveDvo.setQty(String.valueOf(vo.getOstrQty()));
        return moveDvo;
    }

    protected WsnaItemStockItemizationReqDvo setReturningStrWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getOstrDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getOstrDt());
        reqDvo.setWareDv(vo.getStrWareDvCd());
        reqDvo.setWareNo(vo.getStrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getStrWareMngtPrtnrNo());
        reqDvo.setIostTp("161");
        reqDvo.setWorkDiv("A");
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMngtUnitCd());
        reqDvo.setItemGd(vo.getItmGdCd());
        reqDvo.setQty(String.valueOf(vo.getOstrQty()));
        return reqDvo;
    }

    protected WsnaItemStockItemizationReqDvo setReturningDisuseWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getOstrDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getOstrDt());
        reqDvo.setWareDv(vo.getWareDvCd());
        reqDvo.setWareNo(vo.getOstrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        reqDvo.setIostTp("212");
        reqDvo.setWorkDiv("A");
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMngtUnitCd());
        reqDvo.setItemGd(vo.getItmGdCd());
        reqDvo.setQty(String.valueOf(vo.getOstrQty()));
        return reqDvo;
    }

    protected WsnaItemStockItemizationReqDvo setReturningRemoveWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsDvo vo
    ) {
        WsnaItemStockItemizationReqDvo removeDvo = new WsnaItemStockItemizationReqDvo();
        removeDvo.setProcsYm(vo.getOstrDt().substring(0, 6));
        removeDvo.setProcsDt(vo.getOstrDt());
        removeDvo.setWareDv(vo.getStrWareDvCd());
        removeDvo.setWareNo(vo.getStrWareNo());
        removeDvo.setWareMngtPrtnrNo(vo.getStrWareMngtPrtnrNo());
        removeDvo.setIostTp("161");
        removeDvo.setWorkDiv("D");
        removeDvo.setItmPdCd(vo.getItmPdCd());
        removeDvo.setMngtUnit(vo.getMngtUnitCd());
        removeDvo.setItemGd(vo.getItmGdCd());
        removeDvo.setQty(String.valueOf(vo.getOstrQty()));
        return removeDvo;
    }

    protected WsnaItemStockItemizationReqDvo setReturningRemoveMoveWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsDvo vo
    ) {
        WsnaItemStockItemizationReqDvo removeDvo = new WsnaItemStockItemizationReqDvo();
        removeDvo.setProcsYm(vo.getOstrDt().substring(0, 6));
        removeDvo.setProcsDt(vo.getOstrDt());
        removeDvo.setWareDv(vo.getStrWareDvCd());
        removeDvo.setWareNo(vo.getStrWareNo());
        removeDvo.setWareMngtPrtnrNo(vo.getStrWareMngtPrtnrNo());
        removeDvo.setIostTp("991");
        removeDvo.setWorkDiv("D");
        removeDvo.setItmPdCd(vo.getItmPdCd());
        removeDvo.setMngtUnit(vo.getMngtUnitCd());
        removeDvo.setItemGd(vo.getItmGdCd());
        removeDvo.setQty(String.valueOf(vo.getOstrQty()));
        return removeDvo;
    }

    protected WsnaItemStockItemizationReqDvo setReturningOstrRemoveWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsDvo vo
    ) {
        WsnaItemStockItemizationReqDvo removeDvo = new WsnaItemStockItemizationReqDvo();
        removeDvo.setProcsYm(vo.getOstrDt().substring(0, 6));
        removeDvo.setProcsDt(vo.getOstrDt());
        removeDvo.setWareDv(vo.getWareDvCd());
        removeDvo.setWareNo(vo.getOstrWareNo());
        removeDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        removeDvo.setIostTp(vo.getOstrTpCd());
        removeDvo.setWorkDiv("D");
        removeDvo.setItmPdCd(vo.getItmPdCd());
        removeDvo.setMngtUnit(vo.getMngtUnitCd());
        removeDvo.setItemGd(vo.getItmGdCd());
        removeDvo.setQty(String.valueOf(vo.getOstrQty()));
        return removeDvo;
    }

}
