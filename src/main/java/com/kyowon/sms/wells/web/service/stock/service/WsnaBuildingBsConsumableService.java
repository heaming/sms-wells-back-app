package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
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
    private static final String LGST_SPP_MTHD_CD_CRGO = "6";
    private static final String LGST_WK_MTHD_CD_BLD = "WE08";
    private static final String ITM_GD_CD_A = "A";
    private static final String OSTR_OJ_WARE_NO_PAJU = "100002";
    private static final String BFSVC_CSMB_DDLV_OJ_CD_BLD = "3";

    public List<SearchRes> getBuildingBsConsumables(SearchReq dto) {
        return null;
    }

    public PagingResult<SearchRes> getBuildingBsConsumablePages(SearchReq dto, PageInfo pageInfo) {
        // 빌딩정보 조회
        PagingResult<WsnaBuildingBsConsumableDvo> bldInfos = mapper.selectBuildings(dto, pageInfo);

        if (!ObjectUtils.isEmpty(bldInfos)) {
            List<WsnaBuildingBsConsumableDvo> bldAndItemsInfos = new ArrayList<>();
            Iterator<WsnaBuildingBsConsumableDvo> it = bldInfos.iterator();

            while (it.hasNext()) {
                WsnaBuildingBsConsumableDvo bftBldInfo = it.next();
                WsnaBuildingBsConsumableDvo aftBldInfo = new WsnaBuildingBsConsumableDvo();
                List<WsnaBuildingBsConsumableDvo> itemInfos = new ArrayList<>();

                aftBldInfo = bftBldInfo;

                List<String> fxnItemQtys = new ArrayList<>();
                List<String> aplcItemQtys = new ArrayList<>();

                // 빌딩 별 기등록 품목 수량 조회
                itemInfos = mapper.selectItemQtys(dto.mngtYm(), bftBldInfo.getBldCd());

                if (CollectionUtils.isEmpty(itemInfos)) {
                    // 빌딩 별 미등록 품목 계산 수량 조회
                    itemInfos = mapper.selectItemFirstQtys(dto.mngtYm(), bftBldInfo.getBldCd());

                    String mngtYear = dto.mngtYm().substring(0, 4);
                    String mngtMonth = "";
                    mngtMonth = dto.mngtYm().substring(4);
                    mngtMonth = mngtMonth.startsWith("0") ? " " + mngtMonth.substring(1) : mngtMonth;

                    BizAssert.isTrue(
                        itemInfos.size() > 0, "MSG_ALT_BFSVC_CSMB_DDLV_BASE",
                        new String[] {mngtYear, mngtMonth}
                    );

                    for (WsnaBuildingBsConsumableDvo itemInfo : itemInfos) {
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
                    for (WsnaBuildingBsConsumableDvo itemInfo : itemInfos) {
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

        PagingResult<SearchRes> rtnDto = converter.mapDvoToSearchRes(bldInfos);
        rtnDto.setPageInfo(bldInfos.getPageInfo());

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

    public List<SearchBldRes> getBuildingList(String mngtYm) {
        return mapper.selectBuildingList(mngtYm);
    }

    @Transactional
    public int createBuildingBsConsumables(List<CreateReq> dtos) {
        return mapper.mergeBuildingBsConsumables(dtos);
    }

    @Transactional
    public int createBuildingBsConsumablesRequest(String mngtYm) {
        String ostrAkNo = null;
        String ostrAkRgstDt = DateUtil.getNowDayString();

        List<WsnaBuildingBsConsumableDvo> dvos = mapper.selectBfsvcCsmbDdlvIzByMngtYm(mngtYm);

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
                reqDvo.setStrWareNo(dvo.getStrWareNo());

                reqDvos.add(reqDvo);
                ostrAkSn++;
            }

            // BS소모품배부내역 OSTR_NO, OSTR_SN UPDATE
            editBfsvcCsmbDdlvIzOstrAkNoSn(reqDvos, mngtYm);

            // 출고요청 및 배송요청
            bsConsumablesAskService.createBsConsumablesAsk(reqDvos, mngtYm, BFSVC_CSMB_DDLV_OJ_CD_BLD);

            // BS소모품배부상태코드 UPDATE
            editBfsvcCsmbDdlvIzDdlvStatCd(reqDvos, mngtYm);
        } else {
            throw new BizException("MSG_TXT_AK_NO_DATA");
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

    private void editBfsvcCsmbDdlvIzDdlvStatCd(List<WsnaBsConsumablesAskReqDvo> reqDvos, String mngtYm) {
        for (WsnaBsConsumablesAskReqDvo reqDvo : reqDvos) {
            WsnaBuildingBsConsumableDvo dvo = new WsnaBuildingBsConsumableDvo();

            dvo.setMngtYm(mngtYm);
            dvo.setCsmbPdCd(reqDvo.getItmPdCd());
            dvo.setBfsvcCsmbDdlvOjCd("3");
            dvo.setStrWareNo(reqDvo.getStrWareNo());
            dvo.setOstrAkNo(reqDvo.getOstrAkNo());
            dvo.setOstrAkSn(reqDvo.getOstrAkSn());

            mapper.updateBfsvcCsmbDdlvIzDdlvStatCd(dvo);
        }
    }
}
