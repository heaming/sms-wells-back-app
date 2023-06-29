package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto.*;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsOstrDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.sds.sflex.system.config.validation.BizAssert;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaEtcOutOfStorageConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEtcOutOfStorageDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaEtcOutOfStorageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * W-SV-U-0143M01 기타출고 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2023.02.03
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaEtcOutOfStorageService {

    private final WsnaEtcOutOfStorageMapper mapper;
    private final WsnaEtcOutOfStorageConverter converter;

    private final WsnaItemStockItemizationService itemStockservice;

    /**
     * 기타출고 등록 - 조회
     *
     * @param dto : {
     *            ostrDt : 출고일자
     *            ostrSn : 출고순번
     *            itmOstrNo : 품목출고번호
     *            ostrWareNo : 출고창고 }
     * @return 조회결과
     */
    public List<SearchRes> getEtcOutOfStorages(SearchReq dto) {
        return mapper.selectEtcOutOfStorages(dto);
    }

    /**
     * 기타출고 등록 - 엑셀다운로드 조회
     *
     * @param dto : {
     *            ostrDt : 출고일자
     *            ostrSn : 출고순번
     *            itmOstrNo : 품목출고번호
     *            ostrWareNo : 출고창고 }
     * @return 조회결과
     */
    public List<SearchRes> getEtcOutOfStoragesForExcelDownload(SearchReq dto) {
        return mapper.selectEtcOutOfStorages(dto);
    }

    /**
     * 기타출고 등록 - 삭제
     *
     * @param : {
     *          ostrDt : 출고일자
     *          ostrSn : 출고순번
     *          itmOstrNo : 품목출고번호
     *          ostrWareNo : 출고창고 }
     * @return 조회결과
     */
    @Transactional
    public int removeEtcOutOfStorages(List<DeleteReq> dtos) throws Exception {
        int processCount = 0;

        for (DeleteReq dto : dtos) {
            WsnaEtcOutOfStorageDvo etcOutOfStorage = this.converter.mapDeleteReqToWsnaEtcOutOfStorageDvo(dto);
            int result = this.mapper.deleteEtcOutOfStorages(etcOutOfStorage);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");

            //품목재고내역관리 서비스(W-SV-S-0087)의 품목재고내역 삭제 메소드
            WsnaItemStockItemizationReqDvo etcRemoveDvo = setEtcOutOfStoreageRemoveWsnaItemStockItemizationDvo(
                etcOutOfStorage
            );

            itemStockservice.removeStock(etcRemoveDvo);

        }
        //TODO: 현재 삭제처리 후 출고창고의 출고재고수량 복원을 위한 품목재고내역 삭제 메소드호출 필요 (추후 개발)
        return processCount;
    }

    @Transactional
    public int saveEtcOutOfStoragess(List<SaveReq> dtos) throws Exception {
        int processCount = 0;
        int serialNumber = 0;

        SaveReq saveReq = dtos.get(0);
        String itmOstrNo = this.mapper.selectNewItmOstrNo(new FindItmOstrNoReq(saveReq.ostrDt()));
        String WareMngtPrtnrNo = this.mapper.selectWareMngtPrtnrNo(new FindWareMngtPrtnrNoReq(saveReq.ostrWareNo()));

        for (SaveReq dto : dtos) {
            serialNumber += 1;
            WsnaEtcOutOfStorageDvo dvo = this.converter.mapSaveReqToWsnaEtcOutOfStorageDvo(dto);
            dvo.setItmOstrNo(itmOstrNo);
            dvo.setWareMngtPrtnrNo(WareMngtPrtnrNo);
            dvo.setOstrTpCd("217");
            dvo.setMngtUnitCd("10");
            dvo.setOstrSn(String.valueOf(serialNumber));

            int result = this.mapper.insertEtcOutOfStorageOstrIz(dvo);
            BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

            // 품목재고내역관리 서비스(W-SV-S-0087)의 품목재고내역 등록 메소드
            WsnaItemStockItemizationReqDvo etcDvo = setEtcOutOfStoreageWsnaItemStockItemizationDvo(dvo);
            itemStockservice.createStock(etcDvo);

            processCount++;
        }
        return processCount;
    }

    public List<SearchDeptRes> getEtcOutOfStorageDepts() {
        return this.mapper.selectEtcOutOfStorageDepts();
    }

    //기타출고 등록시 품목재고내역 등록
    protected WsnaItemStockItemizationReqDvo setEtcOutOfStoreageWsnaItemStockItemizationDvo(
        WsnaEtcOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(vo.getOstrDt().substring(0, 6));
        reqDvo.setProcsDt(vo.getOstrDt());
        reqDvo.setWareDv(vo.getOstrWareNo().substring(0, 1));
        reqDvo.setWareNo(vo.getOstrWareNo());
        reqDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        reqDvo.setIostTp("217");
        reqDvo.setWorkDiv("A");
        reqDvo.setItmPdCd(vo.getItmPdCd());
        reqDvo.setMngtUnit(vo.getMngtUnitCd());
        reqDvo.setItemGd(vo.getItmGdCd());
        reqDvo.setQty(String.valueOf(vo.getOstrQty()));
        return reqDvo;
    }

    protected WsnaItemStockItemizationReqDvo setEtcOutOfStoreageRemoveWsnaItemStockItemizationDvo(
        WsnaEtcOutOfStorageDvo vo
    ) {
        WsnaItemStockItemizationReqDvo removeDvo = new WsnaItemStockItemizationReqDvo();
        removeDvo.setProcsYm(vo.getOstrDt().substring(0, 6));
        removeDvo.setProcsDt(vo.getOstrDt());
        removeDvo.setWareDv(vo.getOstrWareNo().substring(0, 1));
        removeDvo.setWareNo(vo.getOstrWareNo());
        removeDvo.setWareMngtPrtnrNo(vo.getWareMngtPrtnrNo());
        removeDvo.setIostTp("217");
        removeDvo.setWorkDiv("D");
        removeDvo.setItmPdCd(vo.getItmPdCd());
        removeDvo.setMngtUnit(vo.getMngtUnitCd());
        removeDvo.setItemGd(vo.getItmGdCd());
        removeDvo.setQty(String.valueOf(vo.getOstrQty()));
        return removeDvo;
    }

}
