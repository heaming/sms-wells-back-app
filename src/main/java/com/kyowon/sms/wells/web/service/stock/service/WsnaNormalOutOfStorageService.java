package com.kyowon.sms.wells.web.service.stock.service;

import java.text.ParseException;
import java.util.List;

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
        for (WsnaNormalOutOfStorageDvo vo : voList) {
            StrNoAndOstrNoRes res = mapper.selectStrNoAndOstrNo(vo);
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

            WsnaItemStockItemizationDto.SaveReq ostrDto = setOstrWsnaItemStockItemizationDtoSaveReq(vo);
            cnt += itemStockservice.createStock(ostrDto);

            WsnaItemStockItemizationDto.SaveReq movementDto = setMovementWsnaItemStockItemizationDtoSaveReq(vo);
            itemStockservice.saveStockMovement(movementDto);

            WsnaItemStockItemizationDto.SaveReq strDto = setStrWsnaItemStockItemizationDtoSaveReq(vo);
            itemStockservice.saveStockMovement(strDto);

            cnt += mapper.updateOstrAkIzAfter(vo);
            itmSeq++;
        }
        return cnt;
    }

    public int getNormalOstrRgstChecked(CheckedReq dto) {
        return mapper.selectNormalOstrRgstChecked(dto);
    }

    protected WsnaItemStockItemizationDto.SaveReq setOstrWsnaItemStockItemizationDtoSaveReq(
        WsnaNormalOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationDto.SaveReq reqDto = new WsnaItemStockItemizationDto.SaveReq(
            vo.getTodayStr().substring(0, 6),
            vo.getTodayStr(),
            vo.getOstrWareDvCd(), /*창고구분*/
            vo.getOstrWareNo(),
            vo.getOstrWareMngtPrtnrNo(),
            vo.getOstrTpCd(),
            "A", /*작업구분 workDiv*/
            vo.getItmPdCd(),
            vo.getMngtUnitCd(),
            vo.getItmGdCd(),
            vo.getOutQty(),
            null,
            null,
            null
        );
        return reqDto;
    }

    protected WsnaItemStockItemizationDto.SaveReq setStrWsnaItemStockItemizationDtoSaveReq(
        WsnaNormalOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationDto.SaveReq reqDto = new WsnaItemStockItemizationDto.SaveReq(
            vo.getTodayStr().substring(0, 6),
            vo.getTodayStr(),
            vo.getStrWareDvCd(), /*창고구분*/
            vo.getOstrWareNo(),
            vo.getOstrWareMngtPrtnrNo(),
            vo.getOstrTpCd(),
            "A", /*작업구분 workDiv*/
            vo.getItmPdCd(),
            vo.getMngtUnitCd(),
            vo.getItmGdCd(),
            vo.getOutQty(),
            null,
            null,
            null
        );
        return reqDto;
    }

    protected WsnaItemStockItemizationDto.SaveReq setMovementWsnaItemStockItemizationDtoSaveReq(
        WsnaNormalOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationDto.SaveReq reqDto = new WsnaItemStockItemizationDto.SaveReq(
            vo.getTodayStr().substring(0, 6),
            vo.getTodayStr(),
            vo.getOstrAkWareDvCd(), /*창고구분*/
            vo.getOstrWareNo(),
            vo.getOstrWareMngtPrtnrNo(),
            vo.getOstrTpCd(),
            "A", /*작업구분 workDiv*/
            vo.getItmPdCd(),
            vo.getMngtUnitCd(),
            vo.getItmGdCd(),
            vo.getOutQty(),
            null,
            null,
            null
        );
        return reqDto;
    }

    public int saveStandardWareHouse(MonthlyWarehouseReq dto) {
        WsnaNormalOutOfStorageStdgbDvo dvo = converter.mapToWsnaNormalOutOfStorageStdgbDvo(dto);
        return mapper.updateStandardWareHouse(dvo);
    }

    public StandardWareRes getStandardWareHouse(StandardWareReq dto) {
        return mapper.selectStandardWareHouse(dto);
    }
}
