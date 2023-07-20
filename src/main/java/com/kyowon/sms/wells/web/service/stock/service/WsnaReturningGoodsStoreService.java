package com.kyowon.sms.wells.web.service.stock.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemStockItemizationDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsInStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaReturningGoodsStoreDvo;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaReturningGoodsStoreConverter;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaReturningGoodsStoreMapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto.*;

import lombok.extern.slf4j.Slf4j;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaReturningGoodsStoreService {

    final String RETURN_INSIDE = "261"; // 반품출고(내부)
    final String RETURN_RETURN = "218"; // 리퍼완료출고

    final String RETURN_DISUSE = "212"; //폐기출고

    final String RETURN_QUANTITY = "223"; //물량이동

    final String RETURN_OSTR = "320"; //물량이동

    final String RETURN_STR_QUANTITY = "123"; // 물량이동
    private final WsnaReturningGoodsStoreMapper mapper;
    private final WsnaReturningGoodsStoreConverter converter;

    private final WsnaItemStockItemizationService itemStockservice;

    private final WsnaLogisticsInStorageAskService logisticsService;

    public PagingResult<SearchRes> getReturningGoodsStores(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectReturningGoodsStores(dto, pageInfo);
    }

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
        //        String strQuantityStrTpCd = RETURN_STR_QUANTITY; //----> 123
        //        String quantityOstrTpCd = RETURN_QUANTITY;

        log.info("ostrTpCd ---> " + ostrTpCd);
        log.info("ostrDt ---> " + ostrDt);

        itmOstrNo = this.mapper.selectNextItmOstrNo(new FindItmOstrNoReq(ostrTpCd, ostrDt));
        log.info("itmOstrNo ------------------>" + itmOstrNo);
        itmStrNo = this.mapper.selectNextItmStrNo(new FindItmStrNoReq(ostrTpCd, ostrDt));
        log.info("itmStrNo ------------------>" + itmStrNo);
        ostrAkNo = this.mapper.selectNextOstrAkNo(new FindOstrAkNoReq(ostrAkTpCd, ostrDt));
        log.info("ostrAkNo ------------------>" + ostrAkNo);
        disuseItmOstrNo = this.mapper.selectNextItmOstrNo(new FindItmOstrNoReq(disuseOstrTpCd, ostrDt));
        log.info("disuseItmOstrNo ------------------>" + disuseItmOstrNo);
        strQuantityItmStrNo = this.mapper.selectNextItmStrNo(new FindItmStrNoReq(ostrTpCd, ostrDt));
        log.info("strQuantityItmStrNo ------------------>" + strQuantityItmStrNo);
        quantityItmOstrNo = this.mapper.selectNextItmOstrNo(new FindItmOstrNoReq(ostrTpCd, ostrDt));
        log.info("quantityItmOstrNo ------------------>" + quantityItmOstrNo);
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
            //            dvo.setQuantityOstrTpCd(quantityOstrTpCd);
            dvo.setOstrAkTpCd(ostrAkTpCd);
            //            dvo.setStrQuantityStrTpCd(quantityOstrTpCd);
            dvo.setCfrmDt(cfrmDt);

            log.info("ostrTpCd ------>", dvo.getOstrTpCd());

            String strHgrWareNo = this.mapper.selectHgrWareNo(dvo);
            dvo.setHgrWareNo(strHgrWareNo);
            log.info("strHgrWareNo -------> ", strHgrWareNo);
            String strUpHgrWareNo = this.mapper.selectUpHgrWareNo(dvo);
            dvo.setUpHgrWareNo(strUpHgrWareNo);
            log.info("strUpHgrWareNo -------> ", strUpHgrWareNo);

            String strHgrWareMngtPrtnrNo = this.mapper.selectHgrWarePrtnrNo(dvo);
            String strUpHgrWareMngtPrtnrNo = this.mapper.selectUpHgrWarePrtnrNo(dvo);
            dvo.setHgrWarePrtnrNo(strHgrWareMngtPrtnrNo);
            log.info("strHgrWareMngtPrtnrNo -------> ", strHgrWareMngtPrtnrNo);
            dvo.setUpHgrWarePrtnrNo(strUpHgrWareMngtPrtnrNo);
            log.info("strUpHgrWareMngtPrtnrNo -------> ", strUpHgrWareMngtPrtnrNo);

            if (StringUtils.isNotEmpty(
                dvo.getOstrConfDt()
            ) && ("10".equals(dvo.getRtngdProcsTpCd()) || "11".equals(dvo.getRtngdProcsTpCd())
                || "12".equals(dvo.getRtngdProcsTpCd()) || "20".equals(dvo.getRtngdProcsTpCd())
                || "21".equals(dvo.getRtngdProcsTpCd()) || "22".equals(dvo.getRtngdProcsTpCd()))
                && (StringUtils.isEmpty(dvo.getRtngdRvpyProcsYn()) || !"Y".equals(dvo.getRtngdRvpyProcsYn()))) {

                if (!"X".equals(dvo.getFnlItmGdCd())) {

                    String rmkCn = this.mapper.selectRmkCn(dvo);
                    dvo.setRmkCn(rmkCn);

                    /*물류페기, 리퍼-E급 tt물류폐기일 경우 disuseItmOstrNo 반품요청송신전문*/
                    /*TODO : W-SV-S-0089_물류 반품요청 인터페이스 서비스 호출해야됨*/
                    if ("10".equals(dvo.getRtngdProcsTpCd()) || "11".equals(dvo.getRtngdProcsTpCd())) {

                        result += this.mapper.insertDiDisuseOstrIz(dvo);

                        String logisticsItmOstrNo = dvo.getDisuseItmOstrNo();
                        log.debug(logisticsItmOstrNo);
                        String ostrSn = dvo.getOstrSn();

                        List<WsnaReturningGoodsStoreDvo> logisticsDvo = this.mapper
                            .selectLogisticsReturningGoodsAskInfo(logisticsItmOstrNo, ostrSn);

                        List<WsnaLogisticsInStorageAskReqDvo> returnDvo = this.converter
                            .mapAllReturningGoodsStoreDvoToLogisticsInStorageAskReqDvo(logisticsDvo);
                        logisticsService.createInStorageAsks(returnDvo);
                    }

                    /*공장 발송일(품질팀+tt특별자재) 경우 quantityItmOstrNo*/
                    if (List.of("12", "20", "21", "22").contains(dvo.getRtngdProcsTpCd())) {

                        result += this.mapper.insertQuantityItmOstrIz(dvo);
                        //                                result += this.mapper.insertItmOstrAkIz(dvo);
                        result += this.mapper.insertQuantityItmStrIz(dvo);

                        /*출고창고의 재고모듈을 실행한다.*/
                        WsnaItemStockItemizationReqDvo quantityOstrDto = setQuantityOstrWsnaItemStockItemizationDtoSaveReq(
                            dvo
                        );
                        result += itemStockservice.createStock(quantityOstrDto);
                        /*입고창고 이동재고를 생성*/
                        WsnaItemStockItemizationReqDvo quantityStrDto = setQuantityStrWsnaItemStockItemizationDtoSaveReq(
                            dvo
                        );
                        result += itemStockservice.saveStockMovement(quantityStrDto);
                    }

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

    /*반품유형처리타입이 12, 20, 21, 22 일때*/
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

    /*반품유형처리타입이 12, 20, 21, 22 일때*/
    protected WsnaItemStockItemizationReqDvo setQuantityStrWsnaItemStockItemizationDtoSaveReq(
        WsnaReturningGoodsStoreDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getCfrmDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getCfrmDt());
        reqDvo.setWareDv("1"); /*창고구분*/
        reqDvo.setWareNo("100010");
        reqDvo.setWareMngtPrtnrNo(vo.getUpHgrWarePrtnrNo());
        reqDvo.setIostTp("991");
        reqDvo.setWorkDiv("A");/*작업구분 workDiv*/
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMgtUnt());
        reqDvo.setItemGd(vo.getFnlItmGdCd());
        reqDvo.setQty(vo.getUseQty());
        return reqDvo;
    }

    /*기타출고일 경우*/
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

    public String isOstrTpCd(String rtngdProcsTpCd) {
        if (List.of("80", "81", "82").contains(rtngdProcsTpCd)) {
            //218
            return RETURN_RETURN;
        } else {
            return RETURN_INSIDE;
        }

    }

    public List<SearchRes> getReturningGoodsStoresExcelDownload(SearchReq dto) {
        return mapper.selectReturningGoodsStores(dto);
    }

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
