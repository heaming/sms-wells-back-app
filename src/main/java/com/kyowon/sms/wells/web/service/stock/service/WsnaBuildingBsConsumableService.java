package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaBuildingBsConsumableConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBuildingBsConsumableDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBsConsumablesAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaBuildingBsConsumableDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBuildingBsConsumableMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContext;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsnaBuildingBsConsumableService {
    private final WsnaBuildingBsConsumableMapper mapper;
    private final WsnaBuildingBsConsumableConverter converter;
    private final WsnaBsConsumablesAskService bsConsumablesAskService;

    private static final String OSTR_AK_TP_CD_BS = "380"; // 출고요청유형코드 : BS소모품배부
    private static final String IOST_AK_DV_CD_WELLS = "WE";
    private static final String LGST_SPP_MTHD_CD_CRGO = "8"; // 물류배송방식코드 6 -> 8로 변경(사업장배송)
    private static final String LGST_WK_MTHD_CD_BLD = "WE08";
    private static final String ITM_GD_CD_A = "A";
    private static final String OSTR_OJ_WARE_NO_PAJU = "100002";
    private static final String BFSVC_CSMB_DDLV_OJ_CD_BLD = "3";

    public List<SearchRes> getBuildingBsConsumables(SearchReq dto) {
        // 빌딩정보 조회
        List<WsnaBuildingBsConsumableDvo> bldInfos = mapper.selectBuildings(dto);

        if (!ObjectUtils.isEmpty(bldInfos)) {
            List<WsnaBuildingBsConsumableDvo> bldAndItemsInfos = new ArrayList<>();
            Iterator<WsnaBuildingBsConsumableDvo> it = bldInfos.iterator();

            while (it.hasNext()) {
                WsnaBuildingBsConsumableDvo bftBldInfo = it.next();
                WsnaBuildingBsConsumableDvo aftBldInfo;
                List<WsnaBuildingBsConsumableDvo> rgstItemInfos;
                List<WsnaBuildingBsConsumableDvo> unrgItemInfos;

                aftBldInfo = bftBldInfo;

                List<String> fxnItemQtys = new ArrayList<>();
                List<String> aplcItemQtys = new ArrayList<>();

                // 빌딩 별 기등록 품목 수량 조회
                rgstItemInfos = mapper.selectItemQtys(dto.mngtYm(), bftBldInfo.getBldCd());

                // 빌딩 별 미등록 품목 계산 수량 조회
                unrgItemInfos = mapper.selectItemFirstQtys(dto.mngtYm(), bftBldInfo.getBldCd());

                String mngtYear = dto.mngtYm().substring(0, 4);
                String mngtMonth = "";
                mngtMonth = dto.mngtYm().substring(4);
                mngtMonth = mngtMonth.startsWith("0") ? " " + mngtMonth.substring(1) : mngtMonth;

                BizAssert.isTrue(
                    !ObjectUtils.isEmpty(unrgItemInfos), "MSG_ALT_BFSVC_CSMB_DDLV_BASE",
                    new String[] {mngtYear, mngtMonth}
                );

                for (WsnaBuildingBsConsumableDvo unrgItemInfo : unrgItemInfos) {
                    switch (unrgItemInfo.getBfsvcCsmbDdlvTpCd()) {
                        case "1" -> { // 고정품목
                            int i = 0;

                            for (WsnaBuildingBsConsumableDvo rgstItemInfo : rgstItemInfos) {
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

                            for (WsnaBuildingBsConsumableDvo rgstItemInfo : rgstItemInfos) {
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
                    ObjectUtils.isEmpty(rgstItemInfos) ? unrgItemInfos.get(0).getReqYn()
                        : rgstItemInfos.get(0).getReqYn()
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

        List<SearchRes> rtnDto = converter.mapAllDvoToSearchRes(bldInfos);

        return rtnDto;
    }

    public List<SearchItmRes> getItems(String mngtYm) {
        return mapper.selectItems(mngtYm);
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
}
