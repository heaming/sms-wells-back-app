package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaNewManagerBsConsumableConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.SearchBldRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaNewManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsConsumablesAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaNewManagerBsConsumableDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBuildingBsConsumableMapper;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaNewManagerBsConsumableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContext;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaNewManagerBsConsumableService {
    private final WsnaNewManagerBsConsumableMapper mapper;
    private final WsnaBuildingBsConsumableMapper bldMapper;
    private final WsnaNewManagerBsConsumableConverter converter;
    private final WsnaBsConsumablesAskService bsConsumablesAskService;

    private static final String OSTR_AK_TP_CD_BS = "380"; // 출고요청유형코드 : BS소모품배부
    private static final String IOST_AK_DV_CD_WELLS = "WE";
    private static final String LGST_SPP_MTHD_CD_CRGO = "8"; // 물류배송방식코드 6 -> 8로 변경(사업장배송)
    private static final String LGST_WK_MTHD_CD_MNGER = "WE07";
    private static final String ITM_GD_CD_A = "A";
    private static final String OSTR_OJ_WARE_NO_PAJU = "100002";
    private static final String BFSVC_CSMB_DDLV_OJ_CD_NEW_MNGER = "1";

    public List<SearchItmRes> getItems(String mngtYm) {
        return mapper.selectItems(mngtYm);
    }

    public List<SearchBldRes> selectBuildings() {
        return bldMapper.selectBuildingList();
    }

    //public List<SearchRes> getNewManagerBsConsumables(SearchReq dto) {
    //    return null;
    //}

    public List<SearchRes> getNewManagerBsConsumable(SearchReq dto) {
        List<WsnaNewManagerBsConsumableDvo> bldInfos = mapper.selectBuildings(dto);
        List<WsnaNewManagerBsConsumableDvo> bldAndItemsInfos = new ArrayList<>();
        Iterator<WsnaNewManagerBsConsumableDvo> it = bldInfos.iterator();

        while (it.hasNext()) {
            WsnaNewManagerBsConsumableDvo bfBldInfo = it.next();
            WsnaNewManagerBsConsumableDvo aftBldInfo;
            List<WsnaNewManagerBsConsumableDvo> rgstItemInfos;
            List<WsnaNewManagerBsConsumableDvo> unrgItemInfos;

            aftBldInfo = bfBldInfo;

            List<String> fxnItemQtys = new ArrayList<>();
            List<String> aplcItemQtys = new ArrayList<>();

            // 매니저 별 기등록 품목 수량 조회
            rgstItemInfos = mapper.selectItemQtys(dto.mngtYm(), bfBldInfo.getPrtnrNo());

            // 매니저 별 미등록 품목 계산 수량 조회
            unrgItemInfos = mapper.selectItemFirstQtys(dto.mngtYm(), bfBldInfo.getPrtnrNo());

            String mngtYear = dto.mngtYm().substring(0, 4);
            String mngtMonth = "";
            mngtMonth = dto.mngtYm().substring(4);
            mngtMonth = mngtMonth.startsWith("0") ? " " + mngtMonth.substring(1) : mngtMonth;

            BizAssert.isTrue(
                !ObjectUtils.isEmpty(unrgItemInfos), "MSG_ALT_BFSVC_CSMB_DDLV_BASE", new String[] {mngtYear, mngtMonth}
            );

            for (WsnaNewManagerBsConsumableDvo unrgItemInfo : unrgItemInfos) {
                switch (unrgItemInfo.getBfsvcCsmbDdlvTpCd()) {
                    case "1" -> { // 고정품목
                        int i = 0;

                        for (WsnaNewManagerBsConsumableDvo rgstItemInfo : rgstItemInfos) {
                            // 기등록 품목에 대한 갯수가 있다면
                            if (unrgItemInfo.getFxnPdCd().equals(rgstItemInfo.getCsmbPdCd())) {
                                fxnItemQtys.add(rgstItemInfo.getBfsvcCsmbDdlvQty());
                                i++;
                            }
                        }

                        if (i == 0) { // 기등록 품목이 없으면 미등록 품목 수량을 넣어준다
                            fxnItemQtys.add(unrgItemInfo.getFxnDdlvUnitQty());
                        }
                    }

                    case "2" -> { // 신청품목
                        int i = 0;

                        for (WsnaNewManagerBsConsumableDvo rgstItemInfo : rgstItemInfos) {
                            // 기등록 품목에 대한 갯수가 있다면
                            if (unrgItemInfo.getAplcPdCd().equals(rgstItemInfo.getCsmbPdCd())) {
                                aplcItemQtys.add(rgstItemInfo.getBfsvcCsmbDdlvQty());

                                i++;
                            }
                        }

                        if (i == 0) { // 기등록 품목이 없으면 미등록 품목 수량을 넣어준다
                            aplcItemQtys.add(unrgItemInfo.getAplcDdlvUnitQty());
                        }
                    }
                }
            }

            aftBldInfo.setReqYn(
                ObjectUtils.isEmpty(rgstItemInfos) ? unrgItemInfos.get(0).getReqYn() : rgstItemInfos.get(0).getReqYn()
            );
            aftBldInfo.setBfsvcCsmbDdlvStatCd(
                ObjectUtils.isEmpty(rgstItemInfos) ? "" : rgstItemInfos.get(0).getBfsvcCsmbDdlvStatCd()
            );
            aftBldInfo.setFxnQtys(fxnItemQtys); // 고정품목
            aftBldInfo.setAplcQtys(aplcItemQtys); // 신청품목
            bldAndItemsInfos.add(aftBldInfo);
        }

        List<SearchRes> rtnDto = converter.mapAllDvoToListSearchRes(bldAndItemsInfos);

        return rtnDto;
    }

    public FindTmlmRes getNewManagerBsConsumableAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectNewManagerBsConsumableAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            res = mapper.selectNewManagerBsConsumableAplcFirstClose(mngtYm);
        }

        return res;
    }

    @Transactional
    public int createBuildingBsConsumableAplcClose(CreateTmlmReq dto) {
        WsnaNewManagerBsConsumableDvo dvo = converter.mapCreateTmlmReqToNewManagerBsConsumable(dto);

        return mapper.mergeNewManagerBsConsumableAplcClose(dvo);
    }

    @Transactional
    public int createNewManagerBsConsumables(List<CreateReq> dtos) {
        List<WsnaNewManagerBsConsumableDvo> dvos = converter.mapCreateReqToNewManagerBsConsumable(dtos);

        for (WsnaNewManagerBsConsumableDvo dvo : dvos) {
            if (Integer.parseInt(dvo.getBfsvcCsmbDdlvQty()) > 0) {
                mapper.mergeNewManagerBsConsumables(dvo);
            }
        }

        return 1;
    }

    @Transactional
    public int createNewManagerBsConsumablesRequest(List<CreateOstrReq> dtos) {
        String ostrAkNo = null;
        String ostrAkRgstDt = DateUtil.getNowDayString();
        String mngtYm = dtos.get(0).mngtYm();
        List<String> strWareNos = dtos.stream().map(CreateOstrReq::strWareNo).distinct().toList();
        List<WsnaBsConsumablesAskReqDvo> tempReqDvos = new ArrayList<>();

        for (String strWareNo : strWareNos) {
            List<WsnaNewManagerBsConsumableDvo> dvos = mapper.selectBfsvcCsmbDdlvIzByMngtYm(mngtYm, strWareNo);

            if (!ObjectUtils.isEmpty(dvos)) {
                SFLEXContext context = SFLEXContextHolder.getContext();
                UserSessionDvo userSession = context.getUserSession();
                ostrAkNo = mapper.selectNewOstrAkNo(OSTR_AK_TP_CD_BS, ostrAkRgstDt);
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
                    reqDvo.setOstrAkQty(Integer.parseInt(dvo.getBfsvcCsmbDdlvQty()));
                    reqDvo.setBldCd(dvo.getBldCd());

                    tempReqDvos.add(reqDvo);
                    ostrAkSn++;
                }

                // BS소모품배부내역 OSTR_NO, OSTR_SN UPDATE
                editBfsvcCsmbDdlvIzOstrAkNoSn(tempReqDvos, mngtYm);
            } else {
                throw new BizException("MSG_TXT_AK_NO_DATA");
            }
        }

        // 출고요청 및 배송요청
        List<String> bldCds = tempReqDvos.stream().map(WsnaBsConsumablesAskReqDvo::getBldCd).distinct().toList();

        for (String bldCd : bldCds) {
            List<WsnaBsConsumablesAskReqDvo> askReqDvos = tempReqDvos.stream()
                .filter(x -> bldCd.equals(x.getBldCd())).toList();
            bsConsumablesAskService.createBsConsumablesAsk(askReqDvos, mngtYm, BFSVC_CSMB_DDLV_OJ_CD_NEW_MNGER);
        }

        // BS소모품배부상태코드 UPDATE
        editBfsvcCsmbDdlvIzDdlvStatCd(strWareNos, mngtYm);

        return 1;
    }

    private void editBfsvcCsmbDdlvIzOstrAkNoSn(List<WsnaBsConsumablesAskReqDvo> reqDvos, String mngtYm) {
        for (WsnaBsConsumablesAskReqDvo reqDvo : reqDvos) {
            WsnaNewManagerBsConsumableDvo dvo = new WsnaNewManagerBsConsumableDvo();

            dvo.setMngtYm(mngtYm);
            dvo.setCsmbPdCd(reqDvo.getItmPdCd());
            dvo.setBfsvcCsmbDdlvOjCd("1");
            dvo.setStrWareNo(reqDvo.getStrWareNo());
            dvo.setOstrAkNo(reqDvo.getOstrAkNo());
            dvo.setOstrAkSn(reqDvo.getOstrAkSn());

            mapper.updateBfsvcCsmbDdlvIzOstrAkNoSn(dvo);
        }
    }

    private void editBfsvcCsmbDdlvIzDdlvStatCd(List<String> strWareNos, String mngtYm) {
        // 품목별 단건 update에서 매니저별 일괄 update로 변경
        mapper.updateBfsvcCsmbDdlvIzDdlvStatCd(strWareNos, mngtYm);
    }
}
