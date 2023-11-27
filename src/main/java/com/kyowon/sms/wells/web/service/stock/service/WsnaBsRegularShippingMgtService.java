package com.kyowon.sms.wells.web.service.stock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.service.WsnzHistoryService;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaBsRegularShippingMgtConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaBsRegularShippingMgtDto.*;
import com.kyowon.sms.wells.web.service.stock.dvo.*;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaBsRegularShippingMgtMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.ValidAssert;

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
public class WsnaBsRegularShippingMgtService {

    private final WsnaBsRegularShippingMgtMapper mapper;
    private final WsnaBsRegularShippingMgtConverter converter;
    private final WsnaItemStockItemizationService itemStockService;
    private final WsnaLogisticsOutStorageAskService logisticsOutStorageAskService;

    private final WsnzHistoryService historyService;

    /**
     * (자가필터,건식상품) 배송관리 조회 조건(상품 목록) 조회
     * @param dto { asnYm: 배정년월, sppDvCd: 프로그램ID(A1:자가필터, A2:건식상품) }
     * @return 조회결과
     */
    public List<SearchProductRes> getProducts(SearchProductReq dto) {
        return mapper.selectProducts(dto);
    }

    /**
     * (자가필터,건식상품) 목록 조회
     * @param dto { asnYm: 배정년월, sppDvCd: 프로그램ID(A1:자가필터, A2:건식상품), lgstWkMthdCd: 물류요청작업방식코드
     *              pgGb: 그룹핑코드 (G:배송상품그룹매핑, P:미매핑상품), procsDvCd: 처리구분, rownum: 조회제한건수 }
     * @return 조회결과
     */
    public List<SearchRes> getShippingItems(SearchReq dto) {
        List<WsnaBsRegularShippingMgtDvo> dvos = mapper.selectShippingItems(dto);
        return converter.mapDvoListoSearchResList(dvos);
    }

    /**
     * (자가필터,건식상품) 목록 조회(페이징)
     * @param dto { asnYm: 배정년월, sppDvCd: 프로그램ID(A1:자가필터, A2:건식상품), lgstWkMthdCd: 물류요청작업방식코드
     *              pgGb: 그룹핑코드 (G:배송상품그룹매핑, P:미매핑상품), procsDvCd: 처리구분, rownum: 조회제한건수 }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<SearchRes> getShippingItemPages(SearchReq dto, PageInfo pageInfo) {
        PagingResult<WsnaBsRegularShippingMgtDvo> dvos = mapper.selectShippingItems(dto, pageInfo);
        PagingResult<SearchRes> pagingResult = converter.mapWsnaShippingManagementDvoToSearchRes(
            dvos
        );
        pagingResult.setPageInfo(pageInfo);
        return pagingResult;
    }

    /**
     * (자가필터,건식상품) 데이터 저장
     * @param dtos
     */
    @Transactional
    public int createShippingItems(List<SaveReq> dtos) {
        int processCount = 0;
        String lgstWkMthdCd = "";
        String lgstOstrAkNo = "";
        List<WsnaBsRegularShippingMgtDvo> dvos = converter.mapSaveReqToWsnaShippingManagementDvo(dtos);
        // 물류인터페이스 호출용 dvo
        List<WsnaLogisticsOutStorageAskReqDvo> logisticDvos = new ArrayList<>();
        lgstWkMthdCd = dtos.get(0).lgstWkMthdCd();
        // 물류요청번호 생성
        lgstOstrAkNo = mapper.selectNewLgstOstrAkNo();
        //저장
        for (WsnaBsRegularShippingMgtDvo dvo : dvos) {
            // 암호화 이전 값 따로 세팅.
            String mexnoEncr = dvo.getMexnoEncr();
            String exnoEnncr = dvo.getExnoEncr();
            // 출고요청 번호 생성
            dvo.setOstrAkNo(mapper.selectOstAkNo(dvo));
            // 물류요청구분코드
            dvo.setLgstWkMthdCd(lgstWkMthdCd);
            // 물류요청번호추가
            dvo.setLgstOstrAkNo(lgstOstrAkNo);

            // 저장 전 부품자재들 dvos로 변환 1,2,4 저장필요.
            List<WsnaBsRegularShippingMaterialDvo> materialDvos = this.transferShippingMaterials(dvo);

            // 암호화 이전 값 따로 세팅.
            String tno = materialDvos.get(0).getTno();
            String mpno = materialDvos.get(0).getMpno();

            for (WsnaBsRegularShippingMaterialDvo materialDvo : materialDvos) {
                materialDvo.setWkWareNo("100002"); /* 파주물류센터 */
                // 1.택배 발송정보 저장 (TB_SVPD_OSTR_AK_PCSV_SEND_DTL)
                mapper.insertParcelFwInformation(materialDvo);
                materialDvo.setAdrsTnoVal(tno);
                materialDvo.setAdrsCphonNoVal(mpno);
                // 2.작업출고내역 등록 (TB_SVST_SV_WK_OSTR_IZ)
                mapper.insertOutOfStorage(materialDvo);
                materialDvo.setAdrsTnoVal(tno);
                materialDvo.setAdrsCphonNoVal(mpno);
                // 3.재고정보변경, 물류인터페이스 dvo list
                this.putStockItems(materialDvo);
                // 물류인터페이스 dvo list에 추가
                materialDvo.setAdrsTnoVal(tno);
                materialDvo.setAdrsCphonNoVal(mpno);
                logisticDvos.add(converter.mapMaterialDvoToLogisticDvo(materialDvo));

            }
            // 4.작업결과 저장
            // 고객서비스정기BS주기내역(TB_SVPD_CST_SV_RGBSPR_IZ) update
            mapper.updateBsPeriod(dvo);
            // 고객서비스BS배정내역(TB_SVPD_CST_SV_BFSVC_ASN_IZ) update
            mapper.updateBsAssign(dvo);
            // history 생성
            historyService.insertCstSvBfsvcAsnHistByPk(dvo.getCstSvAsnNo());

            // 작업결과내역(TB_SVPD_CST_SV_WK_RS_IZ) 저장
            dvo.setMexnoEncr(mexnoEncr);
            dvo.setExnoEncr(exnoEnncr);
            mapper.insertWorkResult(dvo);
            processCount += 1;
        }
        // 모종제품은 제외.
        if (ObjectUtils.isNotEmpty(logisticDvos)) {
            //물류인터페이스 호출
            logisticsOutStorageAskService.createSelfFilterOutOfStorageAsks(logisticDvos);
        }
        return processCount;
    }

