package com.kyowon.sms.wells.web.service.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaPcsvOutOfStorageSaveMapper;
import com.sds.sflex.common.utils.DateUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaPcsvOutOfStorageSaveService {

    private final WsnaPcsvOutOfStorageSaveMapper mapper;

    private final WsnaItemStockItemizationService itemStockservice;

    @Transactional
    public void savePcsvOutOfStorage(WsnaPcsvOutOfStorageSaveDvo dvo) {

        if ("1112".equals(dvo.getSvBizDclsfCd())) {
            //정상출고
            mapper.insertPcsvOutOfStorage(dvo); // 작업결과 IU
            mapper.insertPcsvOutOfStorageRvPy(dvo); //사용내역 + 수불처리

            //수불처리(물류 서비스 연동)
            WsnaItemStockItemizationReqDvo itemDvo = setPcsvOstrWsnaItemStockItemizationDtoSaveReq(dvo);
            log.info("itemDvo qty---> {} ", itemDvo.getQty());
            log.info("itemDvo itemPdCd---> {} ", itemDvo.getItmPdCd());
            log.info("itemDvo WareNo ---> {} ", itemDvo.getWareNo());
            log.info("itemDvo WareMngtPrtnrNo---> {} ", itemDvo.getWareMngtPrtnrNo());
            //            try {
            //                itemStockservice.createStock(itemDvo);
            //            } catch (ParseException e) {
            //                throw new RuntimeException(e);
            //            }

            //TODO KSS 마스터 예정일자, 매출일자, 설치일자 업데이트
            //TODO KSS 마스터 변경정보 인서트

        } else if ("1113".equals(dvo.getSvBizDclsfCd())) {
            //재배송출고
            mapper.insertPcsvOutOfStorageRshp(dvo); // 작업결과 IU(재배송)
        }

    }

    private WsnaItemStockItemizationReqDvo setPcsvOstrWsnaItemStockItemizationDtoSaveReq(
        WsnaPcsvOutOfStorageSaveDvo vo
    ) {
        String nowDay = DateUtil.getNowDayString();

        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv(vo.getWkWareNo().substring(0, 1)); /*창고구분*/
        reqDvo.setWareNo(vo.getWkWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo()); //파트너번호
        reqDvo.setItmPdCd(vo.getPdCd());
        reqDvo.setQty(vo.getUseQty());
        reqDvo.setIostTp("213");
        reqDvo.setWorkDiv("A"); /*작업구분 workDiv*/
        reqDvo.setMngtUnit("1");
        reqDvo.setItemGd("A");
        return reqDvo;
    }

}
