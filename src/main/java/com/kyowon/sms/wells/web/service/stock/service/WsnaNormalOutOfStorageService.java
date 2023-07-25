package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaNormalOutOfStorageConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
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

    /**
     * 정상출고 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getNormalOutOfStorage(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectNormalOutOfStorage(dto, pageInfo);
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

    public PagingResult<DetailRes> getNormalOstrRgsts(DetailReq dto, PageInfo pageInfo) {
        return this.mapper.getNormalOstrRgsts(dto, pageInfo);
    }

    public PagingResult<DetailRes> removeNormalOstrRgsts(DetailReq dto, PageInfo pageInfo) {
        return mapper.removeNormalOstrRgsts(dto, pageInfo);
    }

    @Transactional
    public int saveNormalOstrRgsts(List<CreateReq> list) {
        int cnt = 0;
        int itmSeq = 1;
        List<WsnaNormalOutOfStorageDvo> voList = converter.mapAllWsnaNormalOutOfStorageDvos(list);

        if (voList.size() > 0) {
            WsnaNormalOutOfStorageDvo voTemp = voList.get(0);
            StrNoAndOstrNoRes res = mapper.selectStrNoAndOstrNo(voTemp);

            for (WsnaNormalOutOfStorageDvo vo : voList) {
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

                //정상출고
                cnt += itemStockservice.createStock(ostrDvo);

                //이동입고 991
                cnt += itemStockservice.saveStockMovement(movementDvo);

                //정상입고
                cnt += itemStockservice.createStock(strDvo);

                itmSeq++;
            }
        }
        return cnt;
    }

    @Transactional
    public int removeNormalOstrRgsts(List<CreateReq> list) {
        int cnt = 0;
        List<WsnaNormalOutOfStorageDvo> voList = converter.mapAllWsnaNormalOutOfStorageDvos(list);

        if (voList.size() > 0) {
            for (WsnaNormalOutOfStorageDvo vo : voList) {

                WsnaItemStockItemizationReqDvo ostrDvo = setOstrWsnaItemStockItemizationDtoSaveReq(vo);//출고
                WsnaItemStockItemizationReqDvo strDvo = setStrWsnaItemStockItemizationDtoSaveReq(vo);//입고
                WsnaItemStockItemizationReqDvo movementDvo = setMovementWsnaItemStockItemizationDtoSaveReq(vo);//이동입고

                mapper.removeNormalOstr(vo);
                mapper.removeNormalStr(vo);
                mapper.updateRemoveOstrAkIzAfter(vo);

                //정상입고
                movementDvo.setWorkDiv("D");
                movementDvo.setIostTp("121");
                movementDvo.setWareNo(strDvo.getWareNo());
                movementDvo.setWareDv(strDvo.getWareDv());
                movementDvo.setWareMngtPrtnrNo(strDvo.getWareMngtPrtnrNo());
                cnt += itemStockservice.removeStock(strDvo);

                //이동입고 991
                movementDvo.setWorkDiv("D");
                movementDvo.setIostTp("991");
                movementDvo.setWareNo(ostrDvo.getWareNo());
                movementDvo.setWareDv(ostrDvo.getWareDv());
                movementDvo.setWareMngtPrtnrNo(ostrDvo.getWareMngtPrtnrNo());
                cnt += itemStockservice.saveStockMovement(movementDvo);

                //정상출고
                movementDvo.setWorkDiv("D");
                movementDvo.setIostTp("221");
                movementDvo.setWareNo(ostrDvo.getWareNo());
                movementDvo.setWareDv(ostrDvo.getWareDv());
                movementDvo.setWareMngtPrtnrNo(ostrDvo.getWareMngtPrtnrNo());
                cnt += itemStockservice.removeStock(ostrDvo);

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

    public SearchItmOstrAkRes getItmOstrAk(SearchItmOstrAkReq dto) {
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
        resDvo.setWareDv(vo.getStrWareDvCd()); /*창고구분*/
        resDvo.setWareNo(vo.getStrWareNo());
        resDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        resDvo.setIostTp("991");
        resDvo.setWorkDiv("A");/*작업구분 workDiv*/
        resDvo.setItmPdCd(vo.getItmPdCd());
        resDvo.setMngtUnit(vo.getMngtUnitCd());
        resDvo.setItemGd(vo.getItmGdCd());
        resDvo.setQty(vo.getOutQty());

        return resDvo;
    }

}
