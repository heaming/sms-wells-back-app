package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaShippingManagementConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaShippingManagementDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaItemStockItemizationReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaLogisticsOutStorageAskReqDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaShippingManagementDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaShippingMaterialDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaShippingManagementMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0300M01 자가필터 배송관리
 * W-SV-U-0301M01 건식상품 배송관리
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.08.01
 */

@Service
@RequiredArgsConstructor
public class WsnaShippingManagementService {

    private final WsnaShippingManagementMapper mapper;
    private final WsnaShippingManagementConverter converter;
    private final WsnaItemStockItemizationService itemStockService;
    private final WsnaLogisticsOutStorageAskService logisticsOutStorageAskService;

    /**
     * (자가필터,건식상품) 배송관리 조회 조건(상품 목록) 조회
     * @param dto
     * @return
     */
    public List<SearchProductRes> getProducts(SearchProductReq dto) {
        return mapper.selectProducts(dto);
    }

    /**
     * (자가필터,건식상품) 목록 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getShippingItems(SearchReq dto) {
        List<WsnaShippingManagementDvo> dvos = mapper.selectShippingItems(dto);
        return converter.mapWsnaShippingManagementDvoToSearchRes(dvos);
    }

    /**
     * (자가필터,건식상품) 데이터 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int createShippingItems(List<SaveReq> dtos) {
        int processCount = 0;
        List<WsnaShippingManagementDvo> dvos = converter.mapSaveReqToWsnaShippingManagementDvo(dtos);
        // 물류인터페이스 호출용 dvo
        List<WsnaLogisticsOutStorageAskReqDvo> logisticDvos = new ArrayList<>();
        //저장
        for (WsnaShippingManagementDvo dvo : dvos) {
            // 출고요청 번호 생성
            dvo.setOstrAkNo(mapper.selectOstAkNo(dvo));
            // 출고요청 일련번호 초기화

            // 저장 전 부품자재들 dvos로 변환 1,2,4 저장필요.
            List<WsnaShippingMaterialDvo> materialDvos = this.transfetShippingMaterials(dvo);

            // 암호화 이전 값 따로 세팅.
            String tno = materialDvos.get(0).getTno();
            String mpno = materialDvos.get(0).getMpno();

            for (WsnaShippingMaterialDvo materialDvo : materialDvos) {
                // 1.택배 발송정보 저장 (TB_SVPD_OSTR_AK_PCSV_SEND_DTL)
                mapper.insertParcelFwInformation(materialDvo);
                materialDvo.setAdrsTnoVal(tno);
                materialDvo.setAdrsCphonNoVal(mpno);
                // 2.작업출고내역 등록 (TB_SVST_SV_WK_OSTR_IZ)
                mapper.insertOutOfStorage(materialDvo);
                materialDvo.setAdrsTnoVal(tno);
                materialDvo.setAdrsCphonNoVal(mpno);
                // 3.재고정보변경
                this.putStockItems(materialDvo);
                // 물류인터페이스 dvo list에 추가
                materialDvo.setAdrsTnoVal(tno);
                materialDvo.setAdrsCphonNoVal(mpno);
                logisticDvos.add(converter.mapMaterialDvoToLogisticDvo(materialDvo));
            }
            // 4.작업결과 저장
            // 고객서비스정기BS주기내역(TB_SVPD_CST_SV_RGBSPR_IZ) update
            mapper.updateBsPeriod(dvo);
            // 고객서비스BS배정내역(TB_SVPD_CST_SV_BSFVC_ASN_IZ) update
            mapper.updateBsAssign(dvo);
            // 0차월 상품이면, 고객서비스수행내역(TB_SBPD_CST_SV_EXCN_IZ ) update
            if (StringUtils.equals(dvo.getSppThmYn(), "Y")) {
                mapper.updateExecution(dvo);
            }
            // 작업결과내역(TB_SVPD_CST_SV_WK_RS_IZ) 저장
            mapper.insertWorkResult(dvo);
            processCount += 1;
        }

        //물류인터페이스 호출
        logisticsOutStorageAskService.createSelfFilterOutOfStorageAsks(logisticDvos);
        return processCount;
    }

    public List<WsnaShippingMaterialDvo> transfetShippingMaterials(WsnaShippingManagementDvo dvo) {

        List<WsnaShippingMaterialDvo> materialDvos = new ArrayList<>();
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        WsnaShippingMaterialDvo materialDvo = converter.mapShippingManagementDvoToShippingMaterialDvo(dvo);
        String now = DateUtil.getNowString();
        // 물류인터페이스호출용 값 세팅(명칭맞춰주기)
        materialDvo.setOstrAkTpCd("400");
        materialDvo.setOstrAkRgstDt(now.substring(0, 8));
        materialDvo.setIostAkDvCd("WE");
        materialDvo.setMpacSn(0);
        materialDvo.setLgstSppMthdCd("2");
        materialDvo.setLgstWkMthdCd("WE01");
        materialDvo.setAdrsTnoVal(dvo.getTno());
        materialDvo.setAdrsCphonNoVal(dvo.getMpno());
        materialDvo.setWareMngtPrtnrNo(userSession.getEmployeeIDNumber());
        materialDvo.setWareMngtPrtnrOgTpCd(userSession.getOgTpCd());
        materialDvo.setOstrOjWareNo("100002");

        // 자재정보 리스트 생성
        if (StringUtils.isNotEmpty(dvo.getPartCd01())) {
            WsnaShippingMaterialDvo materialDvo1 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo1.setItmPdCd(dvo.getPartCd01());
            materialDvo1.setPartNm(dvo.getPartNm01());
            materialDvo1.setOstrAkQty(dvo.getPartQty01());
            materialDvo1.setPdCn(dvo.getPartNmQty01());
            materialDvo1.setOstrAkSn(1);
            materialDvos.add(materialDvo1);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd02())) {
            WsnaShippingMaterialDvo materialDvo2 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo2.setItmPdCd(dvo.getPartCd02());
            materialDvo2.setPartNm(dvo.getPartNm02());
            materialDvo2.setOstrAkQty(dvo.getPartQty02());
            materialDvo2.setPdCn(dvo.getPartNmQty02());
            materialDvo2.setOstrAkSn(2);
            materialDvos.add(materialDvo2);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd03())) {
            WsnaShippingMaterialDvo materialDvo3 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo3.setItmPdCd(dvo.getPartCd03());
            materialDvo3.setPartNm(dvo.getPartNm03());
            materialDvo3.setOstrAkQty(dvo.getPartQty03());
            materialDvo3.setPdCn(dvo.getPartNmQty03());
            materialDvo3.setOstrAkSn(3);
            materialDvos.add(materialDvo3);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd04())) {
            WsnaShippingMaterialDvo materialDvo4 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo4.setItmPdCd(dvo.getPartCd04());
            materialDvo4.setPartNm(dvo.getPartNm04());
            materialDvo4.setOstrAkQty(dvo.getPartQty04());
            materialDvo4.setPdCn(dvo.getPartNmQty04());
            materialDvo4.setOstrAkSn(4);
            materialDvos.add(materialDvo4);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd05())) {
            WsnaShippingMaterialDvo materialDvo5 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo5.setItmPdCd(dvo.getPartCd05());
            materialDvo5.setPartNm(dvo.getPartNm05());
            materialDvo5.setOstrAkQty(dvo.getPartQty05());
            materialDvo5.setPdCn(dvo.getPartNmQty05());
            materialDvo5.setOstrAkSn(5);
            materialDvos.add(materialDvo5);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd06())) {
            WsnaShippingMaterialDvo materialDvo6 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo6.setItmPdCd(dvo.getPartCd06());
            materialDvo6.setPartNm(dvo.getPartNm06());
            materialDvo6.setOstrAkQty(dvo.getPartQty06());
            materialDvo6.setPdCn(dvo.getPartNmQty06());
            materialDvo6.setOstrAkSn(6);
            materialDvos.add(materialDvo6);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd07())) {
            WsnaShippingMaterialDvo materialDvo7 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo7.setItmPdCd(dvo.getPartCd07());
            materialDvo7.setPartNm(dvo.getPartNm07());
            materialDvo7.setOstrAkQty(dvo.getPartQty07());
            materialDvo7.setPdCn(dvo.getPartNmQty07());
            materialDvo7.setOstrAkSn(7);
            materialDvos.add(materialDvo7);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd08())) {
            WsnaShippingMaterialDvo materialDvo8 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo8.setItmPdCd(dvo.getPartCd08());
            materialDvo8.setPartNm(dvo.getPartNm08());
            materialDvo8.setOstrAkQty(dvo.getPartQty08());
            materialDvo8.setPdCn(dvo.getPartNmQty08());
            materialDvo8.setOstrAkSn(8);
            materialDvos.add(materialDvo8);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd09())) {
            WsnaShippingMaterialDvo materialDvo9 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo9.setItmPdCd(dvo.getPartCd09());
            materialDvo9.setPartNm(dvo.getPartNm09());
            materialDvo9.setOstrAkQty(dvo.getPartQty09());
            materialDvo9.setPdCn(dvo.getPartNmQty09());
            materialDvo9.setOstrAkSn(9);
            materialDvos.add(materialDvo9);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd10())) {
            WsnaShippingMaterialDvo materialDvo10 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo10.setItmPdCd(dvo.getPartCd10());
            materialDvo10.setPartNm(dvo.getPartNm10());
            materialDvo10.setOstrAkQty(dvo.getPartQty10());
            materialDvo10.setPdCn(dvo.getPartNmQty10());
            materialDvo10.setOstrAkSn(10);
            materialDvos.add(materialDvo10);
        }

        return materialDvos;
    }

    public void putStockItems(WsnaShippingMaterialDvo materialDvo) {
        String now = DateUtil.getNowString();
        WsnaItemStockItemizationReqDvo stockItem = new WsnaItemStockItemizationReqDvo();
        // 자재 관리단위 조회
        String mngtUnit = mapper.selectMngtUnit(materialDvo.getItmPdCd());
        // 재고 dvo 값 세팅
        stockItem.setProcsYm(now.substring(0, 8));
        stockItem.setProcsDt(now.substring(8));
        stockItem.setWareDv("1");
        stockItem.setWareNo("100002"); // 파주 물류센터
        stockItem.setWareMngtPrtnrNo("71321");
        stockItem.setIostTp("213");
        stockItem.setWorkDiv("A");
        stockItem.setItmPdCd(materialDvo.getItmPdCd());
        stockItem.setMngtUnit(mngtUnit);
        stockItem.setItemGd("A");
        stockItem.setQty(String.valueOf(materialDvo.getOstrAkQty()));
        //재고변경 service 호출
        itemStockService.createStock(stockItem);
    }
}
