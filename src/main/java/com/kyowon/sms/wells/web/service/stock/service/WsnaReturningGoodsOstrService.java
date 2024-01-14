package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaReturningGoodsOstrConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaReturningGoodsOstrMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0001M01 반품출고 등록
 * </pre>
 *
 * @author hyewon.kim
 * @since 2023.02.14
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaReturningGoodsOstrService {

    private static final String RETURN_INSIDE = "261"; // 반품출고(내부)
    private static final String RETURN_OUTSIDE = "262"; // 반품출고(외부)
    private static final String WARE_DV_CD_LOGISTICS_CENTER = "1"; // 창고구분코드 = 물류센터

    private static final String RETURN_DISUSE = "212"; //폐기출고

    private final WsnaReturningGoodsOstrMapper mapper;

    private final WsnaItemStockItemizationService itemStockservice;

    private final WsnaReturningGoodsOstrConverter converter;

    private final WsnaLogisticsInStorageAskService logisticsService;

    /**
     * 출고요청 창고 조회
     *
     * @param dto
     * @return
     */
    public List<SearchWarehouseRes> getWareHouses(SearchWarehouseReq dto) {
        return this.mapper.selectWareHouses(dto);
    }

    /**
     * 반품출고 조회
     *
     * @param dto
     * @return
     */
    public SearchRes getReturningGoodsOstrs(SearchReq dto) {
        ItemOutOfStorage itemOutOfStorage = this.mapper.selectItemOutOfStorage(dto);
        List<ReturningGoods> returningGoods = this.converter
            .mapAllReturningGoodsDvoToReturningGoods(
                this.mapper.selectReturningGoodsOstrs(itemOutOfStorage.itmOstrNo())
            );

        return new SearchRes(itemOutOfStorage, returningGoods);
    }

    /**
     * 입고마감 체크
     *
     * @param itmOstrNo
     * @return
     */
    public Boolean isClosed(String itmOstrNo) {
        String sellReceiptDate = this.mapper.selectIsClosedByPk(itmOstrNo);
        return StringUtil.isNotEmpty(sellReceiptDate);
    }

    /**
     * 반품출고 등록
     *
     * @param dtos
     * @return
     */
    @Transactional
    public int saveReturningGoodsOstrs(List<SaveReq> dtos) {
        int processCount = 0;
        int serialNumber = 0;

        SaveReq saveReq = dtos.get(0);
        List<String> ostrSns = new ArrayList<>();
        List<WsnaReturningGoodsDvo> dvos = new ArrayList<>();

        // 사용자 세션
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();

        String itmOstrNo = this.mapper.selectNextItmOstrNo(new FindItmOstrNoReq(saveReq.ostrTpCd(), saveReq.ostrDt()));
        String itmStrNo = null;
        //물류에 전송할때 물류배송방식코드분기 처리를 위한 운송코드
        String trnspnCd = saveReq.trnspnCd();

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

            dvo.setWareMngtPrtnrNo(session.getEmployeeIDNumber());
            // 품목출고내역 insert
            int result = this.mapper.insertItemForwardingHistory(dvo);
            dvos.add(dvo);

            result += this.mapper.insertItemReceivingHistory(dvo);

            if (isReturnToLogistics(dvo.getOstrTpCd(), dvo.getStrWareDvCd())) {
                // 품목재고내역관리 서비스(W-SV-S-0087)의 품목재고내역 등록 메소드를 호출 - 반품출고
                WsnaItemStockItemizationReqDvo returnOstrDvo = setReturningOstrWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );
                result += itemStockservice.createStock(returnOstrDvo);
                // 품목재고내역 이동
                WsnaItemStockItemizationReqDvo returnMoveDvo = setReturningMoveWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );
                result += itemStockservice.saveStockMovement(returnMoveDvo);
                // 품목 재고내역 등록
                WsnaItemStockItemizationReqDvo returnStrDvo = setReturningStrWsnaItemStockItemizationDtoSaveReq(
                    dvo
                );

                result += itemStockservice.createStock(returnStrDvo);
            } else if (isReturning(dvo.getOstrTpCd())) {
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

            processCount += result;
        }

        //09.06 물류요청일 경우에는 Transaction 이슈로 저장한 데이터 한번에 넘겨줘야 함
        if (CollectionUtils.isNotEmpty(dvos)) {
            if (isReturnToLogistics(dvos.get(0).getOstrTpCd(), dvos.get(0).getStrWareDvCd())) {
                List<String> sortOstrSns = dvos.stream().map(WsnaReturningGoodsDvo::getOstrSn).distinct()
                    .toList();

                for (String ostrSn : sortOstrSns) {
                    ostrSns.add(ostrSn);
                }
                // 반품(내부)이고 입고 창고가 물류센터인 경우 - 반품요청 중계 테이블 Insert
                List<WsnaReturningGoodsDvo> logisticsDvo = this.mapper
                    .selectLogisticsReturningGoodsAskInfo(itmOstrNo, ostrSns, trnspnCd);

                List<WsnaLogisticsInStorageAskReqDvo> returnDvo = this.converter
                    .mapAllReturningGoodsDvoToLogisticsInStorageAskReqDvo(logisticsDvo);

                logisticsService.createInStorageAsks(returnDvo);

            }
        }

        return processCount;
    }

    /**
     * 반품출고 삭제
     *
     * @param dtos
     * @return
     */
    @Transactional
    public int removeReturningGoodsOstrs(List<RemoveReq> dtos) {
        int processCount = 0;

        RemoveReq removeReq = dtos.get(0);

        List<WsnaReturningGoodsDvo> dvos = this.converter.mapRemoveAllReturningGoodsDvoToReturningGoods(dtos);

        //삭제건들을 담기위한 listDvos
        List<WsnaReturningGoodsDvo> deleteListDvos = new ArrayList<>();

        List<String> deleteOstrSns = new ArrayList<>();

        String ostrPrtnrNo = this.mapper
            .selectOstrPrtnrNo(new FindOstrPrtnrNoReq(removeReq.ostrWareNo(), removeReq.ostrDt()));

        String strPrtnrNo = this.mapper
            .selectStrPrtnrNo(new FindStrPrtnrNoReq(removeReq.strWareNo(), removeReq.ostrDt()));

        for (RemoveReq dto : dtos) {
            WsnaReturningGoodsDvo dvo = this.converter.mapRemoveReqToReturningGoodsDvo(dto);

            dvo.setWareMngtPrtnrNo(ostrPrtnrNo);
            dvo.setStrWareMngtPrtnrNo(strPrtnrNo);

            // 반품출고(내부/외부)인 경우
            if (isReturning(dvo.getOstrTpCd())) {
                // 입고창고가 물류센터인 경우
                if (WARE_DV_CD_LOGISTICS_CENTER.equals(dvo.getStrWareDvCd())) {

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

                    this.mapper.deleteItemReceivingHistory(dvo); // 품목입고내역삭제
                    deleteListDvos.add(dvo);

                } else {
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

                    this.mapper.deleteItemReceivingHistory(dvo); // 품목입고내역삭제
                }
            }

            // TODO: 품목재고내역 삭제 메소드(saveItemStockIzDls)를 호출 - 출고창고의 출고재고수량 복원
            WsnaItemStockItemizationReqDvo ostrRemoveDvo = setReturningOstrRemoveWsnaItemStockItemizationDtoSaveReq(
                dvo
            );

            itemStockservice.removeStock(ostrRemoveDvo);

            int result = this.mapper.deleteItemForwardingHistory(dvo); // 품목출고내역삭제
            // 삭제에 실패 하였습니다.
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }

        //09.07 물류요청건 삭제일 경우에는 Transaction 이슈로 저장한 데이터 한번에 넘겨줘야 함
        if (deleteListDvos.size() > 0) {
            if (isReturning(deleteListDvos.get(0).getOstrTpCd())
                && WARE_DV_CD_LOGISTICS_CENTER.equals(deleteListDvos.get(0).getStrWareDvCd())) {

                List<String> sortOstrSns = dvos.stream().map(WsnaReturningGoodsDvo::getOstrSn).distinct()
                    .toList();

                for (String ostrSn : sortOstrSns) {
                    deleteOstrSns.add(ostrSn);
                }

                //물류에 전송하기위한 데이터 조회
                List<WsnaReturningGoodsDvo> returnListDvo = this.mapper
                    .selectLogisticsRemoveReturn(deleteListDvos.get(0).getItmOstrNo(), deleteOstrSns);
                //물류센터에 출고 요청 취소(삭제) Interface 를 위한 중계테이블에 Insert
                List<WsnaLogisticsInStorageAskReqDvo> returnDvo = this.converter
                    .mapAllRemoveReturningGoodsDvoToLogisticsInStorageAskReqDvo(returnListDvo);

                logisticsService.removeInStorageAsks(returnDvo);
            }
        }

        return processCount;
    }

    /**
     * 출고유형코드 값 체크
     *
     * @param ostrTpCd
     * @return
     */
    public Boolean isReturning(String ostrTpCd) {
        return RETURN_INSIDE.equals(ostrTpCd) || RETURN_OUTSIDE.equals(ostrTpCd);
    }

    /**
     * 반품출고내부 및 물류구분인지 체크
     *
     * @param ostrTpCd
     * @param strWareDvCd
     * @return
     */
    public Boolean isReturnToLogistics(String ostrTpCd, String strWareDvCd) {
        return RETURN_INSIDE.equals(ostrTpCd) && WARE_DV_CD_LOGISTICS_CENTER.equals(strWareDvCd);
    }

    /**
     * 품목재고내역의 등록 호출파라미터 변환
     *
     * @param vo
     * @return
     */
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

    /**
     * 품목재고내역 이동재고를 위한 파라미터 변환
     *
     * @param vo
     * @return
     */
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

    /**
     * 품목재고내역 입고 파라미터 변환
     *
     * @param vo
     * @return
     */
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

    /**
     * 폐기출고 처리를 위한 파라미터 변환환
     *
     * @param vo
     * @return
     */
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

    /**
     * 품목재고내역 입고창고의 재고내역을 삭제하기 위한 파라미터 변환
     *
     * @param vo
     * @return
     */
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

    /**
     * 품목재고내역 입고창고의 이동재고삭제를 위한 파라미터 변환
     *
     * @param vo
     * @return
     */
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

    /**
     * 품목재고내역 삭제를 위한 파라미터 변환
     *
     * @param vo
     * @return
     */
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

    /**
     * 출고창고에 있는 상품의 시점재고 조회
     * @param wareNo 창고번호
     * @param dto 조회조건
     * @return
     */
    public List<SearchPitmStockRes> getPitmStocks(String wareNo, SearchPitmStockReq dto) {
        return mapper.selectPitmStocks(wareNo, dto.itmPdCds(), dto.itmGdCd());
    }
}