    /**
     * (자가필터,건식상품) WsnaBsRegularShippingMaterialDvo 값 세팅
     * @param dvo
     */
    public List<WsnaBsRegularShippingMaterialDvo> transferShippingMaterials(WsnaBsRegularShippingMgtDvo dvo) {

        List<WsnaBsRegularShippingMaterialDvo> materialDvos = new ArrayList<>();
        WsnaBsRegularShippingMaterialDvo materialDvo = converter.mapShippingManagementDvoToShippingMaterialDvo(dvo);
        // 창고담당자 조회
        WareMngtRes wareMngtRes = mapper.selectWareMngtInfo("100002");
        String now = DateUtil.getNowString();
        // 물류인터페이스호출용 값 세팅
        materialDvo.setOstrAkTpCd("400");
        materialDvo.setOstrAkRgstDt(now.substring(0, 8));
        materialDvo.setIostAkDvCd("WE");
        materialDvo.setLgstSppMthdCd("2");
        materialDvo.setAdrsTnoVal(dvo.getTno());
        materialDvo.setAdrsCphonNoVal(dvo.getMpno());
        materialDvo.setWareMngtPrtnrNo(wareMngtRes.wareMngtPrtnrNo());
        materialDvo.setWareMngtPrtnrOgTpCd(wareMngtRes.wareMngtPrtnrOgTpCd());
        materialDvo.setOstrOjWareNo("100002");
        materialDvo.setItmGdCd("A");
        materialDvo.setCstNm(dvo.getCstKnm());
        // 입고요청창고정보. 일단 파주물류센터로 세팅. TODO : 확인필요.
        materialDvo.setStrOjWareNo("100002"); //입고대상창고번호
        materialDvo.setWareDtlDvCd("10");
        materialDvo.setWareNm("교원파주물류센터");
        // null대신 X값 세팅. (물류인터페이스요청)
        materialDvo.setSvCnrCd("X");
        materialDvo.setSvCnrNm("X");
        materialDvo.setSvCnrIchrPrtnrNm("X");
        materialDvo.setSvCnrLkTnoEncr("X");
        materialDvo.setSvCnrAdr("X");
        materialDvo.setOvivTpCd("X");

        // 자재정보 리스트 생성
        if (StringUtils.isNotEmpty(dvo.getPartCd01())) {
            WsnaBsRegularShippingMaterialDvo materialDvo1 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo1.setItmPdCd(dvo.getPartCd01());
            materialDvo1.setPartNm(dvo.getPartNm01());
            materialDvo1.setOstrAkQty(dvo.getPartQty01());
            materialDvo1.setPdCn(dvo.getPartNmQty01());
            materialDvo1.setOstrAkSn(1);
            materialDvos.add(materialDvo1);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd02())) {
            WsnaBsRegularShippingMaterialDvo materialDvo2 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo2.setItmPdCd(dvo.getPartCd02());
            materialDvo2.setPartNm(dvo.getPartNm02());
            materialDvo2.setOstrAkQty(dvo.getPartQty02());
            materialDvo2.setPdCn(dvo.getPartNmQty02());
            materialDvo2.setOstrAkSn(2);
            materialDvos.add(materialDvo2);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd03())) {
            WsnaBsRegularShippingMaterialDvo materialDvo3 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo3.setItmPdCd(dvo.getPartCd03());
            materialDvo3.setPartNm(dvo.getPartNm03());
            materialDvo3.setOstrAkQty(dvo.getPartQty03());
            materialDvo3.setPdCn(dvo.getPartNmQty03());
            materialDvo3.setOstrAkSn(3);
            materialDvos.add(materialDvo3);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd04())) {
            WsnaBsRegularShippingMaterialDvo materialDvo4 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo4.setItmPdCd(dvo.getPartCd04());
            materialDvo4.setPartNm(dvo.getPartNm04());
            materialDvo4.setOstrAkQty(dvo.getPartQty04());
            materialDvo4.setPdCn(dvo.getPartNmQty04());
            materialDvo4.setOstrAkSn(4);
            materialDvos.add(materialDvo4);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd05())) {
            WsnaBsRegularShippingMaterialDvo materialDvo5 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo5.setItmPdCd(dvo.getPartCd05());
            materialDvo5.setPartNm(dvo.getPartNm05());
            materialDvo5.setOstrAkQty(dvo.getPartQty05());
            materialDvo5.setPdCn(dvo.getPartNmQty05());
            materialDvo5.setOstrAkSn(5);
            materialDvos.add(materialDvo5);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd06())) {
            WsnaBsRegularShippingMaterialDvo materialDvo6 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo6.setItmPdCd(dvo.getPartCd06());
            materialDvo6.setPartNm(dvo.getPartNm06());
            materialDvo6.setOstrAkQty(dvo.getPartQty06());
            materialDvo6.setPdCn(dvo.getPartNmQty06());
            materialDvo6.setOstrAkSn(6);
            materialDvos.add(materialDvo6);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd07())) {
            WsnaBsRegularShippingMaterialDvo materialDvo7 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo7.setItmPdCd(dvo.getPartCd07());
            materialDvo7.setPartNm(dvo.getPartNm07());
            materialDvo7.setOstrAkQty(dvo.getPartQty07());
            materialDvo7.setPdCn(dvo.getPartNmQty07());
            materialDvo7.setOstrAkSn(7);
            materialDvos.add(materialDvo7);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd08())) {
            WsnaBsRegularShippingMaterialDvo materialDvo8 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo8.setItmPdCd(dvo.getPartCd08());
            materialDvo8.setPartNm(dvo.getPartNm08());
            materialDvo8.setOstrAkQty(dvo.getPartQty08());
            materialDvo8.setPdCn(dvo.getPartNmQty08());
            materialDvo8.setOstrAkSn(8);
            materialDvos.add(materialDvo8);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd09())) {
            WsnaBsRegularShippingMaterialDvo materialDvo9 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo9.setItmPdCd(dvo.getPartCd09());
            materialDvo9.setPartNm(dvo.getPartNm09());
            materialDvo9.setOstrAkQty(dvo.getPartQty09());
            materialDvo9.setPdCn(dvo.getPartNmQty09());
            materialDvo9.setOstrAkSn(9);
            materialDvos.add(materialDvo9);
        }
        if (StringUtils.isNotEmpty(dvo.getPartCd10())) {
            WsnaBsRegularShippingMaterialDvo materialDvo10 = converter.mapMaterialDvoToMaterialDvo(materialDvo);
            materialDvo10.setItmPdCd(dvo.getPartCd10());
            materialDvo10.setPartNm(dvo.getPartNm10());
            materialDvo10.setOstrAkQty(dvo.getPartQty10());
            materialDvo10.setPdCn(dvo.getPartNmQty10());
            materialDvo10.setOstrAkSn(10);
            materialDvos.add(materialDvo10);
        }

        return materialDvos;
    }

    /**
     * (자가필터,건식상품) 재고정보변경, 물류인터페이스 dvo list 세팅 후 재고변경 서비스 호출
     * @param materialDvo
     */
    public void putStockItems(WsnaBsRegularShippingMaterialDvo materialDvo) {
        String now = DateUtil.getNowString();
        WsnaItemStockItemizationReqDvo stockItem = new WsnaItemStockItemizationReqDvo();
        // 자재 관리단위 조회
        String mngtUnit = mapper.selectMngtUnit(materialDvo.getItmPdCd());
        // 재고 dvo 값 세팅
        stockItem.setProcsYm(now.substring(0, 6));
        stockItem.setProcsDt(now.substring(0, 8));
        stockItem.setWareDv("1");
        stockItem.setWareNo("100002"); // 파주 물류센터
        stockItem.setWareMngtPrtnrNo("99992");
        stockItem.setIostTp("213");
        stockItem.setWorkDiv("A");
        stockItem.setItmPdCd(materialDvo.getItmPdCd());
        stockItem.setMngtUnit(mngtUnit);
        stockItem.setItemGd("A");
        stockItem.setQty(String.valueOf(materialDvo.getOstrAkQty()));
        //재고변경 service 호출
        itemStockService.createStock(stockItem);
    }

    /**
     * (택배)모종상품 배송처리
     * @param
     */
    @Transactional
    public void createSdingShippingItems(WsnaSeedReleaseScheduleCnfmDvo seedDvo) {
        // BS + 택배 + 모종 조회
        // 모종패키지 상품 작업출고내역에 저장
        mapper.insertSdingOutOfStorage(seedDvo);
        WsnaBsRegularShippingMgtDvo sdingDvo = mapper.selectSdingInfo(seedDvo);
        String mexnoEncr = sdingDvo.getMexnoEncr();
        // 고객서비스정기BS주기내역(TB_SVPD_CST_SV_RGBSPR_IZ) update
        mapper.updateBsPeriod(sdingDvo);
        // 고객서비스BS배정내역(TB_SVPD_CST_SV_BFSVC_ASN_IZ) update
        mapper.updateBsAssign(sdingDvo);
        // history 생성
        historyService.insertCstSvBfsvcAsnHistByPk(sdingDvo.getCstSvAsnNo());
        // 작업결과내역(TB_SVPD_CST_SV_WK_RS_IZ) 저장
        sdingDvo.setMexnoEncr(mexnoEncr);
        mapper.insertWorkResult(sdingDvo);

        // 새싹재배기+씨앗인지 조회 후 별도 처리
        WsnaBsRegularShippingMgtDvo wellsfarmDvo = mapper.selectWellsfarmMachine(sdingDvo);
        if (!Objects.isNull(wellsfarmDvo)) {
            mapper.insertOutOfStorage(converter.mapShippingManagementDvoToShippingMaterialDvo(wellsfarmDvo));
            // 고객서비스정기BS주기내역(TB_SVPD_CST_SV_RGBSPR_IZ) update
            mapper.updateBsPeriod(wellsfarmDvo);
            // 고객서비스BS배정내역(TB_SVPD_CST_SV_BSFVC_ASN_IZ) update
            mapper.updateBsAssign(wellsfarmDvo);
            // history 생성
            historyService.insertCstSvBfsvcAsnHistByPk(wellsfarmDvo.getCstSvAsnNo());
            // 작업결과내역(TB_SVPD_CST_SV_WK_RS_IZ) 저장
            mapper.insertWorkResult(wellsfarmDvo);
        }
    }

    /**
     * (자가필터,건식상품) 물류 확정 취소 처리
     * @param map (취소대상) 출고요청번호
     */
    @Transactional
    public void saveLogisticsConfirmCancel(Map<String, String> map) {
        // 출고요청번호
        String ostrAkNo = map.get("PARAM1");
        // 물류확정일자
        String lgstCnfmDt = map.get("PARAM2");
        ValidAssert.hasText(ostrAkNo);
        ValidAssert.hasText(lgstCnfmDt);
        // 재고취소용 정보 조회
        List<WsnaItemStockItemizationReqDvo> cancelDvos = mapper.selectCancelItem(ostrAkNo);
        // 배정번호 조회
        String cstSvAsnNo = mapper.selectAssignNo(ostrAkNo, lgstCnfmDt);
        // 택배발송정보(TB_SVPD_OSTR_AK_PCSV_SEND_DTL) DTA_DL_YN = 'Y'
        mapper.deleteParcelSend(ostrAkNo);
        // 작업출고내역(TB_SVST_SV_WK_OSTR_IZ) DTA_DL_YN = 'Y'
        mapper.deleteWorkOstrItemization(cstSvAsnNo);
        // 재고 취소
        for (WsnaItemStockItemizationReqDvo cancelDvo : cancelDvos) {
            itemStockService.removeStock(cancelDvo);
        }

        // 고객서비스정기BS주기내역(TB_SVPD_CST_SV_RGBSPR_IZ) update값 복구
        mapper.mergeBsPeriodCancel(ostrAkNo, lgstCnfmDt);
        // 고객서비스BS배정내역(TB_SVPD_CST_SV_BFSVC_ASN_IZ) update값 복구
        mapper.updateBsAssignCancel(cstSvAsnNo);
        // history 생성
        historyService.insertCstSvBfsvcAsnHistByPk(cstSvAsnNo);
        // 고객서비스작업결과내역(TB_SVPD_CST_SV_WK_RS_IZ) DELETE
        mapper.deleteWorkResult(cstSvAsnNo);
    }
}
