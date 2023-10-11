package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStockStatusControlDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaStockStatusControlConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStockStatusControlDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStockStatusControlMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0134M01 재고상태조정 관리 Controller
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.07.11
 */

@Service
@RequiredArgsConstructor
public class WsnaStockStatusControlService {

    private final WsnaStockStatusControlMapper mapper;
    private final WsnaStockStatusControlConverter converter;

    // 재고내역 서비스
    private final WsnaItemStockItemizationService stockService;

    private static final String WORK_DIV = "A";

    private static final String WORK_DIV_D = "D";

    private static final String OSTR_IOST_TP_CD = "281";
    private static final String STR_IOST_TP_CD = "181";

    /**
     * 재고상태조정 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getStockStatusControlPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectStockStatusControlPages(dto, pageInfo);
    }

    /**
     * 재고상태조정 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getStockStatusControlsForExcelDownload(SearchReq dto) {
        return mapper.selectStockStatusControlPages(dto);
    }

    /**
     * 창고조회
     * @param dto
     * @return
     */
    public List<SearchWarehouseRes> getStockStatusControlsWarehouse(SearchWarehouseReq dto) {
        return mapper.selectStockStatusControlWarehouse(dto);
    }

    /**
     * 관리부서 조회
     * @param wareNo
     * @return
     */
    public FindOgNmRes getOrganizationDeptName(String wareNo) {
        return mapper.selectOrganizationDeptName(wareNo);
    }

    /**
     * 상품 조회
     * @param dto
     * @return
     */
    public List<SearchItmPdCdRes> getStockStatusItmPdCd(SearchItmPdCdReq dto) {
        return mapper.selectStockStatusItmPdCd(dto);
    }

    /**
     * 상품조회
     * @param dto
     * @return
     */
    public List<SearchWarehouseItmPdCdRes> getStockStatusWarehouseItmPdCd(SearchWarehouseItmPdCdReq dto) {
        return mapper.selectStockStatusWarehouseItmPdCd(dto);
    }

    /**
     * 상태에 따른 상품조회
     * @param dto
     * @return
     */
    public List<SearchWarehouseItmPdCdRes> getStatusProductItmPdCd(SearchStatusProductReq dto) {
        return mapper.selectStatusProductItmPdCd(dto);
    }

    /**
     * 상품에 해당하는 수량 조회
     * @param dto
     * @return
     */
    public SearchPdCdQtyRes getItmPdCdQty(SearchPdCdQtyReq dto) {
        ValidAssert.notNull(dto);

        return mapper.selectItmPdCdQty(dto);

    }

    /**
     * 재고상태조정 관리 저장
     * @param dtos
     * @return
     */

    @Transactional
    public int saveStockStatusControls(List<SaveReq> dtos) {
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        //세션사용자사번
        String employeeIDNumber = session.getEmployeeIDNumber();

        int count = 0;
        for (SaveReq dto : dtos) {

            WsnaStockStatusControlDvo dvo = this.converter.mapSaveReqToWsnaStockStatusControlDvo(dto);

            String rowState = dto.rowState();
            int result = 0;

            if (CommConst.ROW_STATE_CREATED.equals(rowState)) {

                result = this.mapper.insertStockStatusControls(dvo);

                //재고호출을 위한 사용자 세션사번 사용
                dvo.setWareMngtPrtnrNo(employeeIDNumber);
                // 품목재고내역 등록 - 출고창고
                WsnaItemStockItemizationReqDvo ostrStockDvo = this.convertStockItemizationCreateReq(dvo);
                this.stockService.createStock(ostrStockDvo);
                // 품목재고내역 등록 - 입고
                WsnaItemStockItemizationReqDvo strStockDvo = this.convertStockStrItemizationCreateReq(dvo);
                this.stockService.createStock(strStockDvo);

            }

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            count += result;

        }

        return count;
    }

    /**
     * 재고상태조정 삭제
     * @param dtos
     * @return
     */
    @Transactional
    public int updateStockStatusControlsForRemove(List<RemoveReq> dtos) {

        int processCount = 0;
        for (RemoveReq dto : dtos) {
            WsnaStockStatusControlDvo dvo = this.converter.mapRemoveReqToWsnaStockStatusControlDvo(dto);

            int result = this.mapper.updateStockStatusControlsForRemove(dvo);

            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            //품목재고내역 삭제
            WsnaItemStockItemizationReqDvo strStockDvo = this.convertRemoveStrSotckItemizationRemoveReq(dvo);
            this.stockService.removeStock(strStockDvo);

            // 품목재고내역 등록 - 출고창고
            WsnaItemStockItemizationReqDvo ostrStockDvo = this.convertRemoveStockItemizationRemoveReq(dvo);
            this.stockService.removeStock(ostrStockDvo);

            processCount += result;

        }

        return processCount;
    }

