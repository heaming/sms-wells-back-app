package com.kyowon.sms.wells.web.service.stock.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaNormalOutOfStorageConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNormalOutOfStorageDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDetailDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNormalOutOfStorageStdgbDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaNormalOutOfStorageMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.ValidAssert;

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

    // 재고 서비스
    private final WsnaItemStockItemizationService itemStockservice;

    // 물류센터 창고구분코드
    private static final String LGST_WARE_DV_CD = "1";

    // (주)교원프라퍼티파주물류(Wells)
    private static final String SAP_PLNT_CD = "2108";
    // 프라파주창고(Wells)
    private static final String SAP_SAVE_LCT_CD = "21082082";
    private static final int SLICE_SIZE = 999;

    // 출고
    private static final String GUBUN_OSTR = "O";
    // 입고
    private static final String GUBUN_STR = "I";
    // 작업구분 - 등록
    private static final String WORK_DIV_A = "A";
    // 작업구분 - 삭제
    private static final String WORK_DIV_D = "D";

    /**
    * 정상출고 본인 창고 조회
    * @param apyYm   (필수) 기준년월
    * @return
    */
    public List<SearchWarehouse> getWarehouses(String apyYm) {
        return this.mapper.selectWarehouses(apyYm);
    }

    /**
     * 정상출고 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getNormalOutOfStorage(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectNormalOutOfStorage(dto, pageInfo);
    }

    /**
     * 정상출고 등록 정보 조회
     * @param dto
     * @return
     */
    public SearchItmOstrAkRes getItmOstrAk(SearchItmOstrAkReq dto) {
        return this.mapper.selectItmOstrAk(dto);
    }

    /**
    * 표준창고 조회
    * @param dto
    * @return
    */
    public StandardWareRes getStandardWareHouse(StandardWareReq dto) {
        return this.mapper.selectStandardWareHouse(dto);
    }

    /**
     * 정상출고 상세 정보 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<WsnaNormalOutOfStorageDetailDvo> getNormalOstrRgsts(DetailReq dto, PageInfo pageInfo) {
        PagingResult<WsnaNormalOutOfStorageDetailDvo> result = this.mapper.selectNormalOstrRgsts(dto, pageInfo);

        List<WsnaNormalOutOfStorageDetailDvo> dvos = result.getList();

        if (CollectionUtils.isNotEmpty(dvos) && LGST_WARE_DV_CD.equals(dvos.get(0).getOstrWareDvCd())) {
            // 실시간 물류 재고 조회
            this.getRealTimeLogisticStockQtys(dvos);
        }

        result.setList(dvos);
        return result;
    }

    /**
     * 정상출고 상세 정보 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<WsnaNormalOutOfStorageDetailDvo> getNormalOstrRgstsExcelDownload(DetailReq dto) {
        List<WsnaNormalOutOfStorageDetailDvo> dvos = this.mapper.selectNormalOstrRgsts(dto);
        if (CollectionUtils.isNotEmpty(dvos) && LGST_WARE_DV_CD.equals(dvos.get(0).getOstrWareDvCd())) {
            // 실시간 물류 재고 조회
            this.getRealTimeLogisticStockQtys(dvos);
        }

        return dvos;
    }

    /**
     * 정상출고 상세 정보 페이징 조회 (삭제용)
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<WsnaNormalOutOfStorageDetailDvo> getNormalOstrRgstsForRemove(
        DetailRemoveReq dto, PageInfo pageInfo
    ) {
        PagingResult<WsnaNormalOutOfStorageDetailDvo> result = this.mapper
            .selectNormalOstrRgstsForRemove(dto, pageInfo);
        List<WsnaNormalOutOfStorageDetailDvo> dvos = result.getList();

        if (CollectionUtils.isNotEmpty(dvos) && LGST_WARE_DV_CD.equals(dvos.get(0).getOstrWareDvCd())) {
            // 실시간 물류 재고 조회
            this.getRealTimeLogisticStockQtys(dvos);
        }

        result.setList(dvos);
        return result;
    }

    /**
     * 정상출고 상세 정보 엑셀 다운로드 (삭제용)
     * @param dto
     * @return
     */
    public List<WsnaNormalOutOfStorageDetailDvo> getNormalOstrRgstsExcelDownloadForRemove(DetailRemoveReq dto) {

        List<WsnaNormalOutOfStorageDetailDvo> dvos = this.mapper.selectNormalOstrRgstsForRemove(dto);

        if (CollectionUtils.isNotEmpty(dvos) && LGST_WARE_DV_CD.equals(dvos.get(0).getOstrWareDvCd())) {
            // 실시간 물류 재고 조회
            this.getRealTimeLogisticStockQtys(dvos);
        }

        return dvos;
    }

    /**
     * 실시간 물류 센터 재고 조회
     * @param dvos
     */
    private void getRealTimeLogisticStockQtys(List<WsnaNormalOutOfStorageDetailDvo> dvos) {
        if (CollectionUtils.isNotEmpty(dvos)) {

            int size = dvos.size();
            int sliceSize = Math.floorDiv(size, SLICE_SIZE) + (Math.floorMod(size, SLICE_SIZE) > 0 ? 1 : 0);

            for (int i = 0; i < sliceSize; i++) {
                int startIdx = i * SLICE_SIZE;
                int endIdx = (i + 1) * SLICE_SIZE > size ? size : (i + 1) * SLICE_SIZE;

                List<WsnaNormalOutOfStorageDetailDvo> sliceDvos = dvos.subList(startIdx, endIdx);
                List<String> itmPds = sliceDvos.stream().map(WsnaNormalOutOfStorageDetailDvo::getItmPdCd).toList();

                List<RealTimeGradeStockResIvo> stocks = this.itemStockservice
                    .getRealTimeGradeStocks(SAP_PLNT_CD, SAP_SAVE_LCT_CD, itmPds);

                this.setTotalLogisticQty(stocks, sliceDvos);
            }
        }
    }

    /**
     * 물류 물량 셋팅
     * @param stocks
     * @param sliceDvos
     */
    private void setTotalLogisticQty(
        List<RealTimeGradeStockResIvo> stocks, List<WsnaNormalOutOfStorageDetailDvo> sliceDvos
    ) {

        if (CollectionUtils.isNotEmpty(stocks)) {

            int stockSize = stocks.size();

            for (WsnaNormalOutOfStorageDetailDvo dvo : sliceDvos) {
                String pdCd = dvo.getItmPdCd();
                String itmGdCd = dvo.getItmGdCd();

                BigDecimal lgstQty = BigDecimal.ZERO;

                for (int i = 0; i < stockSize; i++) {
                    RealTimeGradeStockResIvo stock = stocks.get(i);

                    String stockPdCd = stock.getItmPdCd();

                    if (pdCd.equals(stockPdCd)) {
                        BigDecimal aGdQty = stock.getLgstAGdQty();
                        BigDecimal bGdQty = stock.getLgstBGdQty();
                        BigDecimal eGdQty = stock.getLgstEGdQty();
                        BigDecimal rGdQty = stock.getLgstRGdQty();

                        switch (itmGdCd) {
                            case "A":
                                lgstQty = aGdQty;
                                break;
                            case "B":
                                lgstQty = bGdQty;
                                break;
                            case "E":
                                lgstQty = eGdQty;
                                break;
                            case "R":
                                lgstQty = rGdQty;
                                break;
                        }

                        stockSize--;
                        stocks.remove(stock);
                        break;
                    }
                }

                dvo.setQty(lgstQty);
            }
        }
    }

    /**
     * 표준 창고적용여부 수정
     * @param dto
     * @return
     */
    @Transactional
    public int editStandardWareHouse(StandardWareReq dto) {
        WsnaNormalOutOfStorageStdgbDvo dvo = converter.mapStandardWareReqToWsnaNormalOutOfStorageStdgbDvo(dto);
        return this.mapper.updateStandardWareHouse(dvo);
    }

    /**
     * 창고마감여부 조회
     * @param ostrDt
     * @param ostrOjWareNo
     * @return
     */
    public String getWareCloseYn(String ostrDt, String ostrOjWareNo) {
        ValidAssert.hasText(ostrDt);
        ValidAssert.hasText(ostrOjWareNo);

        return this.mapper.selectWareCloseYn(ostrDt, ostrOjWareNo);
    }

    /**
     * 정상출고 삭제
     * @param dtos
     * @return
     */
    @Transactional
    public int removeNormalOstrRgsts(List<RemoveReq> dtos) {

        int cnt = 0;

        List<WsnaNormalOutOfStorageDvo> dvos = this.converter.mapAllRemoveReqToWsnaNormalOutOfStorageDvo(dtos);

        for (WsnaNormalOutOfStorageDvo dvo : dvos) {
            // 출고 삭제
            this.mapper.updateItmOstrIzForRemove(dvo);
            // 입고 삭제
            this.mapper.updateItmStrIzForRemove(dvo);

            // 품목재고내역 삭제 - 출고창고
            WsnaItemStockItemizationReqDvo ostrStockReq = this
                .convertStockItemizationCreateReq(dvo, GUBUN_OSTR, WORK_DIV_D);
            this.itemStockservice.createStock(ostrStockReq);

            // 품목재고내역 이동 - 입고창고
            WsnaItemStockItemizationReqDvo strMoveReq = this.convertStockItemizationMoveReq(dvo, WORK_DIV_D);
            this.itemStockservice.saveStockMovement(strMoveReq);

            // 품목재고내역 등록 - 입고창고
            WsnaItemStockItemizationReqDvo strStockReq = this
                .convertStockItemizationCreateReq(dvo, GUBUN_STR, WORK_DIV_D);
            this.itemStockservice.createStock(strStockReq);

            // 출고요청내역 수정
            cnt += this.mapper.updateItmOstrAkIzForRemove(dvo);
        }

        return cnt;
    }

    /**
     * 재고내역 파라미터 변환
     * @param dvo       (필수) 품목 입/출고 dvo
     * @param iostGb    (필수) 입출고 구분 (O : 출고, I : 입고)
     * @return 재고등록 request dvo
     */
    private WsnaItemStockItemizationReqDvo convertStockItemizationCreateReq(
        WsnaNormalOutOfStorageDvo dvo, String iostGb, String workDiv
    ) {

        ValidAssert.notNull(dvo);
        ValidAssert.hasText(iostGb);
        ValidAssert.hasText(workDiv);

        String nowDay = DateUtil.getNowDayString();

        String wareDv = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrWareDvCd() : dvo.getStrWareDvCd();
        String wareNo = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrOjWareNo() : dvo.getStrOjWareNo();
        String wareMngtPrtnrNo = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrPrtnrNo() : dvo.getStrPrtnrNo();
        String iostTp = GUBUN_OSTR.equals(iostGb) ? dvo.getOstrTpCd() : dvo.getStrTpCd();

        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv(wareDv);
        reqDvo.setWareNo(wareNo);
        reqDvo.setWareMngtPrtnrNo(wareMngtPrtnrNo);
        reqDvo.setIostTp(iostTp);
        reqDvo.setWorkDiv(workDiv);
        reqDvo.setItmPdCd(dvo.getItmPdCd());
        reqDvo.setMngtUnit(dvo.getMngtUnitCd());
        reqDvo.setItemGd(dvo.getItmGdCd());
        reqDvo.setQty(String.valueOf(dvo.getOutQty()));

        return reqDvo;
    }

    /**
     * 재고이동 파라미터 변환
     * @param dvo
     * @param workDiv
     * @return
     */
    private WsnaItemStockItemizationReqDvo convertStockItemizationMoveReq(
        WsnaNormalOutOfStorageDvo dvo, String workDiv
    ) {

        ValidAssert.notNull(dvo);
        ValidAssert.hasText(workDiv);

        String nowDay = DateUtil.getNowDayString();
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv(dvo.getStrWareDvCd());
        reqDvo.setWareNo(dvo.getStrOjWareNo());
        reqDvo.setWareMngtPrtnrNo(dvo.getStrPrtnrNo());
        // 이동입고(991)
        reqDvo.setIostTp("991");
        reqDvo.setWorkDiv(workDiv);
        reqDvo.setItmPdCd(dvo.getItmPdCd());
        reqDvo.setMngtUnit(dvo.getMngtUnitCd());
        reqDvo.setItemGd(dvo.getItmGdCd());
        reqDvo.setQty(String.valueOf(dvo.getOutQty()));

        return reqDvo;
    }

    /**
     * 정상출고 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getNormalOutOfStorage(SearchReq dto) {
        return this.mapper.selectNormalOutOfStorage(dto);
    }

    /**
     * 개인창고 요청 자재보유 현황 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<AskRes> getAskMaterialsHavePss(
        AskReq dto, PageInfo pageInfo
    ) {
        return this.mapper.selectAskMaterialsHavePss(dto, pageInfo);
    }

    /**
     * 조직창고 요청 자재보유 현황 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<CenterRes> getAskMaterialsCenterPresentState(
        CenterReq dto, PageInfo pageInfo
    ) {
        return this.mapper.selectAskMaterialsCenterPresentState(dto, pageInfo);
    }

    /**
     * 정상출고 확정
     * @param dtos
     * @return
     */
    @Transactional
    public int saveNormalOstrRgsts(List<CreateReq> dtos) {

        int cnt = 0;

        List<WsnaNormalOutOfStorageDvo> dvos = converter.mapAllCreateReqToWsnaNormalOutOfStorageDvo(dtos);
        WsnaNormalOutOfStorageDvo dvoData = dvos.get(0);
        String strTpCd = dvoData.getStrTpCd();
        String ostrTpCd = dvoData.getOstrTpCd();

        // 입고번호 채번
        String newItmStrNo = this.mapper.selectNewItmStrNo(strTpCd);
        // 출고번호 채번
        String newItmOstrNo = this.mapper.selectNewItmOstrNo(ostrTpCd);

        for (WsnaNormalOutOfStorageDvo dvo : dvos) {
            dvo.setItmOstrNo(newItmOstrNo);
            dvo.setItmStrNo(newItmStrNo);

            int strSn = this.mapper.selectNewStrSn(newItmStrNo);
            dvo.setStrSn(strSn);
            int ostrSn = this.mapper.selectNewOstrSn(newItmOstrNo);
            dvo.setOstrSn(ostrSn);

            // 품목출고내역 생성
            this.mapper.insertNormalOstrRgst(dvo);

            // 품목입고내역 생성
            this.mapper.insertNormalStrRgst(dvo);

            // 품목재고내역 등록 - 출고창고
            WsnaItemStockItemizationReqDvo ostrStockReq = this
                .convertStockItemizationCreateReq(dvo, GUBUN_OSTR, WORK_DIV_A);
            this.itemStockservice.createStock(ostrStockReq);

            // 품목재고내역 이동 - 입고창고
            WsnaItemStockItemizationReqDvo strMoveReq = this.convertStockItemizationMoveReq(dvo, WORK_DIV_A);
            this.itemStockservice.saveStockMovement(strMoveReq);
            // 품목재고내역 등록 - 입고창고
            WsnaItemStockItemizationReqDvo strStockReq = this
                .convertStockItemizationCreateReq(dvo, GUBUN_STR, WORK_DIV_A);
            this.itemStockservice.createStock(strStockReq);

            // 출고요청내역 UPDATE
            cnt += this.mapper.updateItmOstrAkIz(dvo);
        }

        return cnt;
    }

    /**
     * 정상출고 확정 수량 조회
     * @param dto
     * @return
     */
    public int getOstrCnfmCount(CheckReq dto) {
        return this.mapper.selectOstrCnfmCount(dto);

    }
}
