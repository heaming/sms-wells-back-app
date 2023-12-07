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

import com.kyowon.sms.wells.web.service.stock.converter.WsnaNewManagerBsConsumableConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsConsumablesAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNewManagerBsConsumableDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBuildingBsConsumableMapper;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaNewManagerBsConsumableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContext;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0011M01 소모품 배부현황(신입) 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-12-05
 */

@Service
@RequiredArgsConstructor
public class WsnaNewManagerBsConsumableService {

    private final WsnaNewManagerBsConsumableMapper mapper;
    private final WsnaBuildingBsConsumableMapper bldMapper;
    private final WsnaNewManagerBsConsumableConverter converter;
    private final WsnaBsConsumablesAskService bsConsumablesAskService;
    private final WsnaItemStockItemizationService stockService;

    private static final String OSTR_AK_TP_CD_BS = "380"; // 출고요청유형코드 : BS소모품배부
    private static final String IOST_AK_DV_CD_WELLS = "WE";
    private static final String LGST_SPP_MTHD_CD_CRGO = "8"; // 물류배송방식코드 6 -> 8로 변경(사업장배송)
    private static final String LGST_WK_MTHD_CD_MNGER = "WE07";
    private static final String ITM_GD_CD_A = "A";
    private static final String OSTR_OJ_WARE_NO_PAJU = "100002";
    private static final String BFSVC_CSMB_DDLV_OJ_CD_NEW_MNGER = "1";
    private static final String SAP_PLNT_CD = "2108"; // 교원프라퍼티파주물류
    private static final String PAJU_SAP_SAVE_LCT_CD = "21082082"; // 파주창고

    /**
     * 빌딩 목록 조회
     *
     * @param mngtYm
     * @return
     */
    public List<SearchBldRes> selectBuildings(String mngtYm) {
        return bldMapper.selectBuildingList(mngtYm);
    }

    /**
     * 활동물품 조회 (그리드 헤더 표시용)
     *
     * @param mngtYm
     * @return
     */
    public List<SearchItmRes> getItems(String mngtYm) {
        List<WsnaNewManagerBsConsumableDvo> dvos = mapper.selectItems(mngtYm);

        if (!ObjectUtils.isEmpty(dvos)) {
            // 그리드 내 품목명에 파주재고 표시를 위해 로직 추가 - 23.11.17
            // 파주재고 set
            List<String> itmPds = dvos.stream().map(WsnaNewManagerBsConsumableDvo::getCsmbPdCd).toList();

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
     * 신입 소모품 신청 등록기간 조회
     *
     * @param mngtYm
     * @return
     */
    public FindTmlmRes getNewManagerBsConsumableAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectNewManagerBsConsumableAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            // 업무마감 데이터가 없을 경우 달력의 시작, 종료일자를 조회 (휴일 제외)
            res = mapper.selectNewManagerBsConsumableAplcFirstClose(mngtYm);
        }

        return res;
    }

    /**
     * 등록기간 설정
     *
     * @param dto
     * @return
     */
    @Transactional
    public int createBuildingBsConsumableAplcClose(CreateTmlmReq dto) {
        WsnaNewManagerBsConsumableDvo dvo = converter.mapCreateTmlmReqToNewManagerBsConsumable(dto);

        return mapper.mergeNewManagerBsConsumableAplcClose(dvo);
    }

    /**
     * 신청제한수량 조회
     *
     * @param mngtYm
     * @return
     */
    public List<SearchLmQtyRes> getApplicationLimitQty(String mngtYm) {
        return mapper.selectApplicationLimitQty(mngtYm);
    }

    /**
     * 신입 소모품 배부현황 조회
     *
     * @param dto
     * @return
     */
    public List<HashMap<String, Object>> getNewManagerBsConsumable(SearchReq dto) {
        WsnaNewManagerBsConsumableDvo searchDvo = converter.mapSearchReqToNewManagerBsConsumable(dto);

        String mngtYm = searchDvo.getMngtYm();

        // 그리드 헤더상의 품목 조회
        List<WsnaNewManagerBsConsumableDvo> sapMatCds = mapper.selectItems(searchDvo.getMngtYm());

        String mngtYear = mngtYm.substring(0, 4);
        String mngtMonth = mngtYm.substring(4);
        mngtMonth = mngtMonth.startsWith("0") ? " " + mngtMonth.substring(1) : mngtMonth;
        // {0}년 {1}월 배부기준이 없습니다.
        BizAssert.isFalse(
            CollectionUtils.isEmpty(sapMatCds), "MSG_ALT_THM_DATA_NOT_EXST", new String[] {mngtYear, mngtMonth}
        );

        // PIVOT 조건 변환
        String pivotInStr = sapMatCds.stream().map(obj -> {
            String ddlvTpCd = obj.getBfsvcCsmbDdlvTpCd();
            String sapMatCd = obj.getSapMatCd();
            // 고정
            if ("1".equals(ddlvTpCd)) {
                return "'" + sapMatCd + "' AS QTY_" + sapMatCd;
                // 신청
            } else {
                return "'" + sapMatCd + "' AS APLC_QTY_" + sapMatCd;
            }
        }).collect(Collectors.joining(", "));

        // PIVOT 컬럼
        String pivotColumns = sapMatCds.stream()
            .map(obj -> {
                // 배부유형코드
                String ddlvTpCd = obj.getBfsvcCsmbDdlvTpCd();
                // SAP코드
                String sapMatCd = obj.getSapMatCd();
                // 고정
                if ("1".equals(ddlvTpCd)) {
                    return "NVL(T2.QTY_" + sapMatCd + ", 0) AS QTY_" + sapMatCd;
                    // 신청
                } else {
                    return "NVL(T2.APLC_QTY_" + sapMatCd + ", 0) AS APLC_QTY_" + sapMatCd;
                }
            }).collect(Collectors.joining(", "));

        searchDvo.setPivotInStr(pivotInStr);
        searchDvo.setPivotColumns(pivotColumns);

        return mapper.selectNewManagerBsConsumables(searchDvo);
    }