    /**
     * 품목재고 내역 출고 파라미터 변환
     * @param dvo
     * @return
     */
    private WsnaItemStockItemizationReqDvo convertStockItemizationCreateReq(
        WsnaStockStatusControlDvo dvo
    ) {
        ValidAssert.notNull(dvo);

        String nowDay = DateUtil.getNowDayString();
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv(dvo.getWareDvCd());
        reqDvo.setWareNo(dvo.getWareNo());
        reqDvo.setWareMngtPrtnrNo(dvo.getWareMngtPrtnrNo());
        reqDvo.setIostTp(OSTR_IOST_TP_CD);
        reqDvo.setWorkDiv(WORK_DIV);
        reqDvo.setItmPdCd(dvo.getItmPdCd());
        reqDvo.setMngtUnit(dvo.getMgtUnit());
        reqDvo.setItemGd(dvo.getBfctItmGdCd());
        reqDvo.setQty(String.valueOf(dvo.getCtrQty()));
        return reqDvo;

    }

    /**
     * 품목재고내역 입고 파라미터 변환
     * @param dvo
     * @return
     */
    private WsnaItemStockItemizationReqDvo convertStockStrItemizationCreateReq(
        WsnaStockStatusControlDvo dvo
    ) {

        ValidAssert.notNull(dvo);

        String nowDay = DateUtil.getNowDayString();
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(nowDay.substring(0, 6));
        reqDvo.setProcsDt(nowDay);
        reqDvo.setWareDv(dvo.getWareDvCd());
        reqDvo.setWareNo(dvo.getWareNo());
        reqDvo.setWareMngtPrtnrNo(dvo.getWareMngtPrtnrNo());
        reqDvo.setIostTp(STR_IOST_TP_CD);
        reqDvo.setWorkDiv(WORK_DIV);
        reqDvo.setItmPdCd(dvo.getItmPdCd());
        reqDvo.setMngtUnit(dvo.getMgtUnit());
        reqDvo.setItemGd(dvo.getAfctItmGdCd());
        reqDvo.setQty(String.valueOf(dvo.getCtrQty()));
        return reqDvo;

    }

    /**
     * 품목재고내역 입고 삭제 파라미터 변환
     * @param dvo
     * @return
     */
    private WsnaItemStockItemizationReqDvo convertRemoveStockItemizationRemoveReq(
        WsnaStockStatusControlDvo dvo
    ) {
        ValidAssert.notNull(dvo);
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(dvo.getCtrWkDt().substring(0, 6));
        reqDvo.setProcsDt(dvo.getCtrWkDt());
        reqDvo.setWareDv(dvo.getWareDvCd());
        reqDvo.setWareNo(dvo.getWareNo());
        reqDvo.setWareMngtPrtnrNo(dvo.getWareMngtPrtnrNo());
        reqDvo.setIostTp(STR_IOST_TP_CD);
        reqDvo.setWorkDiv(WORK_DIV_D);
        reqDvo.setItmPdCd(dvo.getItmPdCd());
        reqDvo.setMngtUnit(dvo.getMgtUnit());
        reqDvo.setItemGd(dvo.getAfctItmGdCd());
        reqDvo.setQty(String.valueOf(dvo.getCtrQty()));
        return reqDvo;
    }

    /**
     * 품목재고내역 출고 삭제 파라미터 변환
     * @param dvo
     * @return
     */
    private WsnaItemStockItemizationReqDvo convertRemoveStrSotckItemizationRemoveReq(
        WsnaStockStatusControlDvo dvo
    ) {
        ValidAssert.notNull(dvo);
        WsnaItemStockItemizationReqDvo reqDvo = new WsnaItemStockItemizationReqDvo();
        reqDvo.setProcsYm(dvo.getCtrWkDt().substring(0, 6));
        reqDvo.setProcsDt(dvo.getCtrWkDt());
        reqDvo.setWareDv(dvo.getWareDvCd());
        reqDvo.setWareNo(dvo.getWareNo());
        reqDvo.setWareMngtPrtnrNo(dvo.getWareMngtPrtnrNo());
        reqDvo.setIostTp(OSTR_IOST_TP_CD);
        reqDvo.setWorkDiv(WORK_DIV_D);
        reqDvo.setItmPdCd(dvo.getItmPdCd());
        reqDvo.setMngtUnit(dvo.getMgtUnit());
        reqDvo.setItemGd(dvo.getBfctItmGdCd());
        reqDvo.setQty(String.valueOf(dvo.getCtrQty()));
        return reqDvo;
    }
}
