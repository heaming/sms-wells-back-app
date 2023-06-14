package com.kyowon.sms.wells.web.service.stock.service;

import java.text.ParseException;
import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
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
@Service
@RequiredArgsConstructor
public class WsnaNormalOutOfStorageService {
    private final WsnaNormalOutOfStorageMapper mapper;

    private final WsnaNormalOutOfStorageConverter converter;

    private final WsnaItemStockItemizationService itemStockservice;

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
                vo.setTodayStr(res.todayStr());
                vo.setOstrSn(itmSeq);
                vo.setStrSn(itmSeq);
                vo.setQty(vo.getOutQty());
                cnt += mapper.updateOstrAkIz(vo);
                cnt += mapper.insertNormalOstrRgst(vo);
                cnt += mapper.insertNormalStrRgst(vo);

                //1.이동입고->내부출고 991, 출고:221, 입고:121
                WsnaItemStockItemizationReqDvo movementDvo = setMovementWsnaItemStockItemizationDtoSaveReq(vo);//이동입고
                itemStockservice.saveStockMovement(movementDvo);

                WsnaItemStockItemizationReqDvo ostrDvo = setOstrWsnaItemStockItemizationDtoSaveReq(vo);//출고
                cnt += itemStockservice.createStock(ostrDvo);

                WsnaItemStockItemizationReqDvo strDvo = setStrWsnaItemStockItemizationDtoSaveReq(vo);//입고
                itemStockservice.createStock(strDvo);

                cnt += mapper.updateOstrAkIzAfter(vo);
                itmSeq++;
            }
        }
        return cnt;
    }

    public int getNormalOstrRgstChecked(CheckedReq dto) {
        return mapper.selectNormalOstrRgstChecked(dto);
    }

    protected WsnaItemStockItemizationReqDvo setOstrWsnaItemStockItemizationDtoSaveReq(
        WsnaNormalOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getTodayStr().substring(0, 6));
        reqDvo.setProcsDt(vo.getTodayStr());
        reqDvo.setWareDv(vo.getOstrWareDvCd()); /*창고구분*/
        reqDvo.setWareNo(vo.getOstrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getOstrWareMngtPrtnrNo());
        reqDvo.setIostTp(vo.getOstrTpCd());
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMngtUnitCd());
        reqDvo.setItemGd(vo.getItmGdCd());
        reqDvo.setQty(vo.getOutQty());

        return reqDvo;
    }

    protected WsnaItemStockItemizationReqDvo setStrWsnaItemStockItemizationDtoSaveReq(
        WsnaNormalOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getTodayStr().substring(0, 6));
        reqDvo.setProcsDt(vo.getTodayStr());
        reqDvo.setWareDv(vo.getStrWareDvCd()); /*창고구분*/
        reqDvo.setWareNo(vo.getStrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        reqDvo.setIostTp(vo.getStrTpCd());
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMngtUnitCd());
        reqDvo.setItemGd(vo.getItmGdCd());
        reqDvo.setQty(vo.getOutQty());

        return reqDvo;
    }

    protected WsnaItemStockItemizationReqDvo setMovementWsnaItemStockItemizationDtoSaveReq(
        WsnaNormalOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getTodayStr().substring(0, 6));
        reqDvo.setProcsDt(vo.getTodayStr());
        reqDvo.setWareDv(vo.getOstrAkWareDvCd()); /*창고구분*/
        reqDvo.setWareNo(vo.getOstrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getOstrWareMngtPrtnrNo());
//        reqDvo.setIostTp(vo.getOstrTpCd());
        reqDvo.setIostTp("991");
        reqDvo.setWorkDiv("A");/*작업구분 workDiv*/
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMngtUnitCd());
        reqDvo.setItemGd(vo.getItmGdCd());
        reqDvo.setQty(vo.getOutQty());

        return reqDvo;
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
}
