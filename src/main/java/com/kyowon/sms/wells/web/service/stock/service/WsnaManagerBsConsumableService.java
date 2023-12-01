package com.kyowon.sms.wells.web.service.stock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaManagerBsConsumableConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsConsumablesAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaManagerBsConsumableDvo;
import com.kyowon.sms.wells.web.service.stock.ivo.EAI_CBDO1007.response.RealTimeGradeStockResIvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBuildingBsConsumableMapper;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaManagerBsConsumableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContext;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaManagerBsConsumableService {
    private final WsnaManagerBsConsumableMapper mapper;
    private final WsnaBuildingBsConsumableMapper bldMapper;
    private final WsnaManagerBsConsumableConverter converter;
    private final WsnaBsConsumablesAskService bsConsumablesAskService;
    private final WsnaItemStockItemizationService stockService;

    private static final String OSTR_AK_TP_CD_BS = "380"; // 출고요청유형코드 : BS소모품배부
    private static final String IOST_AK_DV_CD_WELLS = "WE";
    private static final String LGST_SPP_MTHD_CD_CRGO = "8"; // 물류배송방식코드 6 -> 8로 변경(사업장배송)
    private static final String LGST_WK_MTHD_CD_MNGER = "WE07";
    private static final String ITM_GD_CD_A = "A";
    private static final String OSTR_OJ_WARE_NO_PAJU = "100002";
    private static final String BFSVC_CSMB_DDLV_OJ_CD_MNGER = "2";
    private static final String SAP_PLNT_CD = "2108"; // 교원프라퍼티파주물류
    private static final String PAJU_SAP_SAVE_LCT_CD = "21082082"; // 파주창고

    /**
     * 고정, 신청품목 조회(그리드 헤더 표시용)
     * @param mngtYm
     * @return
     */
    public List<SearchItmRes> selectItems(String mngtYm) {
        List<WsnaManagerBsConsumableDvo> dvos = mapper.selectItems(mngtYm);

        if (!ObjectUtils.isEmpty(dvos)) {
            // 그리드 내 품목명에 파주재고 표시를 위해 로직 추가 - 23.11.17
            // 파주재고 set
            List<String> itmPds = dvos.stream().map(WsnaManagerBsConsumableDvo::getCsmbPdCd).toList();

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
     * 빌딩 목록 조회
     * @return
     */
    public List<WsnaBuildingBsConsumableDto.SearchBldRes> selectBuildings() {
        return bldMapper.selectBuildingList();
    }

    /**
     * 소모품 배부현황 목록 조회
     * @param dto
     * @return
     */
    public List<HashMap<String, Object>> getManagerBsConsumable(SearchReq dto) {
        WsnaManagerBsConsumableDvo searchDvo = converter.mapSearchReqToManagerBsConsumable(dto);

        // 그리드 헤더상의 품목 조회
        List<WsnaManagerBsConsumableDvo> sapMatCds = mapper.selectItems(searchDvo.getMngtYm());

        // PIVOT 조건 변환
        String pivotInStr = sapMatCds.stream().map(obj -> "'" + obj.getSapMatCd() + "' AS QTY_" + obj.getSapMatCd())
            .collect(Collectors.joining(", "));

        // PIVOT 컬럼
        String pivotColumns = sapMatCds.stream()
            .map(obj -> "NVL(QTY_" + obj.getSapMatCd() + ", 0) AS QTY_" + obj.getSapMatCd())
            .collect(Collectors.joining(", "));

        searchDvo.setPivotInStr(pivotInStr);
        searchDvo.setPivotColumns(pivotColumns);

        return mapper.selectManagerBsConsumables(searchDvo);
    }

    /**
     * 등록기간 조회
     * @param mngtYm
     * @return
     */
    public FindTmlmRes getManagerBsConsumableAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectManagerBsConsumableAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            res = mapper.selectManagerBsConsumableAplcFirstClose(mngtYm);
        }

        return res;
    }

    /**
     * 등록기간 설정
     * @param dto
     * @return
     */
    @Transactional
    public int createManagerBsConsumableAplcClose(CreateTmlmReq dto) {
        WsnaManagerBsConsumableDvo dvo = converter.mapCreateTmlmReqToNewManagerBsConsumable(dto);

        return mapper.mergeManagerBsConsumableAplcClose(dvo);
    }

    /**
     * 소모품 배부현황 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int createManagerBsConsumables(List<CreateReq> dtos) {
        List<WsnaManagerBsConsumableDvo> dvos = converter.mapCreateReqToNewManagerBsConsumable(dtos);

        for (WsnaManagerBsConsumableDvo dvo : dvos) {
            //if (Integer.parseInt(dvo.getBfsvcCsmbDdlvQty()) > 0) {
            mapper.mergeManagerBsConsumables(dvo);
            //}
        }

        return 1;
    }

    /**
     * 소모품 출고요청
     * @param dtos
     * @return
     */
    @Transactional
    public int createManagerBsConsumablesRequest(List<CreateReq> dtos) {
        // 화면에 입력 후 저장하지 않고 바로 출고요청 하는 경우를 대비해 저장 로직 태워줌
        this.createManagerBsConsumables(dtos);

        String ostrAkNo = null;
        String ostrAkRgstDt = DateUtil.getNowDayString();
        String mngtYm = dtos.get(0).mngtYm();
        List<String> strWareNos = dtos.stream().map(CreateReq::strWareNo).distinct().toList();
        List<WsnaBsConsumablesAskReqDvo> tempReqDvos = new ArrayList<>();

        for (String strWareNo : strWareNos) {
            List<WsnaManagerBsConsumableDvo> dvos = mapper.selectBfsvcCsmbDdlvIzByMngtYm(mngtYm, strWareNo);

            if (!ObjectUtils.isEmpty(dvos)) {
                SFLEXContext context = SFLEXContextHolder.getContext();
                UserSessionDvo userSession = context.getUserSession();
                ostrAkNo = mapper.selectNewOstrAkNo(OSTR_AK_TP_CD_BS, ostrAkRgstDt);
                int ostrAkSn = 1;

                for (WsnaManagerBsConsumableDvo dvo : dvos) {
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
                    reqDvo.setOstrAkQty(Integer.parseInt(dvo.getBfsvcCsmbDdlvQty()));
                    reqDvo.setStrWareNo(dvo.getStrWareNo());
                    reqDvo.setBldCd(dvo.getBldCd());

                    tempReqDvos.add(reqDvo);
                    ostrAkSn++;
                }

                // BS소모품배부내역 OSTR_NO, OSTR_SN UPDATE
                editBfsvcCsmbDdlvIzOstrAkNoSn(tempReqDvos, mngtYm);

                // 출고요청 및 배송요청
                //bsConsumablesAskService.createBsConsumablesAsk(reqDvos, mngtYm, BFSVC_CSMB_DDLV_OJ_CD_MNGER);

                // BS소모품배부상태코드 UPDATE
                //editBfsvcCsmbDdlvIzDdlvStatCd(strWareNo, mngtYm);
            } else {
                throw new BizException("MSG_TXT_AK_NO_DATA");
            }
        }

        // 출고요청 및 배송요청
        List<String> bldCds = tempReqDvos.stream().map(WsnaBsConsumablesAskReqDvo::getBldCd).distinct().toList();

        for (String bldCd : bldCds) {
            List<WsnaBsConsumablesAskReqDvo> askReqDvos = tempReqDvos.stream()
                .filter(x -> bldCd.equals(x.getBldCd())).toList();
            bsConsumablesAskService.createBsConsumablesAsk(askReqDvos, mngtYm, BFSVC_CSMB_DDLV_OJ_CD_MNGER);
        }

        // BS소모품배부상태코드 UPDATE
        editBfsvcCsmbDdlvIzDdlvStatCd(strWareNos, mngtYm);

        return 1;
    }

    /**
     * BS소모품배부내역 수정(출고요청번호, 출고요청일련번호)
     * @param reqDvos
     * @param mngtYm
     */
    private void editBfsvcCsmbDdlvIzOstrAkNoSn(List<WsnaBsConsumablesAskReqDvo> reqDvos, String mngtYm) {
        for (WsnaBsConsumablesAskReqDvo reqDvo : reqDvos) {
            WsnaManagerBsConsumableDvo dvo = new WsnaManagerBsConsumableDvo();

            dvo.setMngtYm(mngtYm);
            dvo.setCsmbPdCd(reqDvo.getItmPdCd());
            dvo.setBfsvcCsmbDdlvOjCd("2");
            dvo.setStrWareNo(reqDvo.getStrWareNo());
            dvo.setOstrAkNo(reqDvo.getOstrAkNo());
            dvo.setOstrAkSn(reqDvo.getOstrAkSn());

            mapper.updateBfsvcCsmbDdlvIzOstrAkNoSn(dvo);
        }
    }

    /**
     * BS소모품 배부내역 수정(배부상태코드 30으로)
     * @param strWareNos
     * @param mngtYm
     */
    private void editBfsvcCsmbDdlvIzDdlvStatCd(List<String> strWareNos, String mngtYm) {
        // 품목별 단건 update에서 매니저별 일괄 update로 변경
        mapper.updateBfsvcCsmbDdlvIzDdlvStatCd(strWareNos, mngtYm);
    }

    /**
     * 신청제한수량 조회
     * @param mngtYm
     * @return
     */
    public List<SearchLmQtyRes> getApplicationLimitQty(String mngtYm) {
        return mapper.selectApplicationLimitQty(mngtYm);
    }
}
