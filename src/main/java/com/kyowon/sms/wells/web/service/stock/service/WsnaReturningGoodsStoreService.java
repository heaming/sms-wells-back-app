package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto.*;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaReturningGoodsStoreConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsStoreDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaReturningGoodsStoreMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0108M01 반품입고 관리 Controller
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.05.04
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaReturningGoodsStoreService {

    private static final String RETURN_INSIDE = "261"; // 반품출고(내부)
    private static final String RETURN_RETURN = "218"; // 리퍼완료출고

    private static final String RETURN_DISUSE = "212"; //폐기출고

    private static final String RETURN_QUANTITY = "223"; //물량이동

    private static final String RETURN_OSTR = "320"; //물량이동

    private static final String RETURN_STR_QUANTITY = "123"; // 물량이동
    private final WsnaReturningGoodsStoreMapper mapper;
    private final WsnaReturningGoodsStoreConverter converter;

    private final WsnaItemStockItemizationService itemStockservice;

    private final WsnaLogisticsInStorageAskService logisticsService;

    /**
     * 반품입고 관리 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getReturningGoodsStores(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectReturningGoodsStores(dto, pageInfo);
    }

    /**
     * 반품입고 저장
     * @param dtos
     * @return
     * @throws ParseException
     */
    @Transactional
    public int saveReturningGoodsStores(List<SaveReq> dtos) throws ParseException {
        int processCount = 0;
        int serialNumber = 0;
        String ostrDt = DateUtil.getNowDayString();
        String cfrmDt = DateUtil.getNowDayString();
        String itmOstrNo = null;
        String itmStrNo = null;
        String ostrAkNo = null;
        String disuseItmOstrNo = null; //----> 212
        String strQuantityItmStrNo = null; //----> 123
        String quantityItmOstrNo = null;

        SaveReq saveReq = dtos.get(0);

        String ostrTpCd = isOstrTpCd(saveReq.rtngdProcsTpCd());
        String ostrAkTpCd = RETURN_OSTR;
        String disuseOstrTpCd = RETURN_DISUSE; //----> 212

        itmOstrNo = this.mapper.selectNextItmOstrNo(new FindItmOstrNoReq(ostrTpCd, ostrDt));
        itmStrNo = this.mapper.selectNextItmStrNo(new FindItmStrNoReq(ostrTpCd, ostrDt));
        ostrAkNo = this.mapper.selectNextOstrAkNo(new FindOstrAkNoReq(ostrAkTpCd, ostrDt));
        disuseItmOstrNo = this.mapper.selectNextItmOstrNo(new FindItmOstrNoReq(disuseOstrTpCd, ostrDt));
        strQuantityItmStrNo = this.mapper.selectNextItmStrNo(new FindItmStrNoReq(ostrTpCd, ostrDt));
        quantityItmOstrNo = this.mapper.selectNextItmOstrNo(new FindItmOstrNoReq(ostrTpCd, ostrDt));
        for (SaveReq dto : dtos) {
            serialNumber += 1;
            int result = 0;

            WsnaReturningGoodsStoreDvo dvo = this.converter.mapSaveReqToReturningGoodsDvo(dto);

            dvo.setItmOstrNo(itmOstrNo);
            dvo.setItmStrNo(itmStrNo);
            dvo.setDisuseItmOstrNo(disuseItmOstrNo);
            dvo.setStrQuantityItmStrNo(strQuantityItmStrNo);
            dvo.setQuantityItmOstrNo(quantityItmOstrNo);
            dvo.setOstrSn(String.valueOf(serialNumber));
            dvo.setStrSn(String.valueOf(serialNumber));
            dvo.setOstrAkNo(ostrAkNo);
            dvo.setOstrAkSn(String.valueOf(serialNumber));
            dvo.setDisuseOstrTpCd(disuseOstrTpCd);
            dvo.setOstrTpCd(ostrTpCd);
            dvo.setOstrAkTpCd(ostrAkTpCd);
            dvo.setCfrmDt(cfrmDt);

            String strHgrWareNo = this.mapper.selectHgrWareNo(dvo);
            dvo.setHgrWareNo(strHgrWareNo);
            String strUpHgrWareNo = this.mapper.selectUpHgrWareNo(dvo);
            dvo.setUpHgrWareNo(strUpHgrWareNo);

            String strHgrWareMngtPrtnrNo = this.mapper.selectHgrWarePrtnrNo(dvo);
            String strUpHgrWareMngtPrtnrNo = this.mapper.selectUpHgrWarePrtnrNo(dvo);
            dvo.setHgrWarePrtnrNo(strHgrWareMngtPrtnrNo);
            dvo.setUpHgrWarePrtnrNo(strUpHgrWareMngtPrtnrNo);

            if (StringUtils.isNotEmpty(
                dvo.getOstrConfDt()
            ) && ("10".equals(dvo.getRtngdProcsTpCd()) || "11".equals(dvo.getRtngdProcsTpCd())
                || "12".equals(dvo.getRtngdProcsTpCd()) || "20".equals(dvo.getRtngdProcsTpCd())
                || "21".equals(dvo.getRtngdProcsTpCd()) || "22".equals(dvo.getRtngdProcsTpCd()))
                && (StringUtils.isEmpty(dvo.getRtngdRvpyProcsYn()) || !"Y".equals(dvo.getRtngdRvpyProcsYn()))) {

                if (!"X".equals(dvo.getFnlItmGdCd())) {

                    String rmkCn = this.mapper.selectRmkCn(dvo);
                    dvo.setRmkCn(rmkCn);

                    //반품처리유형이 리퍼용(20), 품질팀(21)인 경우
                    if ("20".equals(dvo.getRtngdProcsTpCd()) || "21".equals(dvo.getRtngdProcsTpCd())) {
                        result += this.mapper.insertQuantityItmOstrIz(dvo);
                        //                                result += this.mapper.insertItmOstrAkIz(dvo);
                        result += this.mapper.insertQuantityItmStrIz(dvo);

                        /*출고창고의 재고모듈을 실행한다.*/
                        WsnaItemStockItemizationReqDvo quantityOstrDto = setQuantityOstrWsnaItemStockItemizationDtoSaveReq(
                            dvo
                        );
                        result += itemStockservice.createStock(quantityOstrDto);

                        /*입고창고 이동재고를 생성한다.*/
                        WsnaItemStockItemizationReqDvo quantityStrDto = setQuantityStrWsnaItemStockItemizationDtoSaveReq(
                            dvo
                        );
                        result += itemStockservice.saveStockMovement(quantityStrDto);

                        /*입고창고 재고모듈을 실행한다.*/
                        WsnaItemStockItemizationReqDvo quantityStrReqDvo = setQuantityStrReqDvoWsnaItemStockItemizationDtoSaveReq(
                            dvo
                        );

                        result += itemStockservice.createStock(quantityStrReqDvo);

                        /*입고창고의 입고확정을 처리한다.*/

                        this.mapper.updateReturningGoodsStrConfirm(dvo);

                    }

                    /*반품처리유형이  10 물류폐기, 11 리퍼-E급 tt물류폐기, 12 리퍼-물류반품, 22 리퍼-tt특별자재인 경우*/
                    if (List.of("10", "11", "12", "22").contains(dvo.getRtngdProcsTpCd())) {

                        result += this.mapper.insertDiDisuseOstrIz(dvo);

                        WsnaItemStockItemizationReqDvo didisuseOstrDto = setDiDiSuseOstrWsnaItemStockItemizationDtoSaveReq(
                            dvo
                        );
                        result += itemStockservice.createStock(didisuseOstrDto);

                    }

                    /*물류페기, 리퍼-E급 tt물류폐기일 경우 disuseItmOstrNo 반품요청송신전문*/
                    //TODO: 추후 삭제 예정(혹시몰라 삭제안함)
                    //                    if ("10".equals(dvo.getRtngdProcsTpCd()) || "11".equals(dvo.getRtngdProcsTpCd())) {
                    //
                    //                        result += this.mapper.insertDiDisuseOstrIz(dvo);
                    //
                    //                        String logisticsItmOstrNo = dvo.getDisuseItmOstrNo();
                    //                        log.debug(logisticsItmOstrNo);
                    //                        String ostrSn = dvo.getOstrSn();
                    //
                    //                        List<WsnaReturningGoodsStoreDvo> logisticsDvo = this.mapper
                    //                            .selectLogisticsReturningGoodsAskInfo(logisticsItmOstrNo, ostrSn);
                    //
                    //                        List<WsnaLogisticsInStorageAskReqDvo> returnDvo = this.converter
                    //                            .mapAllReturningGoodsStoreDvoToLogisticsInStorageAskReqDvo(logisticsDvo);
                    //                        logisticsService.createInStorageAsks(returnDvo);
                    //                    }
                    //
                    //                    /*공장 발송일(품질팀+tt특별자재) 경우 quantityItmOstrNo*/
                    //                    if (List.of("12", "20", "21", "22").contains(dvo.getRtngdProcsTpCd())) {
                    //
                    //                        result += this.mapper.insertQuantityItmOstrIz(dvo);
                    //                        //                                result += this.mapper.insertItmOstrAkIz(dvo);
                    //                        result += this.mapper.insertQuantityItmStrIz(dvo);
                    //
                    //                        /*출고창고의 재고모듈을 실행한다.*/
                    //                        WsnaItemStockItemizationReqDvo quantityOstrDto = setQuantityOstrWsnaItemStockItemizationDtoSaveReq(
                    //                            dvo
                    //                        );
                    //                        result += itemStockservice.createStock(quantityOstrDto);
                    //                        /*입고창고 이동재고를 생성*/
                    //                        WsnaItemStockItemizationReqDvo quantityStrDto = setQuantityStrWsnaItemStockItemizationDtoSaveReq(
                    //                            dvo
                    //                        );
                    //                        result += itemStockservice.saveStockMovement(quantityStrDto);
                    //                    }
                }
                /*출고일자, 스티커 출력 유무, 비고 사항 저장*/
                result += this.mapper.updateWkOstrIz(dvo);

            } else if (StringUtils.isNotEmpty(dvo.getOstrConfDt())
                && (List.of("80", "81", "82").contains(dvo.getRtngdProcsTpCd()))
                && (StringUtils.isEmpty(dvo.getRtngdRvpyProcsYn())
                    || !"Y".equals(dvo.getRtngdRvpyProcsYn()))) {

                String refrRmkCn = this.mapper.selectRefrRmkCn(dvo);
                dvo.setRmkCn(refrRmkCn);

                result += this.mapper.insertRefrOstrFinish(dvo);

                /*TODO : 출고창고 재고모듈을 실행한다. (품목출고내역) 등록*/
                WsnaItemStockItemizationReqDvo etcOstrDvo = setEtcOstrWsnaItemStockItemizationDtoSaveReq(dvo);

                result += itemStockservice.createStock(etcOstrDvo);

                result += this.mapper.updateRefrOstrFinish(dvo);
                /*출고일자가 없는경우*/
            } else {
                result += this.mapper.updateNotOstrConfDt(dvo);
            }
            processCount += result;
        }
        return processCount;

    }

    /**
     * 출고창고 재고모듈 파라미터 변환
     * @param vo
     * @return
     */
    protected WsnaItemStockItemizationReqDvo setQuantityOstrWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsStoreDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getCfrmDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getCfrmDt());
        reqDvo.setWareDv(vo.getHgrWareNo().substring(0, 1)); /*창고구분*/
        reqDvo.setWareNo(vo.getHgrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getHgrWarePrtnrNo());
        reqDvo.setIostTp("261");
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMgtUnt());
        reqDvo.setItemGd(vo.getFnlItmGdCd());
        reqDvo.setQty(vo.getUseQty());
        return reqDvo;
    }

    /**
     * 반품유형처리타입이 20, 21 일때 사용하는 파라미터 변환
     * @param vo
     * @return
     */
    protected WsnaItemStockItemizationReqDvo setQuantityStrWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsStoreDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getCfrmDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getCfrmDt());
        reqDvo.setWareDv("1"); /*창고구분*/
        if ("20".equals(vo.getRtngdProcsTpCd())) {
            reqDvo.setWareNo("100010");
        } else {
            reqDvo.setWareNo("100004");
        }
        reqDvo.setWareMngtPrtnrNo(vo.getUpHgrWarePrtnrNo());
        reqDvo.setIostTp("991");
        reqDvo.setWorkDiv("A");/*작업구분 workDiv*/
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMgtUnt());
        reqDvo.setItemGd(vo.getFnlItmGdCd());
        reqDvo.setQty(vo.getUseQty());
        return reqDvo;
    }

    /**
     * 입고창고 재고모듈 호출을 위한 파라미터 변환
     * (입고창고 = 반품처리유형에 따른 입고창고)
     * @param vo
     * @return
     */
    protected WsnaItemStockItemizationReqDvo setQuantityStrReqDvoWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsStoreDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getCfrmDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getCfrmDt());
        reqDvo.setWareDv("1"); /*창고구분*/
        if ("20".equals(vo.getRtngdProcsTpCd())) {
            reqDvo.setWareNo("100010");
        } else {
            reqDvo.setWareNo("100004");
        }
        reqDvo.setWareMngtPrtnrNo(vo.getUpHgrWarePrtnrNo());
        reqDvo.setIostTp("161");
        reqDvo.setWorkDiv("A");/*작업구분 workDiv*/
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMgtUnt());
        reqDvo.setItemGd(vo.getFnlItmGdCd());
        reqDvo.setQty(vo.getUseQty());

        return reqDvo;
    }

    /**
     * 반품처리유형이  10 물류폐기, 11 리퍼-E급 tt물류폐기, 12 리퍼-물류반품, 22 리퍼-tt특별자재인 경우 파라미터변환
     * @param vo
     * @return
     */
    protected WsnaItemStockItemizationReqDvo setDiDiSuseOstrWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsStoreDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getCfrmDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getCfrmDt());
        reqDvo.setWareDv(vo.getHgrWareNo().substring(0, 1)); /*창고구분*/
        reqDvo.setWareNo(vo.getHgrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getHgrWarePrtnrNo());
        reqDvo.setIostTp("212");
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMgtUnt());
        reqDvo.setItemGd(vo.getFnlItmGdCd());
        reqDvo.setQty(vo.getUseQty());
        return reqDvo;

    }

    /**
     * 기타출고유형일 경우 파라미터 변환
     * @param vo
     * @return
     */
    protected WsnaItemStockItemizationReqDvo setEtcOstrWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsStoreDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getCfrmDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getCfrmDt());
        reqDvo.setWareDv(vo.getHgrWareNo().substring(0, 1)); /*창고구분*/
        reqDvo.setWareNo(vo.getHgrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getHgrWarePrtnrNo());
        reqDvo.setIostTp("218");
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMgtUnt());
        reqDvo.setItemGd(vo.getFnlItmGdCd());
        reqDvo.setQty(vo.getUseQty());
        return reqDvo;
    }

    /**
     * 넘어온 반품유형코드 비교
     * @param rtngdProcsTpCd
     * @return
     */
    public String isOstrTpCd(String rtngdProcsTpCd) {
        if (List.of("80", "81", "82").contains(rtngdProcsTpCd)) {
            //218
            return RETURN_RETURN;
        } else {
            return RETURN_INSIDE;
        }

    }

    /**
     * 반품입고 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getReturningGoodsStoresExcelDownload(SearchReq dto) {
        return mapper.selectReturningGoodsStores(dto);
    }

    /**
     * 반품처리유형코드 저장
     * @param dtos
     * @return
     */
    public int saveReturningGoodsStoreConfirmations(List<SaveConfirmationReq> dtos) {
        int processCount = 0;

        for (SaveConfirmationReq dto : dtos) {
            WsnaReturningGoodsStoreDvo dvo = this.converter.mapSaveConfirmationReqToReturningGoodsDvo(dto);
            dvo.setRtngdConfYn("Y");

            processCount += this.mapper.updateReturningGoodsStoreConfirmations(dvo);

        }

        return processCount;
    }
}
