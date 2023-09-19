package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
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

    public List<WsnaBuildingBsConsumableDto.SearchBldRes> selectBuildings(String mngtYm) {
        return bldMapper.selectBuildingList(mngtYm);
    }

    public PagingResult<SearchRes> getManagerBsConsumablePages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WsnaManagerBsConsumableDvo> bldInfos = mapper.selectBuildings(dto, pageInfo);
        List<WsnaManagerBsConsumableDvo> bldAndItemsInfos = new ArrayList<>();
        Iterator<WsnaManagerBsConsumableDvo> it = bldInfos.iterator();

        while (it.hasNext()) {
            WsnaManagerBsConsumableDvo bfBldInfo = it.next();
            WsnaManagerBsConsumableDvo aftBldInfo = new WsnaManagerBsConsumableDvo();
            List<WsnaManagerBsConsumableDvo> itemInfos = new ArrayList<>();

            aftBldInfo = bfBldInfo;

            List<String> fxnItemQtys = new ArrayList<>();
            List<String> aplcItemQtys = new ArrayList<>();

            // 매니저 별 기등록 품목 수량 조회
            itemInfos = mapper.selectItemQtys(dto.mngtYm(), bfBldInfo.getPrtnrNo());

            if (CollectionUtils.isEmpty(itemInfos)) {
                // 매니저 별 미등록 품목 계산 수량 조회
                itemInfos = mapper.selectItemFirstQtys(dto.mngtYm(), bfBldInfo.getPrtnrNo());

                String mngtYear = dto.mngtYm().substring(0, 4);
                String mngtMonth = "";
                mngtMonth = dto.mngtYm().substring(4);
                mngtMonth = mngtMonth.startsWith("0") ? " " + mngtMonth.substring(1) : mngtMonth;

                BizAssert.isTrue(
                    itemInfos.size() > 0, "MSG_ALT_BFSVC_CSMB_DDLV_BASE",
                    new String[] {mngtYear, mngtMonth}
                );

                for (WsnaManagerBsConsumableDvo itemInfo : itemInfos) {
                    switch (itemInfo.getBfsvcCsmbDdlvTpCd()) {
                        case "1" -> {
                            fxnItemQtys.add(itemInfo.getFxnDdlvUnitQty());
                        }

                        case "2" -> {
                            aplcItemQtys.add(itemInfo.getAplcDdlvUnitQty());
                        }
                    }
                }

                aftBldInfo.setReqYn(itemInfos.get(0).getReqYn());
                aftBldInfo.setFxnQtys(fxnItemQtys); // 고정품목
                aftBldInfo.setAplcQtys(aplcItemQtys); // 신청품목
                bldAndItemsInfos.add(aftBldInfo);

            } else {
                for (WsnaManagerBsConsumableDvo itemInfo : itemInfos) {
                    switch (itemInfo.getBfsvcCsmbDdlvTpCd()) {
                        case "1" -> {
                            fxnItemQtys.add(itemInfo.getBfsvcCsmbDdlvQty());
                        }

                        case "2" -> {
                            aplcItemQtys.add(itemInfo.getBfsvcCsmbDdlvQty());
                        }
                    }
                }

                aftBldInfo.setReqYn(itemInfos.get(0).getReqYn());
                aftBldInfo.setBfsvcCsmbDdlvStatCd(itemInfos.get(0).getBfsvcCsmbDdlvStatCd());
                aftBldInfo.setFxnQtys(fxnItemQtys); // 고정품목
                aftBldInfo.setAplcQtys(aplcItemQtys); // 신청품목
                bldAndItemsInfos.add(aftBldInfo);
            }
        }

        PagingResult<SearchRes> rtnDto = converter.mapDvoToSearchRes(bldAndItemsInfos);
        rtnDto.setPageInfo(bldInfos.getPageInfo());

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

        return mapper.mergeManagerBsConsumables(dvos);
    }

    @Transactional
    public int createManagerBsConsumablesRequest(String mngtYm) {
        String ostrAkNo = null;
        String ostrAkRgstDt = DateUtil.getNowDayString();

        List<WsnaManagerBsConsumableDvo> dvos = mapper.selectBfsvcCsmbDdlvIzByMngtYm(mngtYm);

        if (!ObjectUtils.isEmpty(dvos)) {
            SFLEXContext context = SFLEXContextHolder.getContext();
            UserSessionDvo userSession = context.getUserSession();
            ostrAkNo = mapper.selectNewOstrAkNo(OSTR_AK_TP_CD_BS, ostrAkRgstDt);
            int ostrAkSn = 1;

            List<WsnaBsConsumablesAskReqDvo> reqDvos = new ArrayList<>(dvos.size());

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
                reqDvo.setStrWareNo(dvo.getStrWareNo());

                reqDvos.add(reqDvo);
                ostrAkSn++;
            }

            // BS소모품배부내역 OSTR_NO, OSTR_SN UPDATE
            editBfsvcCsmbDdlvIzOstrAkNoSn(reqDvos, mngtYm);

            // 출고요청 및 배송요청
            bsConsumablesAskService.createBsConsumablesAsk(reqDvos, mngtYm, BFSVC_CSMB_DDLV_OJ_CD_MNGER);

            // BS소모품배부상태코드 UPDATE
            editBfsvcCsmbDdlvIzDdlvStatCd(reqDvos, mngtYm);
        } else {
            throw new BizException("MSG_TXT_AK_NO_DATA");
        }

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

    private void editBfsvcCsmbDdlvIzDdlvStatCd(List<WsnaBsConsumablesAskReqDvo> reqDvos, String mngtYm) {
        for (WsnaBsConsumablesAskReqDvo reqDvo : reqDvos) {
            WsnaManagerBsConsumableDvo dvo = new WsnaManagerBsConsumableDvo();

            dvo.setMngtYm(mngtYm);
            dvo.setCsmbPdCd(reqDvo.getItmPdCd());
            dvo.setBfsvcCsmbDdlvOjCd("2");
            dvo.setStrWareNo(reqDvo.getStrWareNo());
            dvo.setOstrAkNo(reqDvo.getOstrAkNo());
            dvo.setOstrAkSn(reqDvo.getOstrAkSn());

            mapper.updateBfsvcCsmbDdlvIzDdlvStatCd(dvo);
        }
    }
}
