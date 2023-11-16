package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaManagerBsConsumableConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaManagerBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsConsumablesAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaManagerBsConsumableDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBuildingBsConsumableMapper;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaManagerBsConsumableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContext;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WsnaManagerBsConsumableService {
    private final WsnaManagerBsConsumableMapper mapper;
    private final WsnaBuildingBsConsumableMapper bldMapper;
    private final WsnaManagerBsConsumableConverter converter;
    private final WsnaBsConsumablesAskService bsConsumablesAskService;

    private static final String OSTR_AK_TP_CD_BS = "380"; // 출고요청유형코드 : BS소모품배부
    private static final String IOST_AK_DV_CD_WELLS = "WE";
    private static final String LGST_SPP_MTHD_CD_CRGO = "8"; // 물류배송방식코드 6 -> 8로 변경(사업장배송)
    private static final String LGST_WK_MTHD_CD_MNGER = "WE07";
    private static final String ITM_GD_CD_A = "A";
    private static final String OSTR_OJ_WARE_NO_PAJU = "100002";
    private static final String BFSVC_CSMB_DDLV_OJ_CD_MNGER = "2";

    public List<SearchItmRes> selectItems(String mngtYm) {
        return mapper.selectItems(mngtYm);
    }

    public List<WsnaBuildingBsConsumableDto.SearchBldRes> selectBuildings() {
        return bldMapper.selectBuildingList();
    }

    public List<SearchRes> getManagerBsConsumable(SearchReq dto) {
        List<WsnaManagerBsConsumableDvo> bldInfos = mapper.selectBuildings(dto);
        List<WsnaManagerBsConsumableDvo> bldAndItemsInfos = new ArrayList<>();
        Iterator<WsnaManagerBsConsumableDvo> it = bldInfos.iterator();

        while (it.hasNext()) {
            WsnaManagerBsConsumableDvo bfBldInfo = it.next();
            WsnaManagerBsConsumableDvo aftBldInfo;
            List<WsnaManagerBsConsumableDvo> rgstItemInfos;
            List<WsnaManagerBsConsumableDvo> unrgItemInfos;

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

            for (WsnaManagerBsConsumableDvo unrgItemInfo : unrgItemInfos) {
                switch (unrgItemInfo.getBfsvcCsmbDdlvTpCd()) {
                    case "1" -> { // 고정품목
                        int i = 0;

                        for (WsnaManagerBsConsumableDvo rgstItemInfo : rgstItemInfos) {
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

                        for (WsnaManagerBsConsumableDvo rgstItemInfo : rgstItemInfos) {
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

        List<SearchRes> rtnDto = converter.mapAllDvoToSearchRes(bldAndItemsInfos);

        return rtnDto;
    }

    public FindTmlmRes getManagerBsConsumableAplcClose(String mngtYm) {
        FindTmlmRes res = mapper.selectManagerBsConsumableAplcClose(mngtYm);

        if (ObjectUtils.isEmpty(res)) {
            res = mapper.selectManagerBsConsumableAplcFirstClose(mngtYm);
        }

        return res;
    }

    @Transactional
    public int createManagerBsConsumableAplcClose(CreateTmlmReq dto) {
        WsnaManagerBsConsumableDvo dvo = converter.mapCreateTmlmReqToNewManagerBsConsumable(dto);

        return mapper.mergeManagerBsConsumableAplcClose(dvo);
    }

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

    private void editBfsvcCsmbDdlvIzDdlvStatCd(List<String> strWareNos, String mngtYm) {
        // 품목별 단건 update에서 매니저별 일괄 update로 변경
        mapper.updateBfsvcCsmbDdlvIzDdlvStatCd(strWareNos, mngtYm);
    }
}