    /**
     * 신입 소모품 배부내역 저장
     *
     * @param dtos
     * @return
     */
    @Transactional
    public int createNewManagerBsConsumables(List<CreateReq> dtos) {
        int count = 0;

        List<WsnaNewManagerBsConsumableDvo> dvos = converter.mapAllCreateReqToNewManagerBsConsumable(dtos);

        for (WsnaNewManagerBsConsumableDvo dvo : dvos) {
            count += mapper.mergeNewManagerBsConsumables(dvo);
        }

        return count;
    }

    /**
     * 신입 소모품 배부내역 출고요청
     * @param dtos
     * @return
     */
    @Transactional(timeout = 300)
    public int createNewManagerBsConsumablesRequest(List<CreateReq> dtos) {
        int count = 0;

        // 화면에 입력 후 저장하지 않고 바로 출고요청 하는 경우를 대비해 저장 로직 태워줌
        this.createNewManagerBsConsumables(dtos);

        String ostrAkRgstDt = DateUtil.getNowDayString();
        String mngtYm = dtos.get(0).mngtYm();
        List<String> strWareNos = dtos.stream().map(CreateReq::strWareNo).distinct().toList();
        List<WsnaBsConsumablesAskReqDvo> tempReqDvos = new ArrayList<>();

        for (String strWareNo : strWareNos) {
            List<WsnaNewManagerBsConsumableDvo> dvos = mapper.selectBfsvcCsmbDdlvIzByMngtYm(mngtYm, strWareNo);

            if (CollectionUtils.isNotEmpty(dvos)) {
                SFLEXContext context = SFLEXContextHolder.getContext();
                UserSessionDvo userSession = context.getUserSession();

                String ostrAkNo = mapper.selectNewOstrAkNo(OSTR_AK_TP_CD_BS, ostrAkRgstDt);
                int ostrAkSn = 1;

                for (WsnaNewManagerBsConsumableDvo dvo : dvos) {
                    WsnaBsConsumablesAskReqDvo reqDvo = new WsnaBsConsumablesAskReqDvo();

                    reqDvo.setOstrAkNo(ostrAkNo);
                    reqDvo.setOstrAkSn(ostrAkSn);
                    reqDvo.setOstrAkTpCd(OSTR_AK_TP_CD_BS);
                    reqDvo.setOstrAkRgstDt(ostrAkRgstDt);
                    reqDvo.setIostAkDvCd(IOST_AK_DV_CD_WELLS);
                    reqDvo.setWareMngtPrtnrNo(userSession.getEmployeeIDNumber());
                    reqDvo.setWareMngtPrtnrOgTpCd(userSession.getOgTpCd());
                    reqDvo.setLgstSppMthdCd(LGST_SPP_MTHD_CD_CRGO);
                    reqDvo.setLgstWkMthdCd(LGST_WK_MTHD_CD_MNGER);
                    reqDvo.setItmPdCd(dvo.getCsmbPdCd());
                    reqDvo.setItmGdCd(ITM_GD_CD_A);
                    reqDvo.setOstrOjWareNo(OSTR_OJ_WARE_NO_PAJU);
                    reqDvo.setStrWareNo(dvo.getStrWareNo());
                    reqDvo.setOstrAkQty(dvo.getBfsvcCsmbDdlvQty().intValue());
                    reqDvo.setBldCd(dvo.getBldCd());

                    tempReqDvos.add(reqDvo);
                    ostrAkSn++;
                }

                // BS소모품배부내역 OSTR_NO, OSTR_SN UPDATE
                this.editBfsvcCsmbDdlvIzOstrAkNoSn(tempReqDvos, mngtYm);
            } else {
                throw new BizException("MSG_TXT_AK_NO_DATA");
            }
        }

        // 출고요청 및 배송요청
        List<String> bldCds = tempReqDvos.stream().map(WsnaBsConsumablesAskReqDvo::getBldCd).distinct().toList();

        for (String bldCd : bldCds) {
            List<WsnaBsConsumablesAskReqDvo> askReqDvos = tempReqDvos.stream()
                .filter(x -> bldCd.equals(x.getBldCd())).toList();
            count += bsConsumablesAskService
                .createBsConsumablesAsk(askReqDvos, mngtYm, BFSVC_CSMB_DDLV_OJ_CD_NEW_MNGER);
        }

        // BS소모품배부상태코드 UPDATE
        mapper.updateBfsvcCsmbDdlvIzDdlvStatCd(strWareNos, mngtYm);

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
            WsnaNewManagerBsConsumableDvo dvo = this.converter
                .mapWsnaBsConsumablesAskReqDvoToWsnaNewManagerBsConsumableDvo(reqDvo);

            dvo.setMngtYm(mngtYm);
            dvo.setBfsvcCsmbDdlvOjCd(BFSVC_CSMB_DDLV_OJ_CD_NEW_MNGER);

            mapper.updateBfsvcCsmbDdlvIzOstrAkNoSn(dvo);
        }
    }

}
