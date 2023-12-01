package com.kyowon.sms.wells.web.service.stock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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

    public List<HashMap<String, Object>> getBuildingBsConsumables(SearchReq dto) {
        WsnaBuildingBsConsumableDvo searchDvo = converter.mapSearchReqToBuildingBsConsumable(dto);

        // 그리드 헤더상의 품목 조회
        List<WsnaBuildingBsConsumableDvo> sapMatCds = mapper.selectItems(searchDvo.getMngtYm());

        // PIVOT 조건 변환
        String pivotInStr = sapMatCds.stream().map(obj -> "'" + obj.getSapMatCd() + "' AS QTY_" + obj.getSapMatCd())
            .collect(Collectors.joining(", "));

        // PIVOT 컬럼
        String pivotColumns = sapMatCds.stream()
            .map(obj -> "NVL(QTY_" + obj.getSapMatCd() + ", 0) AS QTY_" + obj.getSapMatCd())
            .collect(Collectors.joining(", "));

        searchDvo.setPivotInStr(pivotInStr);
        searchDvo.setPivotColumns(pivotColumns);

        return mapper.selectBuildingBsConsumables(searchDvo);
    }

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

    public FindTmlmRes getBuildingBsConsumableAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectBuildingBsConsumableAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            res = mapper.selectBuildingBsConsumableAplcFirstClose(mngtYm);
        }

        return res;
    }

    @Transactional
    public int createBuildingBsConsumableAplcClose(CreateTmlmReq dto) {
        WsnaBuildingBsConsumableDvo dvo = converter.mapCreateTmlmReqToCsmbDblv(dto);

        return mapper.mergeBuildingBsConsumableAplcClose(dvo);
    }

    public List<SearchBldRes> getBuildingList() {
        return mapper.selectBuildingList();
    }

    @Transactional
    public int createBuildingBsConsumables(List<CreateReq> dtos) {
        for (CreateReq dto : dtos) {
            //if (Integer.parseInt(dto.bfsvcCsmbDdlvQty()) > 0) {
            mapper.mergeBuildingBsConsumables(dto);
            //}
        }

        return 1;
    }

    @Transactional
    public int createBuildingBsConsumablesRequest(List<CreateReq> dtos) {
        // 화면에 입력 후 저장하지 않고 바로 출고요청 하는 경우를 대비해 저장 로직 태워줌
        this.createBuildingBsConsumables(dtos);

        String ostrAkNo = null;
        String ostrAkRgstDt = DateUtil.getNowDayString();
        String mngtYm = dtos.get(0).mngtYm();
        List<String> strWareNos = dtos.stream().map(CreateReq::strWareNo).distinct().toList();

        for (String strWareNo : strWareNos) {
            List<WsnaBuildingBsConsumableDvo> dvos = mapper.selectBfsvcCsmbDdlvIzByMngtYm(mngtYm, strWareNo);

            if (!ObjectUtils.isEmpty(dvos)) {
                SFLEXContext context = SFLEXContextHolder.getContext();
                UserSessionDvo userSession = context.getUserSession();
                ostrAkNo = mapper.selectNewOstrAkNo(OSTR_AK_TP_CD_BS, ostrAkRgstDt);
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
                    reqDvo.setOstrAkQty(Integer.parseInt(dvo.getBfsvcCsmbDdlvQty()));
                    reqDvo.setStrWareNo(dvo.getStrWareNo());
                    reqDvo.setBldCd(dvo.getStrWareNo());

                    reqDvos.add(reqDvo);
                    ostrAkSn++;
                }

                // BS소모품배부내역 OSTR_NO, OSTR_SN UPDATE
                editBfsvcCsmbDdlvIzOstrAkNoSn(reqDvos, mngtYm);

                // 출고요청 및 배송요청
                bsConsumablesAskService.createBsConsumablesAsk(reqDvos, mngtYm, BFSVC_CSMB_DDLV_OJ_CD_BLD);

                // BS소모품배부상태코드 UPDATE
                editBfsvcCsmbDdlvIzDdlvStatCd(strWareNo, mngtYm);
            } else {
                throw new BizException("MSG_TXT_AK_NO_DATA");
            }
        }

        return 1;
    }

    private void editBfsvcCsmbDdlvIzOstrAkNoSn(List<WsnaBsConsumablesAskReqDvo> reqDvos, String mngtYm) {
        for (WsnaBsConsumablesAskReqDvo reqDvo : reqDvos) {
            WsnaBuildingBsConsumableDvo dvo = new WsnaBuildingBsConsumableDvo();

            dvo.setMngtYm(mngtYm);
            dvo.setCsmbPdCd(reqDvo.getItmPdCd());
            dvo.setBfsvcCsmbDdlvOjCd("3");
            dvo.setStrWareNo(reqDvo.getStrWareNo());
            dvo.setOstrAkNo(reqDvo.getOstrAkNo());
            dvo.setOstrAkSn(reqDvo.getOstrAkSn());

            mapper.updateBfsvcCsmbDdlvIzOstrAkNoSn(dvo);
        }
    }

    private void editBfsvcCsmbDdlvIzDdlvStatCd(String strWareNo, String mngtYm) {
        // 품목별 단건 update에서 매니저별 일괄 update로 변경
        mapper.updateBfsvcCsmbDdlvIzDdlvStatCd(strWareNo, mngtYm);
    }

    public List<SearchLmQtyRes> getApplicationLimitQty(String mngtYm) {
        return mapper.selectApplicationLimitQty(mngtYm);
    }
}
