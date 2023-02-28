package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaEtcOutOfStorageConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaEtcOutOfStorageDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaEtcOutOfStorageDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaEtcOutOfStorageMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    public int saveEtcOutOfStorages(List<DeleteReq> dtos) throws Exception {
        int processCount = 0;

        for (WsnaEtcOutOfStorageDto.DeleteReq dto : dtos) {
            WsnaEtcOutOfStorageDvo etcOutOfStorage = this.converter.mapDeleteReqToWsnaEtcOutOfStorageDvo(dto);
            processCount += this.mapper.deleteEtcOutOfStorages(etcOutOfStorage);
        }
        //TODO: 현재 삭제처리 후 출고창고의 출고재고수량 복원을 위한 품목재고내역 삭제 메소드호출 필요 (추후 개발)
        return processCount;
    }

    public int saveEtcOutOfStoragess(List<SaveReq> dtos, String strOjWareNo, String ostrDt) throws Exception {
        int processCount = 0;
        log.info("Pass meta strOjWareNo :" + strOjWareNo);
        log.info("Pass meta ostrDt : " + ostrDt);
        for (WsnaEtcOutOfStorageDto.SaveReq dto : dtos) {
            WsnaEtcOutOfStorageDvo dvo = this.converter.mapSaveReqToWsnaEtcOutOfStorageDvo(dto);
            dvo.setOstrDt(ostrDt);
            log.info(dvo.getOstrDt());
            //품목출고내역의 품목출고 번호, 출고일련번호(OSTR_SN)를 채번한다.
            String itmOstrNo = mapper.selectNewItmOstrNo(dvo);

            dvo.setItmOstrNo(itmOstrNo);
            log.info("Pass meta ItmOstrNo : " + dvo.getItmOstrNo());

            String ostrSn = mapper.selectNewOstrSn(dvo);
            dvo.setOstrSn(ostrSn);
            //TODO: 현재 저장개발진행중

            //            processCount += this.mapper.insertCenterArea(dvo);
        }
        return processCount;
    }
}
