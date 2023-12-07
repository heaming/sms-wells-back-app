package com.kyowon.sms.wells.web.service.stock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaBuildingBsConsumableConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsConsumablesAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBuildingBsConsumableDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBuildingBsConsumableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContext;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-U-0010M01 소모품 배부현황(빌딩별) 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-04
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class WsnaBuildingBsConsumableService {
    private final WsnaBuildingBsConsumableMapper mapper;
    private final WsnaBuildingBsConsumableConverter converter;
    private final WsnaBsConsumablesAskService bsConsumablesAskService;
    private final WsnaItemStockItemizationService stockService;

    private static final String OSTR_AK_TP_CD_BS = "380"; // 출고요청유형코드 : BS소모품배부
    private static final String IOST_AK_DV_CD_WELLS = "WE";
    private static final String LGST_SPP_MTHD_CD_CRGO = "8"; // 물류배송방식코드 6 -> 8로 변경(사업장배송)
    private static final String LGST_WK_MTHD_CD_BLD = "WE08";
    private static final String ITM_GD_CD_A = "A";
    private static final String OSTR_OJ_WARE_NO_PAJU = "100002";
    private static final String BFSVC_CSMB_DDLV_OJ_CD_BLD = "3";
    private static final String SAP_PLNT_CD = "2108"; // 교원프라퍼티파주물류
    private static final String PAJU_SAP_SAVE_LCT_CD = "21082082"; // 파주창고

    /**
     * 빌딩명 조회
     * @param mngtYm
     * @return
     */
    public List<SearchBldRes> getBuildingList(String mngtYm) {
        return mapper.selectBuildingList(mngtYm);
    }

    /**
     * 활동물품 조회 (그리드 헤더 표시용)
     *
     * @param mngtYm
     * @return
     */
    public List<SearchItmRes> getItems(String mngtYm) {
        List<WsnaBuildingBsConsumableDvo> dvos = mapper.selectItems(mngtYm);

        if (!ObjectUtils.isEmpty(dvos)) {
            // 그리드 내 품목명에 파주재고 표시를 위해 로직 추가 - 23.11.17
            // 파주재고 set
            List<String> itmPds = dvos.stream().map(WsnaBuildingBsConsumableDvo::getCsmbPdCd).toList();

            List<RealTimeGradeStockResIvo> pajuStocks = stockService
                .getRealTimeGradeStocks(SAP_PLNT_CD, PAJU_SAP_SAVE_LCT_CD, itmPds);

            dvos.forEach(dvo -> {
                String fxnPdNm = dvo.getFxnPdNm();
                String aplcPdNm = dvo.getAplcPdNm();

                dvo.setFxnPdNm(dvo.getFxnPdNm() + "(" + 0 + ")");
                dvo.setAplcPdNm(dvo.getAplcPdNm() + "(" + 0 + ")");

                pajuStocks.forEach(stock -> {
                    if (dvo.getCsmbPdCd().equals(stock.getItmPdCd())) {
                        BigDecimal pajuLgstCnrStocQty = stock.getLgstAGdQty();

                        if ("1".equals(dvo.getBfsvcCsmbDdlvTpCd())) { // 고정품목
                            dvo.setFxnPdNm(fxnPdNm + "(" + pajuLgstCnrStocQty + ")");
                        } else if ("2".equals(dvo.getBfsvcCsmbDdlvTpCd())) { // 신청품목
                            dvo.setAplcPdNm(aplcPdNm + "(" + pajuLgstCnrStocQty + ")");
                        }
                    }
                });
            });
        }

        return converter.mapAllDvosToSearchItmRes(dvos);
    }

    /**
     * 빌딩별 소모품 신청 등록기간 조회
     * @param mngtYm
     * @return
     */
    public FindTmlmRes getBuildingBsConsumableAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectBuildingBsConsumableAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            // 업무마감 데이터가 없을 경우 달력의 시작, 종료일자를 조회 (휴일 제외)
            res = mapper.selectBuildingBsConsumableAplcFirstClose(mngtYm);
        }

        return res;
    }

    /**
     * 등록기간 설정
     * @param dto
     * @return
     */
    @Transactional
    public int createBuildingBsConsumableAplcClose(CreateTmlmReq dto) {
        WsnaBuildingBsConsumableDvo dvo = converter.mapCreateTmlmReqToCsmbDblv(dto);

        return mapper.mergeBuildingBsConsumableAplcClose(dvo);
    }

    /**
     * 신청제한수량 조회
     * @param mngtYm
     * @return
     */
    public List<SearchLmQtyRes> getApplicationLimitQty(String mngtYm) {
        return mapper.selectApplicationLimitQty(mngtYm);
    }

    /**
     * 빌딩별 소모품 배부현황 조회
     * @param dto
     * @return
     */
    public List<HashMap<String, Object>> getBuildingBsConsumables(SearchReq dto) {
        WsnaBuildingBsConsumableDvo searchDvo = converter.mapSearchReqToBuildingBsConsumable(dto);

        String mngtYm = searchDvo.getMngtYm();

        // 그리드 헤더상의 품목 조회
        List<WsnaBuildingBsConsumableDvo> sapMatCds = mapper.selectItems(mngtYm);
        String mngtYear = mngtYm.substring(0, 4);
        String mngtMonth = mngtYm.substring(4);
        mngtMonth = mngtMonth.startsWith("0") ? " " + mngtMonth.substring(1) : mngtMonth;
        // {0}년 {1}월 배부기준이 없습니다.
        BizAssert.isFalse(
            CollectionUtils.isEmpty(sapMatCds), "MSG_ALT_THM_DATA_NOT_EXST", new String[] {mngtYear, mngtMonth}
        );

        // PIVOT 조건 변환
        String pivotInStr = sapMatCds.stream()
            .map(obj -> {
                // 배부유형코드
                String ddlvTpCd = obj.getBfsvcCsmbDdlvTpCd();
                // 고정
                if ("1".equals(ddlvTpCd)) {
                    return "'" + obj.getSapMatCd() + "' AS QTY_" + obj.getSapMatCd();
                    // 신청
                } else {
                    return "'" + obj.getSapMatCd() + "' AS APLC_QTY_" + obj.getSapMatCd();
                }
            })
            .collect(Collectors.joining(", "));

        // PIVOT 컬럼
        String pivotColumns = sapMatCds.stream()
            .map(obj -> {
                // 배부유형코드
                String ddlvTpCd = obj.getBfsvcCsmbDdlvTpCd();
                // 고정
                if ("1".equals(ddlvTpCd)) {
                    return "NVL(T2.QTY_" + obj.getSapMatCd() + ", 0) AS QTY_" + obj.getSapMatCd();
                    // 신청
                } else {
                    return "NVL(T2.APLC_QTY_" + obj.getSapMatCd() + ", 0) AS APLC_QTY_" + obj.getSapMatCd();
                }
            })
            .collect(Collectors.joining(", "));

        searchDvo.setPivotInStr(pivotInStr);
        searchDvo.setPivotColumns(pivotColumns);

        return mapper.selectBuildingBsConsumables(searchDvo);
    }

    /**
     * 빌딩별 소모품 배부현황 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int createBuildingBsConsumables(List<CreateReq> dtos) {

        int count = 0;

        for (CreateReq dto : dtos) {
            WsnaBuildingBsConsumableDvo dvo = this.converter.mapCreateReqToWsnaBuildingBsConsumableDvo(dto);

            count += mapper.mergeBuildingBsConsumables(dvo);
        }

        return count;
    }

    /**
     * 출고요청
     * @param dtos
     * @return
     */
    @Transactional(timeout = 300)
    public int createBuildingBsConsumablesRequest(List<CreateReq> dtos) {

        int count = 0;
        // 화면에 입력 후 저장하지 않고 바로 출고요청 하는 경우를 대비해 저장 로직 태워줌
        this.createBuildingBsConsumables(dtos);

        String ostrAkRgstDt = DateUtil.getNowDayString();
        String mngtYm = dtos.get(0).mngtYm();
        List<String> strWareNos = dtos.stream().map(CreateReq::strWareNo).distinct().toList();

        for (String strWareNo : strWareNos) {
            List<WsnaBuildingBsConsumableDvo> dvos = mapper.selectBfsvcCsmbDdlvIzByMngtYm(mngtYm, strWareNo);

            if (CollectionUtils.isNotEmpty(dvos)) {

                SFLEXContext context = SFLEXContextHolder.getContext();
                UserSessionDvo userSession = context.getUserSession();
                String ostrAkNo = mapper.selectNewOstrAkNo(OSTR_AK_TP_CD_BS, ostrAkRgstDt);
                int ostrAkSn = 1;

                List<WsnaBsConsumablesAskReqDvo> reqDvos = new ArrayList<>(dvos.size());

                for (WsnaBuildingBsConsumableDvo dvo : dvos) {
                    WsnaBsConsumablesAskReqDvo reqDvo = new WsnaBsConsumablesAskReqDvo();

                    reqDvo.setOstrAkNo(ostrAkNo);
                    reqDvo.setOstrAkSn(ostrAkSn);
                    reqDvo.setOstrAkTpCd(OSTR_AK_TP_CD_BS);
                    reqDvo.setOstrAkRgstDt(ostrAkRgstDt);
                    reqDvo.setIostAkDvCd(IOST_AK_DV_CD_WELLS);
                    reqDvo.setWareMngtPrtnrNo(userSession.getEmployeeIDNumber());
                    reqDvo.setWareMngtPrtnrOgTpCd(userSession.getOgTpCd());
                    reqDvo.setLgstSppMthdCd(LGST_SPP_MTHD_CD_CRGO);
                    reqDvo.setLgstWkMthdCd(LGST_WK_MTHD_CD_BLD);
                    reqDvo.setItmPdCd(dvo.getCsmbPdCd());
                    reqDvo.setItmGdCd(ITM_GD_CD_A);
                    reqDvo.setOstrOjWareNo(OSTR_OJ_WARE_NO_PAJU);
                    reqDvo.setOstrAkQty(dvo.getBfsvcCsmbDdlvQty().intValue());
                    reqDvo.setStrWareNo(dvo.getStrWareNo());
                    reqDvo.setBldCd(dvo.getStrWareNo());

                    reqDvos.add(reqDvo);
                    ostrAkSn++;
                }

                // BS소모품배부내역 OSTR_NO, OSTR_SN UPDATE
                this.editBfsvcCsmbDdlvIzOstrAkNoSn(reqDvos, mngtYm);

                // 출고요청 및 배송요청
                count = this.bsConsumablesAskService.createBsConsumablesAsk(reqDvos, mngtYm, BFSVC_CSMB_DDLV_OJ_CD_BLD);

                // BS소모품배부상태코드 UPDATE
                this.mapper.updateBfsvcCsmbDdlvIzDdlvStatCd(strWareNo, mngtYm);
            } else {
                throw new BizException("MSG_TXT_AK_NO_DATA");
            }
        }

        return count;
    }

    /**
     * 출고요청번호, 일련번호 업데이트
     * @param reqDvos
     * @param mngtYm
     */
    @Transactional
    public void editBfsvcCsmbDdlvIzOstrAkNoSn(List<WsnaBsConsumablesAskReqDvo> reqDvos, String mngtYm) {
        for (WsnaBsConsumablesAskReqDvo reqDvo : reqDvos) {
            WsnaBuildingBsConsumableDvo dvo = this.converter
                .mapWsnaBsConsumablesAskReqDvoToWsnaBuildingBsConsumableDvo(reqDvo);
            dvo.setMngtYm(mngtYm);
            dvo.setBfsvcCsmbDdlvOjCd(BFSVC_CSMB_DDLV_OJ_CD_BLD);

            mapper.updateBfsvcCsmbDdlvIzOstrAkNoSn(dvo);
        }
    }

}
