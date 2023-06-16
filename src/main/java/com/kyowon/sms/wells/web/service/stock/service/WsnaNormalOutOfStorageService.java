package com.kyowon.sms.wells.web.service.stock.service;

import java.text.ParseException;
import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMonthlyItemStocksReqDvo;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaNormalOutOfStorageConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaItemStockItemizationDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageStdgbDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaNormalOutOfStorageMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0142M01 정상출고 관리
 * </pre>
 *
 * @author inho.choi
 * @since 2023-03-14
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class WsnaNormalOutOfStorageService {
    private final WsnaNormalOutOfStorageMapper mapper;

    private final WsnaNormalOutOfStorageConverter converter;

    private final WsnaItemStockItemizationService itemStockservice;

    private final WsnaMonthlyItemStocksService monthlyService;

    public PagingResult<SearchRes> getNormalOutOfStorage(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectNormalOutOfStorage(dto, pageInfo);
    }

    public List<SearchRes> getNormalOutOfStorage(SearchReq dto) {
        return mapper.selectNormalOutOfStorage(dto);
    }

    public List<SearchWarehouse> getWarehouses(SearchReq dto) {
        return mapper.selectWarehouses(dto);
    }

    public PagingResult<AskRes> getAskMaterialsHavePss(
        AskReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAskMaterialsHavePss(dto, pageInfo);
    }

    public PagingResult<CenterRes> getAskMaterialsCenterPresentState(
        AskReq dto, PageInfo pageInfo
    ) {
        return mapper.selectAskMaterialsCenterPresentState(dto, pageInfo);
    }

    public PagingResult<DetailRes> getNormalOutOfStoragesDetails(DetailReq dto, PageInfo pageInfo) {
        return mapper.selectNormalOutOfStoragesDetails(dto, pageInfo);
    }

    @Transactional
    public int saveNormalOstrRgsts(List<CreateReq> list) throws ParseException {
        int cnt = 0;
        int itmSeq = 1;
        List<WsnaNormalOutOfStorageDvo> voList = converter.mapAllWsnaNormalOutOfStorageDvos(list);

        if(voList.size() > 0) {
            WsnaNormalOutOfStorageDvo voTemp = voList.get(0);
            StrNoAndOstrNoRes res = mapper.selectStrNoAndOstrNo(voTemp);

            for (WsnaNormalOutOfStorageDvo vo : voList) {
//            StrNoAndOstrNoRes res = mapper.selectStrNoAndOstrNo(vo);
                vo.setItmOstrNo(res.itmOstrNo());
                vo.setItmStrNo(res.itmStrNo());
                vo.setStrTpCd(res.strTpCd());
                vo.setOstrTpCd(res.ostrTpCd());
                vo.setTodayStr(res.todayStr());
                vo.setOstrSn(itmSeq);
                vo.setStrSn(itmSeq);
                vo.setQty(vo.getOutQty());
                cnt += mapper.insertNormalOstrRgst(vo);
                cnt += mapper.insertNormalStrRgst(vo);
                cnt += mapper.updateOstrAkIz(vo);

                WsnaItemStockItemizationReqDvo ostrDvo = setOstrWsnaItemStockItemizationDtoSaveReq(vo);//출고
                WsnaItemStockItemizationReqDvo strDvo = setStrWsnaItemStockItemizationDtoSaveReq(vo);//입고
                WsnaItemStockItemizationReqDvo movementDvo = setMovementWsnaItemStockItemizationDtoSaveReq(vo);//이동입고
                WsnaMonthlyItemStocksReqDvo monthlyItemStocksDvo = setMonthlyItemStocks(vo);//이동입고

                cnt += itemStockservice.createStock(ostrDvo);
                monthlyItemStocksDvo.setIostTp("221");//정상출고
                monthlyItemStocksDvo.setWareNo(ostrDvo.getWareNo());
                monthlyItemStocksDvo.setWareDv(ostrDvo.getWareDv());
                cnt += monthlyService.saveMonthlyStock(monthlyItemStocksDvo);

                cnt += itemStockservice.createStock(strDvo);
                monthlyItemStocksDvo.setIostTp("121");//정상입고
                monthlyItemStocksDvo.setWareNo(strDvo.getWareNo());
                monthlyItemStocksDvo.setWareDv(strDvo.getWareDv());
                cnt += monthlyService.saveMonthlyStock(monthlyItemStocksDvo);

                //이동입고 991
                cnt += itemStockservice.saveStockMovement(movementDvo);

                monthlyItemStocksDvo.setIostTp("991");
                cnt += monthlyService.saveMonthlyStock(monthlyItemStocksDvo);//이동입고 월별품목재고

                cnt += mapper.updateOstrAkIzAfter(vo);
                itmSeq++;
            }
        }
        return cnt;
    }

    public int getNormalOstrRgstChecked(CheckedReq dto) {
        return mapper.selectNormalOstrRgstChecked(dto);
    }

    @Transactional
    public int saveStandardWareHouse(StandardWareReq dto) {
        WsnaNormalOutOfStorageStdgbDvo dvo = converter.mapToWsnaNormalOutOfStorageStdgbDvo(dto);
        return mapper.updateStandardWareHouse(dvo);
    }

    public StandardWareRes getStandardWareHouse(StandardWareReq dto) {
        return mapper.selectStandardWareHouse(dto);
    }

    public SearchItmOstrAkRes getItmOstrAk(SearchItmOstrAkReq dto){
        return mapper.selectItmOstrAk(dto);
    }

    protected WsnaItemStockItemizationReqDvo setOstrWsnaItemStockItemizationDtoSaveReq(
        WsnaNormalOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationReqDvo resDvo = new WsnaItemStockItemizationReqDvo();
        resDvo.setProcsYm(vo.getTodayStr().substring(0, 6));
        resDvo.setProcsDt(vo.getTodayStr());
        resDvo.setWareDv(vo.getOstrWareDvCd()); /*창고구분*/
        resDvo.setWareNo(vo.getOstrWareNo());
        resDvo.setWareMngtPrtnrNo(vo.getOstrWareMngtPrtnrNo());
        resDvo.setIostTp(vo.getOstrTpCd());
        resDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        resDvo.setItmPdCd(vo.getItmPdCd());
        resDvo.setMngtUnit(vo.getMngtUnitCd());
        resDvo.setItemGd(vo.getItmGdCd());
        resDvo.setQty(vo.getOutQty());

        return resDvo;
    }

    protected WsnaItemStockItemizationReqDvo setStrWsnaItemStockItemizationDtoSaveReq(
        WsnaNormalOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationReqDvo resDvo = new WsnaItemStockItemizationReqDvo();
        resDvo.setProcsYm(vo.getTodayStr().substring(0, 6));
        resDvo.setProcsDt(vo.getTodayStr());
        resDvo.setWareDv(vo.getStrWareDvCd()); /*창고구분*/
        resDvo.setWareNo(vo.getStrWareNo());
        resDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        resDvo.setIostTp(vo.getStrTpCd());
        resDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        resDvo.setItmPdCd(vo.getItmPdCd());
        resDvo.setMngtUnit(vo.getMngtUnitCd());
        resDvo.setItemGd(vo.getItmGdCd());
        resDvo.setQty(vo.getOutQty());

        return resDvo;
    }

    protected WsnaItemStockItemizationReqDvo setMovementWsnaItemStockItemizationDtoSaveReq(
        WsnaNormalOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationReqDvo resDvo = new WsnaItemStockItemizationReqDvo();
        resDvo.setProcsYm(vo.getTodayStr().substring(0, 6));
        resDvo.setProcsDt(vo.getTodayStr());
        resDvo.setWareDv(vo.getStrWareDvCd() ); /*창고구분*/
        resDvo.setWareNo(vo.getStrWareNo());
        resDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo() );
//        resDvo.setIostTp(vo.getOstrTpCd());
        resDvo.setIostTp("991");
        resDvo.setWorkDiv("A");/*작업구분 workDiv*/
        resDvo.setItmPdCd(vo.getItmPdCd());
        resDvo.setMngtUnit(vo.getMngtUnitCd());
        resDvo.setItemGd(vo.getItmGdCd());
        resDvo.setQty(vo.getOutQty());

        return resDvo;
    }

    protected  WsnaMonthlyItemStocksReqDvo setMonthlyItemStocks(WsnaNormalOutOfStorageDvo vo){
        WsnaMonthlyItemStocksReqDvo resDvo = new WsnaMonthlyItemStocksReqDvo();
        resDvo.setProcsYm(vo.getTodayStr().substring(0, 6));
        resDvo.setProcsDt(vo.getTodayStr());
        resDvo.setWareDv(vo.getStrWareDvCd() ); /*창고구분*/
        resDvo.setWareNo(vo.getStrWareNo());
        resDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
//        resDvo.setIostTp(vo.getOstrTpCd());
//        resDvo.setIostTp("991");
        resDvo.setWorkDiv("A");/*작업구분 workDiv*/
        resDvo.setItmPdCd(vo.getItmPdCd());
        resDvo.setMngtUnit(vo.getMngtUnitCd());
        resDvo.setItemGd(vo.getItmGdCd());
        resDvo.setQty(vo.getOutQty());
        return resDvo;
    }
}
